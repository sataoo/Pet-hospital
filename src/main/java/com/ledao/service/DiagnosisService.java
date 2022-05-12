package com.ledao.service;

import com.ledao.entity.Diagnosis;

import java.util.List;
import java.util.Map;

public interface DiagnosisService {
    /**
     * 分页分条件查询诊断结果
     * @param map
     * @return
     */
    List<Diagnosis> list(Map<String, Object> map);
    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getCount(Map<String, Object> map);

    /**
     * 添加诊断结果
     *
     * @param diagnosis
     * @return
     */
    Integer add(Diagnosis diagnosis);

    /**
     * 编辑诊断结果记录
     * @param diagnosis
     * @return
     */
    Integer update(Diagnosis diagnosis);

    /**
     * 根据id删除实体
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id查找实体
     *
     * @param id
     * @return
     */
    Diagnosis findById(Integer id);

}
