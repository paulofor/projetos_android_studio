package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface UsuarioPesquisaAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
		public NaturezaProduto getNaturezaProduto_Pesquisa(); 
		public void setNaturezaProduto_Pesquisa(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_Pesquisa(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_Pesquisa(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
