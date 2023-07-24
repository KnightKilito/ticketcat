package com.tpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tpm.entity.Hall;
import com.tpm.entity.JsonResult;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HallController {
    @Autowired
    HallService hallService;

    //增加放映厅
    @PostMapping("tpm/hall")
    public JsonResult addHall(Hall hall, BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
            hallService.save(hall);
            return new JsonResult("添加成功");
        }
    }
    //删除放映厅
    @DeleteMapping("tpm/hall/{hid}")
    public JsonResult delHall(@PathVariable int hid,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
            boolean isDel = hallService.removeById(hid);
            if (isDel==true){
                return new JsonResult("删除成功");
            }else{
                return new JsonResult(-1,"删除失败");
            }
        }
    }
    //修改放映厅信息
    @PutMapping("tpm/hall")
    public JsonResult updateHall(Hall hall,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
            boolean isUpdate = hallService.updateById(hall);
            if (isUpdate==true){
                return new JsonResult("修改成功");
            }else{
                return new JsonResult(-1,"修改失败");
            }
        }
    }
    //修改放映厅行数
    @PutMapping("tpm/hall/rowsize")
    public JsonResult updateRowsize(@PathVariable int hid,int rowsize,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR, br.getFieldError().getDefaultMessage());
        }else {
            Hall hr = hallService.getById(hid);
            hr.setRowsize(rowsize);
            boolean isUpdate = hallService.updateById(hr);
            if (isUpdate == true){
                return new JsonResult("修改成功");
            }else{
                return new JsonResult(-1,"修改失败");
            }
        }
    }
    //修改放映厅列数
    @PutMapping("tpm/hall/columnsize")
    public JsonResult updateColumnsize(@PathVariable int hid,int columnsize,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR, br.getFieldError().getDefaultMessage());
        }else {
            Hall hc = hallService.getById(hid);
            hc.setColumnsize(columnsize);
            boolean isUpdate = hallService.updateById(hc);
            if (isUpdate == true){
                return new JsonResult("修改成功");
            }else{
                return new JsonResult(-1,"修改失败");
            }
        }
    }
    //通过id查找放映厅
    @GetMapping("tpm/hall/{hid}")
    public JsonResult<Hall> getOneHallByHid(@PathVariable int hid,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
            return new JsonResult<Hall>(hallService.getById(hid));
        }
    }
    //通过名称查找放映厅
    @GetMapping("tpm/hall/{hname}")
    public JsonResult<Hall> findByHname(@PathVariable String hname,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("hname",hname);
            return new JsonResult<Hall>(hallService.listByMap(map));
        }
    }
    //通过影院名称查找放映厅
    @GetMapping("tpm/hall/{cid}")
    public JsonResult<Hall> findByHname(@PathVariable int cid,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("cid",cid);
            return new JsonResult<Hall>(hallService.listByMap(map));
        }
    }
    //通过行数查找放映厅
    @GetMapping("tpm/hall/rowsize/{rowsize}")
    public JsonResult<Hall> findByRowsize(@PathVariable int rowsize){
        Map<String,Object> map = new HashMap<>();
        map.put("rowsize",rowsize);
        return new JsonResult<Hall>(hallService.listByMap(map));
    }
    //通过列数查找放映厅
    @GetMapping("tpm/hall/rowsize/{columnsize}")
    public JsonResult<Hall> findByColumnsize(@PathVariable int columnsize){
        Map<String,Object> map = new HashMap<>();
        map.put("rowsize",columnsize);
        return new JsonResult<Hall>(hallService.listByMap(map));
    }
    //通过行数和列数查找放映厅
    @GetMapping("tpm/hall/{rowsize}/{columnsize}")
    public JsonResult<Hall> findByRowsizeAndColumnsize(@PathVariable int rowsize,@PathVariable int columnsize){
        Map<String,Object> map = new HashMap<>();
        map.put("rowsize",rowsize);
        map.put("columnsize",columnsize);
        return new JsonResult<Hall>(hallService.listByMap(map));
    }
    //通过hid查找行
    @GetMapping("tpm/hall/hid/{hid}")
    public Integer findRyHid(@PathVariable int hid){
        Hall h = hallService.getById(hid);
        return h.getRowsize();
    }
    //通过hid查找列
    @GetMapping("{hid}")
    public Integer findCByHid(@PathVariable int hid){
        Hall h = hallService.getById(hid);
        return h.getColumnsize();
    }
    //分页查询
    @GetMapping("{current}/{limit}")
    public JsonResult<Hall> page(@PathVariable long current,@PathVariable long limit){
        Page<Hall> objectPage = new Page<>(current,limit);
        hallService.page(objectPage,null);
        long total = objectPage.getTotal();
        List<Hall> records = objectPage.getRecords();
        return new JsonResult<Hall>(total,records);
    }
}
