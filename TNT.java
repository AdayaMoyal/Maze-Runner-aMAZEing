package com.example.appbyadaya;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

public class TNT {
    private int start;
    private int x;
    private int y;
    private Bitmap pic;
    private int imgW;
    private int step;
    final int min = 25;
    final int max = 37;
    TNT()
    {

    }
    TNT(int startX, int startY, Bitmap TNTpic)
    {
        start = startX;
        x = startX;
        y = startY;
        pic = TNTpic;
        step = 20;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(pic,x,y,null);
    }

    public void move()
    {
        x -= step;
        if(x <-300)
        {
            x = start;
            step = new Random().nextInt((max - min) + 1) + min;//random number
        }
    }

    public boolean collide(int checkX, int checkY)
    {
        if(x == checkX)
        {
            return true;
        }
        return false;
    }
}
