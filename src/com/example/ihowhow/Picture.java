package com.example.ihowhow;

import java.util.Timer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

public class Picture extends View{
	private  Bitmap mBitmap,myBitmap;  
    private  Canvas mCanvas;  
    private  Path mPath;  
    private  Paint mBitmapPaint;  
    private  Paint mPaint;  
    private  Handler bitmapHandler;  
  
    private  Timer timer;  
    DisplayMetrics dm;  
    private int w,h;
    
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint =  new  Paint();  
        mPaint.setAntiAlias( true );  
        mPaint.setDither( true );  
        mPaint.setColor( 0xFF00FF00 );  
        mPaint.setStyle(Paint.Style.STROKE);  


          
        //�e���j�p   
        mBitmap = Bitmap.createBitmap(w, h,   
            Bitmap.Config.ARGB_8888);  
        mCanvas =  new  Canvas(mBitmap);   //�Ҧ�mCanvas�e���F�賣�Q�O�s�b�FmBitmap��  
          
        mCanvas.drawColor(Color.TRANSPARENT);  
        mPath =  new  Path();  
        mBitmapPaint =  new  Paint(Paint.DITHER_FLAG);  
      
		canvas.drawBitmap(mBitmap,  0 ,  0 , mBitmapPaint);      //����ª��e��         
        canvas.drawPath(mPath, mPaint);   //�e�̫᪺path  
        
        Bitmap  bitmap = null ; //Bitmap��H  
        bitmap =((BitmapDrawable)getResources().getDrawable(R.drawable.hahah)).getBitmap();  
        canvas.drawBitmap(bitmap, 50, 50, null); //ø�s�Ϲ�  
	}

	public Picture(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		 dm =  new  DisplayMetrics();  
		 ((MainActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);  
		 w = dm.widthPixels;  
		 h = dm.heightPixels;
	}

	

}
