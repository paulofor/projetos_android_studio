package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface DispositivoUsuarioAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_ReferenteA(); 
		public void setUsuario_ReferenteA(Usuario item); 
		
		public void addListaUsuario_ReferenteA(Usuario item); 
		public Usuario getCorrenteUsuario_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
