package com.fjjxpjy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjjxpjy.constant.Constant;
import com.fjjxpjy.enums.LoginEnum;
import com.fjjxpjy.service.UserService;
import com.fjjxpjy.utils.VerifyCode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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

    /**
     * @description
     * @author fangjj
     * @date 2020/9/24
     * @params [request, response]
     * @return void
     */
    public void getHead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pic = request.getParameter("pic");
        //完整路径=服务器路径+数据库保存的图片名称
        String realUrl = Constant.LOGIN_PIC + pic;
        File file = new File(realUrl);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while (true) {
            len = bis.read(b);
            if (len == -1) {
                break;
            }
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        bis.close();
    }

    /**
     * @return void
     * @description
     * @author fangjj
     * @date 2020/9/24
     * @params [request, response]
     */
    public void updatePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);

        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 400);
        PrintWriter out = response.getWriter();
        //每个表单域中数据会封装到一个对应的FileItem对象上
        try {
            List<FileItem> items = sfu.parseRequest(request);
            String fileName = "";

            //区分表单域
            for(int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String name = item.getName();

                    // 获取文件名后缀
                    String suffix = name.substring(name.lastIndexOf("."));

                    //获得文件名
                    fileName = UUID.randomUUID().toString() + suffix;
                    // 获取 upload在电脑上真实路径

                    File file = new File( Constant.LOGIN_PIC + fileName);
                    if (!file.exists()) {
                        //将文件写出到指定磁盘
                        item.write(file);
                    }
                }
            }

            String picUrl =  fileName;

            // 修改session中的pic地址
            loginUser.setPic(picUrl);

            session.setAttribute("loginSession",loginUser);
            // 修改数据库中的pic地址
            userService.updateUrl(loginUser.getId(), picUrl);

            //返回前端的数据
            Map<String,String> map = new HashMap<>();
            map.put("code","200");
            map.put("msg",fileName);
            out.write(new ObjectMapper().writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,String> map = new HashMap<>();
            map.put("code","500");
            map.put("msg","失败了");
            out.write(new ObjectMapper().writeValueAsString(map));
        }
    }
    /**
     * @description 获取验证码
     * @author fangjj
     * @date 2020/9/24
     * @params [request, response]
     * @return void
     */
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        String code = verifyCode.getText();

        session.setAttribute(LoginEnum.login_code.getValue(),code);

        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();

    }
}
