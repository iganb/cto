package com.example.cto.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.Complaint;
import com.example.cto.mapper.ComplaintMapper;
import com.example.cto.service.IComplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2023-03-16
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {
    @Resource
    private ComplaintMapper complaintMapper;


    @Override
    public Page<Complaint> complaintPage(Page<Object> objectPage, String wayBillNumber) {
        return complaintMapper.findComplaintPage(objectPage,wayBillNumber);
    }

    @Override
    public Complaint getByWayBillNumber(String wayBillNumber) {
        return complaintMapper.getByWayBillNumber(wayBillNumber);
    }

    @Override
    public int getTouSuNumber() {
        return complaintMapper.getTouSuNumber();
    }
}
