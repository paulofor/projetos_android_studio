package repcom.tela.listauso;

import java.util.List;

import repcom.modelo.CategoriaProduto;
import repcom.modelo.Produto;
import repcom.servico.ProdutoServico;
import repcom.tela.listauso.base.ProdutoListaUsoBase;
import android.content.Context;

public class ProdutoListaUso extends ProdutoListaUsoBase {

	
	private CategoriaProduto categoriaPai = null;
	
	public void setCategoriaPai(CategoriaProduto categoria) {
		categoriaPai = categoria;
	}

	@Override
	protected List<Produto> getListaCorrente(Context contexto, ProdutoServico servico) {
		List<Produto> listaProduto = null;
		if (categoriaPai==null)
			throw new RuntimeException("Sem categoria pai");
		servico.getFiltro().setIdCategoria(categoriaPai.getId());
		listaProduto = servico.ListaPorIdCategoria(getContext());
		return listaProduto;
	}

	@Override
	public String getTituloTela() {
		return "Produtos";
	}
	
	

}
