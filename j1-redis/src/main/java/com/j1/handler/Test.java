package com.j1.handler;


import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by che2 on 2016/9/9.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        new Test().list();


    }

    public void list() {


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("application", "10000 ");
        map.put("timestamp", 100);
        map.put("account", "devin");
        map.put("digest", "");
        map.put("vendorCode", "100");
        String back = HttpRequestProxy.doPost("http://supplier.nevint.com/portal/token", map, "UTF-8");
        System.out.print(back);
        if(back==null || "".equals(back))  {
            System.out.print("返回数据为空");
        }
        JSONObject jsonObject = JSONObject.fromObject(back);
        if(!jsonObject.getString("errorCode").equals("0000")){
            System.out.print("返回的数据不可用");
        }

        String resultData = jsonObject.getString("resultData");
        if ("".equals(resultData)&&resultData!=null){
            Entity entity=new Entity();
            entity.setResultData(resultData);
        }


    }

}
