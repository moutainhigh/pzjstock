package com.pzj.core.product.model.area;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-8.
 */
public class ModifyAreaInfoReqModel implements Serializable {
    /**
     * 区域id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String nameAbbr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }
}
