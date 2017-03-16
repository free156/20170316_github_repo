package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by che2 on 2016/10/24.
 */
public class Client {

    /*public  static  void main(String[] args) throws Exception {


            //对服务端发起连接请求
            Socket socket=new Socket("localhost",9085);
            //接受服务端消息并打印
            InputStream in = socket.getInputStream();
            byte[] b=new byte[1024];
            in.read(b);
            System.out.print(new String(b));

            //给服务端发送响应消息
            OutputStream out = socket.getOutputStream();
            out.write("服务器，我收到了".getBytes());
            in.close();
            out.close();
            socket.close();
//          getCode();

    }*/

//    public static String getCode() {
//        String code = "";
//        for (int i = 0; i < 6; i++) {
//            code += String.valueOf(new Random().nextInt(10));
//            System.out.print(code);
//        }
//        return code;
//    }

   /* public  static void main(String[] args) throws IOException {
        //对服务器发起连接请求
        Socket socket=new Socket("localhost",8888);
        //向服务器写数据
        OutputStream out = socket.getOutputStream();
        out.write("你好，服务器，我是客户端".getBytes());
        out.flush();
        out.close();
//        //接收服务器返回的数据
//        InputStream in = socket.getInputStream();
//        StringBuffer sb=new StringBuffer();
//        byte[] b=new byte[1024];
//        int len=0;
//        while ((len=in.read(b))!=-1){
//            sb.append(new String(b,0,len));
//        }
//        System.out.print(sb.toString());
        socket.close();
//        in.close();
    }*/

    public  static void main(String[] args) throws IOException {
        //对服务器发起请求
        Socket socket=new Socket("localhost",8888);
        //向服务器写数据
        OutputStream out = socket.getOutputStream();
        out.write("我要天猫双十一的电脑的价格".getBytes());
        out.write("e0f".getBytes());
        out.flush();
        //读取服务器的数据
        InputStream in = socket.getInputStream();
        byte[] b=new byte[1025];
        int len=0;
        String temp;
        int index;
        StringBuffer sb=new StringBuffer();
        while ((len=in.read(b))!=-1){
            temp=new String(b,0,len);
            if((index=temp.indexOf("e0f"))!=-1){
                sb.append(temp.substring(0,index));
                break;
            }
            sb.append(temp);
        }
        System.out.println(sb.toString());

        socket.close();
        out.close();
        in.close();
    }


}
