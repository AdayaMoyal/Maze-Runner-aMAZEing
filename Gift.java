package com.example.appbyadaya;

import android.graphics.Bitmap;

public class Gift {
    private int x;
    private int y;
    private Bitmap pic;

    Gift()
    {

    }

    Gift(int startX, int startY, Bitmap giftPic)
    {
        x = startX;
        y = startY;
        pic = giftPic;
    }

    public void move()
    {

    }
}
