package io;

import java.io.*;

/**
 * Created by che2 on 2016/10/17.
 */
public class FileStream {

    public static void main (String[] arg) throws Exception {
//        File file= new File("C:\\Dve_Lib\\1.txt");
//        File file1= new File("C:\\Dve_Lib\\2.txt");
        outWrite();
//        String str="成熟不是岁数，成熟时礼物";
//        FileOutputStream out=new FileOutputStream(file);
//        out.write(str.getBytes());
//        FileWriter out=new FileWriter(file);
//        out.write(str);
//        FileInputStream in=new FileInputStream(file);
//        byte[] buf=new byte[1024];
//        int len= 0;
//        while ((len=in.read(buf))>0){
//            System.out.println(new String(buf,0,len));
//        }
//        in.close();
//        FileWriter out=new FileWriter(file1);
//        FileReader in=new FileReader(file);
//        char[] buf=new char[32];
//        int len=0;
//        while((len=in.read(buf))!=-1){
//            System.out.println(new String(buf,0,len));
//            out.write(buf,0,len);
//        }
//        in.close();
//        out.close();

    }

    public static void fileByteSteam() throws Exception {
        FileInputStream in=new FileInputStream("C:\\Dve_Lib\\1.txt");
        FileOutputStream out =new FileOutputStream("C:\\Dve_Lib\\1.txt");
        String str="我是一只小小鸟，想飞却怎么也飞不高";
//        out.write(str.getBytes());
//        out.close();
        byte[] buf=new byte[10];
        int len=0;
        while((len=in.read(buf))!=-1){
            System.out.print(new String(buf,0,len));
        }
        in.close();
    }

    public static void fileByteSteam1() throws Exception {
        FileInputStream in=new FileInputStream("C:\\Dve_Lib\\1.txt");
//        FileOutputStream out =new FileOutputStream("C:\\Dve_Lib\\1.txt");
//        String str="我是一只小小鸟，想飞却怎么也飞不高";
//        out.write(str.getBytes());
//        out.close();
        byte[] buf=new byte[10];
        int len=in.read(buf);
        while(len!=-1){
            System.out.println(new String(buf,0,len));
            len=in.read(buf);
        }
        in.close();
    }


    public static void fileStringStream() throws Exception {
        FileWriter out=new FileWriter("C:\\Dve_Lib\\2.txt");
        String str="当你在穿山越岭的另一边，我在孤独的路上没有尽头,一辈子有多少的来不及发现已经失去最重要的东西";
//        out.write(str);
//        out.write(str,0,11);
//        out.write( str.toCharArray());
        out.write(str.toCharArray(),0,20);
        out.close();
    }

    public static  void fileInputStream() throws Exception {
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\2.txt");
        byte[] buf=new byte[1024];
        int len=0;
        while ((len=in.read(buf))!=-1){
            System.out.print(new String(buf,0,len));
        }
        in.close();
    }

    public  static  void fileReader() throws Exception {
        FileReader in=new FileReader("C:\\Dve_Lib\\2.txt");
        char[] buf=new char[100];
        int len=0;
        while ((len=in.read(buf))!=-1){
            System.out.print(new String(buf,0,len));
        }
        in.close();
    }

