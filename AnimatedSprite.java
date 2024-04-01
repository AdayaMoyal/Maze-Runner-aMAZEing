package com.example.appbyadaya;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class AnimatedSprite {
    private int x;          //x coordinate on screen
    private int y;          // y coordinate on screen
    private int xStep;      // when you move horizontal... what is the step length
    private int yStep;      // when you move vertical... what is the step length
    private Bitmap myPic;   // A picture for the sprite (how do I look?)
    private int width;      // Screen width
    private int height;     // Screen height
    private int imgH;       // Picture / Image height (calculated)
    private int imgW;       // Picture / Image width (calculated)
    AnimatedSprite(int xStart, int yStart, Bitmap pic, int scrW, int scrH){
        x=xStart;
        y=yStart;
        xStep=20;
        yStep=0;
        myPic=pic;
        width=scrW;
        height=scrH;
        imgH = pic.getHeight();
        imgW = pic.getWidth();
    }
    public int getX()
    {
        return x;
    }
    public void setY(int newY)
    {
        y = newY;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(myPic,x,y,null);
    }

    public void move() {
        x = x - xStep;
        if (x+imgW<=0) x=imgW-20;
    }

    public void setPic (Bitmap updPic)
    {
        myPic = updPic;
    }
}
