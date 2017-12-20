package com.lionkwon.kwonutils.capture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.lionkwon.kwonutils.log.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 
 * @FileName  : Capture.java
 * @Project     : KwonUtils
 * @Date         : 2014. 1. 7. 
 * @작성자      : lionkwon
 * @변경이력 :
 * @프로그램 설명 :
 * 
 * 				 Capture cap = new Capture(MainActivity.this, "testdirectory", "test");
				 cap.shot(getWindow().getDecorView());
				 
 */
public class Capture {

	Context context;
	String directory;
	String filename;
	
	public Capture(Context context, String directory, String filename) {
		this.context = context;
		this.directory = directory;
		this.filename = filename;
	}

	private void screenshot(Bitmap bm) {
		try {
			File path = new File(Environment.getExternalStorageDirectory()+"/"+directory);
			if(! path.isDirectory()) {
				path.mkdirs();
			}

			File f = new File(Environment.getExternalStorageDirectory()+"/"+directory, filename+".png");
			f.createNewFile();
			OutputStream outStream = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			outStream.close();

			context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
					Uri.parse("file://" + Environment.getExternalStorageDirectory())));
		} catch (Exception e) { 
			Logger.error(e);
		}            
	}
	
	
	/**
	 * 
	 * @Method Name  : shot
	 * @작성일   : 2014. 1. 7. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param v1
	 */
	public void shot(View v1){
		try {
			v1.setDrawingCacheEnabled(true);
			Bitmap bm = v1.getDrawingCache();
			screenshot(bm);
			v1.setDrawingCacheEnabled(false);
		} catch (Exception e) {
			Logger.error(e);
		}
	}
	
	public void shot(){
		try {
			View v1 = ((Activity) context).getWindow().getDecorView();
			v1.setDrawingCacheEnabled(true);
			Bitmap bm = v1.getDrawingCache();
			screenshot(bm);
			v1.setDrawingCacheEnabled(false);
		} catch (Exception e) {
			Logger.error(e);
		}
	}
}
