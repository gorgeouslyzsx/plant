package com.example.plant.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class plantBean implements Serializable {
    private static final long serialVersionUID=1L;
    private String bookphotoBase64; // 图书图片的Base64编码字符串
    private transient Bitmap bookphoto;  //图书图片
    private String booknum;  //图书编号
    private String bookname;  //图书名
    private String bookauthor; //作者
    private String bookpress; //出版社
    private String introduct; //图书简介
    private Integer  bookprice; //图书价格
    private Integer number;//购物车每个图书数量
    public Bitmap getbookphoto() {
        if (bookphoto == null && bookphotoBase64 != null) {
            byte[] decodedBytes = Base64.decode(bookphotoBase64, Base64.DEFAULT);
            bookphoto = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }
        return bookphoto;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setbookphotoBase64(String bookphotoBase64) {
        this.bookphotoBase64 = bookphotoBase64;
    }

    public String getbooknum() {
        return booknum;
    }

    public void setbooknum(String booknum) {
        this.booknum = booknum;
    }

    public String getbookname() {
        return bookname;
    }

    public void setbookname(String bookname) {
        this.bookname = bookname;
    }

    public String getbookauthor() {
        return bookauthor;
    }

    public void setbookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public String getbookpress() {
        return bookpress;
    }

    public void setbookpress(String bookpress) {
        this.bookpress = bookpress;
    }

    public String getIntroduct() {
        return introduct;
    }
    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }
    public Integer  getbookprice() {
        return bookprice;
    }
    public void setbookprice(Integer bookprice) {
        this.bookprice = bookprice;
    }

}
