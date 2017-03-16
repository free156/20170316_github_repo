package com.j1.handler;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequestProxy
{
   
    private static int connectTimeOut = 10000;

 
    private static int readTimeOut = 10000;

  
    private static String requestEncoding = "UTF-8";

    public static String doGet(String reqUrl,String requestEncoding,String recvEncoding){
    	HttpRequestProxy.requestEncoding = requestEncoding;
    	return doGet(reqUrl,recvEncoding);
    }
    public static String doPost(String reqUrl, @SuppressWarnings("rawtypes") Map parameters,String requestEncoding, String recvEncoding){
    	HttpRequestProxy.requestEncoding = requestEncoding;
    	return doPost( reqUrl,parameters,recvEncoding);
    }
    @SuppressWarnings("rawtypes")
	public static String doGet(String reqUrl, Map parameters,
            String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        InputStream in = null;
        StringBuffer params = new StringBuffer();
        try
        {
            for (Iterator iter = parameters.entrySet().iterator(); iter
                    .hasNext();)
            {
                Entry element = (Entry) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        HttpRequestProxy.requestEncoding));
                params.append("&");
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout", String
                    .valueOf(HttpRequestProxy.connectTimeOut));
            System.setProperty("sun.net.client.defaultReadTimeout", String
                    .valueOf(HttpRequestProxy.readTimeOut));
           
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            in = url_con.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
//                    recvEncoding));
//            String tempLine = rd.readLine();
//            StringBuffer temp = new StringBuffer();
//            String crlf=System.getProperty("line.separator");
//            while (tempLine != null)
//            {
//                temp.append(tempLine);
//                temp.append(crlf);
//                tempLine = rd.readLine();
//            }
//            responseContent = temp.toString();
//            rd.close();
  
            responseContent = readInputStream(in,recvEncoding );
         
        }
        catch (Exception e)
        {
        	logException(url_con, in, recvEncoding, reqUrl, params.toString(), e);
        	e.printStackTrace();
        }
        finally
        {
        	logException(url_con, in, recvEncoding, reqUrl, params.toString());
        	if (in != null) {
        		try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        	}
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    /**
   
     */
    public static String doGet(String reqUrl, String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        InputStream in = null;
        String queryUrl = reqUrl;
        StringBuffer params = new StringBuffer();
        try
        {
            
            int paramIndex = reqUrl.indexOf("?");

            if (paramIndex > 0)
            {
                queryUrl = reqUrl.substring(0, paramIndex);
                String parameters = reqUrl.substring(paramIndex + 1, reqUrl
                        .length());
                String[] paramArray = parameters.split("&");
                for (int i = 0; i < paramArray.length; i++)
                {
                    String string = paramArray[i];
                    int index = string.indexOf("=");
                    if (index > 0)
                    {
                        String parameter = string.substring(0, index);
                        String value = string.substring(index + 1, string
                                .length());
                        params.append(parameter);
                        params.append("=");
                        params.append(URLEncoder.encode(value,
                                HttpRequestProxy.requestEncoding));
                        params.append("&");
                    }
                }

                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(queryUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout", String
                    .valueOf(HttpRequestProxy.connectTimeOut));//
            System.setProperty("sun.net.client.defaultReadTimeout", String
                    .valueOf(HttpRequestProxy.readTimeOut)); // 
            
//            App.logger.info("HttpRequestProxy url:"+url+" params:" + params);
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();
            in = url_con.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
//                    recvEncoding));
//            String tempLine = rd.readLine();
//            StringBuffer temp = new StringBuffer();
//            String crlf=System.getProperty("line.separator");
//            while (tempLine != null)
//            {
//                temp.append(tempLine);
//                temp.append(crlf);
//                tempLine = rd.readLine();
//            }
//            responseContent = temp.toString();
//            rd.close();
            responseContent = readInputStream(in,recvEncoding );
          
        }
        catch (Exception e)
        {
        	logException(url_con, in, recvEncoding, queryUrl, params.toString(), e);
        	e.printStackTrace();
        }
        finally
        {
        	logException(url_con, in, recvEncoding, queryUrl, params.toString());
        	if (in != null) {
        		try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        	}
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    /**

     
     */
    public static String doPost(String reqUrl, @SuppressWarnings("rawtypes") Map parameters,
            String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        URL url = null;
        StringBuffer params = new StringBuffer();
        InputStream in = null;
        try
        {
            
            for (@SuppressWarnings("rawtypes")
			Iterator iter = parameters.entrySet().iterator(); iter
                    .hasNext();)
            {
                @SuppressWarnings("rawtypes")
				Entry element = (Entry) iter.next();
                if (element.getValue() == null || "".equals(element.getValue())) {
                	continue;
                }
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        HttpRequestProxy.requestEncoding));
                params.append("&");
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            url = new URL(reqUrl);
            
//            App.logger.info("HttpRequestProxy 请求地址 url:"+url+"?" + params);
            
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout", String
                    .valueOf(10000000));
            System.setProperty("sun.net.client.defaultReadTimeout", String
                    .valueOf(10000000)); 
          
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();
            in = url_con.getInputStream();
            
//            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
//                    recvEncoding));
//            String tempLine = rd.readLine();
//            StringBuffer tempStr = new StringBuffer();
//            String crlf=System.getProperty("line.separator");
//            while (tempLine != null)
//            {
//                tempStr.append(tempLine);
//                tempStr.append(crlf);
//                tempLine = rd.readLine();
//            }
            responseContent = readInputStream(in,recvEncoding );
//            rd.close();
        }
        catch (Exception e)
        {
//        	int status = 800;
//        	String content = "";
//			try {
//				status = url_con.getResponseCode();
//				content = readInputStream(in,recvEncoding );
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//        	App.logger.error( String.format("url: %s status: %d params: %s response : %s", url, status , params.toString(), content), e);
        	
        	logException(url_con, in, recvEncoding, url.toString(), params.toString(), e);
        	e.printStackTrace();
        }
        finally  {
        	logException(url_con, in, recvEncoding, url.toString(), params.toString());
        	if (in != null) {
        		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        	}
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return responseContent;
    }
    
    private static void logException(HttpURLConnection url_con, InputStream in, String recvEncoding, String url, String params, Exception e) {
    	int status = 800;
    	String content = "";
		try {
			status = url_con.getResponseCode();
			content = readInputStream(in,recvEncoding );
//			App.logger.error( String.format("HttpRequestProxy url: %s status: %d params: %s response : %s", url, status , params.toString(), content), e);
		} catch (Exception e1) {
//			e1.printStackTrace();
//			App.logger.error(String.format("HttpRequestProxy url: %s status: %d params: %s response : %s", url, status , params.toString(), content), e1);
		}
    	
    }
    private static void logException(HttpURLConnection url_con, InputStream in, String recvEncoding, String url, String params) {
    	try {
			int status = url_con.getResponseCode();
			if(status == 200) return;
			String content = readInputStream(in,recvEncoding );
//			App.logger.error( String.format("HttpRequestProxy url: %s status: %d params: %s response : %s", url, status , params.toString(), content));
		} catch (Exception e1) {
//			e1.printStackTrace();
//			App.logger.error(String.format("HttpRequestProxy url: %s",url)+e1);
		}
    	
    }
    private static String readInputStream(InputStream in, String recvEncoding) throws Exception {
    	BufferedReader rd = null;
    	try {
    		rd = new BufferedReader(new InputStreamReader(in,
        			recvEncoding));
    		 String tempLine = rd.readLine();
             StringBuffer tempStr = new StringBuffer();
             String crlf=System.getProperty("line.separator");
             while (tempLine != null)  {
                 tempStr.append(tempLine);
                 tempStr.append(crlf);
                 tempLine = rd.readLine();
             }
             return tempStr.toString();
    	} catch (Exception e) {
    		throw e;
    	} finally {
    		if (rd != null) {
    			rd.close();
    		}
    	}
    }
    
    
    public static String doPostObject(String reqUrl, Object obj) {
    	return doPostObject( reqUrl, obj, "UTF-8");
    }
    public static String doPostObject(String reqUrl, Object obj, String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        InputStream in = null;
        try
        {
//            StringBuffer params = new StringBuffer();
//            for (@SuppressWarnings("rawtypes")
//			Iterator iter = parameters.entrySet().iterator(); iter
//                    .hasNext();)
//            {
//                @SuppressWarnings("rawtypes")
//				Entry element = (Entry) iter.next();
//                params.append(element.getKey().toString());
//                params.append("=");
//                params.append(URLEncoder.encode(element.getValue().toString(),
//                        HttpRequestProxy.requestEncoding));
//                params.append("&");
//            }
//
//            if (params.length() > 0)
//            {
//                params = params.deleteCharAt(params.length() - 1);
//            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout", String
                    .valueOf(10000000));
            System.setProperty("sun.net.client.defaultReadTimeout", String
                    .valueOf(10000000));
            url_con.setDoOutput(true);
//            byte[] b = params.toString().getBytes();
//            App.logger.info("HttpRequestProxy url:"+url);
            ObjectOutputStream ojs = new ObjectOutputStream(url_con.getOutputStream());
            ojs.writeObject(obj);
            ojs.flush();
            ojs.close();
//            url_con.getOutputStream().write(b, 0, b.length);
//            url_con.getOutputStream().flush();
//            url_con.getOutputStream().close();

           in = url_con.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
//                    recvEncoding));
//            String tempLine = rd.readLine();
//            StringBuffer tempStr = new StringBuffer();
//            String crlf=System.getProperty("line.separator");
//            while (tempLine != null)
//            {
//                tempStr.append(tempLine);
//                tempStr.append(crlf);
//                tempLine = rd.readLine();
//            }
//            responseContent = tempStr.toString();
//            rd.close();
           responseContent = readInputStream(in,recvEncoding );
           
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	logException(url_con, in, recvEncoding, reqUrl, "", e);
        }
        finally
        {
        	if (in != null) {
        		 try {
					in.close();
					in = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

    /**
  
     */
    public static int getConnectTimeOut()
    {
        return HttpRequestProxy.connectTimeOut;
    }

    /**
     
     */
    public static int getReadTimeOut()
    {
        return HttpRequestProxy.readTimeOut;
    }

    /**
     
     */
    public static String getRequestEncoding()
    {
        return requestEncoding;
    }

    /**
  
     */
    public static void setConnectTimeOut(int connectTimeOut)
    {
        HttpRequestProxy.connectTimeOut = connectTimeOut;
    }

    /**
   
     */
    public static void setReadTimeOut(int readTimeOut)
    {
        HttpRequestProxy.readTimeOut = readTimeOut;
    }

  
    public static void setRequestEncoding(String requestEncoding)
    {
        HttpRequestProxy.requestEncoding = requestEncoding;
    }
}


