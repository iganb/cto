package com.example.cto.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.Complaint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cto.entity.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2023-03-16
 */
public interface IComplaintService extends IService<Complaint> {

    Page<Complaint> complaintPage(Page<Object> objectPage, String wayBillNumber);

    Complaint getByWayBillNumber(String wayBillNumber);

    int getTouSuNumber();
}
