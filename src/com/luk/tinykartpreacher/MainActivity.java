package com.luk.tinykartpreacher;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {

	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private float mPreviousX = 0;
	private float mPreviousY = 0;
	
	private Cube3dRenderer mRenderer = new Cube3dRenderer();
	
	private GLSurfaceView openGLView;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        openGLView = new GLSurfaceView(this);
        openGLView.setRenderer(mRenderer);
        openGLView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				float x = e.getX();
			    float y = e.getY();

			    switch (e.getAction()) {
			        case MotionEvent.ACTION_MOVE:

			            float dx = x - mPreviousX;
			            float dy = y - mPreviousY;

			            // reverse direction of rotation above the mid-line
			            if (y > v.getHeight() / 2) {
			              dx = dx * -1 ;
			            }

			            // reverse direction of rotation to left of the mid-line
			            if (x < v.getWidth() / 2) {
			              dy = dy * -1 ;
			            }

			            mRenderer.setAngle(
			                    mRenderer.getAngle() +
			                    ((dx + dy) * TOUCH_SCALE_FACTOR));
			            //requestRender();
			    }

			    mPreviousX = x;
			    mPreviousY = y;
			    return true;
				
				
			    //return false;
					
			}
			
		});
//        view = new CustomGLSurfaceView(this);
//        setContentView(view);
        setContentView(openGLView);
        openGLView.setPreserveEGLContextOnPause(true);
    
    }
    @Override
    protected void onResume(){
    	super.onResume();
    	openGLView.onResume();
    }
    @Override
    protected void onPause(){
    	openGLView.onPause();
    }
    @Override
    protected void onStop(){
    }
    @Override
    protected void onDestroy(){
    	
    }
}
