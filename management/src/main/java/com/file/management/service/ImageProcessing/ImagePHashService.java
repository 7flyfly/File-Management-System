package com.file.management.service.ImageProcessing;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@Service
public class ImagePHashService {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageProcessService imageProcessService;
    //图片缩小后的宽
    private static final int FWIDTH = 8;
    //图片缩小后的高
    private static final int FHEIGHT = 8;

    /**
     * 以从solr中查询出的结果为基础，计算PHash的hamming距离
     * @param queryImagePhash 当前查询图片的PHash
     * @param rawResult_JSONArr 从solr中查询出的结果
     * @param imagePHashSolrName PHash在solr中filed的名称
     * @param hammingDistanceValue hamming距离的阈值
     * @return
     */
    public JSONArray getSimilarImages(String queryImagePhash, JSONArray rawResult_JSONArr,String imagePHashSolrName,
                                        int hammingDistanceValue){
        JSONArray result_JSONArr = new JSONArray();
        for(int i=0; i<rawResult_JSONArr.size(); i++){
            JSONObject result_JSON = rawResult_JSONArr.getJSONObject(i);
            if(result_JSON.containsKey(imagePHashSolrName)){
                //将原生PHash字段中的其他字符去除（PHash为多值）
                String rawResultImagePHash = result_JSON.getString(imagePHashSolrName).replace("\"","").
                        replace("[","").replace("]","");
                ArrayList<String> resultImagePHashList = new ArrayList<>();
                if(rawResultImagePHash.contains(",")){
                    //多个PHash值
                    String[] rawResultImagePHashArr = rawResultImagePHash.split(",");
                    resultImagePHashList= new ArrayList<>(Arrays.asList(rawResultImagePHashArr));
                }else{
                    //单个PHash值
                    resultImagePHashList.add(rawResultImagePHash);
                }
                //计算hamming距离保存小于等于阈值的结果
                for(String resultImagePHash: resultImagePHashList){
                    if(this.hammingDistance(queryImagePhash,resultImagePHash)<=hammingDistanceValue){
                        result_JSONArr.add(result_JSON);
                        break;
                    }
                }
            }
        }
        return result_JSONArr;
    }
    /**
     * 基于pHash算法的指纹数
     * @param inputStream 图片的输入流
     * @return 图片的指纹数
     */
    public String getPHash(InputStream inputStream) {
        BufferedImage img = imageService.readImg(inputStream);
        if(img==null) return "";
        int w = img.getWidth(); //原图像的宽
        int h = img.getHeight(); //原图像的高
        int pix[] = new int[w * h]; //图片缩小后的像素数量
        pix = img.getRGB(0, 0, w, h, pix, 0, w); //图像的RGB值矩阵
        pix = imageProcessService.shrink(pix, w, h, 32, 32); //图片缩小为32*32
        pix = imageProcessService.grayImage(pix, 32, 32); //图片灰度
        ImageProcessService tf = new ImageProcessService();
        int[] dctPix = tf.DCT(pix, 32); // 离散余弦变换变换，得到32*32的DCT系数矩阵
        int avrPix = averageGray(dctPix, FWIDTH, FHEIGHT); //求灰度的均值，只保留左上FWIDTH*FHEIGHT大小，为图像中最低频率（特征提取）
        StringBuilder sb = new StringBuilder();
        //低于均值取0，高于取1
        for(int i=0; i<FHEIGHT; i++) {
            for(int j=0; j<FWIDTH; j++) {
                if(dctPix[i*FWIDTH + j] >= avrPix) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
        }
        long result = 0;
        if(sb.charAt(0) == '0') {
            result = Long.parseLong(sb.toString(), 2); //需要转换的String为二进制，默认为十进制
        } else {
            //如果第一个字符是1，则表示负数，不能直接转换成long
            //todo ????
            result = 0x8000000000000000l ^ Long.parseLong(sb.substring(1), 2);
        }
//        System.out.println(new String(Long.toOctalString(result)));//十进制转8进制
//        System.out.println(new String(Long.toString(result)));//十进制转10进制
        sb = new StringBuilder(Long.toHexString(result));//十进制转十六进制
//        sb = new StringBuilder(new String(Long.toOctalString(result)));//十进制转十六进制

        //末尾不足16位以0补足
        if(sb.length() < 16) {
            int n = 16-sb.length();
            for(int i=0; i<n; i++) {
                sb.insert(0, "0");
            }
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 求灰度图像的均值
     * @param pix 图像的像素矩阵
     * @param w 图像的宽
     * @param h 图像的高
     * @return 灰度均值
     */
    private int averageGray(int[] pix, int w, int h) {
        int sum = 0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                sum = sum+pix[i*w + j];
            }
        }
        return (int)(sum/(w*h));
    }
    /**
     * 计算汉明距离
     * @param s1 指纹数1
     * @param s2 指纹数2
     * @return 汉明距离
     */
    private int hammingDistance(String s1, String s2) {
        int count = 0;
        for(int i=0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                count ++;
            }
        }
        return count;
    }

}
