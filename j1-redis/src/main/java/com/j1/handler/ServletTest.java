package com.j1.handler;

import com.j1.mai.Entity.AsnEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * Created by che2 on 2016/9/13.
 */
@Controller
@RequestMapping("/index")
public class ServletTest {

    @RequestMapping("/test")
    public void easy(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        ServletInputStream inputStream = request.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        AsnEntity asnEntity=(AsnEntity)(objectInputStream.readObject());
    }

}
