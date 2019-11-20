
package  repcom.view.base;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseFragment;
import repcom.modelo.RecebimentoCliente;

// Mudanca no View para se manter dentro do padrao das demais
// classes de UI
public abstract class RecebimentoClienteViewBase extends BaseFragment{
	
	
	//Construtor antigo
	public RecebimentoClienteViewBase(Context context, RecebimentoCliente item) {
		throw new UnsupportedOperationException("Usar o construtor novo de RecebimentoClienteView");
	}
	
	private RecebimentoCliente item;
	
	protected RecebimentoCliente getItem() {
		return item;
	}
	
	// Mantendo o objeto no construtor, porque nao faz sentido criar um View sem um
	// objeto para ser exibido.
	public RecebimentoClienteViewBase(RecebimentoCliente item) {
		super();
		this.item = item;
	}
	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	protected int getLayoutTela() {
		throw new UnsupportedOperationException("Fazer override de getLayoutTela em RecebimentoClienteView retornando view_recebimento_cliente");
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