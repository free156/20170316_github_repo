package socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by che2 on 2016/11/1.
 */
public class AppClient {

    //客户端读服务端写
    /*public static void main(String[] args) throws Exception {

        //连接服务端
        Socket socket = new Socket("localhost", 8888);
        //向服务端写数据

        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("你好，服务器，我是客户端诶");
        writer.flush();
        socket.close();
        writer.close();
    }*/

    //客户端和服务端同事读写
    public static  void main(String[] arg) throws Exception {

        //连接服务端的连接
        Socket socket=new Socket("localhost",9999);
        //向服务端写数据
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("你好，服务端，我想找一本有诸葛亮和曹操打仗的书");
        writer.write("end");
        writer.flush();
        //读取服务端的数据
        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        int len=0;
        char[] b=new char[64];
        StringBuffer sb=new StringBuffer();
        int index;
        String temp;
        while((len=reader.read(b))!=-1){
            temp=new String(b,0,len);
            if((index=temp.indexOf("end"))!=-1){
                sb.append(temp.substring(0,index));
                break;
            }
            sb.append(temp);
        }
        System.out.println(sb.toString());
        socket.close();
        writer.close();
        reader.close();
    }


}