    //带缓存的输出流和带缓存的输入流
    public  static void bufferInputStream()  {
        try {
            FileReader in =new FileReader("C:\\Dve_Lib\\2.txt");
            BufferedReader bufferin=new BufferedReader(in);
            String crlf=System.getProperty("line.separator");
            StringBuffer tempStr = new StringBuffer();
            String s=bufferin.readLine();
            while (s!=null){
                System.out.println(s);
                tempStr.append(s);
                tempStr.append(crlf);
                s=bufferin.readLine();
            }
            in.close();
            bufferin.close();

            //缓存输出流写文件
            FileWriter out =new FileWriter("C:\\Dve_Lib\\1.txt");
            BufferedWriter bufferedWriter=new BufferedWriter(out);
            bufferedWriter.write(tempStr.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  static void bufferInputStream1()  {
        try {
            FileReader in =new FileReader("C:\\Dve_Lib\\2.txt");
            BufferedReader bufferin=new BufferedReader(in);
            String crlf=System.getProperty("line.separator");
            StringBuffer tempStr = new StringBuffer();
            String s="";
            while ((s=bufferin.readLine())!=null){
                System.out.println(s);
                tempStr.append(s);
                tempStr.append(crlf);
            }
            in.close();
            bufferin.close();

            //缓存流写的操作
            FileWriter out=new FileWriter("C:\\Dve_Lib\\1.txt");
            BufferedWriter bufferedWriter=new BufferedWriter(out);
            bufferedWriter.write(tempStr.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  static void bufferInputStream2()  {
        try {
            FileReader in =new FileReader("C:\\Dve_Lib\\2.txt");


            //缓存流写的操作
            FileWriter out =new FileWriter("C:\\Dve_Lib\\1.txt");
            char[] b=new char[10];
            int len=in.read(b);
            while (len!=-1){
                out.write(b);
                len=in.read(b);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readFile() throws Exception {
        try {
            FileInputStream in =new FileInputStream("C:\\Dve_Lib\\1.txt");
            byte[] b=new byte[10];
            int len=0;
            while((len=in.read(b))!=-1){
                System.out.println(new String(b,0,len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readAndWrite() throws Exception {
        //读
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\1.txt");
        FileOutputStream out =new FileOutputStream("C:\\Dve_Lib\\2.txt");
        //写
        byte[] b=new byte[10];
        int len=0;
        while ((len=in.read(b))!=-1){
            out.write(b);
        }
        in.close();
        out.close();
    }

    //输入流读数据的三种方法
    public static void readData() throws Exception {
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\1.txt");
        int[] b=new int[1024];
        int len=0;
        int i=0;
        while ((len=in.read())!=-1){
          b[i++]=len;
        }
        System.out.print(new String(b, 0, b.length));
    }


    public static void readData1() throws Exception {
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\1.txt");
        byte[] b=new byte[10];
        int len=0;
        while((len=in.read(b,0,b.length))!=-1){
            System.out.print(new String(b,0,len));
        }
    }

    public static void readData2() throws Exception {
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\1.txt");
        byte[] b=new byte[10];
        int len=0;
        while((len=in.read(b))!=-1){
            System.out.print(new String(b,0,len));
        }
    }

    public static void writeData() throws Exception {
        FileOutputStream out = new FileOutputStream("C:\\Dve_Lib\\2.txt");
        String crlf=System.getProperty("line.separator");
        String str="生活不止眼前的苟且，\n" +crlf+
                "还有诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间，\n" +crlf+
                "因为没人能看见我的眼泪。" +crlf+
                "诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间";
        out.write(str.getBytes());
        out.close();

    }

    public static void writeData1() throws Exception {
        FileOutputStream out = new FileOutputStream("C:\\Dve_Lib\\2.txt");
        String crlf=System.getProperty("line.separator");
        String str="生活不止眼前的苟且，\n" +crlf+
                "还有诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间，\n" +crlf+
                "因为没人能看见我的眼泪。" +crlf+
                "诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间";
       out.write(str.getBytes(),0,20);
        out.close();

    }


    public static void readAndWrite1() throws Exception {
        //读文件数据
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\1.txt");
        //写数据
        FileOutputStream out = new FileOutputStream("C:\\Dve_Lib\\2.txt");
        byte[] b=new byte[100];
        int len=0;
        while ((len=in.read(b))!=-1){
//            out.write(b,0,len);
            out.write(b);
        }
        in.close();
        out.close();
    }

    public static void writeAndread() throws Exception {
        FileInputStream in =new FileInputStream("C:\\Dve_Lib\\2.txt");
        FileOutputStream out = new FileOutputStream("C:\\Dve_Lib\\2.txt");
        String crlf=System.getProperty("line.separator");
        String str="生活不止眼前的苟且，\n" +crlf+
                "还有诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间，\n" +crlf+
                "因为没人能看见我的眼泪。\n" +crlf+
                "诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间";
        out.write(str.getBytes());
        byte[] b=new byte[50];
        int len=0;
        while ((len=in.read(b))!=-1){
           System.out.print(new String(b, 0, len));
        }
        in.close();
        out.close();
    }

    public static void outWrite() throws Exception {
        FileWriter out=new FileWriter("C:\\Dve_Lib\\2.txt");
        String crlf=System.getProperty("line.separator");
        String str="生活不止眼前的苟且，\n" +crlf+
                "还有诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间，\n" +crlf+
                "因为没人能看见我的眼泪。\n" +crlf+
                "诗和远方的田野。\n" +crlf+
                "雨天是我放声哭泣的时间......";
        out.write(str.toCharArray(),0,19);
        out.close();
    }

}
