package com.fjjxpjy.controller;

import com.fjjxpjy.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author fangjj
 * @date 2020/9/24
 * @description
 */
@WebServlet("/img/*")
public class ImgController extends BaseController {
    private UserService userService =new UserService();

    public void getHead(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        String pic = request.getParameter("pic");
        pic = pic.replace("//", "\\\\");
        FileInputStream fis = new FileInputStream(pic);
        try {
            OutputStream os = response.getOutputStream();
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @description
     * @author fangjj
     * @date 2020/9/24
     * @params [request, response]
     */
    public void updatePic(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);

        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 400);

        //每个表单域中数据会封装到一个对应的FileItem对象上
        try {
            List<FileItem> items = sfu.parseRequest(request);
            String fileName = "";

            //区分表单域
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String name = item.getName();

                    // 获取文件名后缀
                    String suffix = name.substring(name.lastIndexOf("."));

                    //获得文件名
                    fileName = UUID.randomUUID().toString() + suffix;
                    // 获取 upload在电脑上真实路径

                    File file = new File( "d:\\test\\" + fileName);
                    if (!file.exists()) {
                        //将文件写出到指定磁盘
                        item.write(file);
                    }
                }
            }

            String picUrl = "d:\\test\\" + fileName;

            // 修改session中的pic地址
            loginUser.setPic(picUrl);

            // 修改数据库中的pic地址
            userService.updateUrl(loginUser.getId(), picUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
