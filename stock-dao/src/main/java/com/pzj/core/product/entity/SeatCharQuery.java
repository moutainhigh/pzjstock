package com.pzj.core.product.entity;

import java.util.List;

/**
 * Created by Administrator on 2017-5-23.
 */
public class SeatCharQuery extends SeatChar{
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
