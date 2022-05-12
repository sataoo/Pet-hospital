package com.ledao.service.impl;

import com.ledao.entity.Diagnosis;
import com.ledao.mapper.DiagnosisMapper;
import com.ledao.service.DiagnosisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("DiagnosisService")
public class DiagnosisServiceImpl implements DiagnosisService {

    @Resource
    private DiagnosisMapper diagnosisMapper;

    @Override
    public List<Diagnosis> list(Map<String, Object> map) {
        return diagnosisMapper.list(map);
    }

    @Override
    public Long getCount(Map<String, Object> map) {
        return diagnosisMapper.getCount(map);
    }

    @Override
    public Integer add(Diagnosis diagnosis) {
        return diagnosisMapper.add(diagnosis);
    }

    @Override
    public Integer update(Diagnosis diagnosis) {
        return diagnosisMapper.update(diagnosis);
    }

    @Override
    public Integer delete(Integer id) {
        return diagnosisMapper.delete(id);
    }

    @Override
    public Diagnosis findById(Integer id) {
        return diagnosisMapper.findById(id);
    }
}
