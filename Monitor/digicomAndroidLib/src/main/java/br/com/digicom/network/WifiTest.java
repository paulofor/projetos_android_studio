package br.com.digicom.network;

import br.com.digicom.so.Dispositivo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class WifiTest {

	
	public static boolean existe(Context contexto) {
		ConnectivityManager connManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (Dispositivo.emulador()) return true;
		
		if (mWifi.isConnected()) {
		    return true;
		} else {
			return false;
		}
	}
}
