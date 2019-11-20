package br.com.digicom.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.com.digicom.log.DCLog;
import br.com.digicom.quadro.IFragment;

public class UtilImagem {

	private static UtilImagem instancia = new UtilImagem();
	
	public static UtilImagem getInstancia() {
		return instancia;
	}
	
	public Bitmap getBitmapFromUrl(String src) {
		try {
			ThreadImagem thread = new ThreadImagem();
			DCLog.d(DCLog.MULTIMIDIA, this, "Obtendo imagem");
			thread.setUrl(src);
			thread.start();
			thread.join();
			return thread.getBitmap();
		} catch (InterruptedException e) {
			DCLog.e(DCLog.MULTIMIDIA, this, e);
			return null;
		}
	}
}
