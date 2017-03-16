package DoPostTest;

import com.j1.handler.Entity;
import com.j1.handler.HttpRequestProxy;
import com.j1.handler.JsonResponse;
import com.j1.mai.Entity.AsnEntity;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by che2 on 2016/9/13.
 */
public class DoPost {
    private static final Logger LOG = Logger.getLogger(DoPost.class);
    public static void main(String[] args) throws Exception{
        new DoPost().list();


    }

    public void list() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("asnId",1111);
        param.put("asnNo","2222");
        param.put("asnDateStr","3333");


        String back = HttpRequestProxy.doPost("http://localhost:8088/main/detail.html",param , "UTF-8");
        LOG.debug("返回的数据为==========="+back);
        if(back==null || "".equals(back))  {
            System.out.print("返回数据为空");
        }
        JSONObject jsonObject = JSONObject.fromObject(back);
        if(!jsonObject.getString("status").equals("0")){
            System.out.print("返回的数据不可用");
        }

        String resultData = jsonObject.getString("data");
        if (!"".equals(resultData)&&resultData!=null){
            AsnEntity asnEntity = new AsnEntity();
            JSONObject data = JSONObject.fromObject(resultData);
            JSONObject asnEntity2 = data.getJSONObject("asnEntity");
            AsnEntity asnEntity1 = (AsnEntity)(JSONObject.toBean(asnEntity2, AsnEntity.class));
            System.out.print(asnEntity1.toString());
            long asnId = asnEntity2.getLong("asnId");
            asnEntity.setAsnId(asnId);
            asnEntity.setAsnNo(asnEntity2.getString("asnNo") == null ? null : asnEntity2.getString("asnNo"));
//            asnEntity.setAsnDateStr(asnEntity2.getString("asnDateStr") == null ? null : asnEntity2.getString("asnDateStr"));
            System.out.print(asnEntity.toString());
        }


    }
}
