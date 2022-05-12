package com.ledao.controller.admin;

import com.ledao.entity.*;
import com.ledao.service.DiagnosisService;
import com.ledao.service.LogService;
import com.ledao.service.PetService;
import com.ledao.service.UserService;
import com.ledao.util.StringUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台诊断录入Controller
 */
@RestController
@RequestMapping("/admin/diagnosis")
public class DiagnosisAdminController {

    @Resource
    private DiagnosisService diagnosisService;

    @Resource
    private PetService petService;

    @Resource
    private LogService logService;

    @Resource
    private UserService useService;

    @RequestMapping("/list")
    @RequiresPermissions(value ="诊疗收费")
    public Map<String, Object> list(Diagnosis diagnosis, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);

        map.put("customerId", diagnosis.getCustomerId());
        map.put("petId", diagnosis.getPetId());
        map.put("status", diagnosis.getStatus());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        resultMap.put("rows", diagnosisService.list(map));
        resultMap.put("total", diagnosisService.getCount(map));
        return resultMap;
    }

    @RequestMapping("/save")
    @RequiresPermissions(value = {"诊疗录入"},logical = Logical.OR)
    public Map<String, Object> save(Diagnosis diagnosis, HttpSession httpSession) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key;
        User user = (User) httpSession.getAttribute("user");
        String userName = user.getUserName();
        User byUserName = useService.findByUserName(userName);
        diagnosis.setUser(byUserName);
        if (diagnosis.getId() == null) {
            logService.add(new Log(Log.ADD_ACTION, "添加诊疗记录" + diagnosis));
            key = diagnosisService.add(diagnosis);
        } else {
            logService.add(new Log(Log.UPDATE_ACTION, "修改诊疗记录" + diagnosis));
            key = diagnosisService.update(diagnosis);
        }
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }
    @RequestMapping("/settlement")
//    @RequiresPermissions(value = {"诊疗录入"},logical = Logical.OR)
    public Map<String, Object> settlement(Diagnosis diagnosis) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key = 0;
        logService.add(new Log(Log.UPDATE_ACTION, "结算诊疗记录" + diagnosis));
        key = diagnosisService.update(diagnosis);
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

}
