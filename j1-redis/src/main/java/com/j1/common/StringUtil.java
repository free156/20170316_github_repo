/**
 * Page.java
 * 
 * Copyright(C)2008 Founder Corporation.
 * written by Founder Corp.
 */
package com.j1.common;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [类名]<br>
 * Page<br>
 * [功能概要]<br>
 * <br>
 * <br>
 * [変更履歴]<br>
 * 2009-3-16 ver1.00 新建 zhaoxinsheng<br>
 * 
 * @author FOUNDER CORPORATION
 * @version 1.00
 */
public class StringUtil {

	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	private static Object initLock = new Object();

	private static Map<String, String> spellMap;

	/**
	 * 字符串内容为"null"时置为空
	 * 
	 * @param str
	 * @return
	 */
	public static String convertNull(String str) {
		if (str == null)
			return null;
		if (str.trim().equals("null") || str.trim().equals("\"null\""))
			return null;
		return str;
	}

	/**
	 * 判断是否为空，空返回true
	 */
	public static boolean isEmpty(String str) {
		if (null == str)
			str = "";
		str = str.trim();
		return "".equals(str);
	}

	/**
	 * 判断是否不为空，不空返回true
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 函数名称：arrayToStr 函数功能:把数组转换为字符串列表
	 * 
	 * @param array1
	 *            数组
	 * @return 用","隔开的字符串列表
	 */
	public static String arrayToStr(String[] array1) {
		String sResult = "";
		if (array1 == null) {
			return sResult;
		}
		for (int i = 0; i < array1.length; i++) {
			if ("".equals(sResult)) {
				sResult = array1[i];
			} else {
				sResult += "," + array1[i];
			}
		}
		return sResult;
	}

	/**
	 * 函数名称：arrayToStr 函数功能:把数组转换为字符串列表
	 * 
	 * @param array1
	 *            数组
	 * @param splitChar
	 *            分隔符
	 * @return 用splitChar隔开的字符串列表
	 */
	public static String arrayToStr(String[] array1, String splitChar) {
		String sResult = "";
		if (array1 == null) {
			return sResult;
		}
		for (int i = 0; i < array1.length; i++) {
			if ("".equals(sResult)) {
				sResult = array1[i];
			} else {
				sResult += splitChar + array1[i];
			}
		}
		return sResult;
	}

	/**
	 * if aInStr is null,then return defaultStr
	 * 
	 * @param aInStr
	 * @param defaultStr
	 * @return
	 */
	public static String getTrimString(Object aInObj, String defaultStr) {
		if (aInObj == null) {
			return defaultStr.trim();
		} else {
			return aInObj.toString().trim();
		}
	}

	/**
	 * default string is blank.
	 */
	public static String getTrimString(Object aInobj) {
		return getTrimString(aInobj, "");
	}

	public static String LPadString(String origin, int total, String pad) {
		String temp;
		StringBuffer tempBF = new StringBuffer();
		temp = (origin == null) ? "" : getTrimString(origin);

		for (int i = 0; i < total - getTrimString(origin).length(); i++) {
			tempBF.append(pad);
		}
		tempBF.append(temp);
		return tempBF.toString();

	}

	/**
	 * 去除所有空格,包括中间的
	 */
	public static String removeAllSpace(String str) {
		return str.replaceAll("\\s+", "");
	}

	/**
	 * 折扣显示转换
	 */
	public static String discountToString(String discount) {
		if (discount != null && !"".equals(discount)) {
			String value = new Float(discount) * 100 + "";
			return value.substring(0, value.indexOf("."));
		} else {
			return "";
		}
	}

	/**
	 * 将页面上折扣转换成存储格式（缩小100倍）
	 */
	public static Float toDiscount(Float value) {
		return Float.parseFloat(value * 0.01 + "");
	}

	/**
	 * String 转换 数组
	 * 
	 * @param str
	 *            字符串
	 * @param sep
	 *            分割字符
	 * @return
	 */
	public static String[] str2Array(String str, String sep) {
		StringTokenizer token = null;
		String[] array = null;

		// check
		if (str == null || sep == null) {
			return null;
		}

		// get string array
		token = new StringTokenizer(str, sep);
		array = new String[token.countTokens()];
		for (int i = 0; token.hasMoreTokens(); i++) {
			array[i] = token.nextToken();
		}

		return array;
	}

