package br.com.digicom.quadro;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.multimidia.AudioRecurso;

public abstract class BaseDialogFragment extends DialogFragment implements IFragment{

	
	private DigicomContexto ctx = null;
	private View tela = null;
	//private ResourceObj recursoTela = null;
	private GerenciadorFragment ger = new GerenciadorFragment(this);
	
	public final EditText getEditText(final int codigo,final String nome) {
		return ger.getEditText(codigo, nome);
	}
	public final  TextView getTextView(final int codigo,final String nome) {
		return ger.getTextView(codigo, nome);
	}
	public final Button getButton(final int codigo,final String nome) {
		return ger.getButton(codigo, nome);
	}
	public final Spinner getSpinner(final int codigo, final String nome, final List lista, Context contexto) {
		return ger.getSpinner(codigo, nome, lista, contexto);
	}
	
	
	
	protected final String getConteudoEditText(EditText item, String nome) {
		String saida = null;
		if (item==null) {
			throw new RuntimeException("EditText nulo");
		}
		if (item.getText()==null) {
			throw new RuntimeException("EditText " + nome + " esta sem conteudo");
		}
		saida = item.getText().toString();
		return saida;
	}
	protected final long getConteudoNota(RatingBar item) {
		float nota = item.getRating();
		long notaInt = (long) Math.ceil(nota);
		return notaInt;
	}
	protected final long getConteudoNumerico(EditText texto) {
		String valor = texto.getText().toString();
		return Long.parseLong(valor);
	}
	
	protected final DigicomContexto getContext() {
		return ctx;
	}
	public final View getTela() {
		return tela;
	}
	public final ResourceObj getRecurso() {
		return ger.getRecurso();
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
		ctx = new DigicomContexto(this.getActivity(), this);
		inicializaServicos(); // Inicializar os objetos services ( facade das outras camadas )
		inicializaServicosBase();
		carregaModelFromService(); // Carrega os objetos de dados dos servicos
		configuracoesHardware(); // Ajustes de volume, brilho, etc.
	}
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		tela = ger.getTela(inflater, container);
		DCLog.d("ListFragment", this, ger.getLog());
		inicializaItensTela(); // Ligar os itens do xml com itens da classe
		inicializaItensTelaBase(); // Ligar os itens do xml com itens da classe
		carregaElementosTela(); // Colocar conteudo nos objetos de tela - acessar servicos e carregar dados
		carregaElementosTelaBase();
		inicializaListeners(); // colocar os escutadores de eventos nos objetos da tela.
		inicializaListenersBase(); // colocar os escutadores de eventos nos objetos da tela.
		inicioJogoTela(); // Quando a tela possui uma dinamica em que um metodo deve ser executado para reinicializar uma especie de jogo

		return tela;
	}
	
	protected void inicializaListenersBase() {
		// TODO Auto-generated method stub
		
	}
	protected void inicializaItensTelaBase() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	protected abstract ResourceObj getLayoutTela();
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
		
		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = (Fragment) quadro;
		ViewGroup view = (ViewGroup) getTela().findViewById(recurso);
		fragmentTransaction.add(recurso, fragment);
		fragmentTransaction.commit();
		quadro.setChamador(this);

	}
	public final void alteraQuadro(IFragment quadro, int recurso) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.replace(recurso, (Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
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
	
	protected final void retornaTela() {
		if (getUltimoCampo()!=null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getUltimoCampo().getWindowToken(), 0);
		}
		int lastFragmentCount =  getFragmentManager().getBackStackEntryCount() - 1;
		IFragment ultimo = (IFragment) getFragmentManager().getFragments().get(lastFragmentCount);
		ultimo.onAlteraQuadro();
		getFragmentManager().popBackStack();
	}
	protected abstract EditText getUltimoCampo();
}
