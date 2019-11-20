package br.com.digicom.servico;

import java.util.List;

import org.json.JSONException;

import br.com.digicom.dao.DaoSincronismo;

import android.content.Context;


public interface ServicoRemoto<E> {
	public List<E> listaSincronizada(Context contexto);
	@Deprecated
	public List<E> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<E> listaSinc,Context contexto) throws JSONException;
	
	@Deprecated
	public List<E> listaSincronizadaUsuarioV2(Context contexto);
	public int listaSincronizadaDao(DaoSincronismo dao, Context contexto);
	// A alteracao na variavel de versao substitui isso - Mantendo pelo Legado
	//public void listaSincronizadaAlteracaoV2(List<E> listaSinc,Context contexto) throws JSONException;
}
