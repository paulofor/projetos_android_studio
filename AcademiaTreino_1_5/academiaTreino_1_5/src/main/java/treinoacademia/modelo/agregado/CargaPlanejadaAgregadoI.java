package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface CargaPlanejadaAgregadoI{

	// ComChaveEstrangeira
  	
		public ItemSerie getItemSerie_ReferenteA(); 
		public void setItemSerie_ReferenteA(ItemSerie item); 
		
		public void addListaItemSerie_ReferenteA(ItemSerie item); 
		public ItemSerie getCorrenteItemSerie_ReferenteA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
