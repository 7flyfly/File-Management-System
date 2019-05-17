package com.file.management.service.ImageProcessing;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/**
 * 图像处理
 */
@Service
public class ImageProcessService {
//    /**
//     * 要进行DCT变换的图片的宽或高
//     */
//    public static final int N = 256;

    /**
     * 将图片转化成黑白灰度图片
     * @param pix 保存图片像素
     * @param w 二维像素矩阵的宽
     * @param h 二维像素矩阵的高
     * @return 灰度图像矩阵
     */
    public int[] grayImage(int pix[], int w, int h) {
        ColorModel cm = ColorModel.getRGBdefault();
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                //RGB转灰度公式
                pix[i*w + j] = (int) (0.3*cm.getRed(pix[i*w + j]) + 0.58*cm.getGreen(pix[i*w + j]) + 0.12*cm.getBlue(pix[i*w + j]) );
            }
        }
        return pix;
    }

    /**
     * 傅里叶变换
     */
    public int[] FFT() {

        return null;
    }

    /**
     * 离散余弦变换
     * @param pix 原图像的数据矩阵
     * @param n 原图像(n*n)的高或宽
     * @return 变换后的矩阵数组
     */
    public int[] DCT(int[] pix, int n) {
        double[][] iMatrix = new double[n][n];
        //将一维的pix矩阵转换为二位矩阵
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                iMatrix[i][j] = (double)(pix[i*n + j]);
            }
        }
        double[][] quotient = coefficient(n);	//求系数矩阵
        double[][] quotientT = transposingMatrix(quotient, n);	//转置系数矩阵

        double[][] temp = new double[n][n];
        temp = matrixMultiply(quotient, iMatrix, n);
        iMatrix =  matrixMultiply(temp, quotientT, n);

        int newpix[] = new int[n*n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                newpix[i*n + j] = (int)iMatrix[i][j];
            }
        }
        return newpix;
    }

    /**
     * 局部均值的图像缩小
     * @param pix 图像的像素矩阵
     * @param w 原图像的宽
     * @param h 原图像的高
     * @param m 缩小后图像的宽
     * @param n 缩小后图像的高
     * @return
     */
    public int[] shrink(int[] pix, int w, int h, int m, int n) {
        float k1 = (float) m / w;
        float k2 = (float) n / h;
        int ii = (int)(1 / k1); // 采样的行间距
        int jj = (int)(1 / k2); // 采样的列间距
        int dd = ii * jj;
        // int m=0 , n=0;
        // int imgType = img.getType();
        int[] newpix = new int[m * n];
        //采样
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int r = 0, g = 0, b = 0;
                ColorModel cm = ColorModel.getRGBdefault();
                //类似CNN Feature Map 求 均值pooling
                for (int k = 0; k <  jj; k++) {
                    for (int l = 0; l <  ii; l++) {
                        r = r + cm.getRed(pix[(jj * j + k) * w +  (ii * i + l)]);
                        g = g + cm.getGreen(pix[(jj * j + k) * w +  (ii * i + l)]);
                        b = b + cm.getBlue(pix[ (jj * j + k) * w +  (ii * i + l)]);
                    }
                }
                r = r / dd;
                g = g / dd;
                b = b / dd;
                newpix[j * m + i] = 255 << 24 | r << 16 | g << 8 | b; //将二进制值转换为十进制，int型
                // 255<<24 | r<<16 | g<<8 | b 这个公式解释一下，颜色的RGB在内存中是
                // 以二进制的形式保存的，从右到左1-8位表示blue，9-16表示green，17-24表示red
                // 所以"<<24" "<<16" "<<8"分别表示左移24,16,8位
            }
        }
        return newpix;
    }

    /**
     * 求离散余弦变换的系数矩阵
     * @param n n*n矩阵的大小
     * @return 系数矩阵
     */
    private double[][] coefficient(int n) {
        double[][] coeff = new double[n][n];
        double sqrt = 1.0/Math.sqrt(n);
        for(int i=0; i<n; i++) {
            coeff[0][i] = sqrt;
        }
        for(int i=1; i<n; i++) {
            for(int j=0; j<n; j++) {
                coeff[i][j] = Math.sqrt(2.0/n) * Math.cos(i*Math.PI*(j+0.5)/(double)n);
            }
        }
        return coeff;
    }

    /**
     * 矩阵相乘(仅限方阵)
     * @param A 矩阵A
     * @param B 矩阵B
     * @param n 矩阵的大小n*n
     * @return 结果矩阵
     */
    private double[][] matrixMultiply(double[][] A, double[][] B, int n) {
        double nMatrix[][] = new double[n][n];
        double t = 0.0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                t = 0;
                for(int k=0; k<n; k++) {
                    t += A[i][k]*B[k][j];
                }
                nMatrix[i][j] = t;
            }
        }
        return nMatrix;
    }

    /**
     * 矩阵转置
     * @param matrix 原矩阵
     * @param n 矩阵(n*n)的高或宽
     * @return 转置后的矩阵
     */
    private double[][]  transposingMatrix(double[][] matrix, int n) {
        double nMatrix[][] = new double[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                nMatrix[i][j] = matrix[j][i];
            }
        }
        return nMatrix;
    }
}
