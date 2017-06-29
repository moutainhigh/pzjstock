package com.pzj.core.product.seatRecord.exception;

import com.pzj.core.common.exception.StockException;
import com.pzj.core.product.common.exception.TheaterException;
import com.pzj.core.product.model.OccupyStockResponse;

/**
 * Created by Administrator on 2017-6-7.
 */
public class SeatRecordOccupyStockException extends TheaterException {
	public SeatRecordOccupyStockException(StockException theaterException, OccupyStockResponse response) {
		super(theaterException);
		setOccupyStockResponse(response);
	}

	private OccupyStockResponse occupyStockResponse;

	public SeatRecordOccupyStockException(Integer errorCode, String errorMsg, OccupyStockResponse occupyStockResponse) {
		super(errorCode, errorMsg);
		this.occupyStockResponse = occupyStockResponse;
	}

	public OccupyStockResponse getOccupyStockResponse() {
		return occupyStockResponse;
	}

	public void setOccupyStockResponse(OccupyStockResponse occupyStockResponse) {
		this.occupyStockResponse = occupyStockResponse;
	}

}
