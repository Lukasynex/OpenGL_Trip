package com.luk.tinykartpreacher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class SecondCube {

	// private float vertices[] = {
	// 1, 1, -1,// p0 - topFrontRight
	// 1, -1, -1, // p1 bottomFront Right
	// -1, -1, -1, // p2 bottomFront Left
	// -1, 1, -1, // p3 top Front Left
	// 1, 1, 1, // p4
	// 1, -1, 1, // p5
	// -1, -1, 1, // p6
	// -1, 1, 1, // p7
	// };
	
	//good verticies
	 private float vertices[] = {
	 3, 0.2f, -0.2f,// p0 - topFrontRight
	 3, -0.2f, -0.2f, // p1 bottomFront Right
	 -0.2f, -0.2f, -0.2f, // p2 bottomFront Left
	 -0.2f, 0.2f, -0.2f, // p3 top Front Left
	 3, 0.2f, 0.2f, // p4
	 3, -0.2f, 0.2f, // p5
	 -0.2f, -0.2f, 0.2f, // p6
	 -0.2f, 0.2f, 0.2f, // p7
	 };

//	 private float vertices[] = {
//	 3, 1, 0,// p0 - topFrontRight
//	 3, 0, 0, // p1 bottomFront Right
//	 0, 0, 0, // p2 bottomFront Left
//	 0, 1, 0, // p3 top Front Left
//	 3, 1, 1, // p4
//	 3, 0, 1, // p5
//	 0, 0, 1, // p6
//	 0, 1, 1, // p7
//	 };

	private FloatBuffer vertBuff;

	private short[] pIndex = { 3, 4, 0, 0, 4, 1, 3, 0, 1, 3, 7, 4, 7, 6, 4, 7,
			3, 6, 3, 1, 2, 1, 6, 2, 6, 3, 2, 1, 4, 5, 5, 6, 1, 6, 5, 4

	};
	// private float rgbaVals[] = {
	// 1, 1, 0, 0.5f,
	// .25f, 0, .85f, 1,
	// 0,1, 1, 1
	// };

	// private ShortBuffer colorBuff;

	private ShortBuffer pBuff;

	public SecondCube() {
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);

		ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);

	}

	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColor4f(0.2f, 0.3f, 0.6f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length,
				GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}

	int textures[] = new int[1];

	public void loadGLTexture(GL10 gl, Context context, int tex) {
		// Get the texture from the Android resource directory
		InputStream is = context.getResources().openRawResource(tex);
		// R.drawable.texture
		Bitmap bitmap = null;
		try {
			// BitmapFactory is an Android graphics utility for images
			bitmap = BitmapFactory.decodeStream(is);
			// bitmap = BitmapFactory.decodeResource(getResources(),
			// R.drawable.)

		} finally {
			// Always clear and close
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}
		}

		// Generate one texture pointer...
		gl.glGenTextures(1, textures, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		// Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		// Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);

		// Use the Android GLUtils to specify a two-dimensional texture image
		// from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// Clean up
		bitmap.recycle();
	}
}
