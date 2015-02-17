package com.luk.tinykartpreacher;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private GLSurfaceView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        openGLView = new GLSurfaceView(this);
        openGLView.setRenderer(
//        		new CustomLukasynoRenderer()
        		new Cube3dRenderer()
        		
        		
        		);
        
        setContentView(openGLView);
    
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
}
