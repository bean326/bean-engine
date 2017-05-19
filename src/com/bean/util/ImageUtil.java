package com.bean.util;



import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
/**
 * 图片压缩处理
 * @author 崔素强
 */
public class ImageUtil {


    public static BufferedImage zoomImage(File srcfile)
    {
        BufferedImage result = null;
        try {
            BufferedImage im = ImageIO.read(srcfile);
			/* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();
            //压缩计算
            float resizeTimes = 0.3f;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/
			/* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);
			/* 新生成结果图片 */
            result = new BufferedImage(toWidth, toHeight,BufferedImage.TYPE_INT_RGB);
            result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight,java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        } catch (Exception e) {
            System.out.println("NarrowImage.zoomImage -> exception" );
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 不压缩大小,只压缩质量
     * @return
     */
    private static BufferedImage zoomImageByOriginal(InputStream inputStream){
        BufferedImage result = null;
        try{

            BufferedImage im = ImageIO.read(inputStream);

            int toWidth = im.getWidth();
            int toHeight = im.getHeight();
            result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
            result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     *            原始图像
     *            倍数,比如0.5就是缩小一半,0.98等等double类型
     * @return 返回处理后的图像
     */
    private static BufferedImage zoomImage(String srcPath, float resizeTimes) {
        BufferedImage result = null;
        try {
            File srcfile = new File(srcPath);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");

            }
            BufferedImage im = ImageIO.read(srcfile);
			/* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();

            if(width >= 1000 || height >= 1000){
                //压缩计算
//			/* 调整后的图片的宽度和高度 */
                int toWidth = (int) (width * resizeTimes);
                int toHeight = (int) (height * resizeTimes);
			/* 新生成结果图片 */
                result = new BufferedImage(toWidth, toHeight,BufferedImage.TYPE_INT_RGB);
                result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight,java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            }else{
                result = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
                result.getGraphics().drawImage(im.getScaledInstance(width, height,java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            }

        } catch (Exception e) {
            System.out.println("NarrowImage.zoomImage" + e.getMessage());
        }
        return result;
    }

    /**
     * 高保真压缩图片(不压缩大小)
     * @return
     */
    public static boolean writeHighQualityByOriginal(InputStream inputStream, String outputPath) {

        try {
            BufferedImage im = zoomImageByOriginal(inputStream);
            ImageIO.write(im,  "jpeg" , new File(outputPath));
            im.flush();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 高保真压缩图片
     * @return
     */
    public static boolean writeHighQuality(String inputPath, String outputPath) {
        try {
            BufferedImage im = zoomImage(inputPath,0.3f);
            ImageIO.write(im, "jpeg", new File(outputPath));
            im.flush();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 高保真压缩图片
     * @return
     */
    public static boolean writeHighQuality(String inputPath, String outputPath, float resizeTimes) {

        try {
            BufferedImage im = zoomImage(inputPath,resizeTimes);
            ImageIO.write(im,  "jpeg" , new File(outputPath));
            im.flush();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    /**
//     * 按照指定的大小缩放
//     * @param srcImageFile 大图地址
//     * @param subImage 小图地址
//     * @param h 高
//     * @param w 宽
//     * @return
//     * @throws Exception
//     */
//    public static void scale(String srcImageFile, String subImage ,int h,int w) {
//        try {
//            Thumbnails.of(srcImageFile).size(h,w).toFile(subImage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 按照比例进行缩放 例如0.25f
//     * @param srcImageFile 大图地址
//     * @param subImage 小图地址
//     * @param f 压缩比例 0.25f
//     * @return
//     * @throws Exception
//     */
//    public static void scale(String srcImageFile, String subImage ,float f) {
//        try {
//            Thumbnails.of(srcImageFile).scale(f).toFile(subImage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//

//    /**
//     * 按照指定的宽高压缩文件，等比压缩
//     * @param srcImageFile 大图地址
//     * @param subImage 小图地址
//     * @param w 宽
//     * @param h 高
//     * @param s 压缩比例
//     * @return
//     * @throws Exception
//     */
//    public static void scale(String srcImageFile, String subImage ,int w ,int h ,int s) {
//        try {
//            File file = new File(srcImageFile);
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//
//            int width = src.getWidth();
//            int height = src.getHeight();
//
//            if(width > 1024 || height > 1024){
//                if(width > height){
//                    Thumbnails.of(srcImageFile).size( height/ (width / s), s).toFile(subImage);
//                }else if (height > width){
//                    Thumbnails.of(srcImageFile).size(width/ (height / s), s).toFile(subImage);
//                }else{
//                    logger.info("等比压缩 ：=====================");
//                    Thumbnails.of(srcImageFile).scale(0.3).toFile(subImage);
//                }
//            }else{
//                Thumbnails.of(srcImageFile).size(w,h).toFile(subImage);
//            }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error(e);
//        }
//    }
//
//    /**
//     * 不按照比例，按指定大小进行缩放
//     * @param srcImageFile 大图地址
//     * @param subImage 小图地址
//     * @param width 宽
//     * @param high 高
//     * @return
//     * @throws Exception
//     */
//    public static void scaleRatio(String srcImageFile,String subImage,int width,int high){
//        try {
//            Thumbnails.of(srcImageFile).size(width, high).keepAspectRatio(false).toFile(subImage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 按照指定的大小压缩，并旋转图片 rotate(角度),正数：顺时针 负数：逆时针
//     * @param srcImageFile 大图地址
//     * @param subImage 小图地址
//     * @param width 宽
//     * @param high 高
//     * @return
//     * @throws Exception
//     */
//    public static void scaleRotate(String srcImageFile,String subImage, int rotate,int width, int high){
//        try {
//            Thumbnails.of(srcImageFile).size(width, high).rotate(rotate).toFile(subImage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 输出到BufferedImage
//     * @param srcImageFile 大图地址
//     * @param subImage  缩略图地址
//     * @param width 宽
//     * @param high 高
//     * @return
//     * @throws Exception
//     */
//    public static void scaleBufferedImage(String srcImageFile,String subImage,int width, int high,String suffix ){
//        //asBufferedImage() 返回BufferedImage
//        try {
//            BufferedImage thumbnail = Thumbnails.of(srcImageFile).size(width, high).asBufferedImage();
//            ImageIO.write(thumbnail, suffix, new File(subImage));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 转化图像格式
//     * @param srcImageFile 大图地址
//     * @param subImage 缩略图地址
//     * @param width 宽
//     * @param high 高
//     * @param suffix 格式后缀，例如：.png .PNG
//     * @return
//     * @throws Exception
//     */
//    public static void scaleFormat(String srcImageFile,String subImage,int width, int high,String suffix){
//        try {
//            Thumbnails.of(srcImageFile).size(width, high).outputFormat(suffix).toFile(subImage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 输出到OutputStream
//     * @param srcImageFile 大图地址
//     * @param subImage 缩略图地址
//     * @param width 宽
//     * @param high 高
//     * @return
//     * @throws Exception
//     */
//    public static void scaleOutputStream(String srcImageFile,String subImage,int width, int high){
//        try {
//            OutputStream os = new FileOutputStream(srcImageFile);
//            Thumbnails.of(subImage).size(width, high).toOutputStream(os);
//        }  catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 添加水印
//     * @param image 原图地址
//     * @param subimage 缩略图地址
//     * @param width 宽度
//     * @param high 高度
//     * @param printing 水印图地址
//     * @param pf 水印图大小
//     * @param tf 透明度
//     * @return
//     * @throws Exception
//     */
//    public static void scale(String image,String subimage,int width,int high,String printing,float pf, float tf){
//
//        try {
//            Thumbnails.of(image)
//                    .size(width, high)
//                            //watermark(位置，水印图，透明度)
//                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(printing)), pf).outputQuality(tf)
//                    .toFile(subimage);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 添加水印
//     * @param image 原图地址
//     * @param subimage 缩略图地址
//     * @param width 原图宽度
//     * @param high 原图高度
//     * @param width 需要裁剪后（200）宽度
//     * @param high 需要裁剪后（200）高度
//     * @return
//     * @throws Exception
//     */
//    public static void scale(String image,String subimage,int width,int high,int newWidth,int newHigh){
//        try {
//            Thumbnails.of(image)
//                    .sourceRegion(Positions.CENTER, width,high)
//                    .size(newWidth, newHigh)
//                    .keepAspectRatio(false)
//                    .toFile(subimage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String args[]){
        String inPath = "E:/1477377124552_21.jpg";
        String outPath = "E:/1477377124552_21_small.jpg";

        ImageUtil.writeHighQuality(inPath,outPath,1f);

    }

}