package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface ItemSerieAgregadoI{

	// ComChaveEstrangeira
  	
		public Exercicio getExercicio_ExecucaoDe(); 
		public void setExercicio_ExecucaoDe(Exercicio item); 
		
		public void addListaExercicio_ExecucaoDe(Exercicio item); 
		public Exercicio getCorrenteExercicio_ExecucaoDe(); 
		
		
		public SerieTreino getSerieTreino_PertencenteA(); 
		public void setSerieTreino_PertencenteA(SerieTreino item); 
		
		public void addListaSerieTreino_PertencenteA(SerieTreino item); 
		public SerieTreino getCorrenteSerieTreino_PertencenteA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
		public CargaPlanejada getCorrenteCargaPlanejada_Possui();
		public void addListaCargaPlanejada_Possui(CargaPlanejada item);
		public List<CargaPlanejada> getListaCargaPlanejada_Possui();
		public List<CargaPlanejada> getListaCargaPlanejada_PossuiOriginal();
		public List<CargaPlanejada> getListaCargaPlanejada_Possui(int qtde);
		public void setListaCargaPlanejada_Possui(List<CargaPlanejada> lista); 
		public void setListaCargaPlanejada_PossuiByDao(List<CargaPlanejada> lista); 
		public void criaVaziaListaCargaPlanejada_Possui();
		public boolean existeListaCargaPlanejada_Possui();
 		
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Gera();
		public void addListaExecucaoItemSerie_Gera(ExecucaoItemSerie item);
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera();
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_GeraOriginal();
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera(int qtde);
		public void setListaExecucaoItemSerie_Gera(List<ExecucaoItemSerie> lista); 
		public void setListaExecucaoItemSerie_GeraByDao(List<ExecucaoItemSerie> lista); 
		public void criaVaziaListaExecucaoItemSerie_Gera();
		public boolean existeListaExecucaoItemSerie_Gera();
 		
	
	// UmPraUm
	
}
