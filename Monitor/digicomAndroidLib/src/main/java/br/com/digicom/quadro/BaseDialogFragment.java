package br.com.digicom.quadro;

import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.multimidia.AudioRecurso;

public abstract class BaseDialogFragment extends DialogFragment implements IFragment{

	private boolean isModal = false;
	private DigicomContexto ctx = null;
	private View tela = null;
	//private ResourceObj recursoTela = null;
	private GerenciadorFragment ger = new GerenciadorFragment(this);
	private GerenciadorItensTela gerItens = new GerenciadorItensTela(this);
	private IFragment chamador;
	
	private BundleFragment bundle = null;
	private IFragment container;
	public final void setBundle(BundleFragment dado) {
		bundle = dado;
	}
	public final BundleFragment getBundle() {
		return bundle;
	}
	
	private Activity mActivity = null;
	public void setActivityAlternativo(Activity activity) {
		mActivity = activity;
	}
	public Activity getActivityAlternativo() {
		return mActivity;
	}
	
	public void setContainerFragment(IFragment dado) {
		this.container = dado;
	}
	public IFragment getContainerFragment() {
		return this.container;
	}
	public IFragment getPrincipal() {
		return ger.getPrincipal(tela);
	}
	
	public void setMoeda(int codigo, String nome, float valor) {
		this.gerItens.setMoeda(codigo, nome, valor);
	}
	public void setSpinnerIdObjeto(int codigo, String nome, long idObjeto) {
		this.gerItens.setSpinnerIdObjeto(codigo,nome,idObjeto);
	}
	public void setSpinnerLabel(int codigo, String nome, String label) {
		this.gerItens.setSpinnerLabel(codigo,nome,label);
	}
	public void setSpinnerLabel(int codigo, String nome, long label) {
		this.gerItens.setSpinnerLabel(codigo,nome,label);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//DCLog.d(DCLog.CICLO_VIEW, this, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//DCLog.d(DCLog.CICLO_VIEW, this, "onCreate");
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onCancel(DialogInterface dialog) {
		//DCLog.d(DCLog.CICLO_VIEW, this, "onCancel");
		super.onCancel(dialog);
	}
	@Override
	public void onStart() {
		//DCLog.d(DCLog.CICLO_VIEW, this, "onStart");
		super.onStart();
	}
	@Override
	public void onStop() {
		//DCLog.d(DCLog.CICLO_VIEW, this, "onStop");
		super.onStop();
	}
	
	public final void show(FragmentManager fragMan, IFragment chamador) {
		this.chamador = chamador;
		this.show(fragMan, chamador.getClass().getSimpleName());
	}
	
	@Override
	public final void show(FragmentManager manager, String tag) {
		isModal = true;
		super.show(manager, tag);
	}
	@Override
	public final int show(FragmentTransaction transaction, String tag) {
		isModal = true;
		return super.show(transaction, tag);
	}
	public final EditText getEditText(final int codigo,final String nome) {
		return gerItens.getEditText(codigo, nome);
	}
	public final  TextView getTextView(final int codigo,final String nome) {
		return gerItens.getTextView(codigo, nome);
	}
	public final  void setTexto(final int codigo,final String nome, final String texto) {
		gerItens.setTexto(codigo, nome, texto);
	}
	public final  void setTexto(final int codigo,final String nome, final long texto) {
		gerItens.setTexto(codigo, nome, texto);
	}
	public final Button getButton(final int codigo,final String nome) {
		return gerItens.getButton(codigo, nome);
	}
	public final Spinner getSpinner(final int codigo, final String nome, final List lista, Context contexto) {
		return gerItens.getSpinner(codigo, nome, lista, contexto);
	}
	
	public final Object getSpinnerObjeto(final int codigo, final String nome) {
		return gerItens.getSpinnerObjeto(codigo, nome);
	}
	
	public final boolean estaPreenchido(final int codigo, final String nome) {
		return gerItens.estaPreenchido(codigo, nome);
	}
	public final boolean spinnerPreenchido(final int codigo, final String nome) {
		return gerItens.spinnerPreenchido(codigo, nome);
	}
	public final boolean spinnerCompare(final int codigo, final String nome, final Object comparacao) {
		return gerItens.spinnerCompara(codigo, nome, comparacao);
	}
	public final void spinnerSetLista(final int codigo, final String nome, List lista, Context contexto) {
		gerItens.spinnerSetLista(codigo, nome, lista, contexto);
	}
	/*
	public final void setSpinner(final int codigo, final String nome, final List lista, Context contexto) {
		gerItens.setSpinner(codigo, nome, lista, contexto);
	}
	*/
	
	public final String getConteudoEditText(int codigo, String nome) {
		return gerItens.getConteudoEditText(codigo, nome);
	}
	public final long getRatingBarNota(int item, String nome) {
		return gerItens.getRatingBarNota(item, nome);
	}
	public final long getConteudoNumerico(int item, String nome) {
		return gerItens.getConteudoNumerico(item,nome);
	}
	public final float getConteudoMoeda(int item, String nome) {
		return gerItens.getConteudoMoeda(item,nome);
	}
	public final boolean getConteudoRadio(int item, String nome) {
		return gerItens.getConteudoRadio(item, nome);
	}
	
	public void setRatingBarNota(int codigo, String nome, long conteudo) {
		this.gerItens.setRatingBarNota(codigo, nome, conteudo);
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
		DCLog.d(DCLog.CICLO_VIEW, this, "onAttach");
		super.onAttach(activity);
		ctx = new DigicomContexto(this.getActivity(), this);
		inicializaServicos(); // Inicializar os objetos services ( facade das outras camadas )
		inicializaServicosBase();
		carregaModelFromService(); // Carrega os objetos de dados dos serviços
		configuracoesHardware(); // Ajustes de volume, brilho, etc.
	}
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DCLog.d(DCLog.CICLO_VIEW, this, "onCreateView");
		super.onCreateView(inflater, container, savedInstanceState);
		tela = ger.getTela(inflater, container);
		ger.inicializacaoDialogFragment();
		return tela;
	}
	
