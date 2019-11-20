package br.com.lojadigicom.repcom.modelo.montador;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.lojadigicom.repcom.modelo.Venda;
import br.com.lojadigicom.repcom.modelo.VendaVo;
//import br.com.lojadigicom.repcom.modelo.vo.FabricaVo;
import android.database.Cursor;
//import br.com.digicom.dao.MontadorDaoBase;
//import br.com.digicom.dao.MontadorDaoI;
//import br.com.digicom.modelo.DCIObjetoDominio;
//import br.com.digicom.modelo.ObjetoSinc;
import br.com.lojadigicom.repcom.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.repcom.framework.dao.DaoException;
import br.com.lojadigicom.repcom.framework.dao.DaoItemRetorno;

public class VendaMontador implements MontadorDaoI {

	private Venda principal = new VendaVo();
	
	private boolean sinc = false;
	public VendaMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public VendaMontador() {
		this.sinc = false;
	}
	public void desligaSinc() {
		this.sinc = false;
	}

	public DCIObjetoDominio getItemSinc(Cursor c) {
		DCIObjetoDominio obj = getItem(c);
		((ObjetoSinc)obj).setOperacaoSinc(getString(c,quantidadeCampos()));
		return obj;
	}

	public boolean getItemListaInterna(Cursor c, DCIObjetoDominio objeto)
    {
    	objeto = ((MontadorDaoI)this).getItem(c);
        return true;
    }
    public boolean getItemRegistroInterno(Cursor c, DCIObjetoDominio objeto)
    {
    	objeto = ((MontadorDaoI)this).getItem(c);
        return true;
    }

	public DCIObjetoDominio getItem(Cursor c) {
		DCIObjetoDominio objeto = null;
		//objeto = FabricaVo.criaVenda();
		objeto = new VendaVo();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		//objeto = FabricaVo.criaVenda();
		objeto = new VendaVo();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		Venda item = null;
		item = (Venda) entrada;
		item.setIdVenda(getInt(c,pos++));
		item.setData(this.getTimestampData(c,pos++));
		item.setValorTotal(getFloat(c,pos++));
		item.setVendaFechada(getLogic(c,pos++));
		item.setIdClienteFp(getInt(c,pos++));
		item.setIdUsuarioS(getInt(c,pos++));
		
		
		
		//if (sinc) {
		//	((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		//}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?6+0+1:6+0);
 	}
 	
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
	
	
	public DaoItemRetorno extraiRegistroListaInterna(Cursor paramResultadoLista, DCIObjetoDominio objeto)
		    throws DaoException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		objeto = ((MontadorDaoI)this).getItem(paramResultadoLista);
		DaoItemRetorno item = new DaoItemRetorno();
		item.setInsere(true);
		item.setObjeto(objeto);
		return item;
	}

	
	public DaoItemRetorno extraiRegistroListaInternaSinc(Cursor paramResultadoLista, DCIObjetoDominio objeto)
		    throws DaoException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		objeto = ((MontadorDaoI)this).getItem(paramResultadoLista);
		DaoItemRetorno item = new DaoItemRetorno();
		item.setInsere(true);
		item.setObjeto(objeto);
		return item;
	}
}
