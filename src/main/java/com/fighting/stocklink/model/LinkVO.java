package com.fighting.stocklink.model;


import lombok.Data;

@Data
public class LinkVO {

    /**
     * 排序，用来展示图片的顺序
     */
    private Integer order;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 链接名称
     */
    private String linkName;

    /**
     * 链接描述，用来描述指标的作用
     */
    private String linkDescribe;

}
