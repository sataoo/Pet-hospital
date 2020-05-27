package com.ledao.service;

import com.ledao.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * 客户商品评论Service接口
 *
 * @author LeDao
 * @company
 * @create 2020-05-27 22:35
 */
public interface CommentService {

    /**
     * 条件查询客户商品评论
     *
     * @param map
     * @return
     */
    List<Comment> list(Map<String, Object> map);

    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getCount(Map<String, Object> map);

    /**
     * 添加客户商品评论
     *
     * @param comment
     * @return
     */
    Integer add(Comment comment);
}
