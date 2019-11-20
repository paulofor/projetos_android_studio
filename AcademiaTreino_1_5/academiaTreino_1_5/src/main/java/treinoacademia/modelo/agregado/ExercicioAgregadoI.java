package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface ExercicioAgregadoI{

	// ComChaveEstrangeira
  	
		public GrupoMuscular getGrupoMuscular_Para(); 
		public void setGrupoMuscular_Para(GrupoMuscular item); 
		
		public void addListaGrupoMuscular_Para(GrupoMuscular item); 
		public GrupoMuscular getCorrenteGrupoMuscular_Para(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
		public ItemSerie getCorrenteItemSerie_Gera();
		public void addListaItemSerie_Gera(ItemSerie item);
		public List<ItemSerie> getListaItemSerie_Gera();
		public List<ItemSerie> getListaItemSerie_GeraOriginal();
		public List<ItemSerie> getListaItemSerie_Gera(int qtde);
		public void setListaItemSerie_Gera(List<ItemSerie> lista); 
		public void setListaItemSerie_GeraByDao(List<ItemSerie> lista); 
		public void criaVaziaListaItemSerie_Gera();
		public boolean existeListaItemSerie_Gera();
 		
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Executado();
		public void addListaExecucaoItemSerie_Executado(ExecucaoItemSerie item);
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado();
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_ExecutadoOriginal();
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado(int qtde);
		public void setListaExecucaoItemSerie_Executado(List<ExecucaoItemSerie> lista); 
		public void setListaExecucaoItemSerie_ExecutadoByDao(List<ExecucaoItemSerie> lista); 
		public void criaVaziaListaExecucaoItemSerie_Executado();
		public boolean existeListaExecucaoItemSerie_Executado();
 		
	
	// UmPraUm
	
}
