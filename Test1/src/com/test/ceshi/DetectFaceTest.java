package com.test.ceshi;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DetectFaceTest {

	static{  
        // ����opencv�Ŀ�  
        String opencvpath = "D:\\Tools\\opencv\\build\\java\\x64\\";
        //String opencvpath = System.getProperty("user.dir") + "\\opencv\\x64\\";
        String opencvDllName = opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll";  
        System.load(opencvDllName);  
    }  
  
  
    /**  
     * opencvʵ������ʶ��  
     * @param imagePath  
     * @param outFile  
     * @throws Exception  
     */  
    public static void detectFace(String imagePath,  String outFile) throws Exception  
    {  
  
        System.out.println("Running DetectFace ... ");  
        // �������ļ�lbpcascade_frontalface.xml�д���һ������ʶ���������ļ�λ��opencv��װĿ¼��  
        CascadeClassifier faceDetector = new CascadeClassifier(  
                "D:\\Tools\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");  
  
        Mat image = Imgcodecs.imread(imagePath);  
  
        // ��ͼƬ�м������  
        MatOfRect faceDetections = new MatOfRect();  
  
        faceDetector.detectMultiScale(image, faceDetections);  
  
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));  
  
        Rect[] rects = faceDetections.toArray();  
        if(rects != null && rects.length > 1){  
            throw new RuntimeException("����һ����");  
        }  
        // ��ÿһ��ʶ�������������Χ����һ������  
        Rect rect = rects[0];  
  
        Imgproc.rectangle(image, new Point(rect.x-2, rect.y-2),  
                          new Point(rect.x + rect.width, rect.y + rect.height),  
                          new Scalar(0, 255, 0));  
  
        Imgcodecs.imwrite(outFile, image);  
        System.out.println(String.format("����ʶ��ɹ�������ͼƬ�ļ�Ϊ�� %s", outFile));  
  
  
    }  
  
  
    /**  
     * opencvʵ������ʶ��  
     * @param imagePath  
     * @param outFile  
     * @throws Exception  
     */  
    public static void detectEye(String imagePath,  String outFile) throws Exception {  
  
  
        CascadeClassifier eyeDetector = new CascadeClassifier(  
                "D:\\Tools\\opencv\\sources\\data\\haarcascades\\haarcascade_eye.xml");  
  
        Mat image = Imgcodecs.imread(imagePath);  //��ȡͼƬ  
  
        // ��ͼƬ�м������  
        MatOfRect faceDetections = new MatOfRect();  
  
        eyeDetector.detectMultiScale(image, faceDetections, 2.0,1,1,new Size(20,20),new Size(20,20));  
  
        System.out.println(String.format("Detected %s eyes", faceDetections.toArray().length));  
        Rect[] rects = faceDetections.toArray();  
        if(rects != null && rects.length <2){  
            throw new RuntimeException("����һ˫�۾�");  
        }  
        Rect eyea = rects[0];  
        Rect eyeb = rects[1];  
  
  
        System.out.println("a-�������� " + eyea.x + " and " + eyea.y);  
        System.out.println("b-�������� " + eyeb.x + " and " + eyeb.y);  
  
        //��ȡ�������۵ĽǶ�  
        double dy=(eyeb.y-eyea.y);  
        double dx=(eyeb.x-eyea.x);  
        double len=Math.sqrt(dx*dx+dy*dy);  
        System.out.println("dx is "+dx);  
        System.out.println("dy is "+dy);  
        System.out.println("len is "+len);  
  
        double angle=Math.atan2(Math.abs(dy),Math.abs(dx))*180.0/Math.PI;  
        System.out.println("angle is "+angle);  
  
        for(Rect rect:faceDetections.toArray()) {  
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x  
                    + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));  
        }  
        Imgcodecs.imwrite(outFile, image);  
  
        System.out.println(String.format("����ʶ��ɹ�������ͼƬ�ļ�Ϊ�� %s", outFile));  
  
    }  
  
  
    /**  
     * �ü�ͼƬ������װ����С  
     * @param imagePath  
     * @param posX  
     * @param posY  
     * @param width  
     * @param height  
     * @param outFile  
     */  
    public static void imageCut(String imagePath,String outFile, int posX,int posY,int width,int height ){  
  
        //ԭʼͼ��  
        Mat image = Imgcodecs.imread(imagePath);  
  
        //��ȡ�����򣺲���,����X,����Y,��ͼ���,��ͼ����  
        Rect rect = new Rect(posX,posY,width,height);  
  
        //����Ч��һ��  
        Mat sub = image.submat(rect);   //Mat sub = new Mat(image,rect);  
  
        Mat mat = new Mat();  
        Size size = new Size(300, 300);  
        Imgproc.resize(sub, mat, size);//���������н�ͼ������  
  
        Imgcodecs.imwrite(outFile, mat);  
        System.out.println(String.format("ͼƬ���гɹ������к�ͼƬ�ļ�Ϊ�� %s", outFile));  
  
    }  
  
  
    /**  
     *  
     * @param imagePath  
     * @param outFile  
     */  
    public static void setAlpha(String imagePath,  String outFile) {  
        /**  
         * ���Ӳ�����  
         * ��ȡͼƬ�����Ƴɰ�͸��  
         */  
        try {  
  
            ImageIcon imageIcon = new ImageIcon(imagePath);  
  
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(),  
                              imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);  
  
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();  
  
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());  
  
            //ѭ��ÿһ�����ص㣬�ı����ص��Alphaֵ  
            int alpha = 100;  
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {  
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {  
                    int rgb = bufferedImage.getRGB(j2, j1);  
                    rgb = ( (alpha + 1) << 24) | (rgb & 0x00ffffff);  
                    bufferedImage.setRGB(j2, j1, rgb);  
                }  
            }  
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());  
  
            //����ͼƬΪPNG  
            ImageIO.write(bufferedImage, "png",  new File(outFile));  
  
            System.out.println(String.format("����ͼƬ��͸���ɹ���ͼƬ�ļ�Ϊ�� %s", outFile));  
  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    /**  
     * Ϊͼ�����ˮӡ  
     * @param buffImgFile ��ͼ  
     * @param waterImgFile ˮӡ  
     * @param outFile ���ͼƬ  
     * @param alpha   ͸����  
     * @throws IOException  
     */  
    private static void watermark(String buffImgFile,String waterImgFile,String outFile, float alpha) throws IOException {  
        // ��ȡ��ͼ  
        BufferedImage buffImg = ImageIO.read(new File(buffImgFile));  
  
        // ��ȡ��ͼ  
        BufferedImage waterImg = ImageIO.read(new File(waterImgFile));  
  
        // ����Graphics2D�������ڵ�ͼ�����ϻ�ͼ  
        Graphics2D g2d = buffImg.createGraphics();  
  
        int waterImgWidth = waterImg.getWidth();// ��ȡˮӡ��ͼ�Ŀ��  
  
        int waterImgHeight = waterImg.getHeight();// ��ȡˮӡ��ͼ�ĸ߶�  
  
        // ��ͼ�κ�ͼ����ʵ�ֻ�Ϻ�͸��Ч��  
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));  
  
        // ����  
        g2d.drawImage(waterImg, 0, 0, waterImgWidth, waterImgHeight, null);  
  
        g2d.dispose();// �ͷ�ͼ��������ʹ�õ�ϵͳ��Դ  
  
        //����ͼƬΪPNG  
        ImageIO.write(buffImg, "png",  new File(outFile));  
  
        System.out.println(String.format("ͼƬ���ˮӡ�ɹ���ͼƬ�ļ�Ϊ�� %s", outFile));  
    }  
  
  
    /**  
     * ͼƬ�ϳ�  
     * @param image1  
     * @param image2  
     * @param posw  
     * @param posh  
     * @param outFile  
     * @return  
     */  
    public static void simpleMerge(String image1, String image2, int posw, int posh, String outFile) throws IOException{  
  
        // ��ȡ��ͼ  
        BufferedImage buffImg1 = ImageIO.read(new File(image1));  
  
        // ��ȡ��ͼ  
        BufferedImage buffImg2 = ImageIO.read(new File(image2));  
  
        //�ϲ�����ͼ��  
        int w1 = buffImg1.getWidth();  
        int h1 = buffImg1.getHeight();  
  
        int w2 = buffImg2.getWidth();  
        int h2 = buffImg2.getHeight();  
  
        BufferedImage imageSaved = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_ARGB); //����һ���µ��ڴ�ͼ��  
  
        Graphics2D g2d = imageSaved.createGraphics();  
  
        g2d.drawImage(buffImg1, null, 0, 0);  //���Ʊ���ͼ��  
  
        for (int i = 0; i < w2; i++) {  
            for (int j = 0; j < h2; j++) {  
                int rgb1 = buffImg1.getRGB(i + posw, j + posh);  
                int rgb2 = buffImg2.getRGB(i, j);  
  
                /*if (rgb1 != rgb2) {  
                    rgb2 = rgb1 & rgb2;  
                }*/  
                imageSaved.setRGB(i + posw, j + posh, rgb2); //�޸�����ֵ  
            }  
        }  
  
        ImageIO.write(imageSaved, "png", new File(outFile));  
  
        System.out.println(String.format("ͼƬ�ϳɳɹ����ϳ�ͼƬ�ļ�Ϊ�� %s", outFile));  
  
    }  
  
    public static void main(String[] args) throws Exception {  
  
        //����ʶ��  
        detectFace("D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg", "D:\\WorkSpace\\Test1\\WebRoot\\picture\\personFaceDetect.png");  
  
        //����ʶ��  
        detectEye("D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg",  "D:\\WorkSpace\\Test1\\WebRoot\\picture\\personEyeDetect.png");  
        /*
        //ͼƬ����  
        imageCut("D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg","D:\\WorkSpace\\Test1\\WebRoot\\picture\\personCut.png", 50, 50,100,100);  
  
        //����ͼƬΪ��͸��  
        setAlpha("D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg", "D:\\WorkSpace\\Test1\\WebRoot\\picture\\personAlpha.png");  
  
  
        //ΪͼƬ���ˮӡ  
        watermark("D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg","D:\\WorkSpace\\Test1\\WebRoot\\picture\\ling.jpg","D:\\WorkSpace\\Test1\\WebRoot\\picture\\personWaterMark.png", 0.2f);  
  
  
        //ͼƬ�ϳ�  
        simpleMerge("D:\\WorkSpace\\Test1\\WebRoot\\picture\\1.jpg", "D:\\WorkSpace\\Test1\\WebRoot\\picture\\ling.jpg", 45, 50, "D:\\WorkSpace\\Test1\\WebRoot\\picture\\personMerge.png");  
  */
    }  
}
