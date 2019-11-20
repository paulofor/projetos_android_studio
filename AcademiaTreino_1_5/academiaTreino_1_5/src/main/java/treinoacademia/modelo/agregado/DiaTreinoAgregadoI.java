package treinoacademia.modelo.agregado;

import java.util.List;
import treinoacademia.modelo.*;

public interface DiaTreinoAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
		public SerieTreino getSerieTreino_SerieDia(); 
		public void setSerieTreino_SerieDia(SerieTreino item); 
		
		public void addListaSerieTreino_SerieDia(SerieTreino item); 
		public SerieTreino getCorrenteSerieTreino_SerieDia(); 
		
		
	
	// SemChaveEstrangeira
	
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_FoiRealizado();
		public void addListaExecucaoItemSerie_FoiRealizado(ExecucaoItemSerie item);
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado();
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizadoOriginal();
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado(int qtde);
		public void setListaExecucaoItemSerie_FoiRealizado(List<ExecucaoItemSerie> lista); 
		public void setListaExecucaoItemSerie_FoiRealizadoByDao(List<ExecucaoItemSerie> lista); 
		public void criaVaziaListaExecucaoItemSerie_FoiRealizado();
		public boolean existeListaExecucaoItemSerie_FoiRealizado();
 		
	
	// UmPraUm
	
}
