package com.ledao.entity;

import java.util.Date;

public class Diagnosis {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 要预约的客户对象
     */
    private Customer customer;
    /**
     * 要预约的客户id
     */
    private Integer customerId;
    /**
     * 要预约的宠物
     */
    private Pet pet;
    /**
     * 宠物id添加或修改预约单时用到
     */
    private Integer petId;

    /**
     * 预约单创建时间
     */
    private Date createDate;
    /**
     * 诊断描述(预约医生时可以输入病情描述)
     */
    private String description;
    /**
     * 接受预约的医生或美容师
     */
    private User user;
    /**
     * 接受预约的医生或美容师编号
     */
    private Integer userId;
    /**
     * 状态(0.未处理 1.预约成功 2.预约失败)
     */
    private Integer status;
    /**
     * 备注(如果预约不成功可以输入原因)
     */
    private Integer price;

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", customer=" + customer +
                ", customerId=" + customerId +
                ", pet=" + pet +
                ", petId=" + petId +
                ", createDate=" + createDate +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", userId=" + userId +
                ", status=" + status +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
