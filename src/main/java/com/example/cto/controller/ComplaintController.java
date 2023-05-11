package com.example.cto.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.entity.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IComplaintService;
import com.example.cto.entity.Complaint;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zc
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Resource
    private IComplaintService complaintService;


    @PostMapping
    public Result save(@RequestBody Complaint complaint) {

//        String current=new SimpleDateFormat("yyyy-MM-dd HH:mm:SSS").format(new Date());
//
//        long timestamp = System.currentTimeMillis();
//        Timestamp time=new Timestamp(timestamp);
//        System.out.println(time);
//        complaint.setCreateTime(time);


        return Result.success(complaintService.saveOrUpdate(complaint));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(complaintService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(complaintService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(complaintService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(complaintService.getById(id));
    }
    //查看投诉
    @GetMapping("/checkComplaint")
    public Result checkComplaint(@RequestParam String wayBillNumber){
        return Result.success(complaintService.getByWayBillNumber(wayBillNumber));
    }
    //处理投诉
    @PostMapping("/saveOne")
    public Result saveOne(@RequestBody Complaint complaint) {
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        complaint.setOverTime(nousedate);
        return Result.success(complaintService.saveOrUpdate(complaint));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String wayBillNumber) {
        Page<Complaint> page = complaintService.complaintPage(new Page<>(pageNum, pageSize), wayBillNumber);

        return Result.success(page);
    }

    public int getTouSuNumber(){
        return complaintService.getTouSuNumber();
    }

}

