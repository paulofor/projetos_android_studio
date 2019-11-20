package br.com.digicom.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilDatas {
	
	public static String getDiaSemana(Timestamp data) {
		Locale ptBr = new Locale("pt", "BR");
		String day = (new SimpleDateFormat("EEEE",ptBr)).format(data.getTime());
		return day;
	}
	
	public static long converteDD_MM_AAAA(String data) {
		// DD_MM_AAAA
		// 0123456789
		String dia = data.substring(0,2);
		String mes = data.substring(3,5);
		String ano = data.substring(6);
		return Long.parseLong(ano + mes + dia);
	}
	
	public static String getDataCorrenteFormatoSqlite() {
		Calendar cal = Calendar.getInstance();
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String saida = formato.format(cal.getTime());
		return saida + "000000";
	}
	
	public static long getDataLongDeslocada(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, dias);
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String saida = formato.format(cal.getTime());
		return Long.parseLong(saida);
	}
	public static long getDataHoraLongDeslocada(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, dias);
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
		String saida = formato.format(cal.getTime());
		return Long.parseLong(saida);
	}
	
	public static String getDataHoraAtual() {
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formato.format(agora);
	}
	public static String getDataAtual() {
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.format(agora);
	}
	public static long getDataAtual01MesAnoLong() {
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("01MMyyyy");
        String data = formato.format(agora);
        return Long.parseLong(data);
	}
	public static Timestamp getDataAtual01MesAnoTimestamp() {
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("01/MM/yyyy");
        String data = formato.format(agora);
        return convertDD_MM_AAAA2Timestamp(data);
	}
	
	public static Timestamp getTimestampAtual() {
		return new Timestamp(System.currentTimeMillis());
	}
	public static Timestamp getDataTimestampAtual() {
		Timestamp saida = new Timestamp(System.currentTimeMillis());
		saida.setHours(0);
		saida.setMinutes(0);
		saida.setSeconds(0);
		saida.setNanos(0);
		return saida;
	}
	
	
	// Usar barra na data
	public static Timestamp convertDD_MM_AAAA2Timestamp(String data) {
		Timestamp timestamp = null;
		data = data.replaceAll("-", "/");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(data);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		};
		return timestamp;
	}
	public static long converteTimestamp(Timestamp dataHora) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
		String saida = formato.format(dataHora.getTime());
		return Long.parseLong(saida);
	}

}
