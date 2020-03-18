package com.ledao.entity;

import lombok.Data;

import java.util.Date;

/**
 * 客户宠物病例单
 *
 * @author LeDao
 * @company
 * @create 2020-03-18 23:39
 */
@Data
public class MedicalRecord {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 客户真实姓名
     */
    private String customerName;
    /**
     * 客户的宠物名称
     */
    private String petName;
    /**
     * 病例描述
     */
    private String content;
}
