package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface SerieTreinoAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
		public ItemSerie getCorrenteItemSerie_Possui();
		public void addListaItemSerie_Possui(ItemSerie item);
		public List<ItemSerie> getListaItemSerie_Possui();
		public List<ItemSerie> getListaItemSerie_PossuiOriginal();
		public List<ItemSerie> getListaItemSerie_Possui(int qtde);
		public void setListaItemSerie_Possui(List<ItemSerie> lista); 
		public void setListaItemSerie_PossuiByDao(List<ItemSerie> lista); 
		public void criaVaziaListaItemSerie_Possui();
		public boolean existeListaItemSerie_Possui();
 		
		public DiaTreino getCorrenteDiaTreino_SerieDia();
		public void addListaDiaTreino_SerieDia(DiaTreino item);
		public List<DiaTreino> getListaDiaTreino_SerieDia();
		public List<DiaTreino> getListaDiaTreino_SerieDiaOriginal();
		public List<DiaTreino> getListaDiaTreino_SerieDia(int qtde);
		public void setListaDiaTreino_SerieDia(List<DiaTreino> lista); 
		public void setListaDiaTreino_SerieDiaByDao(List<DiaTreino> lista); 
		public void criaVaziaListaDiaTreino_SerieDia();
		public boolean existeListaDiaTreino_SerieDia();
 		
	
	// UmPraUm
	
}
