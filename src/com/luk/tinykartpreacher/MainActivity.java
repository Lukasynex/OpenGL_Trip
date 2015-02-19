package com.luk.tinykartpreacher;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {

	private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	private float mPreviousX = 0;
	private float mPreviousY = 0;
	private GLSurfaceView openGLView;
	private Cube3dRenderer renderer = new Cube3dRenderer(this);
//	private OpenGLRenderer a = new OpenGLRenderer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		openGLView = new GLSurfaceView(this);
		openGLView.setRenderer(renderer);
//		openGLView.setRenderer(new SquareRenderer(true));
		
//		openGLView.setRenderer(a);
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

					if(Math.abs(dx) > Math.abs(3*dy))
					
					renderer.setAngleZ(renderer.getAngleZ()
							+ ((-dx) * TOUCH_SCALE_FACTOR));
					else if(Math.abs(dy) > Math.abs(3*dx))
					renderer.setAngleY(renderer.getAngleY()
							+ ((dy) * TOUCH_SCALE_FACTOR));
				}

				mPreviousX = x;
				mPreviousY = y;
				return true;
			}

		});
		setContentView(openGLView);

		openGLView.setPreserveEGLContextOnPause(true);

	}

	@Override
	protected void onResume() {
		super.onResume();
		openGLView.onResume();
//		renderer.onResume();
	}

	@Override
	protected void onPause() {
		openGLView.onPause();
//		renderer.onPause();
	}

	@Override
	protected void onStop() {
	}

	@Override
	protected void onDestroy() {

	}
}
