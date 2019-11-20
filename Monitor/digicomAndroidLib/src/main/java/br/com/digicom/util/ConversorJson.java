package br.com.digicom.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

public class ConversorJson {

	public static int getInteger(JSONObject json, String nomeCampo) throws JSONException{
		String idStr = json.getString(nomeCampo);
		return  Integer.parseInt(idStr);
	}
	public static float getFloat(JSONObject json, String nomeCampo) throws JSONException{
		String idStr = json.getString(nomeCampo);
		idStr = idStr.replace(',', '.');
		return  Float.parseFloat(idStr);
	}
	
	public static Timestamp getTimestampData(JSONObject json, String nomeCampo) throws JSONException{
		
		Timestamp saida = null;
		String idStr = json.getString(nomeCampo);
		if (idStr==null) return null;;
	    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	    	java.util.Date date = (java.util.Date)formatter.parse(idStr);
		    saida = new Timestamp(date.getTime());
		} catch (ParseException e) {
			saida = null;
		}
		return saida;
	}
	
	public static Timestamp getTimestampHora(JSONObject json, String nomeCampo) throws JSONException{
		Timestamp saida = null;
		String idStr = json.getString(nomeCampo);
		if (idStr==null) return null;;
	    DateFormat formatter = new SimpleDateFormat("HH:mm");
	    try {
	    	java.util.Date date = (java.util.Date)formatter.parse(idStr);
		    saida = new Timestamp(date.getTime());
		} catch (ParseException e) {
			saida = null;
		}
		return saida;
	}
	public static Timestamp getTimestampDataHora(JSONObject json, String nomeCampo) throws JSONException{
		Timestamp saida = null;
		String idStr = json.getString(nomeCampo);
		if (idStr==null) return null;;
	    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    try {
	    	java.util.Date date = (java.util.Date)formatter.parse(idStr);
		    saida = new Timestamp(date.getTime());
		} catch (ParseException e) {
			saida = null;
		}
		return saida;
	}
	public static boolean getLogic(JSONObject json, String nomeCampo) throws JSONException{
		String idStr = json.getString(nomeCampo);
		if (idStr.equals("True")) return true;
		return  false;
	}
	
	public static String getString(JSONObject json, String nomeCampo) throws JSONException{
		return json.getString(nomeCampo);
	}
}
