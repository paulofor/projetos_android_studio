package repcom.modelo.agregado;

import java.util.List;
import repcom.modelo.*;

public interface ClienteAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
		public ClienteInteresseCategoria getCorrenteClienteInteresseCategoria_Associada();
		public void addListaClienteInteresseCategoria_Associada(ClienteInteresseCategoria item);
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada();
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_AssociadaOriginal();
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada(int qtde);
		public void setListaClienteInteresseCategoria_Associada(List<ClienteInteresseCategoria> lista); 
		public void setListaClienteInteresseCategoria_AssociadaByDao(List<ClienteInteresseCategoria> lista); 
		public void criaVaziaListaClienteInteresseCategoria_Associada();
		public boolean existeListaClienteInteresseCategoria_Associada();
 		
		public ContatoCliente getCorrenteContatoCliente_Estabelece();
		public void addListaContatoCliente_Estabelece(ContatoCliente item);
		public List<ContatoCliente> getListaContatoCliente_Estabelece();
		public List<ContatoCliente> getListaContatoCliente_EstabeleceOriginal();
		public List<ContatoCliente> getListaContatoCliente_Estabelece(int qtde);
		public void setListaContatoCliente_Estabelece(List<ContatoCliente> lista); 
		public void setListaContatoCliente_EstabeleceByDao(List<ContatoCliente> lista); 
		public void criaVaziaListaContatoCliente_Estabelece();
		public boolean existeListaContatoCliente_Estabelece();
 		
		public Venda getCorrenteVenda_Comprou();
		public void addListaVenda_Comprou(Venda item);
		public List<Venda> getListaVenda_Comprou();
		public List<Venda> getListaVenda_ComprouOriginal();
		public List<Venda> getListaVenda_Comprou(int qtde);
		public void setListaVenda_Comprou(List<Venda> lista); 
		public void setListaVenda_ComprouByDao(List<Venda> lista); 
		public void criaVaziaListaVenda_Comprou();
		public boolean existeListaVenda_Comprou();
 		
	
	// UmPraUm
	
}
