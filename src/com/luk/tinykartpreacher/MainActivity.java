package com.luk.tinykartpreacher;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private float mPreviousX = 0;
	private float mPreviousY = 0;

	private GLSurfaceView openGLView;
	private Cube3dRenderer mRenderer = new Cube3dRenderer(this);
//	private LukasynoShaderRendererGLES20 mRenderer = new LukasynoShaderRendererGLES20();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		openGLView = (GLSurfaceView)findViewById(R.id.rabarbar);
		openGLView = new GLSurfaceView(this);
		openGLView.setRenderer(mRenderer);
//		openGLView.setEGLContextClientVersion(2);
		openGLView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent e) {
				float x = e.getX();
				float y = e.getY();

				switch (e.getAction()) {
				case MotionEvent.ACTION_MOVE:

					float dx = x - mPreviousX;
					float dy = y - mPreviousY;

					if (y > v.getHeight() / 2) {
						dx = dx * -1;
					}
					if (x < v.getWidth() / 2) {
						dy = dy * -1;
					}

					mRenderer.setAngleZ(mRenderer.getAngleZ()
							+ ((dx) * TOUCH_SCALE_FACTOR));

					mRenderer.setAngleY(mRenderer.getAngleY()
							+ ((dy) * TOUCH_SCALE_FACTOR));
//					mRenderer.setAngle(mRenderer.getAngle()
//							+ ((dy) * TOUCH_SCALE_FACTOR));
				}

				mPreviousX = x;
				mPreviousY = y;
				return true;
			}

		});

		
//		SeekBar barZ = (SeekBar)findViewById(R.id.seekBar1);
//		SeekBar barY = (SeekBar)findViewById(R.id.seekBar2);
//
//		barZ.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//			@Override
//			public void onStopTrackingTouch(SeekBar seekBar) {
//			}
//
//			@Override
//			public void onStartTrackingTouch(SeekBar seekBar) {
//			}
//
//			@Override
//			public void onProgressChanged(SeekBar seekBar, int progress,
//					boolean fromUser) {
//				mRenderer.setZAngle(progress);
//			}
//		});
//		barY.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//			@Override
//			public void onStopTrackingTouch(SeekBar seekBar) {
//			}
//
//			@Override
//			public void onStartTrackingTouch(SeekBar seekBar) {
//			}
//
//			@Override
//			public void onProgressChanged(SeekBar seekBar, int progress,
//					boolean fromUser) {
//				mRenderer.setYAngle(progress);
//			}
//		});
		
//		RelativeLayout lay = (RelativeLayout)findViewById(R.id.lays);
	//	View view = (View)findViewById(R.id.rabarbar);
		//view = (GLSurfaceView)openGLView;
		
		
//		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
//		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
//
//		if (supportsEs2) 
//		{
//			// Request an OpenGL ES 2.0 compatible context.
//			openGLView.setEGLContextClientVersion(2);
//
//			// Set the renderer to our demo renderer, defined below.
//			openGLView.setRenderer(new WithShaderRendering());
//		} 
//		else 
//		{
//			// This is where you could create an OpenGL ES 1.x compatible
//			// renderer if you wanted to support both ES 1 and ES 2.
//			return;
//		}
		
		
		setContentView(openGLView);
		
		openGLView.setPreserveEGLContextOnPause(true);

	}

	@Override
	protected void onResume() {
		super.onResume();
		openGLView.onResume();
	}

	@Override
	protected void onPause() {
		openGLView.onPause();
	}

	@Override
	protected void onStop() {
	}

	@Override
	protected void onDestroy() {

	}
}
