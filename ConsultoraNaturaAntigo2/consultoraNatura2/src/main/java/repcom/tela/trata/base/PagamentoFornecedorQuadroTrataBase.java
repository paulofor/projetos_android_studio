
package repcom.tela.trata.base;

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
import repcom.app.R;
import repcom.modelo.*;
import repcom.modelo.agregado.PagamentoFornecedorAgregadoI;
import repcom.servico.FabricaServico;
import repcom.servico.*;
import repcom.modelo.vo.FabricaVo;
import br.com.digicom.quadro.BaseDialogFragment;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.ResultadoValidacao;

public abstract class PagamentoFornecedorQuadroTrataBase extends BaseDialogFragment implements IFragmentEdicao {

	
	private PagamentoFornecedor item = null;
	private boolean insercao = false;
	private boolean alteracao = false;
	//protected Activity activity = null;
	
	// Para manipular apenas os dados relacionados
	protected PagamentoFornecedorAgregadoI getItem() {
		return (PagamentoFornecedorAgregadoI) item;
	}

	
	
	public void setItem(DCIObjetoDominio item) {
		this.item = (PagamentoFornecedor) item;
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
	}
	
	
	protected void setOnOk(View tela) {
		Button btn = (Button) tela.findViewById(R.id.btnOkTrataPagamentoFornecedor);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!alteracao && !insercao) {
					throw new UnsupportedOperationException("Nao foi selecionado nem alteracao nem insercao para PagamentoFornecedorQuadroTrata");
				} else {
					if (item==null) {
						throw new UnsupportedOperationException("Item com valor null em PagamentoFornecedorQuadroTrataBase");
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
	public boolean posInsereRetornaTela(PagamentoFornecedor valor) {
		return true;
	}
	public boolean posAlteraRetornaTela(PagamentoFornecedor valor) {
		return true;
	}

	protected abstract ResultadoValidacao validaCamposTela();
	//	throw new RuntimeException("Implementar validaCamposTela de PagamentoFornecedorQuadroTrataBase");
	//}

	@Override
	protected EditText getUltimoCampo() {
		return null;
	}
	@Override
	public void onAlteraQuadro() {
	}

	protected final void alteraItem(PagamentoFornecedor valor) {
		PagamentoFornecedorServico exibicaoSrv = FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_SQLITE);
		//View saida = LayoutInflater.from(getActivity()).inflate(R.layout.trata_pagamento_fornecedor, null);
		transfereTelaParaVo(valor);
		//exibicaoSrv.alteraParaSincronizacao(valor);
		exibicaoSrv.finalizaItemTela(valor, false, getContext());
	}
	// Passando o parametro que mesmo estando disponivel ? preciso 
	// que fique claro qual objeto deve ser tratado, colocado aqui por
	// outro quadro
	protected final void insereItem(PagamentoFornecedor valor) {
		PagamentoFornecedorServico exibicaoSrv = FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_SQLITE);
		//View saida = LayoutInflater.from(getActivity()).inflate(R.layout.trata_pagamento_fornecedor, null);
		transfereTelaParaVo(valor);
		//exibicaoSrv.insereParaSincronizacao(valor);
		exibicaoSrv.finalizaItemTela(valor, true, getContext());
	}
	
	protected void transfereTelaParaVo(PagamentoFornecedor vo)  {
		throw new UnsupportedOperationException("Fazer override de transfereTelaParaVo em PagamentoFornecedorQuadroTrata ou verificar se nao esta sendo chamada via super");
	}
	protected void transfereVoParaTela(final PagamentoFornecedor vo)  {
		throw new UnsupportedOperationException("Fazer override de transfereVoParaTela em PagamentoFornecedorQuadroTrata ou verificar se nao esta sendo chamada via super");
	}
	public String getTituloTela()  {
		return "Alterar em " + this.getClass().getSimpleName();
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.trata_pagamento_fornecedor,"R.layout.trata_pagamento_fornecedor");
		return recurso;
	}


	@Override
	protected final void inicializaItensTelaBase() {
		if (item==null) {
			PagamentoFornecedorServico servico = FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_SQLITE);
			item = FabricaVo.criaPagamentoFornecedor();
			item = servico.inicializaItemTela(item, getContext());
			
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
	
	// public por causa de uma interface
	public void alteraQuadro(IFragment quadro) {
		setTituloTela();
		alteraQuadro(quadro,br.com.digicom.R.id.fragment_container);
	}
	
	
	
	
	
	
	
	
	
	// Se a interface IFragmentEdicao fosse implementada no n?vel superior poderia ficar na arquitetura.
	@Override
	public boolean validaDadosQuadro() {
		throw new RuntimeException("Implementar validaDadosQuadro em " + this.getClass().getSimpleName());
	}
}
