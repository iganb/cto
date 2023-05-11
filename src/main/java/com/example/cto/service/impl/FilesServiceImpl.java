package com.example.cto.service.impl;

import com.example.cto.entity.Files;
import com.example.cto.mapper.FilesMapper;
import com.example.cto.service.IFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements IFilesService {

}
