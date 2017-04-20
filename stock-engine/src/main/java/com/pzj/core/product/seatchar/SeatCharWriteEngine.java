package com.pzj.core.product.seatchar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.utils.CRCUtils;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.SeatChar;
import com.pzj.core.product.model.seat.CreateSeatCharInfoReqModel;
import com.pzj.core.product.model.seat.CreateSeatCharReqModel;
import com.pzj.core.product.model.seat.ModifySeatCharInfoReqModel;
import com.pzj.core.product.model.seat.ModifySeatCharReqModel;
import com.pzj.core.product.write.SeatCharWriteMapper;
import com.pzj.framework.idgen.IDGenerater;

/**
 * Created by Administrator on 2017-3-17.
 */
@Component
@Transactional(value = "stock.transactionManager")
public class SeatCharWriteEngine {
	private static final Logger logger = LoggerFactory.getLogger(SeatCharWriteEngine.class);

	@Resource
	private IDGenerater idGenerater;
	@Resource
	private SeatCharWriteMapper seatCharWriteMapper;

	public List<SeatChar> querySeatCharByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		return seatCharWriteMapper.selectSeatCharByIds(ids);
	}

	public Boolean createSeatChar(CreateSeatCharReqModel createSeatCharReqModel) {
		validateCreateSeatChar(createSeatCharReqModel);

		List<CreateSeatCharInfoReqModel> seatCharInfos = createSeatCharReqModel.getSeatCharInfos();

		List<SeatChar> seatChars = new ArrayList<>(seatCharInfos.size());

		Date currentDate = new Date();
		for (CreateSeatCharInfoReqModel seatCharInfo : seatCharInfos) {
			SeatChar seatChar = new SeatChar();
			seatChar.setId(idGenerater.nextId());
			seatChar.setNameType(seatCharInfo.getNameType());
			seatChar.setLineName(seatCharInfo.getLineName());
			seatChar.setColumnName(seatCharInfo.getColumnName());
			seatChar.setAbscissa(seatCharInfo.getAbscissa());
			seatChar.setOrdinate(seatCharInfo.getOrdinate());
			seatChar.setType(seatCharInfo.getSeatType());
			seatChar.setAreaId(seatCharInfo.getAreaId());
			seatChar.setScenicId(createSeatCharReqModel.getTheaterId());
			seatChar.setCreateTime(currentDate);
			seatChar.setSeatUnique(seatUnique(seatChar));
			seatChars.add(seatChar);
		}

		checkRepeatSeat(seatChars);

		seatCharWriteMapper.insertBatch(seatChars);

		return true;
	}

	private void validateCreateSeatChar(CreateSeatCharReqModel createSeatCharReqModel) {
		if (createSeatCharReqModel == null) {
			throw new TheaterException();
		}
		if (createSeatCharReqModel.getTheaterId() == null) {
			throw new TheaterException();
		}

		List<CreateSeatCharInfoReqModel> seatCharInfos = createSeatCharReqModel.getSeatCharInfos();
		if (seatCharInfos == null) {
			throw new TheaterException();
		}

		for (CreateSeatCharInfoReqModel seatCharInfo : seatCharInfos) {
			validateCreateSeatChar(seatCharInfo);
		}
	}

	private void validateCreateSeatChar(CreateSeatCharInfoReqModel createSeatCharInfoReqModel) {
		if (createSeatCharInfoReqModel == null) {
			throw new TheaterException();
		}
		if (createSeatCharInfoReqModel.getAbscissa() == null) {
			throw new TheaterException();
		}
		if (createSeatCharInfoReqModel.getOrdinate() == null) {
			throw new TheaterException();
		}
		if (createSeatCharInfoReqModel.getSeatType() == null) {
			throw new TheaterException();
		}
	}

	private Long seatUnique(SeatChar seatChar) {
		StringBuffer sb = new StringBuffer();
		sb.append(seatChar.getScenicId());
		sb.append(":");
		sb.append(seatChar.getAbscissa());
		sb.append(":");
		sb.append(seatChar.getOrdinate());

		Long crcUniq = CRCUtils.convertUniqueLong(sb.toString());

		return crcUniq;
	}

	public void checkRepeatSeat(List<SeatChar> seatChars) {
		List<SeatChar> repeatSeats = seatCharWriteMapper.selectRepeatSeat(seatChars);
		if (repeatSeats == null || repeatSeats.isEmpty()) {
			return;
		}

		List<Long> seatUniques = new ArrayList<Long>();
		for (SeatChar seatChar : repeatSeats) {
			seatUniques.add(seatChar.getSeatUnique());
		}

		TheaterExceptionCode code = TheaterExceptionCode.SEAT_RULE_REPEAT;
		String templateMessage = code.getTemplateMessage(seatUniques);
		logger.error(templateMessage);
		throw new TheaterException(code);
	}

	public Boolean modifySeatChar(ModifySeatCharReqModel modifySeatCharReqModel) {
		validateModifySeatChar(modifySeatCharReqModel);

		List<ModifySeatCharInfoReqModel> seatCharInfos = modifySeatCharReqModel.getSeatCharInfos();

		List<SeatChar> seatChars = new ArrayList<>(seatCharInfos.size());

		Date currentDate = new Date();
		for (ModifySeatCharInfoReqModel seatCharInfo : seatCharInfos) {
			SeatChar seatChar = new SeatChar();
			seatChar.setId(seatCharInfo.getSeatId());
			seatChar.setNameType(seatCharInfo.getNameType());
			seatChar.setLineName(seatCharInfo.getLineName());
			seatChar.setColumnName(seatCharInfo.getColumnName());
			seatChar.setType(seatCharInfo.getSeatType());
			seatChar.setAreaId(seatCharInfo.getAreaId());
			seatChar.setUpdateTime(currentDate);
			seatChars.add(seatChar);
		}

		seatCharWriteMapper.updateBatch(seatChars);

		return true;
	}

	private void validateModifySeatChar(ModifySeatCharReqModel modifySeatCharReqModel) {
		if (modifySeatCharReqModel == null) {
			throw new TheaterException();
		}

		List<ModifySeatCharInfoReqModel> seatCharInfos = modifySeatCharReqModel.getSeatCharInfos();
		if (seatCharInfos == null) {
			throw new TheaterException();
		}

		for (ModifySeatCharInfoReqModel seatCharInfo : seatCharInfos) {
			validateModifySeatChar(seatCharInfo);
		}
	}

	private void validateModifySeatChar(ModifySeatCharInfoReqModel modifySeatCharReqModel) {
		if (modifySeatCharReqModel == null) {
			throw new TheaterException();
		}
		if (modifySeatCharReqModel.getSeatId() == null) {
			throw new TheaterException();
		}
		if (modifySeatCharReqModel.getAreaId() == null && modifySeatCharReqModel.getSeatType() == null
				&& modifySeatCharReqModel.getLineName() == null && modifySeatCharReqModel.getColumnName() == null
				&& modifySeatCharReqModel.getNameType() == null) {
			throw new TheaterException();
		}
	}
}
