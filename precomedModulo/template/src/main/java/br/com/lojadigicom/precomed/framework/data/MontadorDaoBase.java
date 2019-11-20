package br.com.lojadigicom.precomed.framework.data;

import android.database.Cursor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;


public abstract class MontadorDaoBase {
	
	public abstract DCIObjetoDominio getItem(Cursor c);
	public abstract int quantidadeCampos();
	
	//public DaoItemRetorno extraiRegistroListaInterna(Cursor paramResultadoLista, DCIObjetoDominio objeto)
	//	    throws DaoException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
	//	objeto = ((MontadorDaoI)this).getItem(paramResultadoLista);
	//	DaoItemRetorno item = new DaoItemRetorno();
	//	item.setInsere(true);
	//	item.setObjeto(objeto);
	//	return item;
	//}

	
	//public DaoItemRetorno extraiRegistroListaInternaSinc(Cursor paramResultadoLista, DCIObjetoDominio objeto)
	//	    throws DaoException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
	//	objeto = ((MontadorDaoI)this).getItem(paramResultadoLista);
	//	DaoItemRetorno item = new DaoItemRetorno();
	//	item.setInsere(true);
	//	item.setObjeto(objeto);
	//	return item;
	//}

	
	//protected DCIObjetoDominio getItemSinc(Cursor c) {
	//	DCIObjetoDominio obj = getItem(c);
	//	((ObjetoSinc)obj).setOperacaoSinc(getString(c,quantidadeCampos()));
	//	return obj;
	//}
	
	protected String getString(Cursor c,int posicao) {
		return c.getString(posicao);
	}
	protected int getInt(Cursor c,int posicao) {
		return c.getInt(posicao);
	}
	protected boolean getLogic(Cursor c,int posicao) {
		int i= c.getInt(posicao);
		return (i==1);
	}
	protected float getFloat(Cursor c,int posicao) {
		float c1 = c.getFloat(posicao);
		return c1;
	}
	
	protected Timestamp getTimestamp(Cursor c,int posicao) {
		
		long num = c.getLong(posicao);
		Timestamp ret = new Timestamp(num);
		return ret;
	}
	protected Timestamp getTimestampData(Cursor c,int posicao) {
		Timestamp saida = null;
		String idStr = String.valueOf(c.getLong(posicao));
		if (idStr==null) return null;;
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    try {
	    	java.util.Date date = (java.util.Date)formatter.parse(idStr);
		    saida = new Timestamp(date.getTime());
		} catch (ParseException e) {
			saida = null;
		}
		return saida;
	}
	protected Timestamp getTimestampDataHora(Cursor c,int posicao) {
		Timestamp saida = null;
		String idStr = String.valueOf(c.getLong(posicao));
		if (idStr==null) return null;;
	    DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    try {
	    	java.util.Date date = (java.util.Date)formatter.parse(idStr);
		    saida = new Timestamp(date.getTime());
		} catch (ParseException e) {
			saida = null;
		}
		return saida;
	}

}
