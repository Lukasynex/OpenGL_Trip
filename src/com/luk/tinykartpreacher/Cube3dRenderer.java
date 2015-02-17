package com.luk.tinykartpreacher;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import android.view.MotionEvent;

public class Cube3dRenderer implements Renderer {

	private Context context;
	private volatile float angleX = 0.0f;
	private volatile float angleY = 0.0f;
	private volatile float angleZ = 0.0f;
	
	
	private Custom3dCube cube;
//	private TexturedCube cube;
	private SecondCube cube2;


	public Cube3dRenderer(Context context) {
		cube = new Custom3dCube();
		cube2 = new SecondCube();
//		cube = new TexturedCube();
		this.context = context;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
//		cube.loadGLTexture(gl, this.context);
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	
		
		gl.glDisable(GL10.GL_DITHER);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(.8f, 0f, .2f, 1f);
		gl.glClearDepthf(1f);
	}
	@Override
	public void onDrawFrame(GL10 gl) {

		gl.glDisable(GL10.GL_DITHER);

		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		//tak było
//		GLU.gluLookAt(gl, 0, 0, - 5, 0, 0, 0, 0, 2, 0);
		GLU.gluLookAt(gl, 3, 0, - 8, 0, 0, 0, 0, 2, 0);
		
//		long time = SystemClock.uptimeMillis() % 4000L;
//		float angle = 0.090f * ((int) time);
		
//		gl.glRotatef(angleX, 1, 0, 0);
		gl.glRotatef(angleZ, 0, 1, 0);
		
		cube.draw(gl);
		gl.glRotatef(angleY, 0, 0, 1);
		cube2.draw(gl);
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, .5f, 1, 25);

	}

	public void setAngleX(float f) {
		angleX = f;
	}
	public void setAngleY(float f) {
		angleY = f;
	}
	public void setAngleZ(float f) {
		angleZ = f;
	}
	public float getAngleX(){
		return angleX;
	}
	public float getAngleY(){
		return angleY;
	}
	public float getAngleZ(){
		return angleZ;
	}
	
	
}
