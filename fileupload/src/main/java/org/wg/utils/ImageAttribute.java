package org.wg.utils;

/**
 * 图片属性
 *
 * @author shifeng.tang
 * @version 1.0
 * @date 2016年3月19日 下午5:23:49
 */
public class ImageAttribute {
    //图片宽度
    private Integer width;

    //图片高度
    private Integer height;

    //是否等比例压缩
    private boolean isEqualRatio;

    //是否需要增加文件名字后缀,一組里面只能存在一个true
    private boolean noShowSuffix;

    /**
     * 图片
     */
    private byte[] imgByte;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件名称（包含后缀）
     */
    private String fileName;


    public ImageAttribute(Integer width, Integer height, boolean isEqualRatio) {
        this.width = width;
        this.height = height;
        this.isEqualRatio = isEqualRatio;
        this.noShowSuffix = false;
    }

    public ImageAttribute(Integer width, Integer height, boolean isEqualRatio, boolean noShowSuffix) {
        this.width = width;
        this.height = height;
        this.isEqualRatio = isEqualRatio;
        this.noShowSuffix = noShowSuffix;
    }

    public ImageAttribute(Integer width, Integer height, boolean isEqualRatio, boolean noShowSuffix, byte[] imgByte,
                          String filePath, String fileName) {
        this.width = width;
        this.height = height;
        this.isEqualRatio = isEqualRatio;
        this.noShowSuffix = noShowSuffix;
        this.imgByte = imgByte;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean isEqualRatio() {
        return isEqualRatio;
    }

    public void setEqualRatio(boolean isEqualRatio) {
        this.isEqualRatio = isEqualRatio;
    }

    public boolean isNoShowSuffix() {
        return noShowSuffix;
    }

    public void setNoShowSuffix(boolean noShowSuffix) {
        this.noShowSuffix = noShowSuffix;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
