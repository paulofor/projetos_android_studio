package br.com.digicom.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.digicom.log.DCLog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ThreadImagem extends Thread {
	
	private String urlStr = null;
	private Bitmap bitmap = null;
	
	public void setUrl(String valor) {
		urlStr = valor;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}

	@Override
	public void run() {
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			DCLog.e(DCLog.MULTIMIDIA, this, e);
			e.printStackTrace();
		}
		
	}

}
