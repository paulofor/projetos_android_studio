
package  br.com.lojadigicom.coletorprecocliente.framework.telefonia;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class Telefone {


    public static String getNumero(Context ctx) {
        //TelephonyManager tMgr =(TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        //String mPhoneNumber = tMgr.getDeviceId();
        //return mPhoneNumber.trim();

        String deviceId = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId.trim();
        //return "+552192902732";
    }


 	public static String getModelo(Context ctx) {
        return Build.MODEL;
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
