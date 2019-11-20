package br.com.digicom.quadro;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.widget.util.SpinnerUtil;

public class GerenciadorFragment {

	private Fragment frag;
	private ResourceObj recursoTela = null;

	/*
	public final void alteraQuadro(IFragment quadro, int recurso) {
		quadro.onAlteraQuadro();
		FragmentTransaction transaction = frag.getActivity().getFragmentManager().beginTransaction();
		transaction.replace(recurso, (Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
		DCLog.d(DCLog.BACK_STACK_TRACE, this,"Adicao de : " + quadro.getClass().getSimpleName());
	}
	*/
	
	
	
	public String getLog() {
		String log = "";
		if (recursoTela!=null) {
			log = "( " + recursoTela.getNome() + " )";
			if (frag instanceof BaseListFragment) {
				log += " -> " + getNomeAdapter((BaseListFragment)frag);
			}
		}
		return log; 
	}
	/*
	public String getLog(BaseListFragment quadroLista) {
		String log = "";
		if (recursoTela!=null) {
			log = "( " + recursoTela.getNome() + " )";
			log += " -> " + getNomeAdapter(quadroLista);
		}
		return log; 
	}
	*/
	
	public String getNomeAdapter(BaseListFragment quadroLista) {
		String nome = "";
		if (quadroLista.getAdapter()!=null) {
			nome = quadroLista.getAdapter().getClass().getSimpleName();
		}
		return nome;
	}
	
	public void setElementoTela(IFragment quadro, int recurso) {
		
		FragmentManager fragmentManager = frag.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = (Fragment) quadro;
		ViewGroup view = (ViewGroup) ((IFragment)frag).getTela().findViewById(recurso);
		fragmentTransaction.add(recurso, fragment);
		fragmentTransaction.commit();
		quadro.setChamador((IFragment)frag);
		// Deveria ser para todos os tipos de quadros mas começas com List
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
		if (frag instanceof BaseFragment) {
			BaseFragment quadro = (BaseFragment) frag;
			recursoTela = quadro.getLayoutTela();
		}
		tela = inflater.inflate(recursoTela.getCodigo(), container, false);
		DCLog.d(DCLog.CRIA_FRAGMENT, this, frag.getClass().getSimpleName() + " ( " + recursoTela.getNome() + " ) ");
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
	
		
	// Futuro pode ser usado para os demais fragments. Testar se são iguais
	public void inicializacaoListFragment() {
		BaseListFragment bsf = (BaseListFragment) this.frag;
		bsf.inicializaServicos(); // Inicializar os objetos services ( facade das outras camadas )
		bsf.inicializaServicosBase();
		bsf.inicializaItensTela(); // Ligar os itens do xml com itens da classe
		bsf.inicializaItensTelaBase();
		bsf.inicializaListeners(); // colocar os escutadores de eventos nos objetos da tela.
		bsf.inicializaListenersBase();
		//inicioJogoTela(); // Quando a tela possui uma dinamica em que um metodo deve ser executado para reinicializar uma especie de jogo
	}
	public void inicializacaoBaseFragment() {
		BaseFragment bsf = (BaseFragment) this.frag;
		bsf.inicializaItensTela(); // Ligar os itens do xml com itens da classe
		bsf.inicializaItensTelaBase(); // Ligar os itens do xml com itens da classe
		bsf.carregaElementosTela(); // Colocar conteudo nos objetos de tela - acessar servicos e carregar dados
		bsf.carregaElementosTelaBase();
		bsf.inicializaListeners(); // colocar os escutadores de eventos nos objetos da tela.
		bsf.inicializaListenersBase(); // colocar os escutadores de eventos nos objetos da tela.
		bsf.inicioJogoTela(); // Quando a tela possui uma dinamica em que um metodo deve ser executado para reinicializar uma especie de jogo
		bsf.inicializaItensArquitetura(); // Para ser usado pela DigicomLib
	}
	public void inicializacaoDialogFragment() {
		BaseDialogFragment bsf = (BaseDialogFragment) this.frag;
		bsf.inicializaItensTela(); // Ligar os itens do xml com itens da classe
		bsf.inicializaItensTelaBase(); // Ligar os itens do xml com itens da classe
		bsf.carregaElementosTela(); // Colocar conteudo nos objetos de tela - acessar servicos e carregar dados
		bsf.carregaElementosTelaBase();
		bsf.inicializaListeners(); // colocar os escutadores de eventos nos objetos da tela.
		bsf.inicializaListenersBase(); // colocar os escutadores de eventos nos objetos da tela.
		bsf.inicioJogoTela(); // Quando a tela possui uma dinamica em que um metodo deve ser executado para reinicializar uma especie de jogo
		if (bsf.getDialog()!=null) bsf.getDialog().setTitle(((IFragment) this.frag).getTituloTela());
	}
	
	public void onResume() {
		((IFragment) this.frag).setTituloTela();
		DCLog.d("ListFragment", frag, getLog());
	}
	
	public IFragment getPrincipal(View tela) {
		IFragment principal = (IFragment) tela.findViewWithTag(TrocaQuadro.TAG_FRAGMENTO);
		return principal;
	}
}
