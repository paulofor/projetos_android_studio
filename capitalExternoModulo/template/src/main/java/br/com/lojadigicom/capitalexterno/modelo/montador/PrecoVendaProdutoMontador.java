package br.com.lojadigicom.capitalexterno.modelo.montador;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.lojadigicom.capitalexterno.modelo.PrecoVendaProduto;
import br.com.lojadigicom.capitalexterno.modelo.PrecoVendaProdutoVo;
//import br.com.lojadigicom.capitalexterno.modelo.vo.FabricaVo;
import android.database.Cursor;
//import br.com.digicom.dao.MontadorDaoBase;
//import br.com.digicom.dao.MontadorDaoI;
//import br.com.digicom.modelo.DCIObjetoDominio;
//import br.com.digicom.modelo.ObjetoSinc;
import br.com.lojadigicom.capitalexterno.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.framework.dao.MontadorDaoI;
import br.com.lojadigicom.capitalexterno.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.capitalexterno.framework.dao.DaoException;
import br.com.lojadigicom.capitalexterno.framework.dao.DaoItemRetorno;

public class PrecoVendaProdutoMontador implements MontadorDaoI {

	private PrecoVendaProduto principal = new PrecoVendaProdutoVo();
	
	private boolean sinc = false;
	public PrecoVendaProdutoMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public PrecoVendaProdutoMontador() {
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
		//objeto = FabricaVo.criaPrecoVendaProduto();
		objeto = new PrecoVendaProdutoVo();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		//objeto = FabricaVo.criaPrecoVendaProduto();
		objeto = new PrecoVendaProdutoVo();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		PrecoVendaProduto item = null;
		item = (PrecoVendaProduto) entrada;
		item.setIdPrecoVendaProduto(getInt(c,pos++));
		item.setValor(getFloat(c,pos++));
		
		
		
		//if (sinc) {
		//	((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		//}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?2+0+1:2+0);
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
