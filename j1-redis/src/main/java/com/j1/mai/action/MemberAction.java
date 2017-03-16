package com.j1.mai.action;

//import com.j1.mai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by che2 on 2016/9/7.
 */
@Controller
//@RequestMapping("/main")
public class MemberAction {

//    @Autowired
//    private UserService userService;
    @RequestMapping("/init")
      public void main(String name,Integer age){
//        userService.init(name,age);
        System.out.println("姓名========:"+name);
        System.out.println("年龄========:"+age);
    }

    @RequestMapping("/upload")
    public void upload(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ServletInputStream in = request.getInputStream();
        DataInputStream dis = new DataInputStream(in);



//       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获得文件
//        MultipartFile multipartFile = multipartRequest.getFile("file");
        //获得文件名
//        String filename = multipartFile.getOriginalFilename();

        byte[] b=new byte[1024];
        int len=0;
        StringBuffer sb=new StringBuffer();
        while ((len=dis.read(b,0,len))!=-1){
            sb.append(new String(b,0,len));
        }

        System.out.println(sb);
    }

}
