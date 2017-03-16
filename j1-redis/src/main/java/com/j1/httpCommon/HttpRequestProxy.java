package com.j1.httpCommon;

import net.sf.json.JSONObject;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by che2 on 2016/11/10.
 */
public class HttpRequestProxy {

    public static void main(String[] args){
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        String reqUrl="http://localhost:9080/nextev-webservice/detail.do";
//        map.put("asnId",1111);
//        map.put("asnNo","bbb");
//        map.put("asnDateStr","bbb");
//        dopost(reqUrl,map,"UTF-8");
        JSONObject json=new JSONObject();
        json.put("Token","");
        json.put("BizData","{\"VendorUUID\":\"NEXTEVSUPPLIER\",\"ActionPlanNumber\":\"REC-ES8010003\",\"Round\":\"1\",\"PartNo\":\"\",\"PlantId\":\"1\"}");
        String reqUrl="http://apiportaluat.nextev.com:6001/rfq/getRFQPartBasicList";
        doPostJson(reqUrl, json,"UTF-8");


    }

    public static void dopost(String reqUrl,Map paramaters,String recvEncoding){

        StringBuffer sb=new StringBuffer();

        URL url=null;

        //拼装请求参数
        try {
            for(Iterator iter =paramaters.entrySet().iterator();iter.hasNext();){
                Map.Entry element = (Map.Entry) iter.next();
                if(element.getValue()==null||"".equals(element.getValue())){
                    continue;//跳过本次循环，进行下次循环
                }
                sb.append(element.getKey().toString());
                sb.append("=");
                sb.append(URLEncoder.encode(element.getValue().toString(), "UTf-8"));//当参数中有汉字
//                sb.append(element.getValue());
                sb.append("&");
            }
            if(sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
            }
            System.out.println(sb);

            url=new URL(reqUrl);
            HttpURLConnection url_con= (HttpURLConnection) url.openConnection();
            url_con.setDoOutput(true);
//            url_con.setDoInput(true);
            url_con.setConnectTimeout(30000);
            url_con.setReadTimeout(30000);
            url_con.setRequestMethod("POST");
//            url_con.setRequestProperty("Content-type", "application/octet-stream");
            OutputStream out = url_con.getOutputStream();
            out.write(sb.toString().getBytes());
            out.flush();
            out.close();
            InputStream inputStream = url_con.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = in.readLine();
            StringBuffer sbuf=new StringBuffer();
            while(line!=null){
                sbuf.append(line);
                line=in.readLine();
            }
            System.out.println(sbuf);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void doPostJson(String reqUrl,JSONObject json,String recvEncoding){

        URL url=null;
        try {
            url=new URL(reqUrl);
            url=new URL(reqUrl);
            HttpURLConnection url_con= (HttpURLConnection) url.openConnection();
            url_con.setDoOutput(true);
            url_con.setDoInput(true);
            url_con.setRequestMethod("POST");
            url_con.setRequestProperty("Content-type", "application/json");
            OutputStream out = url_con.getOutputStream();
            out.write(json.toString().getBytes());
            out.flush();
            out.close();
            InputStream inputStream = url_con.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = in.readLine();
            StringBuffer sbuf=new StringBuffer();
            while(line!=null){
                sbuf.append(line);
                line=in.readLine();
            }
            System.out.println(sbuf);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
