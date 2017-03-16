package com.j1.mai.action;

import com.j1.mai.Entity.AsnEntity;
import com.j1.mai.service.AsnQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by che2 on 2016/9/12.
 */
@Controller
public class ConverterAction {

    @Autowired
    private AsnQueryService AsnQueryService;

    @RequestMapping("/converterTest")
    @ResponseBody
    public AsnEntity asnEntityToJson(HttpServletRequest request,HttpServletResponse Response){

        AsnEntity asnEntity = AsnQueryService.sanQuery(191);


        return asnEntity;
    }


//    @RequestMapping("/converterTest")
    @ResponseBody
    public AsnEntity saveAsnEntity(HttpServletRequest request,HttpServletResponse Response,@RequestBody AsnEntity asnEntity){

//        AsnEntity asnEntity = AsnQueryService.sanQuery(191);


        return asnEntity;
    }



    private AsnEntity makeDate(){
        AsnEntity asnEntity=new AsnEntity();
        asnEntity.setAsnId(111);
        asnEntity.setAsnNo("aaaa");
//        asnEntity.setAsnDateStr("bbbb");
        return  asnEntity;
    }
}
