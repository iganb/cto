package com.example.cto.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.entity.Complaint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2023-03-16
 */
public interface ComplaintMapper extends BaseMapper<Complaint> {

    Page<Complaint> findComplaintPage(Page<Object> objectPage,@Param("wayBillNumber") String wayBillNumber);

    Complaint getByWayBillNumber(@Param("wayBillNumber")String wayBillNumber);

    @Select("select count(*) from complaint")
    int getTouSuNumber();
}
