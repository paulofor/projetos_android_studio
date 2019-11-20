package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface VendaAgregadoI{

	// ComChaveEstrangeira
  	
		public Cliente getCliente_FeitaPara(); 
		public void setCliente_FeitaPara(Cliente item); 
		
		public void addListaCliente_FeitaPara(Cliente item); 
		public Cliente getCorrenteCliente_FeitaPara(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
		public ProdutoVenda getCorrenteProdutoVenda_Associada();
		public void addListaProdutoVenda_Associada(ProdutoVenda item);
		//public List<ProdutoVenda> getListaProdutoVenda_Associada();
		public List<ProdutoVenda> getListaProdutoVenda_AssociadaOriginal();
		//public List<ProdutoVenda> getListaProdutoVenda_Associada(int qtde);
		//public void setListaProdutoVenda_Associada(List<ProdutoVenda> lista); 
		//public void setListaProdutoVenda_AssociadaByDao(List<ProdutoVenda> lista); 
		//public void criaVaziaListaProdutoVenda_Associada();
		public boolean existeListaProdutoVenda_Associada();
 		
		public ParcelaVenda getCorrenteParcelaVenda_Possui();
		public void addListaParcelaVenda_Possui(ParcelaVenda item);
		//public List<ParcelaVenda> getListaParcelaVenda_Possui();
		public List<ParcelaVenda> getListaParcelaVenda_PossuiOriginal();
		//public List<ParcelaVenda> getListaParcelaVenda_Possui(int qtde);
		//public void setListaParcelaVenda_Possui(List<ParcelaVenda> lista); 
		//public void setListaParcelaVenda_PossuiByDao(List<ParcelaVenda> lista); 
		//public void criaVaziaListaParcelaVenda_Possui();
		public boolean existeListaParcelaVenda_Possui();
 		
	
	// UmPraUm
	
}
