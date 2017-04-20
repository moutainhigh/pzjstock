package com.pzj.core.product.common.exception;

import com.pzj.framework.exception.ServiceException;

/**
 * Created by Administrator on 2017-3-20.
 */
public class TheaterException extends ServiceException {

    private final int code;

    public TheaterException() {
        super(TheaterExceptionCode.ERROR.getMsg());
        this.code = TheaterExceptionCode.ERROR.getCode();
    }

    public TheaterException(TheaterExceptionCode theaterExceptionCode) {
        super(theaterExceptionCode.getMsg());
        this.code = theaterExceptionCode.getCode();
    }

    public TheaterException(int code, String message) {
        super(message);
        this.code = code;
    }
    public TheaterException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = TheaterExceptionCode.ERROR.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
