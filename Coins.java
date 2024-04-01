package com.example.appbyadaya;

import android.graphics.Bitmap;

public class Coins {
    private int start;
    private int x;
    private int y;
    private Bitmap pic;

    Coins()
    {

    }

    Coins(int startX, int startY, Bitmap coinPic)
    {
        start = startX;
        x = startX;
        y = startY;
        pic = coinPic;
    }
}
