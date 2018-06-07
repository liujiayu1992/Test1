package com.test.ceshi;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import sun.misc.BASE64Encoder;

import com.baidu.aip.face.AipFace;

public class Sample {

	//设置APPID/AK/SK
    public static final String APP_ID = "11355843";
    public static final String API_KEY = "z0KzetrAMOcszsD00SYSCZuo";
    public static final String SECRET_KEY = "jocB4koNsTOOGcQ9k6h3YDGn6jqmvxlu";

    public static void main(String[] args) {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
     // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        
        String imgFile = "D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg";
        String imageType = "BASE64";
      //将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        byte[] data = null;  
        String imag=null;
        //读取图片字节数组  
        try   
        {  
        	FileInputStream in = new FileInputStream(new File(imgFile));
            data = new byte[in.available()];
             imag=data.toString();
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        System.out.println(encoder.encode(data));//返回Base64编码过的字节数组字符串  
        // 人脸检测
        JSONObject res = client.detect(encoder.encode(data),imageType, options);
        System.out.println(res.toString(2));   
    }
}
