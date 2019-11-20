
package  br.com.lojadigicom.repcom.framework.util;


import android.content.ContentValues;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Paulo on 20/11/2015.
 */
public class UtilDatas {


 	public static Timestamp getDataHora() {
        Date date= new Date();
        return new Timestamp(date.getTime());
    }

    public static String getDataDeslocada(int dias) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, dias);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String saida = formato.format(cal.getTime());
        return saida;
    }
   	public static String getDataDeslocada(int dias, Timestamp referencia) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(referencia.getTime());
        cal.add(Calendar.DAY_OF_YEAR, dias);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String saida = formato.format(cal.getTime());
        return saida;
    }

    public static long getDataLong(String data) {
    	if (data.length()==0) return 0;
        String dataNum = data.substring(6) + data.substring(3, 5) + data.substring(0, 2) ;
        long num = Long.parseLong(dataNum);
        return num;
    }
    public static long getDataHoraLong(String data) {
        // DD-MM-AAAA HH:MM:SS
        // 01234567890123456789
        if (data.length()==0) return 0;
        String dataNum = data.substring(6) + data.substring(3, 5) + data.substring(0, 2) +
                data.substring(11,13) + data.substring(14,16) + data.substring(17);
        long num = Long.parseLong(dataNum);
        return num;
    }

    public static long getDataLong(Timestamp data) {
    	if (data==null) return 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        String saida = formato.format(data);
        return Long.parseLong(saida);
    }
    public static long getDataHoraLong(Timestamp data) {
    	if (data==null) return 0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
        String saida = formato.format(data);
        return Long.parseLong(saida);
    }



    protected void putValorData(ContentValues valores, String tipo, String campo) {
        String dataNum = campo.substring(6) + campo.substring(3, 5) + campo.substring(0, 2) ;
        long num = Long.parseLong(dataNum);
        valores.put(tipo, num);
    }
    protected void putValorData(ContentValues valores, String tipo, Timestamp campo) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        long num = 0;
        if (campo!=null)
            num = Long.parseLong(formato.format(campo));
        valores.put(tipo, num);
    }
    protected void putValorDataHora(ContentValues valores, String tipo, Timestamp campo) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");

        long num = 0;
        if (campo!=null) {
            String texto = formato.format(campo);
            num = Long.parseLong(formato.format(campo));
        }
        valores.put(tipo, num);
    }
}
