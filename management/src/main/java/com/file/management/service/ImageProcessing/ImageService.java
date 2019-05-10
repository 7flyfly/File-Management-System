package com.file.management.service.ImageProcessing;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.net.URL;

/**
 * 图片读取等对图片的基本操作
 */
@Service
public class ImageService {
    /**
     * 读取图片
     * @param inputStream 图片的输入流
     * @return 返回图片的BufferedImage对象
     */
    public BufferedImage readImg(InputStream inputStream) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return img;
        }
        return img;
    }

    /**
     * 读取图片
     * @param srcPath 图片的存储位置
     * @return 返回图片的BufferedImage对象
     */
    public BufferedImage readImg(String srcPath) {
        BufferedImage img = null;
        try {
            if(srcPath.contains("http")){
                URL url= new URL(srcPath);
                img = ImageIO.read(url.openStream());
            }else{
                img = ImageIO.read(new File(srcPath)); //图片是本地资源
            }
        } catch (IOException e) {
            e.printStackTrace();
            return img;
        }
        return img;
    }

    /**
     * 将图片写入磁盘
     * @param img 图像的BufferedImage对象
     * @param formatName 存储的文件格式
     * @param destPath 图像要保存的存储位置
     */
    public void writeImg(BufferedImage img, String formatName,
                                String destPath) {
        OutputStream out = null;
        try {
            // int imgType = img.getType();
            // System.out.println("w:" + img.getWidth() + "  h:" +
            // img.getHeight());
            out = new FileOutputStream(destPath);
            ImageIO.write(img, formatName, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
