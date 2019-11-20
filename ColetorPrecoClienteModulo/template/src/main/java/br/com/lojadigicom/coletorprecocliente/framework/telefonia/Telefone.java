
package  br.com.lojadigicom.coletorprecocliente.framework.telefonia;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;

public class Telefone {


   public static String getNumero(Context ctx) {
        String android_id = Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id.trim();
        //TelephonyManager tMgr =(TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        //String mPhoneNumber = tMgr.getDeviceSoftwareVersion();
        //return mPhoneNumber.trim();
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
