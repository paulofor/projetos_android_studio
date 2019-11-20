package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface ProdutoClienteAgregadoI{

	// ComChaveEstrangeira
  	
		public NaturezaProduto getNaturezaProduto_ReferenteA(); 
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
		public InteresseProduto getCorrenteInteresseProduto_Possui();
		public void addListaInteresseProduto_Possui(InteresseProduto item);
		//public List<InteresseProduto> getListaInteresseProduto_Possui();
		public List<InteresseProduto> getListaInteresseProduto_PossuiOriginal();
		//public List<InteresseProduto> getListaInteresseProduto_Possui(int qtde);
		//public void setListaInteresseProduto_Possui(List<InteresseProduto> lista); 
		//public void setListaInteresseProduto_PossuiByDao(List<InteresseProduto> lista); 
		//public void criaVaziaListaInteresseProduto_Possui();
		public boolean existeListaInteresseProduto_Possui();
 		
		public PrecoDiarioCliente getCorrentePrecoDiarioCliente_Possui();
		public void addListaPrecoDiarioCliente_Possui(PrecoDiarioCliente item);
		//public List<PrecoDiarioCliente> getListaPrecoDiarioCliente_Possui();
		public List<PrecoDiarioCliente> getListaPrecoDiarioCliente_PossuiOriginal();
		//public List<PrecoDiarioCliente> getListaPrecoDiarioCliente_Possui(int qtde);
		//public void setListaPrecoDiarioCliente_Possui(List<PrecoDiarioCliente> lista); 
		//public void setListaPrecoDiarioCliente_PossuiByDao(List<PrecoDiarioCliente> lista); 
		//public void criaVaziaListaPrecoDiarioCliente_Possui();
		public boolean existeListaPrecoDiarioCliente_Possui();
 		
	
	// UmPraUm
	
}
