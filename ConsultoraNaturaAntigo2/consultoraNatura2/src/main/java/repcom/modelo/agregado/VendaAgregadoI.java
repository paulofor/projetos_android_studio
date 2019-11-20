package repcom.modelo.agregado;

import java.util.List;
import repcom.modelo.*;

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
		public List<ProdutoVenda> getListaProdutoVenda_Associada();
		public List<ProdutoVenda> getListaProdutoVenda_AssociadaOriginal();
		public List<ProdutoVenda> getListaProdutoVenda_Associada(int qtde);
		public void setListaProdutoVenda_Associada(List<ProdutoVenda> lista); 
		public void setListaProdutoVenda_AssociadaByDao(List<ProdutoVenda> lista); 
		public void criaVaziaListaProdutoVenda_Associada();
		public boolean existeListaProdutoVenda_Associada();
 		
	
	// UmPraUm
	
}
