package br.com.digicom.quadro;


import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.multimidia.AudioRecurso;

public abstract class BaseFragment extends Fragment implements IFragment{

	
	private DigicomContexto ctx = null;
	private View tela = null;
	private IFragment container = null;
	
	private GerenciadorFragment ger = new GerenciadorFragment((Fragment)this);
	private GerenciadorItensTela gerItens = new GerenciadorItensTela((IFragment)this);
	
	public void setContainerFragment(IFragment dado) {
		this.container = dado;
	}
	public IFragment getContainerFragment() {
		return this.container;
	}
	
	
	public IFragment getPrincipal() {
		return ger.getPrincipal(tela);
	}
	
	private Activity mActivity = null;
	public void setActivityAlternativo(Activity activity) {
		mActivity = activity;
	}
	public Activity getActivityAlternativo() {
		return mActivity;
	}
	
	private BundleFragment bundle = null;
	public final void setBundle(BundleFragment dado) {
		bundle = dado;
	}
	public final BundleFragment getBundle() {
		return bundle;
	}
	
	
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
		carregaModelFromService(); // Carrega os objetos de dados dos serviços
		configuracoesHardware(); // Ajustes de volume, brilho, etc.
	}
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		//tela = inflater.inflate(getLayoutTela(), container, false);
		tela = ger.getTela(inflater, container);
		ger.inicializacaoBaseFragment();
		return tela;
	}
	
	
	
	@Override
	public void onAlteraQuadro() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		ger.onResume();
	}
	protected void inicializaItensArquitetura() {
		
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
	
	public final ResourceObj getRecurso() {
		return ger.getRecurso();
	}
	
	
	
	/*
	public final void alteraQuadro(IFragment quadro, int recurso) {
		ger.alteraQuadro(quadro, recurso);
	}
	*/
	public void setTituloTela() {
		String titulo = getTituloTela();
		this.getActivity().getActionBar().setTitle(titulo);
	}
	public abstract String getTituloTela();
	
	public final View getView(final int codigo,final String nome) {
		return gerItens.getView(codigo, nome);
	}
	public final EditText getEditText(final int codigo,final String nome) {
		return gerItens.getEditText(codigo, nome);
	}
	public final  TextView getTextView(final int codigo,final String nome) {
		return gerItens.getTextView(codigo, nome);
	}
	public final Button getButton(final int codigo,final String nome) {
		return gerItens.getButton(codigo, nome);
	}
	public final Spinner getSpinner(final int codigo, final String nome, final List lista, Context contexto) {
		return gerItens.getSpinner(codigo, nome, lista, contexto);
	}
	public final void setTexto(final int codigo,final String nome, final String texto) {
		gerItens.setTexto(codigo, nome, texto);
	}
	public final void setTexto(final int codigo,final String nome, final long texto) {
		gerItens.setTexto(codigo, nome, texto);
	}
	
	public final float getConteudoDecimal(final int codigo,final String nome) {
		return gerItens.getConteudoDecimal(codigo, nome);
	}
	public final long getConteudoNumerico(final int codigo,final String nome) {
		return gerItens.getConteudoNumerico(codigo, nome);
	}
	@Override
	public final void inicializaParaTeste() {
		//tela = ger.getTela(inflater, container);
		ger.inicializacaoBaseFragment();
	}
	
	
	
}
