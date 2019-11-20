package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface ParcelaVendaAgregadoI{

	// ComChaveEstrangeira
  	
		public Venda getVenda_PertenceA(); 
		public void setVenda_PertenceA(Venda item); 
		
		public void addListaVenda_PertenceA(Venda item); 
		public Venda getCorrenteVenda_PertenceA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
