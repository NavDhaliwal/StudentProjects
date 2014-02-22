package game.airhockey;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AirHockey extends Activity {
    /** Called when the activity is first created. */
	GestureDetector gestures;
	FrameLayout frame;
	float x=0,y=0;
	float velX,velY,accX,accY,vX,vY,dx,dy,fx,fy,m;
	float acc=10;
	MyView v;
	//ImageView pusher;
	 LinearLayout lay;
    //private Matrix translate;  
    private Bitmap droid;
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        frame = (FrameLayout) findViewById(R.id.graphics_holder);  
         lay=(LinearLayout)findViewById(R.id.lay);
         
        //frame.addView(image);
         //pusher=(ImageView)findViewById(R.id.pusher); 
        // pusher.layout(10,20,10,20);
        // pusher.bringToFront();
        
         //pusher.scrollTo(10,100);
         //translate = new Matrix();  
         gestures = new GestureDetector(this,new GestureListener());  
         droid = BitmapFactory.decodeResource(getResources(),  
                 R.drawable.pusher); 
         v=new MyView(this);
         
         frame.addView(v);
    }
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
       // v.onMove(event.getX(),event.getY());
    	return gestures.onTouchEvent(event);  
    }  
    private class GestureListener implements GestureDetector.OnGestureListener,  
    GestureDetector.OnDoubleTapListener {  
 
//public GestureListener(PlayAreaView view) {  
   // this.view = view;  
//}
@Override
public boolean onDown(MotionEvent event) {
	// TODO Auto-generated method stub
	Log.d("onDown","Yes");
	x=event.getX();
	y=event.getY();
	Log.d("X=",""+x);
    Log.d("Y=",""+y);
	v.onMove();
	return true;
}
@Override
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY) {
	// TODO Auto-generated method stub
	
	final float distanceTimeFactor = 0.3f;  
    final float totalDx = (distanceTimeFactor * velocityX/2);  
    final float totalDy = (distanceTimeFactor * velocityY/2);  
  velX=velocityX;
   velY=velocityY;
   accX=velX*velX/(2*totalDx);
   accY=velY*velY/(2*totalDy);
   dx=e2.getX();
   dy=e2.getY();
   fx=dx+totalDx;
   fy=dy+totalDy;
   acc=10;
   m=(fy-dy)/(fx-dx);
   v.onAnimateMove(totalDx, totalDy,  
           (long) (1000 * distanceTimeFactor)); 
   Log.d("On Fling dx",""+dx); 
   Log.d("On Fling dy",""+dy);
   Log.d("On Fling Totaldx",""+totalDx); 
   Log.d("On Fling Totaldy",""+totalDy);
   Log.d("On Fling velocityX",""+velocityX);
   Log.d("On Fling velocityY",""+velocityY);
   Log.d("On Fling duration",""+(long) (1000 * distanceTimeFactor));
    return true;
           
	//return false;
}
@Override
public void onLongPress(MotionEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
		float distanceY) {
	// TODO Auto-generated method stub
	x=e2.getX();
	y=e2.getY();
	dx=x;
	dy=y;
	Log.i("X = ",""+x);
	Log.i("Y = ",""+y);
	v.onMove();
	//pusher.scrollTo((int)e2.getX(),(int)e2.getY());
	
	return true;
}
@Override
public void onShowPress(MotionEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public boolean onSingleTapUp(MotionEvent event) {
	// TODO Auto-generated method stub
	//Log.d("onSingle tap","Yes");
	return false;
}
@Override
public boolean onDoubleTap(MotionEvent e) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean onDoubleTapEvent(MotionEvent e) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean onSingleTapConfirmed(MotionEvent e) {
	// TODO Auto-generated method stub
	//Log.d("onSingleTap Confirmed","Yes");
	return false;
}  
}

   
        
    
    private class MyView extends View
    {
    	public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
    	////////change///////////////////////////////
    	  
    	private OvershootInterpolator animateInterpolator;  
    	private long startTime;  
    	private long endTime;  
    	private float totalAnimDx;  
    	private float totalAnimDy;  
    	  
    	public void onAnimateMove(float dx, float dy, long duration) {  
    	   // animateStart = new Matrix(translate);  
    	    animateInterpolator = new OvershootInterpolator();  
    	    startTime = System.currentTimeMillis();  
    	    endTime = startTime + duration;  
    	    totalAnimDx = dx;  
    	    totalAnimDy = dy;
    	    velX/=10;
    	    velY/=10;
    	    post(new Runnable() {  
    	        @Override  
    	        public void run() {  
    	            onAnimateStep();  
    	        }  
    	    });  
    	}
    	
    	private void onAnimateStep() {  
    	    long curTime = System.currentTimeMillis();  
    	    float percentTime = (float) (curTime - startTime)  
    	            / (float) (endTime - startTime);  
    	    float percentDistance = ((android.view.animation.Interpolator) animateInterpolator)  
    	            .getInterpolation(percentTime);  
    	    //float curDx = percentDistance * totalAnimDx;  
    	    //float curDy = percentDistance * totalAnimDy;
    	    //percentTime*=10;
    	     
    	   /* accX=-10;
    	    accY=-10;
    	    vX=velX+accX*percentTime;
    	    vY=velY+accY*percentTime;
    	    x=dx+(vX*vX-velX*velX)/(2*accX);
    	    y=dy+(vY*vY-velY*velY)/(2*accY);
    	    velX=vX;
    	    velY=vY;
    	    */
    	    if(x>=lay.getRight()-1)
    	    {
    	    	velX=-velX;
    	    	m=-m;
    	    	dx=x;
    	    	dy=y;
    	    	//Log.d("lay.getRight()",""+lay.getRight());
    	    }
    	    if(x<=lay.getLeft()+1)
    	    {
    	    	velX=-velX;
    	    	m=-m;
    	    	dx=x;
    	    	dy=y;
    	    	//Log.d("lay.getRight()",""+lay.getRight());
    	    }
    	    if(y>=lay.getBottom()-1)
    	    {
    	    	//velX=-velX;
    	    	m=-m;
    	    	dx=x;
    	    	dy=y;
    	    	//Log.d("lay.getRight()",""+lay.getRight());
    	    }
    	    if(y<=lay.getTop()+1)
    	    {
    	    	//velX=-velX;
    	    	m=-m;
    	    	dx=x;
    	    	dy=y;
    	    	//Log.d("lay.getRight()",""+lay.getRight());
    	    }
    	    //if(fx!=dx)
    	   // {
    	    	   
    	    x=x+acc*velX/Math.abs(velX);
    	    y=dy+m*(x-dx);
    	    //}
    	    	 //  else
    	    	  // {
    	    	//	   x=dx;
    	    	//	   y=y+acc*velY/Math.abs(velY);
    	    
    	//}
    	    acc-=.1;
    	    
    	     //x+=(velX*percentTime+accX*percentTime*percentTime/2)/400;
    	     //y+=(velY*percentTime+accY*percentTime*percentTime/2)/400;
    	     Log.d("percentTime=",""+percentTime);
    	     Log.d("X=",""+x);
    	     Log.d("Y=",""+y);
    	    //translate.set(animateStart);
    	  //  x=curDx;
    	  //  y=curDy;
    	    onMove();  
    	  
    	    //Log.v(DEBUG_TAG, "We're " + percentDistance + " of the way there!");  
    	    //percentTime < 1.0f&&
    	    if (acc>0) 
    	    {  
    	        post(new Runnable() 
    	        {  
    	            @Override  
    	            public void run() 
    	            {  
    	                onAnimateStep();  
    	            }  
    	        });  
    	    }  
    	}  
    	///////change END////////////////////////////
		protected void onDraw(Canvas canvas) {  
            
        	//Log.d("R u in onDraw","Yes");
        	//canvas.drawBitmap(droid, translate, null);
        	canvas.drawBitmap(droid,x-23,y-70,null);
        	//Paint p=new Paint();
        	//p.setColor(Color.RED);
        	//canvas.drawCircle(x,y,20,p);
        	
        	
        }
        public void onMove() {  
        	//if(curDx>=frame.getLeft() && curDx<=frame.getRight() && curDy<=frame.getBottom() && curDy>=frame.getTop())  
        	{   
        		//Log.i("CurX...........",Float.toString(curDx));
             
            
            invalidate();
         
        	}
        }  
    }
    
      
   
    
      
}