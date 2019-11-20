package repcom.tela.trata.impl;

import java.util.List;

import repcom.app.R;
import repcom.modelo.Produto;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.FabricaServico;
import repcom.servico.ProdutoServico;
import repcom.tela.trata.base.ProdutoVendaQuadroTrataBase;
import repcom.view.adapter.listaedicao.ProdutoListEdicaoAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.quadro.ResultadoValidacao;

public class ProdutoVendaQuadroTrata extends ProdutoVendaQuadroTrataBase  implements OnQueryTextListener, IQuadroListaEdicao{

	private SearchView pesquisaProdutoParaVenda = null;
	private ProdutoServico produtoSrv = null;
	private Produto produtoPesquisa = FabricaVo.criaProduto();
	private ListView lstPesquisaProduto = null;
	
	
	@Override
	protected void inicializaServicos() {
		produtoSrv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	}

	
	
	
	@Override
	protected void inicializaListeners() {
		pesquisaProdutoParaVenda.setOnQueryTextListener(this);
	}




	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao novo = new ResultadoValidacao();
		novo.setProsseguir(true);
		return novo;
	}
	
	@Override
	protected void inicializaItensTela() {
		pesquisaProdutoParaVenda = (SearchView) getTela().findViewById(R.id.pesquisaProdutoParaVenda);
		this.lstPesquisaProduto = (ListView) getTela().findViewById(R.id.lstPesquisaProduto);
	}




	@Override
	public boolean onQueryTextChange(String arg) {
		return atualizaNome(arg);
	}


	@Override
	public boolean onQueryTextSubmit(String arg) {
		return atualizaNome(arg);
	}
	
	private boolean atualizaNome(String nome) {
		this.produtoPesquisa.setNome(nome);
		this.produtoSrv.getFiltro().setItem(produtoPesquisa);
		List<Produto> lista = produtoSrv.PesquisaTrechoNome(getContext());
		ListAdapter adapt = new ProdutoListEdicaoAdapter(lista,this,getContext().getContext());
		lstPesquisaProduto.setAdapter(adapt);
		return false;
	}




	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
