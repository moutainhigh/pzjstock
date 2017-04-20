package com.pzj.core.product.screeings;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.common.exception.TheaterExceptionCode;
import com.pzj.core.product.entity.Screeings;
import com.pzj.core.product.model.screeings.CreateScreeningsReqModel;
import com.pzj.core.product.model.screeings.ModifyScreeningReqModel;
import com.pzj.core.product.write.ScreeingsWriteMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017-3-17.
 */
@Component("screeningsWriteEngine")
@Transactional(value = "stock.transactionManager")
public class ScreeningsWriteEngine {

    private static final Logger logger = LoggerFactory.getLogger(ScreeningsWriteEngine.class);

    @Resource
    private ScreeingsWriteMapper screeningsWriteMapper;

    public Long createScreenings(CreateScreeningsReqModel createScreeningsReqModel){
        Screeings screenings = ScreeningModelConvert.convert(createScreeningsReqModel);

        Long screeningsId = createScreenings(screenings);

        return screeningsId;
    }

    public Long createScreenings(Screeings screenings){
        createScreeningsCheck(screenings);

        createScreeningsInit(screenings);

        screeningsWriteMapper.insertScreeings(screenings);

        return screenings.getId();
    }

    private void createScreeningsCheck(Screeings screenings){
        if (screenings == null){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }
        if (screenings.getScenicId() == null){
            throw new TheaterException(TheaterExceptionCode.SCREENINGS_NULL_THEATERID);
        }
        if (screenings.getSupplierId() == null){
            throw new TheaterException(TheaterExceptionCode.SCREENINGS_NULL_SUPPLIERID);
        }
        if (screenings.getName() == null){
            throw new TheaterException(TheaterExceptionCode.SCREENINGS_NULL_NAME);
        }
        if (screenings.getStartTime() == null){
            throw new TheaterException(TheaterExceptionCode.SCREENINGS_NULL_STARTTIME);
        }
        if (screenings.getEndTime() == null){
            throw new TheaterException(TheaterExceptionCode.SCREENINGS_NULL_ENDTIME);
        }
    }

    private void createScreeningsInit(Screeings screenings) {
        screenings.setCreateTime(new Date());
        screenings.setState(ScreeningState.ENABLE.getValue());
    }

    public Boolean modifyScreenings(ModifyScreeningReqModel modifyScreeningReqModel){
        Screeings screenings = ScreeningModelConvert.convert(modifyScreeningReqModel);

        Boolean modify = modifyScreenings(screenings);

        return modify;
    }

    public Boolean modifyScreenings(Screeings screenings){
        modifyScreeningsCheck(screenings);

        int update = screeningsWriteMapper.updateScreeingsById(screenings);

        return update == 1;
    }

    private void modifyScreeningsCheck(Screeings screenings) {
        if (screenings == null){
            throw new TheaterException(TheaterExceptionCode.PARAMETER_EMPTY);
        }
        if (screenings.getId() == null){
            throw new TheaterException(TheaterExceptionCode.SCREENINGS_NULL_ID);
        }
        if (screenings.getName() == null &&
                screenings.getCode() == null &&
                screenings.getStartTime() == null &&
                screenings.getEndTime() == null){
            throw new TheaterException(TheaterExceptionCode.MODIFY_DATA_EMPTY);
        }
    }
}
