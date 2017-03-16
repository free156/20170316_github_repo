package com.j1.mai.action;

import com.j1.mai.Entity.AsnEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by che2 on 2016/9/12.
 */
@Controller
@RequestMapping("/main")
public class AsnQueryAction {

//    @Autowired
//    private AsnQueryService asnQueryService;

    @RequestMapping("/asnQuery")
    public void asnQuery(HttpServletRequest request,
                         HttpServletResponse response,
            AsnEntity asnEntity) throws IOException, ClassNotFoundException {
//       AsnEntity asnEntity =asnQueryService.sanQuery();
        InputStream in = request.getInputStream();

        ObjectInputStream ois = new ObjectInputStream(in);
        AsnEntity asnEntity1=(AsnEntity)(ois.readObject());
        System.out.print(asnEntity1.getAsnId());
    }


}
