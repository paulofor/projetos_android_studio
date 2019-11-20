package repcom.modelo.agregado;

import java.util.List;
import repcom.modelo.*;

public interface DispositivoUsuarioAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_ReferenteA(); 
		public void setUsuario_ReferenteA(Usuario item); 
		
		public void addListaUsuario_ReferenteA(Usuario item); 
		public Usuario getCorrenteUsuario_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
