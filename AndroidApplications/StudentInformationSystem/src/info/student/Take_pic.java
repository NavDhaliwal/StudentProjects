package info.student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.hardware.Camera;
public class Take_pic extends Activity implements SurfaceHolder.Callback
{
	SurfaceView mSurfaceView;
	SurfaceHolder mSurfaceHolder;
	Camera mCamera;
	boolean mPreviewRunning=true;
	static StudentDB db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().setFormat(PixelFormat.TRANSLUCENT);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.take_pic);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

		WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//setContentView(R.layout.camera_surface);

		mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);


		mSurfaceHolder = mSurfaceView.getHolder();

		mSurfaceHolder.addCallback(this);

		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);



		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (mPreviewRunning) {
			mCamera.stopPreview();
		}
		Camera.Parameters p = mCamera.getParameters();

		p.setPreviewSize(100,150);

		mCamera.setParameters(p);

		try {

		mCamera.setPreviewDisplay(holder);

		} catch (IOException e) {


		e.printStackTrace();

		}

		mCamera.startPreview();

		mPreviewRunning = true;



		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mCamera = Camera.open();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mPreviewRunning = false;
		mCamera.release();
	}
	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] imageData, Camera c) 
		{
			Bitmap bitmapimage = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
			Cursor cr=db.getAllInfo();
			cr.moveToLast();
			int img=cr.getInt(0);
			String filepath = "/sdcard/"+"s"+img+".png";
			cr.close();
			File imagefile = new File(filepath);
			try
			{
			FileOutputStream fos = new FileOutputStream(imagefile); 
			bitmapimage.compress(CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
			Intent in=new Intent("submit.back");
			startActivity(in);
			
			}
			catch(IOException e)
			{
				Toast.makeText(Take_pic.this,"File not Found",(int) System.currentTimeMillis()).show();
			}

		}

		};

static void getDB(StudentDB db)
{
	Take_pic.db=db;
}

		

}
