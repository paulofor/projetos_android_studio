package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface ProdutoClienteAgregadoI{

	// ComChaveEstrangeira
  	
		public NaturezaProduto getNaturezaProduto_ReferenteA(); 
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
		public InteresseProduto getCorrenteInteresseProduto_Possui();
		public void addListaInteresseProduto_Possui(InteresseProduto item);
		//public List<InteresseProduto> getListaInteresseProduto_Possui();
		public List<InteresseProduto> getListaInteresseProduto_PossuiOriginal();
		//public List<InteresseProduto> getListaInteresseProduto_Possui(int qtde);
		//public void setListaInteresseProduto_Possui(List<InteresseProduto> lista); 
		//public void setListaInteresseProduto_PossuiByDao(List<InteresseProduto> lista); 
		//public void criaVaziaListaInteresseProduto_Possui();
		public boolean existeListaInteresseProduto_Possui();
 		
	
	// UmPraUm
	
}
