package socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by che2 on 2016/11/1.
 */
public class AppServer {

    //客户端读服务端写
    /*public static void main(String[] args) throws Exception {
        //创建一个serversocket对象指定端口监听客户端的连接
        ServerSocket server=new ServerSocket(8888);
        //接受客户端的连接
        Socket socket = server.accept();
        //读取客户端的数据
        Reader reader = new InputStreamReader(socket.getInputStream());
        int len;
        char[] b=new char[64];
        StringBuffer sb=new StringBuffer();
        while ((len=reader.read(b))!=-1){
            sb.append(new String(b,0,len));
        }
        System.out.println(sb.toString());
//        server.close();
        socket.close();
        reader.close();
    }*/

    //客户端和服务端同事读写
    public static  void main(String[] arg) throws IOException {

        //创建一个serversocket对象，指定端口并监听客户端的连接
        ServerSocket server=new ServerSocket(9999);
        while (true) {
            //接受客户端的连接
            Socket socket = server.accept();
            //读客户端的数据
            Reader reader = new InputStreamReader(socket.getInputStream());
            int len = 0;
            char[] b = new char[64];
            StringBuffer sb = new StringBuffer();
            int index;
            String temp;
            while ((len = reader.read(b)) != -1) {
                temp = new String(b, 0, len);
                if ((index = temp.indexOf("end")) != -1) {
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
            }
            System.out.println(sb.toString());

            //向客户端写数据
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write("你想要的书的名字是《三国演义》");
            writer.write("end");
            writer.flush();
            socket.close();
            reader.close();
            writer.close();
        }

    }

}
