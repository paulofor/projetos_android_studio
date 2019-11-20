package br.com.digicom.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UtilDatas {
	
	public static String converteDDMMAAAA(Timestamp data) {
		if (data==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }
	
	
	public static String primeiroDiaAnoCorrente() {
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("01-01-yyyy");
        return formato.format(agora);
	}
	
	public static String getDD_MM(String data) {
		// DD-MM-AAAA
		// 0123456789
		return data.substring(0,5);
	}
	
	public static Timestamp somaMeses(Timestamp data, int numMeses) {
		Timestamp saida = data;
		int dia = data.getDate();
		int mes = data.getMonth();
		int ano = data.getYear();
		int mesNovo = mes + numMeses;
		if (mesNovo > 12) {
			mesNovo = mesNovo - 12;
			saida.setYear(ano+1);
		}
		saida.setMonth(mesNovo);
		if (dia==31) {
			if (mesNovo==2 || mesNovo==4 || mesNovo==6 || mesNovo==9 || mesNovo==11) {
				saida.setDate(30);
			}
		}
		if (mesNovo==2 && dia>28) {
			saida.setDate(28);
		}
		return saida;
	}
	
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
	public static long getDataAtual01MesAnoLongBD() {
		Timestamp agora =  new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMM01");
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
	public static Timestamp getTimestamp(long milisegundos) {
		Timestamp saida = new Timestamp(0);
		int second = (int) (TimeUnit.MILLISECONDS.toSeconds(milisegundos) % 60);
		int minute = (int) (TimeUnit.MILLISECONDS.toMinutes(milisegundos) % 60);
		int hour = (int) TimeUnit.MILLISECONDS.toHours(milisegundos);
		saida.setHours(hour);
		saida.setMinutes(minute);
		saida.setSeconds(second);
		saida.setDate(0);
		saida.setMonth(0);
		saida.setYear(0);
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
	
	public static String converte2HHMMSS(long tempo) {
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(tempo),
	            TimeUnit.MILLISECONDS.toMinutes(tempo) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(tempo)),
	            TimeUnit.MILLISECONDS.toSeconds(tempo) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempo)));
		return hms;
	}

	public static String converte2MMSS(long tempo) {
		String hms = String.format("%03d:%02d", TimeUnit.MILLISECONDS.toMinutes(tempo) ,
	            TimeUnit.MILLISECONDS.toSeconds(tempo) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempo)));
		return hms;
	}
}
