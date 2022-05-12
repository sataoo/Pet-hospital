package com.ledao.controller.admin;

import com.ledao.entity.Customer;
import com.ledao.entity.Log;
import com.ledao.entity.PageBean;
import com.ledao.entity.Pet;
import com.ledao.service.CustomerService;
import com.ledao.service.LogService;
import com.ledao.service.PetService;
import com.ledao.util.DateUtil;
import com.ledao.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理客户Controller
 *
 * @author LeDao
 * @company
 * @create 2020-01-18 19:09
 */
@RestController
@RequestMapping("/admin/pet")
public class PetAdminController {

    @Value("${petImageFilePath}")
    private String petImageFilePath;

    @Resource
    private PetService petService;

    @Resource
    private LogService logService;

    @Resource
    private CustomerService customerService;

    /**
     * 下拉框模糊查询
     *
     * @param q
     * @return
     */
    @RequestMapping("/comboList")
//    @RequiresPermissions(value = {"销售出库", "客户退货", "销售单据查询", "客户退货查询", "客户统计"}, logical = Logical.OR)
    public Pet comboList(String q) {
        if (q == null) {
            q = "";
        }
        return petService.findByName(StringUtil.formatLike(q));
    }

    /**
     * 根据用户id查找宠物实体
     * @param customerId
     * @return
     */
    @RequestMapping("/petList")
    public List<Pet> petList( @RequestParam(value = "customerId", required = false) Integer customerId) {
        return petService.findByCustomerId(customerId);
    }


    /**
     * 分页查询客户信息
     *
     * @param searchCustomer
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions(value = "宠物管理")
    public Map<String, Object> list(Pet searchPet, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "contact", required = false) String contact,@RequestParam(value = "customerId", required = false) Integer customerId) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
//        if (searchPet.getCustomer()!=null){
//            map.put("name", StringUtil.formatLike(searchPet.getCustomer().getName()));
//        }else{
//            map.put("name", null);
//        }
        map.put("name", StringUtil.formatLike(searchPet.getName()));
        Customer customer = null;
        if (contact!=null){
            customer = customerService.findByContact((contact));
        }
        if (customer!=null){
            customer.setId(customerId);
        }
        map.put("customer",customer);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        resultMap.put("rows", petService.list(map));
        resultMap.put("total", petService.getCount(map));
        logService.add(new Log(Log.SEARCH_ACTION, "查询宠物信息"));
        return resultMap;
    }

    /**
     * 添加或者修改客户信息
     *
     * @param customer
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions(value = "宠物管理")
    public Map<String, Object> save(Pet pet, @RequestParam("petImage") MultipartFile file, @RequestParam(value = "contact",required = false) String contact) throws Exception {
        Map<String, Object> resultMap = new HashMap<>(16);
        if (!file.isEmpty()) {
            // 获取上传的文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr2() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(petImageFilePath + newFileName));
            if (pet.getId() != null) {
                FileUtils.deleteQuietly(new File(petImageFilePath + petService.findById(pet.getId()).getImageName()));
            }
            pet.setImageName(newFileName);
        }
        int key;
        Customer byContact = customerService.findByContact(contact);
        pet.setCustomer(byContact);
        if (pet.getId() == null) {
            key = petService.add(pet);
            logService.add(new Log(Log.ADD_ACTION, "添加宠物信息" + pet));
        } else {
            key = petService.update(pet);
            logService.add(new Log(Log.UPDATE_ACTION, "修改宠物信息" + pet));
        }
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    @RequestMapping("/delete")
    @RequiresPermissions(value = "宠物管理")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<>(16);
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            logService.add(new Log(Log.DELETE_ACTION, "删除宠物信息" + petService.findById(id)));
            petService.delete(id);
        }
        resultMap.put("success", true);
        return resultMap;
    }
}
