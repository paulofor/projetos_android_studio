package br.com.digicom.so;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class Dispositivo {
	
	public static boolean emulador() {
		String hardware = Build.HARDWARE;
		String device = Build.DEVICE;
		String produto = Build.PRODUCT;
		String marca = Build.BRAND;
		String modelo = Build.MODEL;
		String fabricante = Build.MANUFACTURER;
		return ("sdk".equals(modelo) && "sdk".equals(produto));
	}

	public static int getVersao() {
		return android.os.Build.VERSION.SDK_INT;
	}
	public static String getFabricante() {
		return android.os.Build.MANUFACTURER;
	}
	public static String getModelo() {
		return android.os.Build.MODEL;
	}
	public static String getIMEI(Context ctx) {
		TelephonyManager tMgr =(TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		String mPhoneNumber = tMgr.getDeviceId();
		return mPhoneNumber.trim();
		//return "15555215554";
	}
	
	public static String getId(Context ctx) {
		String android_id = Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
		return android_id;
	}
	
	public static boolean isTablet(Context context) {
	    return (context.getResources().getConfiguration().screenLayout
	            & Configuration.SCREENLAYOUT_SIZE_MASK)
	            >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
}
