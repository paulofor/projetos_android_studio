package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface ExecucaoItemSerieAgregadoI{

	// ComChaveEstrangeira
  	
		public ItemSerie getItemSerie_ReferenteA(); 
		public void setItemSerie_ReferenteA(ItemSerie item); 
		
		public void addListaItemSerie_ReferenteA(ItemSerie item); 
		public ItemSerie getCorrenteItemSerie_ReferenteA(); 
		
		
		public DiaTreino getDiaTreino_Em(); 
		public void setDiaTreino_Em(DiaTreino item); 
		
		public void addListaDiaTreino_Em(DiaTreino item); 
		public DiaTreino getCorrenteDiaTreino_Em(); 
		
		
		public Exercicio getExercicio_ReferenteA(); 
		public void setExercicio_ReferenteA(Exercicio item); 
		
		public void addListaExercicio_ReferenteA(Exercicio item); 
		public Exercicio getCorrenteExercicio_ReferenteA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
