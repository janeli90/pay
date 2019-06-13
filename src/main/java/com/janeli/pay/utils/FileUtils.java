/**@Title: FileUtils.java 
 * @Package com.webside.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author tianlei   
 * @date 2017年1月12日 下午4:32:01 
 * @version V1.0   
 */
package com.janeli.pay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

/**@ClassName: FileUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tianlei
 * @date 2017年1月12日 下午4:32:01 
 */
public class FileUtils {
    private static String  picArr = ".JPG_.PNG_.IMG";
    public static void saveFile(String newFile, InputStream is) throws Exception{
        String filePath = newFile.substring(0,newFile.lastIndexOf("/"));
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(newFile);
        byte[] b = new byte[1024];
        while((is.read(b)) != -1){
            fos.write(b);
        }
        is.close();
        fos.close();
    }
    
    public static boolean deleteFile(File file)
	{
		if (file.exists()) 
		{
			if (file.isFile()) 
			{
				return file.delete();
			} 
			else if (file.isDirectory()) 
			{
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) 
				{
					deleteFile(files[i]);
				}
				return file.delete();
			}
		} 
		else 
		{
			return false;
		}
		return false;
	}
    
    public static boolean checkPicture(String fileName){
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        if(picArr.indexOf(prefix.toUpperCase()) < 0){
            return false;
        }else{
            return true;
        }
    }
    
  //图片转化成base64字符串  
    public static String getImageStr(String imgFile)  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
    	File file = new File(imgFile);
        if(!file.exists()){
            return "";
        }
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    } 
    
    public static  String createFileName(String merchantCode, String fileName,String fileType,String picDir){
        StringBuilder s = new StringBuilder();
        s.append(picDir).append("/").append(merchantCode).append("/").append(merchantCode)
        .append("_").append(fileType).append(fileName.substring(fileName.lastIndexOf(".")));
        return s.toString();
    }
    
    public static String getImgDir(String merchantCode, String commPath){
        StringBuilder s = new StringBuilder();
        s.append(commPath).append("dat/merc/").append(merchantCode).append("/");
        return s.toString();
    }
}
