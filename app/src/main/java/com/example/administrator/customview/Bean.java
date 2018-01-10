package com.example.administrator.customview;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/10
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class Bean {

    private int imageId;
    private String tittle;

    public Bean(int imageId, String tittle) {
        this.imageId = imageId;
        this.tittle = tittle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
