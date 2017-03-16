package nextevRestfulTest;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by che2 on 2016/11/10.
 */
public class GetRFQPartBasicListTest {

    public static void main(String[] args){
        getRFQPartBasicListTest();
    }

    private static void getRFQPartBasicListTest() {
        try {
            URL url = new URL("http://apiportaluat.nextev.com:6001/rfq/getRFQPartBasicList");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setRequestProperty("Content-type", "application/json");
            urlConn.setRequestMethod("POST");
            urlConn.connect();

            JSONObject jb=new JSONObject();
//            jb.put("Content-type","application/json; charset=utf-8");
//            jb.put("Token","");
//            jb.put("BizData","{\"VendorUUID\":\"NEXTEVSUPPLIER\",\"ActionPlanNumber\":\"REC-ES8010003\",\"Round\":\"1\",\"PartNo\":\"\",\"PlantId\":\"1\"}");
            String str="{\"Token\":\"\",\"BizData\":{\"VendorUUID\":\"NEXTEVSUPPLIER\",\"ActionPlanNumber\":\"REC-ES8010003\",\"Round\":\"1\",\"PartNo\":\"\",\"PlantId\":\"1\"}}";
            urlConn.getOutputStream().write(str.getBytes());
            urlConn.getOutputStream().flush();
//            InputStream in = urlConn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8")) ;
            String line=br.readLine();
            StringBuffer sb=new StringBuffer();
            while (line!=null){
                sb.append(line);
               line= br.readLine();
            }
            System.out.print(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
