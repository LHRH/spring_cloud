package com.lh.cloud.tool;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
	// 压缩   
	public static byte[] compress(String str) throws IOException {
		if (str == null || str.length() == 0) {   
			return null;   
		}   
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes("utf-8"));   
		gzip.close();   
		
		return out.toByteArray();   
	}  

	// 解压缩   
	public static String uncompress(byte[] bytes) throws IOException {
		if (bytes == null || bytes.length == 0) {   
			return null;   
		}   
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];   
		int n;
		while ((n = gunzip.read(buffer))>= 0) {   
			out.write(buffer, 0, n);
		}   
		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)   
		return out.toString();   
	}   

	// 测试方法   
	public static void main(String[] args) throws IOException {
		
//		//测试字符串
//		String str="加州花园123abc%5B%7加州花园加州花园加州花园加州花园加州花园B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221";
//
//		System.out.println("原长度："+str.length());
//
//		System.out.println("压缩后："+ZipUtil.compress(str).length());
//
//		System.out.println("解压缩："+ZipUtil.uncompress(ZipUtil.compress(str)));
	}  
	
	// 黄老师解压缩   
	public static String uncompress(String str) throws IOException {
	    if (str == null || str.length() == 0) {
	        return str;
	    }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
	    GZIPInputStream gunzip = new GZIPInputStream(in);
	    byte[] buffer = new byte[256];
	    int n;
	    while ((n = gunzip.read(buffer))>= 0) {
	        out.write(buffer, 0, n);
	    }
	    // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
	    return out.toString();
	}

}
