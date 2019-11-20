package br.com.digicom.servico;

import java.util.List;

import android.content.Context;
import br.com.digicom.dao.DaoSincronismo;


public interface ServicoLocal<E> {

	public void CriaSeNaoExiste(Context contexto);
	public void dropCreate(Context contexto);
	public void insertAll(List<E> lista,Context contexto);
	public List<E> listaSincronizadaAlteracao(Context contexto);
	public List<E> listaSincronizadaAlteracaoV2(Context contexto);
	public List<E> listaSincronizadaDependenteSoAlteracao(Context contexto);
	public void limpaSinc(List lista);
	//public void limpaSinc(E item);
	public void insertLocal(E item);
	public E atribuiUsuario(E item);
	
	public DaoSincronismo getDaoSincronismo();
}
