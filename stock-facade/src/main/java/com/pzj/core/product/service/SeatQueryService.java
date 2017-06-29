package com.pzj.core.product.service;

import java.util.ArrayList;

import com.pzj.core.product.model.QuerySeatDescResModel;
import com.pzj.framework.context.Result;
import com.pzj.framework.context.ServiceContext;

public interface SeatQueryService {
	/**
	 * 废弃不使用，更改为：SeatRecordQueryService#queryValidSeatRecordByTransactionId
	 */
	@Deprecated
	Result<ArrayList<QuerySeatDescResModel>> querySeatByTransactionId(String transacTionId,
			ServiceContext serviceContext);
}
