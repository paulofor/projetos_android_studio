
package treinoacademia.tela.trata.base;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.support.v4.app.FragmentTransaction;
import br.com.digicom.quadro.IFragment;

//import android.widget.CheckBox;
//import android.widget.TextView;
import android.widget.EditText;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import treinoacademia.app.R;
import treinoacademia.modelo.*;
import treinoacademia.modelo.agregado.ItemSerieAgregadoI;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.*;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.app.Constantes;
import br.com.digicom.quadro.BaseDialogFragment;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.ResultadoValidacao;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.log.DCLog;

public abstract class ItemSerieQuadroTrataBase extends BaseDialogFragment implements IFragmentEdicao {

	
	private ItemSerie item = null;
	private boolean insercao = false;
	private boolean alteracao = false;
	//protected Activity activity = null;
	
	// Para manipular apenas os dados relacionados
	protected ItemSerieAgregadoI getItem() {
		return (ItemSerieAgregadoI) item;
	}

	public void logObjetoInterno() {
		DCLog.d(DCLog.OBJETO_INTERNO, this, "item = "  + item + "(" + (item!=null?item.getClass().getSimpleName():"-") + ")");
	}
	
	public void setItem(DCIObjetoDominio item) {
		this.item = (ItemSerie) item;
		//this.activity = activity;
		//preencheCampos();
	}
	public void setInsercao() {
		insercao = true;
	}
	public void setAlteracao() {
		alteracao = true;
	}
	
	@Override
	protected final void carregaElementosTelaBase() {
		if (item!=null && alteracao) {
			this.transfereVoParaTela(item);
		}
		logObjetoInterno();
	}
	
	
	protected void setOnOk(View tela) {
		View btn = (View) tela.findViewById(R.id.btnOkTrataItemSerie);
		// Verificando se existe o bot?o de ok na tela.
		if (btn==null) {
			throw new RuntimeException("R.id.btnOkTrataItemSerie n?o encontrado em " + this.getRecurso().getNome());
		}
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!alteracao && !insercao) {
					throw new UnsupportedOperationException("Nao foi selecionado nem alteracao nem insercao para ItemSerieQuadroTrata");
				} else {
					if (item==null) {
						throw new UnsupportedOperationException("Item com valor null em ItemSerieQuadroTrataBase");
					}
					if (validaCamposTela().getProsseguir()) {
						if (alteracao) {
							alteraItem(item);
							if (posAlteraRetornaTela(item))
								retornaTela();
						}
						if (insercao) {
							insereItem(item);
							if (posInsereRetornaTela(item))
								retornaTela();
						}
					}
				}
			}
		});
	}
	public boolean posInsereRetornaTela(ItemSerie valor) {
		return true;
	}
	public boolean posAlteraRetornaTela(ItemSerie valor) {
		return true;
	}

	protected abstract ResultadoValidacao validaCamposTela();
	//	throw new RuntimeException("Implementar validaCamposTela de ItemSerieQuadroTrataBase");
	//}

	@Override
	protected EditText getUltimoCampo() {
		return null;
	}
	@Override
	public void onAlteraQuadro() {
	}

	protected final void alteraItem(ItemSerie valor) {
		ItemSerieServico exibicaoSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		//View saida = LayoutInflater.from(getActivity()).inflate(R.layout.trata_item_serie, null);
		transfereTelaParaVo(valor);
		//exibicaoSrv.alteraParaSincronizacao(valor);
		exibicaoSrv.finalizaItemTela(valor, false, getContext());
	}
	// Passando o parametro que mesmo estando disponivel ? preciso 
	// que fique claro qual objeto deve ser tratado, colocado aqui por
	// outro quadro
	protected final void insereItem(ItemSerie valor) {
		ItemSerieServico exibicaoSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		//View saida = LayoutInflater.from(getActivity()).inflate(R.layout.trata_item_serie, null);
		transfereTelaParaVo(valor);
		//exibicaoSrv.insereParaSincronizacao(valor);
		exibicaoSrv.finalizaItemTela(valor, true, getContext());
	}
	
	protected void transfereTelaParaVo(ItemSerie vo)  {
		throw new UnsupportedOperationException("Fazer override de transfereTelaParaVo em ItemSerieQuadroTrata ou verificar se nao esta sendo chamada via super");
	}
	protected void transfereVoParaTela(final ItemSerie vo)  {
		throw new UnsupportedOperationException("Fazer override de transfereVoParaTela em " + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super");
	}
	public String getTituloTela()  {
		return "Alterar em " + this.getClass().getSimpleName();
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.trata_item_serie,"R.layout.trata_item_serie");
		return recurso;
	}


	@Override
	protected final void inicializaItensTelaBase() {
		if (item==null) {
			ItemSerieServico servico = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
			item = FabricaVo.criaItemSerie();
			item = servico.inicializaItemTela(item, getContext());
		}
			
		if (this.serieTreino!=null) {
			item.setSerieTreino_PertencenteA(serieTreino);
		}
			
	}


	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}


	
	

	@Override
	protected final void inicializaListenersBase() {
		setOnOk(getTela());
	}


	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub
		
	}
	
	// Como passou a ser um IFragment precisa desse m?todo. 
	// N?o sei se faz sentido. Acho que sim. Qualquer trecho de tela pode ter itercao com audio
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	// Classes dependentes para n?o associativas
	private SerieTreino serieTreino;
	public final void setSerieTreino(SerieTreino valor) {
		serieTreino = valor;
		serieTreino.setContexto(getContext());
	}
	protected final SerieTreino getSerieTreino() {
		return serieTreino;
	}
	
	
	
	
	
	
	protected Spinner getSpinnerSerieTreino(int codigo, String nome) {
		SerieTreinoServico srv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		List<SerieTreino> listaTela = srv.getAllTela(getContext().getContext());
		Spinner spn = this.getSpinner(codigo, nome, listaTela, this.getContext().getContext()); 
		return spn;
	}
	/*
	protected Spinner carregaSpinnerSerieTreino(Spinner spn) {
		SerieTreinoServico srv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		List<SerieTreino> listaTela = srv.getAllTela(getContext().getContext());
		ArrayAdapter<SerieTreino> dataAdapter = new ArrayAdapter<SerieTreino>(getContext().getContext(), android.R.layout.simple_spinner_item, listaTela);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn.setAdapter(dataAdapter);
		return spn;
	}
	*/
	
	
	
	@Override
	public final void extraiBundle() {
		this.item = (ItemSerie) this.getBundle().getObjeto(Constantes.CHAVE_ITEM_SERIE);
		if ((Boolean)this.getBundle().getObjeto(Constantes.CHAVE_ALTERACAO))
			this.setAlteracao();
		else
			this.setInsercao();
		
		boolean existeItem = false;
		
		this.serieTreino = (SerieTreino) this.getBundle().getObjetoPermiteNull(Constantes.CHAVE_SERIE_TREINO);
		existeItem = existeItem || (this.serieTreino!=null);
		
		if (!existeItem) throw new RuntimeException("Sem referencia");
		
		extraiBundleComplemento();
	}
	protected void extraiBundleComplemento() {
	}
	
	// Se a interface IFragmentEdicao fosse implementada no n?vel superior poderia ficar na arquitetura.
	@Override
	public boolean validaDadosQuadro() {
		throw new RuntimeException("Implementar validaDadosQuadro em " + this.getClass().getSimpleName());
	}
}
