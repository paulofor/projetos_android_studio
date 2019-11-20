package br.com.digicom.quadro;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseMenuFragment.OnButtonClick;



public abstract class BaseListFragment extends ListFragment  implements IFragment{
	
	private DigicomContexto ctx = null;
	private Activity activity = null;
	protected OnButtonClick mCallback;
	private View tela = null;
	private IFragment chamador;
	private boolean tituloFrame = false;
	
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
	
	public void setChamador(IFragment chamador) {
		this.chamador = chamador;
	}
	protected IFragment getChamador() {
		return chamador;
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
	public BaseListFragment() {
		super();
		
	}
	
	protected abstract ResourceObj getLayoutTela();
	
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		tela = ger.getTela(inflater, container);
		ger.inicializacaoListFragment();
		this.tituloFrame();
		
		return tela;
	}
	
	
	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAlteraQuadro() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResourceObj getRecurso() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onResume() {
		super.onResume();
		DCLog.d("ListFragment", this, ger.getLog());
	}
	public abstract BaseAdapter getAdapter();
	
	//protected abstract int getLayoutTela();
	protected abstract void inicializaItensTela();
	protected abstract void inicializaListeners();
	
	protected abstract void inicializaServicos();
	protected void inicializaServicosBase() {
	}
	protected void inicializaItensTelaBase() {
	}
	protected void inicializaListenersBase() {
	}

	
	@Override
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        ctx = new DigicomContexto(activity, this);
        this.activity = activity;
        try {
			mCallback = (OnButtonClick) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		DCIObjetoDominio item =  (DCIObjetoDominio) getListAdapter().getItem(position);
		mCallback.onButtonClickLista(getPalavraChave(), item); 
	}
	
	protected abstract String getPalavraChave();
	
	protected void setTituloTela() {
		String titulo = getTituloTela();
		this.getActivity().getActionBar().setTitle(titulo);
	}
	public String getTituloTela() {
		String nome = this.getClass().getSimpleName();
		return nome;
	}
	public final void alteraQuadro(IFragment quadro, int recurso) {
		ger.alteraQuadro(quadro, recurso);
	}

	public final void setElementoTela(IFragment quadro, int recurso) {
		this.ger.setElementoTela(quadro, recurso);
	}
	
	
	public void setTituloFrame() {
		tituloFrame = true;
		
	}
	private void tituloFrame() {
		if (tituloFrame) {
			/*
			ViewGroup quadro = (ViewGroup)this.getTela();
			View itemTela = quadro.getChildAt(0);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) itemTela.getLayoutParams();
			TextView titulo = new TextView(this.getContext().getContext());
			titulo.setText(getTituloTela());
			titulo.setLayoutParams(new   ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			quadro.addView(titulo);
			params.addRule(RelativeLayout.BELOW, quadro.getId());
			*/
			TextView titulo = (TextView) this.getTela().findViewById(getLayoutTitulo());
			titulo.setText(getTituloTela());
		}
	}
	protected int getLayoutTitulo() {
		throw new RuntimeException("Para mostrar o titulo e preciso criar TextView em " + ger.getLog());
	}
	
}
