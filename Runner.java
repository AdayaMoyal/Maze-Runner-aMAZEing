package com.example.appbyadaya;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Runner {
    private int x;
    private int y;
    private Bitmap[] characterPics;
    private int count;
    private int currentPic;
    private int speed;

    public Runner(int startX, int startY, Bitmap[] pics) {
        x = startX;
        y = startY;
        characterPics = pics;
        count = 0;
        currentPic = 0;
        speed = 2;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(characterPics[currentPic], x, y, null);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void updatePic()
    {
        if (count >= speed)
        {
            currentPic++;
            if (currentPic > 6 || currentPic >= characterPics.length) {
                currentPic = 1;
            }
            count = 1;
        }
        else {
            count++;
        }
    }

    public void setY(int newY)
    {
        y = newY;
    }

    public void setPic(int pic)
    {
        currentPic = pic;
    }
}