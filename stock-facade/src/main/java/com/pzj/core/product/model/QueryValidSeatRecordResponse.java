package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-29.
 */
public class QueryValidSeatRecordResponse implements Serializable{
    private List<QuerySeatRecordResponse> querySeatRecordResponses;

    public List<QuerySeatRecordResponse> getQuerySeatRecordResponses() {
        return querySeatRecordResponses;
    }

    public void setQuerySeatRecordResponses(List<QuerySeatRecordResponse> querySeatRecordResponses) {
        this.querySeatRecordResponses = querySeatRecordResponses;
    }
}
