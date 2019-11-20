
package coletapreco.tela.trata.base;

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
import coletapreco.app.R;
import coletapreco.modelo.*;
import coletapreco.modelo.agregado.PalavraAgregadoI;
import coletapreco.servico.FabricaServico;
import coletapreco.servico.*;
import coletapreco.modelo.vo.FabricaVo;
import br.com.digicom.quadro.BaseDialogFragment;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.ResultadoValidacao;

public abstract class PalavraQuadroTrataBase extends BaseDialogFragment implements IFragmentEdicao {

	
	private Palavra item = null;
	private boolean insercao = false;
	private boolean alteracao = false;
	//protected Activity activity = null;
	
	// Para manipular apenas os dados relacionados
	protected PalavraAgregadoI getItem() {
		return (PalavraAgregadoI) item;
	}

	
	
	public void setItem(DCIObjetoDominio item) {
		this.item = (Palavra) item;
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
		Button btn = (Button) tela.findViewById(R.id.btnOkTrataPalavra);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!alteracao && !insercao) {
					throw new UnsupportedOperationException("Nao foi selecionado nem alteracao nem insercao para PalavraQuadroTrata");
				} else {
					if (item==null) {
						throw new UnsupportedOperationException("Item com valor null em PalavraQuadroTrataBase");
					}
					if (validaCamposTela().getProsseguir()) {
						if (alteracao) {
							alteraItem(item);
							retornaTela();
						}
						if (insercao) {
							insereItem(item);
							retornaTela();
						}
					}
				}
			}
		});
	}

	protected abstract ResultadoValidacao validaCamposTela();
	//	throw new RuntimeException("Implementar validaCamposTela de PalavraQuadroTrataBase");
	//}

	@Override
	protected EditText getUltimoCampo() {
		return null;
	}
	@Override
	public void onAlteraQuadro() {
	}

	protected final void alteraItem(Palavra valor) {
		PalavraServico exibicaoSrv = FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
		//View saida = LayoutInflater.from(getActivity()).inflate(R.layout.trata_palavra, null);
		transfereTelaParaVo(valor);
		//exibicaoSrv.alteraParaSincronizacao(valor);
		exibicaoSrv.finalizaItemTela(valor, false, getContext());
	}
	// Passando o parametro que mesmo estando disponivel ? preciso 
	// que fique claro qual objeto deve ser tratado, colocado aqui por
	// outro quadro
	protected final void insereItem(Palavra valor) {
		PalavraServico exibicaoSrv = FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
		//View saida = LayoutInflater.from(getActivity()).inflate(R.layout.trata_palavra, null);
		transfereTelaParaVo(valor);
		//exibicaoSrv.insereParaSincronizacao(valor);
		exibicaoSrv.finalizaItemTela(valor, true, getContext());
	}
	
	protected void transfereTelaParaVo(Palavra vo)  {
		throw new UnsupportedOperationException("Fazer override de transfereTelaParaVo em PalavraQuadroTrata ou verificar se nao esta sendo chamada via super");
	}
	protected void transfereVoParaTela(final Palavra vo)  {
		throw new UnsupportedOperationException("Fazer override de transfereVoParaTela em PalavraQuadroTrata ou verificar se nao esta sendo chamada via super");
	}
	public String getTituloTela()  {
		return "Alterar em " + this.getClass().getSimpleName();
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.trata_palavra,"R.layout.trata_palavra");
		return recurso;
	}


	@Override
	protected final void inicializaItensTelaBase() {
		if (item==null) {
			PalavraServico servico = FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
			item = FabricaVo.criaPalavra();
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
