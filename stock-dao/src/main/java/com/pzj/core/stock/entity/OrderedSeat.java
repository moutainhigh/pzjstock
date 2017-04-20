package com.pzj.core.stock.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderedSeat implements Serializable {

    private static final long serialVersionUID = 1999205584773944425L;

    /** 预约座位主键id */
    private Long              id;
    /** 库存主键id */
    private Long              stockId;
    /** 状态 （1 正常 2 停用 ，默认为1）*/
    private Integer           state;
    /** 地接社ID */
    private Long              resellerId;
    /** 地接社部门ID */
    private Long              secResellerId;
    /** 地接社部门ID */
    private Long              guideId;
    /** 操作者id */
    private Long              operatorId;
    /** 导游手机号 */
    private String            guideMobile;
    /** 预约座位信息 */
    private String            seatNumber;
    /** 总预约座位数 */
    private Integer           totalSeat;
    /** 是否团客占座：0，散客；1，团客（默认为1） */
    private Integer           isTeam;
    /** 散客占座人姓名 */
    private String            appointName;
    /** 预约唯一id */
    private String            appointMobile;
    /** 散客占座人联系方式 */
    private Long              appointOnlyId;
    /** 创建时间 */
    private Date              createTime;
    /** 预约时间 */
    private Date              appointTime;
    /** 失效时间 */
    private Date              unvalidTime;
    /** 修改时间 */
    private Date              updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getResellerId() {
        return resellerId;
    }

    public void setResellerId(Long resellerId) {
        this.resellerId = resellerId;
    }

    public Long getSecResellerId() {
        return secResellerId;
    }

    public void setSecResellerId(Long secResellerId) {
        this.secResellerId = secResellerId;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getGuideMobile() {
        return guideMobile;
    }

    public void setGuideMobile(String guideMobile) {
        this.guideMobile = guideMobile == null ? null : guideMobile.trim();
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber == null ? null : seatNumber.trim();
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }

    public Integer getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }

    public String getAppointName() {
        return appointName;
    }

    public void setAppointName(String appointName) {
        this.appointName = appointName == null ? null : appointName.trim();
    }

    public String getAppointMobile() {
        return appointMobile;
    }

    public void setAppointMobile(String appointMobile) {
        this.appointMobile = appointMobile == null ? null : appointMobile.trim();
    }

    public Long getAppointOnlyId() {
        return appointOnlyId;
    }

    public void setAppointOnlyId(Long appointOnlyId) {
        this.appointOnlyId = appointOnlyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Date getUnvalidTime() {
        return unvalidTime;
    }

    public void setUnvalidTime(Date unvalidTime) {
        this.unvalidTime = unvalidTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        StringBuilder tostr = new StringBuilder(OrderedSeat.class.getSimpleName());
        tostr.append("[");
        tostr.append("id: ").append(id).append(",");
        tostr.append("stockId: ").append(stockId).append(",");
        tostr.append("state: ").append(state).append(",");
        tostr.append("resellerId: ").append(resellerId).append(",");
        tostr.append("secResellerId: ").append(secResellerId).append(",");
        tostr.append("guideId: ").append(guideId).append(",");
        tostr.append("guideMobile: ").append(guideMobile).append(",");
        tostr.append("seatNumber: ").append(seatNumber).append(",");
        tostr.append("totalSeat: ").append(totalSeat).append(",");
        tostr.append("isTeam: ").append(isTeam).append(",");
        tostr.append("appointName: ").append(appointName).append(",");
        tostr.append("appointMobile: ").append(appointMobile).append(",");
        tostr.append("appointOnlyId: ").append(appointOnlyId).append(",");
        tostr.append("createTime: ").append(createTime).append(",");
        tostr.append("appointTime: ").append(appointTime).append(",");
        tostr.append("unvalidTime: ").append(unvalidTime).append(",");
        tostr.append("updateTime: ").append(updateTime).append(",");
        tostr.append("]");
        return tostr.toString().intern();
    }
}