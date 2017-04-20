package com.pzj.core.stock.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.common.utils.CommonUtils;
import com.pzj.core.stock.context.ShowResponse;
import com.pzj.core.stock.event.InitDockSeatData;
import com.pzj.core.stock.exception.errcode.StockExceptionCode;
import com.pzj.core.stock.model.ParamModel;
import com.pzj.core.stock.model.ShowModel;
import com.pzj.core.stock.seat.OccupySeatEngine;
import com.pzj.core.stock.seat.RandomAssignSeatEngine;
import com.pzj.core.stock.seat.ReleaseSeatEngine;
import com.pzj.core.stock.seat.RollbackOccupySeatEngine;
import com.pzj.core.stock.service.ShowService;
import com.pzj.dock.supplier.service.ISeatDockingService;
import com.pzj.dock.supplier.vo.DockSeatVO;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.converter.ObjectConverter;

@Service("showService")
public class ShowServiceImpl implements ShowService {

	private final static Logger logger = LoggerFactory.getLogger(ShowServiceImpl.class);

	@Resource(name = "occupySeatEngine")
	private OccupySeatEngine occupySeatEngine;
	@Resource(name = "releaseSeatEngine")
	private ReleaseSeatEngine releaseSeatEngine;
	@Resource(name = "randomAssignSeatEngine")
	private RandomAssignSeatEngine randomAssignSeatEngine;
	@Resource(name = "rollbackOccupySeatEngine")
	private RollbackOccupySeatEngine rollbackOccupySeatEngine;

	@Resource(name = "occupySeatValidator")
	private ObjectConverter<ShowModel, ServiceContext, ParamModel> occupySeatValidator;
	@Resource(name = "releaseSeatValidator")
	private ObjectConverter<ShowModel, ServiceContext, ParamModel> releaseSeatValidator;
	@Resource(name = "randomAssignSeatValidator")
	private ObjectConverter<ShowModel, ServiceContext, ParamModel> randomAssignSeatValidator;
	@Resource(name = "rollbackOccupySeatValidator")
	private ObjectConverter<ShowModel, ServiceContext, ParamModel> rollbackOccupySeatValidator;

	@Autowired
	private ISeatDockingService seatDockingService;
	@Autowired
	private InitDockSeatData initDockSeatData;

