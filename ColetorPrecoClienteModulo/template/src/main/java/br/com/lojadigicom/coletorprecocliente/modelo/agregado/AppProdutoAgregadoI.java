package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface AppProdutoAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public NaturezaProduto getCorrenteNaturezaProduto_Atende();
		public void addListaNaturezaProduto_Atende(NaturezaProduto item);
		//public List<NaturezaProduto> getListaNaturezaProduto_Atende();
		public List<NaturezaProduto> getListaNaturezaProduto_AtendeOriginal();
		//public List<NaturezaProduto> getListaNaturezaProduto_Atende(int qtde);
		//public void setListaNaturezaProduto_Atende(List<NaturezaProduto> lista); 
		//public void setListaNaturezaProduto_AtendeByDao(List<NaturezaProduto> lista); 
		//public void criaVaziaListaNaturezaProduto_Atende();
		public boolean existeListaNaturezaProduto_Atende();
 		
		public DispositivoUsuario getCorrenteDispositivoUsuario_UsadoPor();
		public void addListaDispositivoUsuario_UsadoPor(DispositivoUsuario item);
		//public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPor();
		public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPorOriginal();
		//public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPor(int qtde);
		//public void setListaDispositivoUsuario_UsadoPor(List<DispositivoUsuario> lista); 
		//public void setListaDispositivoUsuario_UsadoPorByDao(List<DispositivoUsuario> lista); 
		//public void criaVaziaListaDispositivoUsuario_UsadoPor();
		public boolean existeListaDispositivoUsuario_UsadoPor();
 		
	
	// UmPraUm
	
}
