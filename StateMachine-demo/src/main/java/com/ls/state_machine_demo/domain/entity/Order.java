package com.ls.state_machine_demo.domain.entity;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import javax.validation.constraints.NotNull;
//import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
//import io.swagger.annotations.ApiModelProperty;
//import org.hibernate.validator.constraints.Length;
//import org.jetbrains.annotations.NotNull;

/**
 * 订单表
 * @TableName tb_order
 */
//@TableName("tb_order")
public class Order implements Serializable {
    /**
     * 主键ID
     */
//    @NotNull(message="[主键ID]不能为空")
    //@ApiModelProperty("主键ID")
    private Long id;
    /**
     * 订单编码
     */
//    @Size(max= 128,message="编码长度不能超过128")
    //@ApiModelProperty("订单编码")
//    @Length(max= 128,message="编码长度不能超过128")
    private String orderCode;
    /**
     * 订单状态
     */
    //@ApiModelProperty("订单状态")
    private Integer status;
    /**
     * 订单名称
     */
//    @Size(max= 64,message="编码长度不能超过64")
    //@ApiModelProperty("订单名称")
//    @Length(max= 64,message="编码长度不能超过64")
    private String name;
    /**
     * 价格
     */
    //@ApiModelProperty("价格")
    private BigDecimal price;
    /**
     * 删除标记，0未删除  1已删除
     */
//    @NotNull(message="[删除标记，0未删除  1已删除]不能为空")
    //@ApiModelProperty("删除标记，0未删除  1已删除")
    private Integer deleteFlag;
    /**
     * 创建时间
     */
//    @NotNull(message="[创建时间]不能为空")
    //@ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
//    @NotNull(message="[更新时间]不能为空")
    //@ApiModelProperty("更新时间")
    private Date updateTime ;
    /**
     * 创建人
     */
//    @Size(max= 32,message="编码长度不能超过32")
    //@ApiModelProperty("创建人")
//    @Length(max= 32,message="编码长度不能超过32")
    private String createUserCode;
    /**
     * 更新人
     */
//    @Size(max= 32,message="编码长度不能超过32")
    //@ApiModelProperty("更新人")
//    @Length(max= 32,message="编码长度不能超过32")
    private String updateUserCode ;
    /**
     * 版本号
     */
//    @NotNull(message="[版本号]不能为空")
    //@ApiModelProperty("版本号")
    private Integer version;
    /**
     * 备注
     */
//    @Size(max= 64,message="编码长度不能超过64")
    //@ApiModelProperty("备注")
//    @Length(max= 64,message="编码长度不能超过64")
    private String remark ;
    /**
     * 主键ID
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 订单编码
     */
    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }
    /**
     * 订单状态
     */
    public void setStatus(Integer status){
        this.status = status;
    }
    /**
     * 订单名称
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 价格
     */
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    /**
     * 删除标记，0未删除  1已删除
     */
    public void setDeleteFlag(Integer deleteFlag){
        this.deleteFlag = deleteFlag;
    }
    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /**
     * 更新时间
     */
    public void setUpdateTime (Date updateTime ){
        this.updateTime  = updateTime ;
    }
    /**
     * 创建人
     */
    public void setCreateUserCode(String createUserCode){
        this.createUserCode = createUserCode;
    }
    /**
     * 更新人
     */
    public void setUpdateUserCode (String updateUserCode ){
        this.updateUserCode  = updateUserCode ;
    }
    /**
     * 版本号
     */
    public void setVersion(Integer version){
        this.version = version;
    }
    /**
     * 备注
     */
    public void setRemark (String remark ){
        this.remark  = remark ;
    }
    /**
     * 主键ID
     */
    public Long getId(){
        return this.id;
    }
    /**
     * 订单编码
     */
    public String getOrderCode(){
        return this.orderCode;
    }
    /**
     * 订单状态
     */
    public Integer getStatus(){
        return this.status;
    }
    /**
     * 订单名称
     */
    public String getName(){
        return this.name;
    }
    /**
     * 价格
     */
    public BigDecimal getPrice(){
        return this.price;
    }
    /**
     * 删除标记，0未删除  1已删除
     */
    public Integer getDeleteFlag(){
        return this.deleteFlag;
    }
    /**
     * 创建时间
     */
    public Date getCreateTime(){
        return this.createTime;
    }
    /**
     * 更新时间
     */
    public Date getUpdateTime (){
        return this.updateTime ;
    }
    /**
     * 创建人
     */
    public String getCreateUserCode(){
        return this.createUserCode;
    }
    /**
     * 更新人
     */
    public String getUpdateUserCode (){
        return this.updateUserCode ;
    }
    /**
     * 版本号
     */
    public Integer getVersion(){
        return this.version;
    }
    /**
     * 备注
     */
    public String getRemark (){
        return this.remark ;
    }
}
