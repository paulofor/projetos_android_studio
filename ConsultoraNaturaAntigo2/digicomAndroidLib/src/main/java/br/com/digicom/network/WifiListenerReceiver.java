package br.com.digicom.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import br.com.digicom.servico.WifiServicoI;


// Uniao das outras duas classes do pacote
public class WifiListenerReceiver extends BroadcastReceiver {

	private WifiServicoI servico = null;
	
	public void setServico(WifiServicoI servicoEscuta) {
		servico = servicoEscuta;
	}
	
	public void registraReceiver(Context ctx) {
		IntentFilter intentFilter = new IntentFilter();
		//intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
		intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		ctx.registerReceiver(this, intentFilter);
	}
	
	@Override
	public void onReceive(Context ctx, Intent intent) {
		ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		if (isConnected) {
			boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
			if (isWiFi) {
				entrouNoWifi();
			}
		} else {
			saiuDoWifi();
		}
		/*
		final String action = intent.getAction();
	    if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
	        if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
	            entrouNoWifi();
	        } else {
	        	saiuDoWifi();
	        }
	    }
	    */
	}
	
	private void entrouNoWifi() {
		if (servico!=null) {
			servico.entrouWifi();
		}
	}
	
	private void saiuDoWifi() {
		if (servico!=null) {
			servico.saiuWifi();
		}
	}
}
