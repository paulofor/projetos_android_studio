package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.capitalexterno.modelo.*;

public interface NegocioAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public CaracteristicaMercado getCorrenteCaracteristicaMercado_Possui();
		public void addListaCaracteristicaMercado_Possui(CaracteristicaMercado item);
		//public List<CaracteristicaMercado> getListaCaracteristicaMercado_Possui();
		public List<CaracteristicaMercado> getListaCaracteristicaMercado_PossuiOriginal();
		//public List<CaracteristicaMercado> getListaCaracteristicaMercado_Possui(int qtde);
		//public void setListaCaracteristicaMercado_Possui(List<CaracteristicaMercado> lista); 
		//public void setListaCaracteristicaMercado_PossuiByDao(List<CaracteristicaMercado> lista); 
		//public void criaVaziaListaCaracteristicaMercado_Possui();
		public boolean existeListaCaracteristicaMercado_Possui();
 		
	
	// UmPraUm
	
}
