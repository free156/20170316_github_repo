package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by che2 on 2016/10/24.
 */
public class Server {

    /*public  static  void main (String[] args) throws Exception {



            //构造serversocket实例，指定端口监听客户端的连接请求
            ServerSocket serverSocket=new ServerSocket(9085);
            //建立连接
            Socket socket = serverSocket.accept();
            //向客户端发送消息
            OutputStream os = socket.getOutputStream();
            os.write("你好，我是服务器".getBytes());
            InputStream is = socket.getInputStream();
            //接受客户端的响应
            byte[] b=new byte[1024];
            int len=0;
            while ((len=is.read(b))!=-1){
                System.out.print(new String(b,0,len));
            }
//            is.read(b);
//            System.out.print(new String(b));
          *//*  is.read(b);
            System.out.print(new String(b));*//*
            os.close();
            is.close();
            socket.close();
            serverSocket.close();


    }*/

    /*public  static  void main(String[] args) throws Exception {
        //创建一个serverSocket实例，指定端口监听客户端的连接请求
        ServerSocket serverSocket=new ServerSocket(8888);
        //建立连接
        Socket accept = serverSocket.accept();
//        // 向客户端发送消息
//        OutputStream out = accept.getOutputStream();
//        out.write("客户端我收到了你的消息".getBytes());
//        out.flush();
//        out.close();

        //建立连接后我们就可以获取socket的Inputstream，从中读取数据
        InputStream in = accept.getInputStream();
        StringBuffer sb=new StringBuffer();
        byte[] b=new byte[1024];
        int len=0;
        while((len=in.read(b))!=-1){
           sb.append(new String(b,0,len));
        }
        System.out.print(sb.toString());


        serverSocket.close();
        accept.close();
        in.close();

    }*/

    //客户端和服务端同时读写

    /*public static void main(String[] args) throws Exception {
        //创建一个serverSocket实例，指定端口监听客户端的链接
        ServerSocket server = new ServerSocket(8888);
        //和客户端建立连接
        while (true) {
            Socket socket = server.accept();
            //读取客户端发过来的消息
            InputStream in = socket.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            String temp;
            int index;
            StringBuffer sb = new StringBuffer();
            while ((len = in.read(b)) != -1) {
//            sb.append(new String(b, 0, len));
                temp = new String(b, 0, len);
                if ((index = temp.indexOf("e0f")) != -1) {
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
            }
            System.out.println(sb.toString());
            //向客户端写数据
            OutputStream out = socket.getOutputStream();
            out.write("你好客户端，我收到了你消息，你要的我资料我给你了".getBytes());
            out.write("e0f".getBytes());
            out.flush();
            out.close();
//            server.close();
            socket.close();
            in.close();
        }
    }*/

    public  static  void main(String[] args) throws Exception {
        //创建一个serversocket实例,指定端口监听客户端的链接
        ServerSocket server=new ServerSocket(8888);
        while (true){
            //接受客户端的请求
            Socket socket = server.accept();
            //每次客户端请求都创建一个新的线程处理
            new Thread(new Task(socket)).start();//使该线程开始执行；Java 虚拟机调用该线程的 run 方法。

        }

    }

    static  class Task implements Runnable{

        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        public void run() {

            try {
                handleSocket();
            } catch (IOException e) {

            }
        }

        private void handleSocket() throws IOException {
            //读取客户端发送消息
            InputStream in = socket.getInputStream();
            byte[] b=new byte[1024];
            StringBuffer sb=new StringBuffer();
            int len;
            int index;
            String temp;
            while ((len=in.read(b))!=-1){
                temp=new String(b,0,len);
                if ((index=temp.indexOf("e0f"))!=-1){
                    sb.append(temp.substring(0,index));
                    break;
                }
                sb.append(temp);
            }
            System.out.println(sb);

            //给客户端返回消息
            OutputStream out = socket.getOutputStream();
            out.write("你好客户端，我收到了你消息，你要的我资料我给你了".getBytes());
            out.flush();
            out.close();
            socket.close();
            in.close();
        }
    }

}
