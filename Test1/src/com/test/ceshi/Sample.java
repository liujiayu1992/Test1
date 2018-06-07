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

	//����APPID/AK/SK
    public static final String APP_ID = "11355843";
    public static final String API_KEY = "z0KzetrAMOcszsD00SYSCZuo";
    public static final String SECRET_KEY = "jocB4koNsTOOGcQ9k6h3YDGn6jqmvxlu";

    public static void main(String[] args) {
        // ��ʼ��һ��AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
     // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        
        String imgFile = "D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg";
        String imageType = "BASE64";
      //��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��  
        byte[] data = null;  
        String imag=null;
        //��ȡͼƬ�ֽ�����  
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
        //���ֽ�����Base64����  
        BASE64Encoder encoder = new BASE64Encoder();  
        System.out.println(encoder.encode(data));//����Base64��������ֽ������ַ���  
        // �������
        JSONObject res = client.detect(encoder.encode(data),imageType, options);
        System.out.println(res.toString(2));   
    }
}