	@Override
	public Result<Boolean> occupySeat(ShowModel showModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = occupySeatValidator.convert(showModel, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("occupy seat fail, Illegal params. request: {}, context: {}.", showModel, serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("occupy seat. request: {}, context: {}.", showModel, serviceContext);
		}

		//检查座位是否可以占用
		try {
			occupySeatEngine.checkSeatIsAvai(showModel);
		} catch (Exception e) {
			logger.error("occupy seat data check fail. request: " + showModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);
			return result;
		}

		//调用对接系统占座
		try {
			if (null == showModel.getIsCallOffline() || showModel.getIsCallOffline() == ShowModel.CALL_OFFLINE_YES) {
				DockSeatVO dockSeatVO = initDockSeatData.initDockSeat(showModel);
				logger.info("invoke dock occupy seat param:{}", JSONConverter.toJson(dockSeatVO));
				DockSeatVO retDockSeatVO = seatDockingService.havedockSeat(dockSeatVO);
				logger.info("invoke dock occupy seat return result:{}", JSONConverter.toJson(retDockSeatVO));
				if (!CommonUtils.checkObjectIsNull(retDockSeatVO)) {
					String retCode = retDockSeatVO.getCode();
					if (!CommonUtils.checkStringIsNullStrict(retCode) && !"10000".equals(retCode)) { // 10000 成功
						throw new StockException("调用对接系统占座失败,占座失败的座位号有【" + retDockSeatVO.getSeats() + "】");
					}
				}
			}
		} catch (Throwable e) {
			logger.error("occupy seat invoke dock occupy seat fail. request: " + showModel + ", context: "
					+ serviceContext, e);

			CommonUtils.setResultErr(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_CODE,
					StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG, result);
			return result;
			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		try {
			occupySeatEngine.occupySeat(showModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("occupy seat fail. request: " + showModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("occupy seat success. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.ShowService#releaseSeat(com.pzj.core.stock.model.ShowModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> releaseSeat(ShowModel showModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = releaseSeatValidator.convert(showModel, serviceContext);
		if (!paramModel.paramIsOk()) {
			logger.error("release seat fail, Illegal params. request: {}, context: {}.", showModel, serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;

			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("release seat. request: {}, context: {}.", showModel, serviceContext);
		}
		//调用对接系统释放座位
		try {
			if (null == showModel.getIsCallOffline() || showModel.getIsCallOffline() == ShowModel.CALL_OFFLINE_YES) {
				DockSeatVO dockSeatVO = initDockSeatData.initDockSeat(showModel);
				logger.info("invoke dock release seat param:{}", JSONConverter.toJson(dockSeatVO));
				DockSeatVO retDockSeatVO = seatDockingService.refunddockSeat(dockSeatVO);
				logger.info("invoke dock release seat return result:{}", JSONConverter.toJson(retDockSeatVO));
				if (!CommonUtils.checkObjectIsNull(retDockSeatVO)) {
					String retCode = retDockSeatVO.getCode();
					if (!CommonUtils.checkStringIsNullStrict(retCode) && !"10000".equals(retCode)) { // 10000 成功
						throw new StockException("调用对接系统释放座位失败,释放失败的座位号有【" + retDockSeatVO.getSeats() + "】");
					}
				}
			}
		} catch (Throwable e) {
			logger.error("release seat invoke dock release seat fail. request: " + showModel + ", context: "
					+ serviceContext, e);
			CommonUtils.setResultErr(StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_CODE,
					StockExceptionCode.INVOKE_OTHER_SERVICE_ERR_MSG, result);
			return result;

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		try {
			releaseSeatEngine.releaseSeat(showModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("release seat fail. request: " + showModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("release seat success. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

	@Override
	public Result<ShowResponse> randomAssignSeat(ShowModel showModel, ServiceContext serviceContext) {
		Result<ShowResponse> result = new Result<ShowResponse>();
		ParamModel paramModel = randomAssignSeatValidator.convert(showModel, null);
		if (!paramModel.paramIsOk()) {
			logger.error("random assign seat fail, Illegal params. request: {}, context: {}.", showModel,
					serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("random assign seat. request: {}, context: {}.", showModel, serviceContext);
		}

		//通过产品获取中间表id 校验中间表id不为空
		try {
			//获取动态分配座位数组
			String[] randomSeatList = randomAssignSeatEngine.randomAssignSeat(showModel);

			//封装返回对象
			ShowResponse showResponse = new ShowResponse();
			showResponse.setRandomAssignSeats(randomSeatList);
			result.setData(showResponse);

		} catch (Throwable e) {
			logger.error("random assign seat fail. request: " + showModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("random assign seat. result: {}", JSONConverter.toJson(result));
		}

		return result;
	}

	/** 
	 * @see com.pzj.core.stock.service.ShowService#rollbackOccupySeat(com.pzj.core.stock.model.ShowModel, com.pzj.framework.context.ServiceContext)
	 */
	@Override
	public Result<Boolean> rollbackOccupySeat(ShowModel showModel, ServiceContext serviceContext) {
		Result<Boolean> result = new Result<Boolean>(Boolean.FALSE);
		ParamModel paramModel = rollbackOccupySeatValidator.convert(showModel, null);
		if (!paramModel.paramIsOk()) {
			logger.error("rollback occupy seat fail, Illegal params. request: {}, context: {}.", showModel,
					serviceContext);
			CommonUtils.setParamErr(result, paramModel.getParamErrorMsg());
			return result;
			//			throw new ParameterErrorException(paramModel.getParamErrorMsg());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("rollback occupy seat. request: {}, context: {}.", showModel, serviceContext);
		}

		//回滚对接系统座位

		try {
			rollbackOccupySeatEngine.rollbackOccupySeat(showModel);
			result.setData(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error("rollback occupy seat fail. request: " + showModel + ", context: " + serviceContext, e);
			CommonUtils.convertException(e, result);

			//			if (e instanceof ServiceException) {
			//				throw e;
			//			}
			//			throw new StockException(StockExceptionCode.STOCK_ERR_MSG, e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("rollback occupy seat success. result: {}.", JSONConverter.toJson(result));
		}
		return result;
	}

}
