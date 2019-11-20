package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;

public interface OportunidadeDiaAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
