package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface UsuarioNaturezaAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_PesquisadoPor(); 
		public void setUsuario_PesquisadoPor(Usuario item); 
		
		public void addListaUsuario_PesquisadoPor(Usuario item); 
		public Usuario getCorrenteUsuario_PesquisadoPor(); 
		
		
		public NaturezaProduto getNaturezaProduto_Pesquisa(); 
		public void setNaturezaProduto_Pesquisa(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_Pesquisa(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_Pesquisa(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
