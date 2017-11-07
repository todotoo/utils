package org.wg.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 图片工具
 *
 * @author wg
 * @version 1.0
 */
public class ImageUtil {

    private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    static {
        System.setProperty("java.awt.headless", "true");
    }

    /**
     * 保存图片
     *
     * @throws IOException
     */
    public static void saveImage(byte[] bytes, String path) throws IOException {
        FileUtils.writeByteArrayToFile(new File(path), bytes);
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        byte[] sourceBytes = FileUtils.readFileToByteArray(new File("F:\\PrivateCentre\\images\\壁纸\\zhuoku251.jpg"));
        String targetPath = "D:\\home\\webapps\\filestore\\images\\xx.jpg";
//        ImageUtil.resize(sourceBytes, 300, 300, targetPath);
        ImageUtil.resize(sourceBytes, 0.1F, targetPath);
//        thumbnailatorTest.test3();
//        thumbnailatorTest.test4();
//        thumbnailatorTest.test5();
//        thumbnailatorTest.test6();
//        thumbnailatorTest.test7();
//        thumbnailatorTest.test8();
//        thumbnailatorTest.test9();
    }

    /**
     * 指定大小进行缩放
     *
     * @throws IOException
     */
    public static void resize(byte[] sourceBytes, int width, int height, String targetPath) throws IOException {
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        // 临时目录
        String tempDir = System.getProperty("java.io.tmpdir");
        String imageName = UUIDGenerator.getUUID().toString();
        File tempSourceFile = new File(tempDir + "/" + imageName);
        // 把图片流输出到临时文件中
        FileUtils.writeByteArrayToFile(tempSourceFile, sourceBytes);
        // 压缩成指定大小，输出到指定位置
        Thumbnails.of(tempSourceFile).size(width, height).toFile(targetPath);
        // 删除文件
        FileUtils.deleteQuietly(tempSourceFile);
    }

    /**
     * 按照比例进行缩放
     *
     * @throws IOException
     */
    public static void resize(byte[] sourceBytes, float ratio, String targetPath) throws IOException {
        // 临时目录
        String tempDir = System.getProperty("java.io.tmpdir");
        String imageName = UUIDGenerator.getUUID().toString();
        File tempSourceFile = new File(tempDir + "/" + imageName);
        // 把图片流输出到临时文件中
        FileUtils.writeByteArrayToFile(tempSourceFile, sourceBytes);
        // scale(比例)
        Thumbnails.of(tempSourceFile).scale(ratio).toFile(targetPath);
        // 删除文件
        FileUtils.deleteQuietly(tempSourceFile);
    }

    /**
     * 按照比例进行缩放
     * @param srcFile 源文件图片
     * @param ratio 压缩的比例
     * @param targetPath 输出的目标路径
     * @throws IOException
     */
    public static void resize(File srcFile, float ratio, String targetPath) throws IOException {
        // scale(比例)
        Thumbnails.of(srcFile).scale(ratio).toFile(targetPath);
    }

    /**
     * 不按照比例，指定大小进行缩放
     *
     * @throws IOException
     */
    private void test3() throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of("images/test.jpg").size(120, 120).keepAspectRatio(false).toFile("C:/image_120x120.jpg");
    }

    /**
     * 旋转
     *
     * @throws IOException
     */
    private void test4() throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针
         */
        Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(90).toFile("C:/image+90.jpg");
        Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(-90).toFile("C:/iamge-90.jpg");
    }

    /**
     * 水印
     *
     * @throws IOException
     */
    private void test5() throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of("images/test.jpg").size(1280, 1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile("C:/image_watermark_bottom_right.jpg");
        Thumbnails.of("images/test.jpg").size(1280, 1024).watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile("C:/image_watermark_center.jpg");
    }

    /**
     * 裁剪
     *
     * @throws IOException
     */
    private void test6() throws IOException {
        /**
         * 图片中心400*400的区域
         */
        Thumbnails.of("images/test.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("C:/image_region_center.jpg");
        /**
         * 图片右下400*400的区域
         */
        Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("C:/image_region_bootom_right.jpg");
        /**
         * 指定坐标
         */
        Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false).toFile("C:/image_region_coord.jpg");
    }

    /**
     * 转化图像格式
     *
     * @throws IOException
     */
    private void test7() throws IOException {
        /**
         * outputFormat(图像格式)
         */
        Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("png").toFile("C:/image_1280x1024.png");
        Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("gif").toFile("C:/image_1280x1024.gif");
    }

    /**
     * 输出到OutputStream
     *
     * @throws IOException
     */
    private void test8() throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
        Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     *
     * @throws IOException
     */
    private void test9() throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
    }
}