	public static String trim(String str) { // 去掉字符串2端的空白字符
		try {
			if (str == null) {
				return null;
			}
			str = str.trim();
			if (str == null) {
				return null;
			}
			return str;
		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}

	/**
	 * list<String> 转换SQLString<br>
	 * 
	 * @param inputList
	 *            输入的List型
	 * @return String
	 */
	public static String listToSQLString(List<String> inputList) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < inputList.size(); i++) {
			// output.append("'");
			if (StringUtil.isNotEmpty(inputList.get(i))) {
				output.append(inputList.get(i));
				// output.append("'");
				if (i != inputList.size() - 1) {
					output.append(",");
				}
			}
		}
		return output.toString();
	}

	/***
	 * 判断字符串是否数字
	 * 
	 * @param str
	 * @return flag
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEnglish(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// System.out.println(StringUtil.removeAllSpac
		System.out.println(isEnglish("我是中"));
	}

	/**
	 * 得到单前系统的本地编码
	 * 
	 * @param encodedStr
	 * @return
	 */
	public static String getLocalStr(String encodedStr) {
		try {
			if (!(new String(encodedStr.getBytes("UTF-8"))).equals(encodedStr)) {
				return new String(encodedStr.getBytes("UTF-8"));
			} else if (!(new String(encodedStr.getBytes("ISO-8859-1")))
					.equals(encodedStr)) {
				return new String(encodedStr.getBytes("ISO-8859-1"));
			}
			return encodedStr;
		} catch (UnsupportedEncodingException e) {
			return encodedStr;
		}
	}

	public static String getFullSpell(String cnStr) {
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}
		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			int ascii = getCnAscii(chars[i]);
			if (ascii == 0) { // 取ascii时出错

				retuBuf.append(chars[i]);
			} else {
				String spell = getSpellByAscii(ascii);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400

		} // end of for

		return retuBuf.toString();
	}

	public static int getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误

			return 0;
		}
		if (bytes.length == 1) { // 英文字符

			return bytes[0];
		}
		if (bytes.length == 2) { // 中文字符

			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte) - 256 * 256;
			// System.out.println("ASCII=" + ascii);

			return ascii;
		}
		return 0; // 错误

	}

	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param ascii
	 *            int 字符对应的ASCII
	 * @return String 拼音,首先判断ASCII是否>0&<160,如果是返回对应的字符,
	 * 
	 *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	public static String getSpellByAscii(int ascii) {
		if (ascii > 0 && ascii < 160) { // 单字符

			return String.valueOf((char) ascii);
		}
		if (ascii < -20319 || ascii > -10247) { // 不知道的字符

			return null;
		}
		Set<?> keySet = spellMap.keySet();
		Iterator<?> it = keySet.iterator();
		String spell0 = null;
		;
		String spell = null;
		int asciiRang0 = -20319;
		int asciiRang;
		while (it.hasNext()) {
			spell = (String) it.next();
			Object valObj = spellMap.get(spell);
			if (valObj instanceof Integer) {
				asciiRang = ((Integer) valObj).intValue();
				if (ascii >= asciiRang0 && ascii < asciiRang) { // 区间找到

					return (spell0 == null) ? spell : spell0;
				} else {
					spell0 = spell;
					asciiRang0 = asciiRang;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 输入 0 返回 Y（已删除） 输入 1 返回 N（未删除）
	 * 
	 * @param chars
	 *            1显示，0不显示
	 * @return
	 */
	public static String getYorN(String chars) {
		if (null == chars || "".equals(chars)) {
			return "";
		} else {
			String res = "";
			if (chars.equals("1"))
				res = "N";
			if (chars.equals("0"))
				res = "Y";

			return res;
		}
	}

	/**
	 * 返回所输长度的0字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getZeroString(Integer length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	/**
	 * 将
	 * 
	 * @param filePath
	 * @param info
	 */
	public static void writeFile(String filePath, String info) {
		File file = new File(filePath);
		try {
			file.createNewFile();
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8");
			PrintWriter pw = new PrintWriter(out);
			pw.println(info);
			pw.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空文件夹下的文件
	 * 
	 * @param path
	 */
	public static void cleanDir(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			f.delete();
		}
	}

	/**
	 * 获取随即字符串
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static final String randomString(int length) {

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz_"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
							.toCharArray();
					// numbersAndLetters =
					// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
			// randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}

	/**
	 * @description 把id list 转换成字符串 格式是： '22','22',
	 * @author xiang.zhuanglong
	 * @param list
	 * @return
	 */
	public static String listTranSqlstr(List<?> list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				sb.append("'").append(list.get(i)).append("'").append(",");
			}
			sb.append("'").append(list.get(list.size() - 1)).append("'");
		}
		return sb.toString();
	}

	/**
	 * @description 检测时间格式是否是对的
	 * @author xiang.zhuanglong
	 * @param dateStr
	 *            格式：2012-12-09
	 * @param dateFormat
	 *            格式：yyyy-MM-dd
	 * @return
	 */
	public static boolean isValidDate(String dateStr, String dateFormat) {
		if (dateStr == null || dateStr.length() == 0) {
			return true;
		} else {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			try {
				Date date = df.parse(dateStr);
				if (!dateStr.equals(df.format(date))) {
					return false;
				}
				return true;
			} catch (ParseException e) {
				return false;
			}
		}
	}

	/**
	 * @description 返回时间字符串 按照dateFormat 格式
	 * @author xiang.zhuanglong
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String returnDateFormat(Date date, String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}

	/**
	 * @author xiang.zhuanglong 获取订单状态
	 * @param stateInt
	 * @return
	 */
	public static String returnOrderState(int stateInt) {
		if (stateInt == 18) {
			return "已退款";
		} else if (stateInt == 19) {
			return "退货完成";
		} else if (stateInt == 20) {
			return "退货中";
		} else if (stateInt == 21) {
			return "已出库";
		} else if (stateInt == 22) {
			return "配货中";
		} else if (stateInt == 23) {
			return "已收货";
		} else if (stateInt == 24) {
			return "缺货中";
		} else if (stateInt == 25) {
			return "已取消";
		} else if (stateInt == 26) {
			return "已完成";
		} else if (stateInt == 27) {
			return "已发货";
		} else if (stateInt == 28) {
			return "已审核";
		} else if (stateInt == 29) {
			return "已提交";
		} else {
			return "已提交";
		}
	}

	/**
	 * @author xiang.zhuanglong
	 * @description 得到支付状态 IS_PAID
	 * @param state
	 * @return
	 */
	public static String returnOrderPayState(String state) {
		if ("Y".equals(state)) {
			return "已支付";
		} else if ("N".equals(state)) {
			return "未支付";
		} else {
			return "部分线下支付";
		}
	}

	/**
	 * @author xiang.zhuanglong
	 * @description 得到支付类别 PAYMENT_MODE
	 * @param state
	 * @return
	 */
	public static String returnOrderPayStyle(String style) {
		if ("KDFH".equals(style)) {
			return "款到发货";
		} else if ("HDFK".equals(style)) {
			return "货到付款";
		} else if ("HDFK_POS".equals(style)) {
			return "货到付款pos";
		} else {
			return "自提付款";
		}
	}

	/**
	 * @description 得到手机充值 订单状态Map
	 * @return
	 */
	public static Map<String, String> getPhoneStateMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "全部");
		map.put("success", "成功");
		map.put("paid", "已支付");
		map.put("commit", "已提交");
		return map;
	}

	/**
	 * @description 把大图的路径+addMark
	 * @param bigImageUrl
	 *            (大图的路径)
	 * @param addMark
	 * @return 小图路径
	 */
	public static String getSmallImageUrl(String bigImageUrl, String addMark) {
		String fileSmallUrl = StringUtils.substring(bigImageUrl, 0,
				StringUtils.indexOf(bigImageUrl, "."))
				+ addMark
				+ StringUtils.substring(bigImageUrl,
						StringUtils.indexOf(bigImageUrl, "."),
						bigImageUrl.length());
		return fileSmallUrl;
	}

	/**
	 * list转string
	 * 
	 * @param list
	 * @return
	 */
	public static String list2String(List<?> list) {
		String result = list.toString();
		result = result.substring(1, result.length() - 1);
		result = result.replace(",", " ");
		if (result.trim().length() == 0) {
			result = ",";
		}
		return result;
	}

	/**
	 * InputStream 转 String
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String convertStreamToString(java.io.InputStream input)
			throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input,
				"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		input.close();
		return sb.toString();
	}

	/**
	 * 把对象转换为int数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回i。
	 */
	public static int toInt(Object obj,int defaultInt) {
		try {
			if (obj != null)
				defaultInt = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return defaultInt;
	}
	
}
