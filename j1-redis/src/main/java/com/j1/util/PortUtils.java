package com.j1.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

public class PortUtils {
	
	private static String H_IP = null;

	public static boolean isPortUsing(int port) {
		boolean flag = false;
		try {
			Socket socket = new Socket("localhost", port);
			socket.close();
			flag = true;
		} catch (IOException e) {

		}
		return flag;
	}

	public static String getIp() {
		String sIP = null;
		if(null != H_IP){
			sIP = H_IP;
		}else{
			InetAddress ip = null;
			try {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces
							.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
								&& ip.getHostAddress().indexOf(":") == -1) {
							bFindIP = true;
							break;
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != ip) {
				sIP = ip.getHostAddress();
				H_IP = sIP;
			}
		}
		return sIP;
	}

	public static void main(String[] args) {
		System.out.println(getIp());
	}
}
