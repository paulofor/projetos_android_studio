package br.com.digicom.quadro;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;
import br.com.digicom.multimidia.AudioRecurso;

public abstract class BaseFragment extends Fragment implements IFragment{

	
	private DigicomContexto ctx = null;
	private View tela = null;
	
	
	private GerenciadorFragment ger = new GerenciadorFragment(this);
	
	protected DigicomContexto getContext() {
		// Passar para o GerenciadorFragment ?
		if (ctx==null) {
			ctx = new DigicomContexto(this.getActivity(), this);
		}
		return ctx;
	}
	public View getTela() {
		return tela;
	}
	
	
	private IFragment chamador;
	
	public void setChamador(IFragment chamador) {
		this.chamador = chamador;
	}
	protected IFragment getChamador() {
		return chamador;
	}
	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		inicializaServicos(); // Inicializar os objetos services ( facade das outras camadas )
		inicializaServicosBase();
		carregaModelFromService(); // Carrega os objetos de dados dos servicos
		configuracoesHardware(); // Ajustes de volume, brilho, etc.
	}
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		DCLog.d("ListFragment", this, "");
		tela = inflater.inflate(getLayoutTela(), container, false);
		inicializaItensTela(); // Ligar os itens do xml com itens da classe
		inicializaItensTelaBase(); // Ligar os itens do xml com itens da classe
		carregaElementosTela(); // Colocar conteudo nos objetos de tela - acessar servicos e carregar dados
		carregaElementosTelaBase();
		inicializaListeners(); // colocar os escutadores de eventos nos objetos da tela.
		inicializaListenersBase(); // colocar os escutadores de eventos nos objetos da tela.
		inicioJogoTela(); // Quando a tela possui uma dinamica em que um metodo deve ser executado para reinicializar uma especie de jogo
		inicializaItensArquitetura(); // Para ser usado pela DigicomLib
		return tela;
	}
	
	protected void inicializaItensArquitetura() {
		
	}
	
	protected void inicializaListenersBase() {
		// TODO Auto-generated method stub
		
	}
	protected void inicializaItensTelaBase() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	protected abstract int getLayoutTela();
	protected void inicializaItensTela() {
	}
	protected abstract void inicializaServicos();
	protected void inicializaServicosBase() {
	}
	protected void carregaElementosTela(){
	}
	
	// Para ser usado pelos componentes base
	protected void carregaElementosTelaBase() {
	}
	protected void inicializaListeners() {
		
	}
	protected abstract void inicioJogoTela();
	protected void configuracoesHardware() {
	}
	protected void carregaModelFromService() {
	}
	
	
	
	// Coloca um Fragment dentro um ressource do XML da tela corrente.
	protected void setElementoTela(IFragment quadro, int recurso) {
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = (Fragment) quadro;
		ViewGroup view = (ViewGroup) getTela().findViewById(recurso);
		fragmentTransaction.add(recurso, fragment);
		fragmentTransaction.commit();
		quadro.setChamador(this);
		// Deveria ser para todos os tipos de quadros mas comecas com List
		if (quadro instanceof BaseListFragment) {
			BaseListFragment lista = (BaseListFragment) quadro;
			//lista.setTituloFrame();
		}

	}
	

	public final void alteraQuadro(IFragment quadro, int recurso) {
		ger.alteraQuadro(quadro, recurso);
	}
	
	protected void retornaQuadro() {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.popBackStack();
	}
	
	protected void setTituloTela() {
		String titulo = getTituloTela();
		this.getActivity().getActionBar().setTitle(titulo);
	}
	public abstract String getTituloTela();
	
}
