package com.pzj.core.stock.stockupdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pzj.core.common.exception.ParameterErrorException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.common.CommonTradeOpeStockModel;
import com.pzj.core.stock.entity.LockRecord;
import com.pzj.core.stock.entity.Stock;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.exception.stock.NotFoundStockException;
import com.pzj.core.stock.exception.stock.NotFoundStockRecordException;
import com.pzj.core.stock.model.OccupyStockRequestModel;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.StockQueryRequestModel;
import com.pzj.core.stock.stockquery.QueryStockByRuleEngine;
import com.pzj.core.stock.write.LockRecordWriteMapper;
import com.pzj.framework.converter.JSONConverter;

@Component("commonTradeStockEngine")
public class CommonTradeStockEngine {
	private final Logger logger = LoggerFactory.getLogger(CommonTradeStockEngine.class);

	@Resource(name = "lockRecordWriteMapper")
	private LockRecordWriteMapper lockRecordWriteMapper;
	@Resource(name = "occupyStockEngine")
	private OccupyStockEngine occupyStockEngine;
	@Resource(name = "releaseStockEngine")
	private ReleaseStockEngine releaseStockEngine;
	@Resource(name = "queryStockByRuleEngine")
	private QueryStockByRuleEngine queryStockByRuleEngine;

	/**
	 * 占库存
	 * @param orderStockModelList
	 * @return Boolean
	 */
	@Transactional(value = "stock.transactionManager", timeout = 2)
	public CommonTradeOpeStockModel occupyStock(List<OccupyStockRequestModel> orderStockModelList) {
		logger.info("CommonTradeStockEngine occupyStock:::" + JSONConverter.toJson(orderStockModelList));
		ParamModel paramModel = convert(orderStockModelList, 1);
		if (!paramModel.paramIsOk()) {
			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		CommonTradeOpeStockModel commonOperateModel = getOperateStocks(orderStockModelList, 1);
		if (!CommonUtils.checkCollectionIsNull(commonOperateModel.getReleaseStocks())) {
			releaseStockEngine.releaseStock(commonOperateModel.getReleaseStocks());
		}
		if (!CommonUtils.checkCollectionIsNull(commonOperateModel.getOccupyStocks())) {
			occupyStockEngine.occupyStock(commonOperateModel.getOccupyStocks());
		}

		return commonOperateModel;
	}

	/**
	 * 释放库存
	 * @param orderStockModelList
	 * @return Boolean
	 */
	@Transactional(value = "stock.transactionManager", timeout = 2)
	public Boolean releaseStock(List<OccupyStockRequestModel> orderStockModelList) {
		logger.info("CommonTradeStockEngine releaseStock:::" + JSONConverter.toJson(orderStockModelList));
		ParamModel paramModel = convert(orderStockModelList, 2);
		if (!paramModel.paramIsOk()) {
			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}
		CommonTradeOpeStockModel commonOperateModel = getOperateStocks(orderStockModelList, 2);
		if (!CommonUtils.checkCollectionIsNull(commonOperateModel.getReleaseStocks())) {
			releaseStockEngine.releaseStock(commonOperateModel.getReleaseStocks());
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public ParamModel convert(List<OccupyStockRequestModel> stockModelList, Integer operateType) {
		String operateName = operateType == 1 ? "占用" : "释放";
		ParamModel paramModel = ParamModel.getInstance();
		if (CommonUtils.checkObjectIsNull(stockModelList)) {
			paramModel.setErrorModel(operateName + "库存传入stockModelList对象集合为空!");
			return paramModel;
		}

		Map<String, String> filterRepeatMap = new HashMap<String, String>();
		Iterator<OccupyStockRequestModel> itera = stockModelList.iterator();
		OccupyStockRequestModel stockModel = null;
		String repeatKey = "";
		while (itera.hasNext()) {
			stockModel = itera.next();
			if (CommonUtils.checkObjectIsNull(stockModel)) {
				itera.remove();
				continue;
			}
			if (CommonUtils.checkStringIsNullStrict(stockModel.getTransactionId())) {
				paramModel.setErrorModel(operateName + "库存传入transactionId交易ID为空!");
				return paramModel;
			}
			if (CommonUtils.checkLongIsNull(stockModel.getProductId(), true)) {
				paramModel.setErrorModel(operateName + "库存传入productId产品ID为空!");
				return paramModel;
			}
			//占库存必须传库存id
			//			if (operateType == 1 && CommonUtils.checkLongIsNull(stockModel.getStockId(), true)) {
			//				paramModel.setErrorModel(operateName + "库存传入stockId库存ID为空!");
			//				return paramModel;
			//			}
			if (operateType == 1 && CommonUtils.checkLongIsNull(stockModel.getStockRuleId(), Boolean.TRUE)) {
				paramModel.setErrorModel(operateName + "库存传入库存规则ID为空!");
				return paramModel;
			}
			if (stockModel.getStockNum() == null || stockModel.getStockNum() < 0) {
				paramModel.setErrorModel(operateName + "库存传入stockNum数错误!");
				return paramModel;
			}
			repeatKey = stockModel.getTransactionId() + stockModel.getProductId();
			if (filterRepeatMap.containsKey(repeatKey)) {
				itera.remove();
				continue;
			} else {
				filterRepeatMap.put(repeatKey, repeatKey);
			}
		}
		if (CommonUtils.checkCollectionIsNull(stockModelList)) {
			paramModel.setErrorModel(operateName + "库存传入List<OccupyStockRequestModel>对象为空!");
			return paramModel;
		}
		return paramModel;
	}

	private CommonTradeOpeStockModel getOperateStocks(List<OccupyStockRequestModel> stockModelList, Integer operateType) {
		CommonTradeOpeStockModel commonTradeOpeStock = new CommonTradeOpeStockModel();
		commonTradeOpeStock.setTransactionId(stockModelList.get(0).getTransactionId());
		commonTradeOpeStock.setProductId(stockModelList.get(0).getProductId());
		List<OccupyStockRequestModel> occupyStocks = new ArrayList<OccupyStockRequestModel>();
		List<OccupyStockRequestModel> releaseStocks = new ArrayList<OccupyStockRequestModel>();
		Iterator<OccupyStockRequestModel> itera = stockModelList.iterator();
		OccupyStockRequestModel stockModel = null, tempStockModel = null;
		LockRecord lockRecord = null;
		StockQueryRequestModel model = null;
		Stock stock = null;
		Integer occupyNum = 0, releaseNum = 0;
		while (itera.hasNext()) {
			stockModel = itera.next();
			lockRecord = lockRecordWriteMapper.queryLockRecordByTranPro(stockModel.getTransactionId(),
					stockModel.getProductId());
			int curOccupyNum = stockModel.getStockNum();
			if (lockRecord == null && (operateType == 2 || curOccupyNum == 0)) {
				throw new NotFoundStockRecordException();
			}
			//占库存
			int historyLockNum = (lockRecord == null || CommonUtils.checkIntegerIsNull(lockRecord.getLockNum(), true)) ? 0
					: lockRecord.getLockNum();
			if (operateType == 1) {
				if (curOccupyNum == 0) {
					if (historyLockNum > 0) {
						stockModel.setStockNum(historyLockNum);
						releaseStocks.add(stockModel);

						releaseNum += stockModel.getStockNum();
					}
				} else if (curOccupyNum == historyLockNum) {
					itera.remove();
					continue;
				} else {
					if (lockRecord != null && historyLockNum > 0) {
						tempStockModel = new OccupyStockRequestModel();
						BeanUtils.copyProperties(stockModel, tempStockModel);
						tempStockModel.setStockNum(historyLockNum);
						releaseStocks.add(tempStockModel);

						releaseNum += tempStockModel.getStockNum();
					}
					occupyStocks.add(stockModel);
					occupyNum += stockModel.getStockNum();
				}
				model = new StockQueryRequestModel();
				model.setRuleId(stockModel.getStockRuleId());
				model.setStockTime(CommonUtils.getStockDateInteger(stockModel.getShowDate()) + "");
				stock = queryStockByRuleEngine.queryStockByRule(model);
				if (stock == null) {
					throw new NotFoundStockException(StockExceptionCode.NOT_FOUND_STOCK_ERR_MSG);
				}
				if (tempStockModel != null) {
					tempStockModel.setStockId(stock.getId());
				}
				stockModel.setStockId(stock.getId());

			} else {
				stockModel.setStockId(lockRecord.getStockId());
				releaseStocks.add(stockModel);
			}
		}
		commonTradeOpeStock.setOccupyStocks(occupyStocks);
		commonTradeOpeStock.setReleaseStocks(releaseStocks);
		opeStock(occupyNum, releaseNum, commonTradeOpeStock);
		return commonTradeOpeStock;
	}

	private void opeStock(Integer occupyNum, Integer releaseNum, CommonTradeOpeStockModel commonTradeOpeStock) {
		String type = occupyNum > releaseNum ? CommonTradeOpeStockModel.OCCUPY_STOCK
				: CommonTradeOpeStockModel.RELEASE_STOCK;
		Integer num = Math.abs(occupyNum - releaseNum);
		commonTradeOpeStock.setOpeNum(num);
		commonTradeOpeStock.setType(num == 0 ? "" : type);
	}

}
