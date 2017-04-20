package com.pzj.core.product.entity;

import java.io.Serializable;
import java.util.Date;

public class Acting implements Serializable {

    private static final long serialVersionUID = 5222324090872261015L;

    /** 演艺主键id */
    private Long              id;
    /** 景区id */
    private Long              scenicId;
    /** 供应商id */
    private Long              supplierId;
    /** 状态（1 正常 2 停用，默认为1） */
    private Integer           state;
    /** 演艺名称 */
    private String            name;
    /** 是否需要选座（1 需要选座 2 不需要选座，默认2）*/
    private Integer              whetherNeedSeat;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              updateTime;
    /** 演艺备注 */
    private String            remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScenicId() {
        return scenicId;
    }

    public void setScenicId(Long scenicId) {
        this.scenicId = scenicId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getWhetherNeedSeat() {
        return whetherNeedSeat;
    }

    public void setWhetherNeedSeat(Integer whetherNeedSeat) {
        this.whetherNeedSeat = whetherNeedSeat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(Acting.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("scenicId: ").append(scenicId).append(",");
        tostr.append("supplierId: ").append(supplierId).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("name: ").append(name).append(",");
        tostr.append("whetherNeedSeat: ").append(whetherNeedSeat).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("remarks: ").append(remarks).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}