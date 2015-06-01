package com.example.ihowhow;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private static final int MENU_TAKE_PICTURE = Menu.FIRST,MENU_SHOW_PICTURE = Menu.FIRST + 1 ;
	
	private Camera mCamera;
	private CameraPreview mCamPreview ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        mCamPreview = new CameraPreview(this);
        setContentView(mCamPreview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
    	menu.add(0,MENU_TAKE_PICTURE,0,"�Ӭ�");
    	menu.add(0,MENU_SHOW_PICTURE,0,"��ܷӤ�");
        return true;
    }

    /* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mCamera = Camera.open();
		mCamPreview.set(this, mCamera);
		
		super.onResume();
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
		super.onPause();
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
		switch (item.getItemId()) {
		case MENU_TAKE_PICTURE:
			mCamera.takePicture(camShutterCallback, camRawDataCallback, camJpegCallback);
			break;

		default:
			break;
		}
        return super.onOptionsItemSelected(item);
    }
	
	ShutterCallback camShutterCallback = new ShutterCallback() {
		public void onShutter() {
			// �q���ϥΪ̤w�������,�Ҧp�o�X�@���n��
			Toast.makeText(MainActivity.this, "success�I", Toast.LENGTH_SHORT)
			.show();
		}
	};

	PictureCallback camRawDataCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// �Ψӱ�����l���v�����
		}
	};

	PictureCallback camJpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// �Ψӱ������Y��jpeg�榡���v�����

			FileOutputStream outStream = null;
			try {
				outStream = new FileOutputStream("sdcard/photo.jpg");
				outStream.write(data);
				outStream.close();
			} catch (IOException e) {
				Toast.makeText(MainActivity.this, "�v�����x�s���~�I", Toast.LENGTH_SHORT)
					.show();
			}

			mCamera.startPreview();
		}
	};
}
