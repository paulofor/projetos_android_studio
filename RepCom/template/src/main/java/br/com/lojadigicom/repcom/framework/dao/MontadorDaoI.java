
package  br.com.lojadigicom.repcom.framework.dao;

import java.lang.reflect.InvocationTargetException;

import android.database.Cursor;

import br.com.lojadigicom.repcom.framework.dao.DaoException;
import br.com.lojadigicom.repcom.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;


public interface MontadorDaoI {

	public void desligaSinc();
	public DCIObjetoDominio getItem(Cursor c, int posicao);
	public DCIObjetoDominio getItem(Cursor c);
	public DCIObjetoDominio getItemSinc(Cursor c);
	public int quantidadeCampos();
	public DaoItemRetorno extraiRegistroListaInterna(Cursor c, DCIObjetoDominio objeto)
			    throws DaoException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}