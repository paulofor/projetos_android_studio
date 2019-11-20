package br.com.lojadigicom.treinoacademia.modelo.montador;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.lojadigicom.treinoacademia.modelo.Exercicio;
import br.com.lojadigicom.treinoacademia.modelo.ExercicioVo;
//import br.com.lojadigicom.treinoacademia.modelo.vo.FabricaVo;
import android.database.Cursor;
//import br.com.digicom.dao.MontadorDaoBase;
//import br.com.digicom.dao.MontadorDaoI;
//import br.com.digicom.modelo.DCIObjetoDominio;
//import br.com.digicom.modelo.ObjetoSinc;
import br.com.lojadigicom.treinoacademia.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.framework.dao.MontadorDaoI;
import br.com.lojadigicom.treinoacademia.framework.modelo.ObjetoSinc;
import br.com.lojadigicom.treinoacademia.framework.dao.DaoException;
import br.com.lojadigicom.treinoacademia.framework.dao.DaoItemRetorno;

public class ExercicioMontador implements MontadorDaoI {

	private Exercicio principal = new ExercicioVo();
	
	private boolean sinc = false;
	public ExercicioMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public ExercicioMontador() {
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
		//objeto = FabricaVo.criaExercicio();
		objeto = new ExercicioVo();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		//objeto = FabricaVo.criaExercicio();
		objeto = new ExercicioVo();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		Exercicio item = null;
		item = (Exercicio) entrada;
		item.setIdExercicio(getInt(c,pos++));
		item.setDescricaoExercicio(getString(c,pos++));
		item.setImagem(getString(c,pos++));
		item.setTitulo(getString(c,pos++));
		item.setSubtitulo(getString(c,pos++));
		item.setIdGrupoMuscularP(getInt(c,pos++));
		item.setIdUsuarioS(getInt(c,pos++));
		
		
		
		//if (sinc) {
		//	((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		//}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?7+0+1:7+0);
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
