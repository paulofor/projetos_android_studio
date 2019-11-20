
package  coletapreco.view.base;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseFragment;
import br.com.digicom.layout.ResourceObj;
import coletapreco.modelo.DispositivoUsuario;

// Mudanca no View para se manter dentro do padrao das demais
// classes de UI
public abstract class DispositivoUsuarioViewBase extends BaseFragment{
	
	
	//Construtor antigo
	public DispositivoUsuarioViewBase(Context context, DispositivoUsuario item) {
		throw new UnsupportedOperationException("Usar o construtor novo de DispositivoUsuarioView");
	}
	@Override
	public String getTituloTela() {
		return "Alterar em " + this.getClass().getSimpleName();
	}
	
	private DispositivoUsuario item;
	
	protected DispositivoUsuario getItem() {
		this.item.setContexto(getContext()); // Estranhamente esse dado se perde.
		return item;
	}
	
	// Mantendo o objeto no construtor, porque nao faz sentido criar um View sem um
	// objeto para ser exibido.
	
	// Se existisse um View <<digicom>> colocaria l? o construtor.
	public DispositivoUsuarioViewBase(DispositivoUsuario item) {
		super();
		this.item = item;
		
	}
	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
	}
	@Override
	public void onAlteraQuadro() {
	}
	

	@Override
	protected int getLayoutTela() {
		throw new UnsupportedOperationException("Fazer override de getLayoutTela em " + this.getClass().getSimpleName() + " retornando view_dispositivo_usuario ou similar");
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase
	@Override
	@Deprecated
	public ResourceObj getRecurso() {
		throw new UnsupportedOperationException("Fazer override de getLayoutTela em " + this.getClass().getSimpleName() + " retornando view_dispositivo_usuario ou similar");
	}

	@Override
	protected void inicializaItensTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void carregaElementosTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicializaListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub
		
	}
	
	protected void retornaTela() {
		if (getUltimoCampo()!=null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getUltimoCampo().getWindowToken(), 0);
		}
		getFragmentManager().popBackStack();
	}
	protected View getUltimoCampo() {
		return null;
	}
}