package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface DispositivoUsuarioAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_ReferenteA(); 
		public void setUsuario_ReferenteA(Usuario item); 
		
		public void addListaUsuario_ReferenteA(Usuario item); 
		public Usuario getCorrenteUsuario_ReferenteA(); 
		
		
		public AppProduto getAppProduto_Usa(); 
		public void setAppProduto_Usa(AppProduto item); 
		
		public void addListaAppProduto_Usa(AppProduto item); 
		public AppProduto getCorrenteAppProduto_Usa(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
