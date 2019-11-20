package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface NaturezaProdutoAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public CategoriaLoja getCorrenteCategoriaLoja_Possui();
		public void addListaCategoriaLoja_Possui(CategoriaLoja item);
		public List<CategoriaLoja> getListaCategoriaLoja_Possui();
		public List<CategoriaLoja> getListaCategoriaLoja_PossuiOriginal();
		public List<CategoriaLoja> getListaCategoriaLoja_Possui(int qtde);
		public void setListaCategoriaLoja_Possui(List<CategoriaLoja> lista); 
		public void setListaCategoriaLoja_PossuiByDao(List<CategoriaLoja> lista); 
		public void criaVaziaListaCategoriaLoja_Possui();
		public boolean existeListaCategoriaLoja_Possui();
 		
		public LojaNatureza getCorrenteLojaNatureza_Encontrada();
		public void addListaLojaNatureza_Encontrada(LojaNatureza item);
		public List<LojaNatureza> getListaLojaNatureza_Encontrada();
		public List<LojaNatureza> getListaLojaNatureza_EncontradaOriginal();
		public List<LojaNatureza> getListaLojaNatureza_Encontrada(int qtde);
		public void setListaLojaNatureza_Encontrada(List<LojaNatureza> lista); 
		public void setListaLojaNatureza_EncontradaByDao(List<LojaNatureza> lista); 
		public void criaVaziaListaLojaNatureza_Encontrada();
		public boolean existeListaLojaNatureza_Encontrada();
 		
		public OportunidadeDia getCorrenteOportunidadeDia_Possui();
		public void addListaOportunidadeDia_Possui(OportunidadeDia item);
		public List<OportunidadeDia> getListaOportunidadeDia_Possui();
		public List<OportunidadeDia> getListaOportunidadeDia_PossuiOriginal();
		public List<OportunidadeDia> getListaOportunidadeDia_Possui(int qtde);
		public void setListaOportunidadeDia_Possui(List<OportunidadeDia> lista); 
		public void setListaOportunidadeDia_PossuiByDao(List<OportunidadeDia> lista); 
		public void criaVaziaListaOportunidadeDia_Possui();
		public boolean existeListaOportunidadeDia_Possui();
 		
		public UsuarioPesquisa getCorrenteUsuarioPesquisa_PesquisadoPor();
		public void addListaUsuarioPesquisa_PesquisadoPor(UsuarioPesquisa item);
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor();
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPorOriginal();
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor(int qtde);
		public void setListaUsuarioPesquisa_PesquisadoPor(List<UsuarioPesquisa> lista); 
		public void setListaUsuarioPesquisa_PesquisadoPorByDao(List<UsuarioPesquisa> lista); 
		public void criaVaziaListaUsuarioPesquisa_PesquisadoPor();
		public boolean existeListaUsuarioPesquisa_PesquisadoPor();
 		
	
	// UmPraUm
	
}
