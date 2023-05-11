package com.example.cto.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cto.common.Result;
import com.example.cto.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.cto.service.IFilesService;
import com.example.cto.entity.Files;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zc
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/files")
public class FilesController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FilesMapper fileMapper;


    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename=originalFilename.substring(0,file.getOriginalFilename().lastIndexOf("."));
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();


        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile =new File(fileUploadPath);
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);

        String  md5 = SecureUtil.md5(file.getInputStream());
        Files files = getFileMd5(md5);
        //获取文件url
        String url;
        if (files!=null) {
            url=files.getUrl();
            //文件已存在，删除正在上传的重复文件
            uploadFile.delete();
        }else {
            file.transferTo(uploadFile);
            url=  "http://localhost:8081/files/" + fileUUID;

        }

        // 存储数据库
        Files saveFile = new Files();
        saveFile.setName(filename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
//
        return url;
    }

    @GetMapping("{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        java.io.File uploadFile=new java.io.File(fileUploadPath+fileUUID);
        ServletOutputStream os=response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
    }

    /**
     *  查询md5是否存在
     * @param md5
     * @return
     */
    private Files getFileMd5(String md5){

        QueryWrapper<Files> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }


//    @GetMapping("/page")
//    public Result findPage(@RequestParam Integer pageNum,
//                           @RequestParam Integer pageSize,
//                           @RequestParam(defaultValue = "") String name) {
//
//        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("is_delete",false);
//
//        if (!"".equals(name)) {
//            queryWrapper.like("name", name);
//        }
//
//        queryWrapper.orderByDesc("id");
//        return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
//    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = fileMapper.selectById(id);
        files.setIsDelete(true);
        fileMapper.updateById(files);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files files1:files){
            files1.setIsDelete(true);
            fileMapper.updateById(files1);
        }
        return Result.success();
    }

    // 新增或者更新
    @PostMapping("/update")
    public Result update(@RequestBody Files files) {
        return Result.success(fileMapper.updateById(files));
    }

}

