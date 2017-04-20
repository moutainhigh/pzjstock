package com.pzj.core.product.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-3-20.
 */
public class OccupyStockReqsModel implements Serializable {
    private List<OccupyStockReqModel> occupyStockReqs;

    public List<OccupyStockReqModel> getOccupyStockReqs() {
        return occupyStockReqs;
    }

    public void setOccupyStockReqs(List<OccupyStockReqModel> occupyStockReqs) {
        this.occupyStockReqs = occupyStockReqs;
    }

    public void addOccupyStockReqModel(OccupyStockReqModel occupyStockReqModel){
        if (occupyStockReqs == null){
            occupyStockReqs = new ArrayList<>();
        }
        occupyStockReqs.add(occupyStockReqModel);
    }
}
