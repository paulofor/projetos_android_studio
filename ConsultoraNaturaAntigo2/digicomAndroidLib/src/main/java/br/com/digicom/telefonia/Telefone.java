package br.com.digicom.telefonia;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;

public class Telefone {

	
	public static String getNumero(Context ctx) {
		TelephonyManager tMgr =(TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		String mPhoneNumber = tMgr.getLine1Number();
		return mPhoneNumber.trim();
		//return "+552192902732";
	}
	

	
	
	
	
	public static float getNivelBateria(Context ctx) {
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = ctx.registerReceiver(null, ifilter);

		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale;
		return batteryPct;
	}
}
