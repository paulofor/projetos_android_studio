package br.com.digicom.quadro;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.widget.util.SpinnerUtil;

public class GerenciadorFragment {

	private Fragment frag;
	private ResourceObj recursoTela = null;
	
	public final void alteraQuadro(IFragment quadro, int recurso) {
		quadro.onAlteraQuadro();
		FragmentTransaction transaction = frag.getActivity().getSupportFragmentManager().beginTransaction();
		transaction.replace(recurso, (Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	
	
	public String getLog() {
		String log = "";
		if (recursoTela!=null) {
			log = "( " + recursoTela.getNome() + " )";
			if (recursoTela.getNomeAdapter()!=null) {
				log += " -> " + recursoTela.getNomeAdapter();
			}
		}
		
		return log; 
	}
	
	public void setElementoTela(IFragment quadro, int recurso) {
		
		FragmentManager fragmentManager = frag.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = (Fragment) quadro;
		ViewGroup view = (ViewGroup) ((IFragment)frag).getTela().findViewById(recurso);
		fragmentTransaction.add(recurso, fragment);
		fragmentTransaction.commit();
		quadro.setChamador((IFragment)frag);
		// Deveria ser para todos os tipos de quadros mas comecas com List
		if (quadro instanceof BaseListFragment) {
			BaseListFragment lista = (BaseListFragment) quadro;
			//lista.setTituloFrame();
		}

	}
	
	public View getTela(LayoutInflater inflater, ViewGroup container) {
		View tela = null;
		if (frag instanceof BaseListFragment) {
			BaseListFragment quadro = (BaseListFragment) frag;
			recursoTela = quadro.getLayoutTela();
			
		}
		if (frag instanceof BaseDialogFragment) {
			BaseDialogFragment quadro = (BaseDialogFragment) frag;
			recursoTela = quadro.getLayoutTela();
		}
		tela = inflater.inflate(recursoTela.getCodigo(), container, false);
		return tela;
	}
	
	public GerenciadorFragment(Fragment valor) {
		frag = valor;
	}
	
	public final ResourceObj getRecurso() {
		return recursoTela;
	}
	public void setRecurso(ResourceObj valor) {
		this.recursoTela = valor;
	}
	
	private IFragment getFragment() {
		return (IFragment) frag;
	}
	
	public EditText getEditText(int codigo, String nome) {
		EditText campo = null;
		try {
			campo = (EditText) getFragment().getTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + getFragment().getClass().getSimpleName() + "  (" + getFragment().getRecurso().getNome() + ")");
		}
		if (campo==null) {
			throw new RuntimeException(nome + " nao encontrado em " + getFragment().getRecurso().getNome());
		}
		return campo;
	}
	public TextView getTextView(int codigo, String nome) {
		TextView campo = null;
		try {
			campo = (TextView) getFragment().getTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + getFragment().getRecurso().getNome());
		}
		if (campo==null) {
			throw new RuntimeException(nome + " nao encontrado em " + getFragment().getRecurso().getNome());
		}
		return campo;
	}
	public Button getButton(int codigo, String nome) {
		Button campo = null;
		try {
			campo = (Button) getFragment().getTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + getFragment().getRecurso().getNome());
		}
		if (campo==null) {
			throw new RuntimeException(nome + " nao encontrado em " + getFragment().getRecurso().getNome());
		}
		return campo;
	}



	public Spinner getSpinner(int codigo, String nome, List lista, Context contexto) {
		Spinner campo = (Spinner) getFragment().getTela().findViewById(codigo);
		if (campo==null) {
			throw new RuntimeException(nome + " nao encontrado em " + getFragment().getRecurso().getNome());
		}
		SpinnerUtil.setLista(lista,campo,contexto);
		return campo;
	}
	
	// Futuro pode ser usado para os demais fragments. Testar se sao iguais
	public void inicializacaoListFragment() {
		BaseListFragment bsf = (BaseListFragment) this.frag;
		bsf.inicializaServicos(); // Inicializar os objetos services ( facade das outras camadas )
		bsf.inicializaServicosBase();
		bsf.inicializaItensTela(); // Ligar os itens do xml com itens da classe
		bsf.inicializaItensTelaBase();
		bsf.inicializaListeners(); // colocar os escutadores de eventos nos objetos da tela.
		bsf.inicializaListenersBase();
		//inicioJogoTela(); // Quando a tela possui uma dinamica em que um metodo deve ser executado para reinicializar uma especie de jogo
		if (frag instanceof BaseListFragment) {
			BaseListFragment quadro = (BaseListFragment) frag;
			recursoTela.setAdapter(quadro.getAdapter());
		}
	}
	
}
