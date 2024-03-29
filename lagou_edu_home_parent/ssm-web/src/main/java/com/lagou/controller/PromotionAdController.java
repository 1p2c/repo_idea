package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.PromotionAd;
import com.lagou.damain.PromotionAdVo;
import com.lagou.damain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage( PromotionAdVo  promotionAdVo){

        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", allPromotionAdByPage);
        return  responseResult;
    }

    /**
     * 图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断 接受到的上传文件是否为空
        if (file.isEmpty()){
            throw  new RuntimeException();
        }
        //2.获取项目的部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String webappsPath  = realPath.substring(0, realPath.indexOf("ssm_web"));
        //3.获取原文件名
        //ll.jsp
        String filename = file.getOriginalFilename();

        //4.生成新文件名
        //234.jpg
        String newFileName= System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        //5.文件上传
        //D:.../apache-tomcat-8.5.56\...\ upload
        String uploadPath = webappsPath+"upload\\";
        File filePath=new File(uploadPath,newFileName);

        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录："+filePath);
        }

        //图片进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名和路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }
    /**
     * 广告动态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true,200,"广告动态上下线成功",null);
    }
}
