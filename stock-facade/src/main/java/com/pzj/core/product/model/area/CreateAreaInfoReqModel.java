package com.pzj.core.product.model.area;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-8.
 */
public class CreateAreaInfoReqModel implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String nameAbbr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }
}
