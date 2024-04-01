package com.example.appbyadaya;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AlertDialog;

public class GameManager extends SurfaceView implements Runnable {
    private int scrHeight;
    private int scrWidth;
    private Canvas myCanvas;
    private SurfaceHolder holder;
    private Bitmap aPic1;
    private Bitmap aPic2;
    private Paint bgPaint;
    private AnimatedSprite as1;
    private AnimatedSprite as2;
    private Thread thread;
    private Bitmap [] characterPics;
    private Runner runner;
    private TNT tnt;
    private int count;
    private boolean start;

    GameManager(Context context, int width, int height) {
        super(context);
        scrWidth = width;
        scrHeight = height;
        holder = getHolder();
        myCanvas = new Canvas();
        bgPaint = new Paint();
        bgPaint.setColor(Color.WHITE);
        aPic1 = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        aPic2 = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        as1 = new AnimatedSprite(0,-50, aPic1,scrWidth,scrHeight);
        as2 = new AnimatedSprite(as1.getX() + aPic1.getWidth(),-50, aPic2,scrWidth,scrHeight);
        count = 0;
        thread = new Thread(this);
        setPicturesInArray();
        start = true;
        runner = new Runner(15, 890, characterPics);
        tnt = new TNT(scrWidth, 950, BitmapFactory.decodeResource(getResources(), R.drawable.tnt));
        thread.start();
    }

    private void drawSurface() {
        if (holder.getSurface().isValid() == true)
        {
            myCanvas = holder.lockCanvas();
            as1.draw(myCanvas);
            as2.draw(myCanvas);
            runner.draw(myCanvas);
            tnt.draw(myCanvas);
            holder.unlockCanvasAndPost(myCanvas);
        }
    }

    @Override
    public void run()
    {
        while (true) {
            drawSurface();
            if (start == true) {
                runner.updatePic();
                count++;
            }
            as1.move();
            as2.move();
            as1.setPic(aPic1);
            as2.setPic(aPic2);
            tnt.move();
            if(tnt.collide(runner.getX(), runner.getY()) == true)
            {
                Log.d("tag", "collide");
                break;
            }
            try {
                thread.sleep(35);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPicturesInArray()
    {
        characterPics = new Bitmap[12];
        characterPics[0] = BitmapFactory.decodeResource(getResources(), R.drawable.stand);
        characterPics[1] = BitmapFactory.decodeResource(getResources(), R.drawable.run1);
        characterPics[2] = BitmapFactory.decodeResource(getResources(), R.drawable.run2);
        characterPics[3] = BitmapFactory.decodeResource(getResources(), R.drawable.run3);
        characterPics[4] = BitmapFactory.decodeResource(getResources(), R.drawable.run4);
        characterPics[5] = BitmapFactory.decodeResource(getResources(), R.drawable.run5);
        characterPics[6] = BitmapFactory.decodeResource(getResources(), R.drawable.run6);
        characterPics[7] = BitmapFactory.decodeResource(getResources(), R.drawable.jump1);
        characterPics[8] = BitmapFactory.decodeResource(getResources(), R.drawable.jump2);
        characterPics[9] = BitmapFactory.decodeResource(getResources(), R.drawable.jump3);
        characterPics[10] = BitmapFactory.decodeResource(getResources(), R.drawable.die1);
        characterPics[11] = BitmapFactory.decodeResource(getResources(), R.drawable.die2);
    }

    public void sleep()
    {
        try {
            thread.sleep(170);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void characterDie()
    {
        runner.setPic(9);
        sleep();
        runner.setPic(10);
        sleep();
        runner.setY(1050);
        runner.setPic(11);
        sleep();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        start = false;
        count = 11;
        runner.setY(650);
        runner.setPic(7);
        sleep();
        runner.setY(700);
        runner.setPic(8);
        sleep();
        runner.setY(810);
        runner.setPic(9);
        sleep();
        invalidate();
        runner.setY(890);
        start = true;
        return false;
    }
}