	protected void inicializaListenersBase() {
		// TODO Auto-generated method stub
		
	}
	protected void inicializaItensTelaBase() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	@Override
	public void onAlteraQuadro() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onResume() {
		DCLog.d(DCLog.CICLO_VIEW, this, "onResume");
		super.onResume();
		ger.onResume();
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
	
	
	
	public final void setTituloTela() {
		String titulo = getTituloTela();
		this.getActivity().getActionBar().setTitle(titulo);
	}
	public abstract String getTituloTela();
	
	@Deprecated
	/**
	 * Usar o TrocaQuadro
	 */
	protected final void retornaTela() {
		if (getUltimoCampo()!=null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getUltimoCampo().getWindowToken(), 0);
		}
		if (chamador!=null) {
			IFragment corrente = this.chamador;
			while (corrente!=null) {
				corrente.onAlteraQuadro();
				corrente = corrente.getContainerFragment();
			}
		}
		if (isModal) {
			this.dismiss(); // Atualiza o fragment anterior via OnStart.
		} else {
			int total = getFragmentManager().getBackStackEntryCount();
			int lastFragmentCount = total - 1;
			//BackStackEntry fragTotal =  getFragmentManager().getBackStackEntryAt(total);
			BackStackEntry anterior =  getFragmentManager().getBackStackEntryAt(lastFragmentCount);
			IFragment ultimo = (IFragment) getFragmentManager().findFragmentById(lastFragmentCount);
			if (ultimo!=null)
				ultimo.onAlteraQuadro();
			getFragmentManager().popBackStack();
		}
		
	}
	protected abstract EditText getUltimoCampo();
	
	
	@Deprecated
	/**
	 * Usar o TrocaQuadro
	 */
	protected void retornaTelaComRefresh() {
		retornaTela();
		//View v = this.getActivity().findViewById(R.id.fragment_principal);
		IFragment frag = (IFragment) getActivity().getFragmentManager().findFragmentByTag(TrocaQuadro.TAG_FRAGMENTO);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(frag);
	}
	
	@Override
	public final void inicializaParaTeste() {
		//tela = ger.getTela(inflater, container);
		ger.inicializacaoBaseFragment();
	}
}
