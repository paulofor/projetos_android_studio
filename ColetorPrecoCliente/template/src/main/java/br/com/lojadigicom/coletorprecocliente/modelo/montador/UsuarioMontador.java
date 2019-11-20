package br.com.lojadigicom.coletorprecocliente.modelo.montador;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioVo;
//import br.com.lojadigicom.coletorprecocliente.modelo.vo.FabricaVo;
import android.database.Cursor;
//import br.com.digicom.dao.MontadorDaoBase;
//import br.com.digicom.dao.MontadorDaoI;
//import br.com.digicom.modelo.DCIObjetoDominio;
//import br.com.digicom.modelo.ObjetoSinc;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoException;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoItemRetorno;

public class UsuarioMontador implements MontadorDaoI {

	private Usuario principal = new UsuarioVo();
	
	private boolean sinc = false;
	public UsuarioMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public UsuarioMontador() {
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
		//objeto = FabricaVo.criaUsuario();
		objeto = new UsuarioVo();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		//objeto = FabricaVo.criaUsuario();
		objeto = new UsuarioVo();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		Usuario item = null;
		item = (Usuario) entrada;
		item.setIdUsuario(getInt(c,pos++));
		item.setNomeUsuario(getString(c,pos++));
		
		
		
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
