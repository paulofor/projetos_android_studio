package repcom.modelo.agregado;

import java.util.List;
import repcom.modelo.*;

public interface ProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public LinhaProduto getLinhaProduto_EstaEm(); 
		public void setLinhaProduto_EstaEm(LinhaProduto item); 
		
		public void addListaLinhaProduto_EstaEm(LinhaProduto item); 
		public LinhaProduto getCorrenteLinhaProduto_EstaEm(); 
		
		
	
	// SemChaveEstrangeira
	
		public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada();
		public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor item);
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada();
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal();
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde);
		public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> lista); 
		public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> lista); 
		public void criaVaziaListaProdutoPedidoFornecedor_Associada();
		public boolean existeListaProdutoPedidoFornecedor_Associada();
 		
		public ProdutoVenda getCorrenteProdutoVenda_Associada();
		public void addListaProdutoVenda_Associada(ProdutoVenda item);
		public List<ProdutoVenda> getListaProdutoVenda_Associada();
		public List<ProdutoVenda> getListaProdutoVenda_AssociadaOriginal();
		public List<ProdutoVenda> getListaProdutoVenda_Associada(int qtde);
		public void setListaProdutoVenda_Associada(List<ProdutoVenda> lista); 
		public void setListaProdutoVenda_AssociadaByDao(List<ProdutoVenda> lista); 
		public void criaVaziaListaProdutoVenda_Associada();
		public boolean existeListaProdutoVenda_Associada();
 		
		public PrecoProduto getCorrentePrecoProduto_Possui();
		public void addListaPrecoProduto_Possui(PrecoProduto item);
		public List<PrecoProduto> getListaPrecoProduto_Possui();
		public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal();
		public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde);
		public void setListaPrecoProduto_Possui(List<PrecoProduto> lista); 
		public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> lista); 
		public void criaVaziaListaPrecoProduto_Possui();
		public boolean existeListaPrecoProduto_Possui();
 		
		public CategoriaProdutoProduto getCorrenteCategoriaProdutoProduto_Possui();
		public void addListaCategoriaProdutoProduto_Possui(CategoriaProdutoProduto item);
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui();
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_PossuiOriginal();
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui(int qtde);
		public void setListaCategoriaProdutoProduto_Possui(List<CategoriaProdutoProduto> lista); 
		public void setListaCategoriaProdutoProduto_PossuiByDao(List<CategoriaProdutoProduto> lista); 
		public void criaVaziaListaCategoriaProdutoProduto_Possui();
		public boolean existeListaCategoriaProdutoProduto_Possui();
 		
	
	// UmPraUm
	
}
