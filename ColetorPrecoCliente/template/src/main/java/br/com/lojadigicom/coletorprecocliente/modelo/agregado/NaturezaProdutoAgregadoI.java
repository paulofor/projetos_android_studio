package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface NaturezaProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public AppProduto getAppProduto_AtendidoPor(); 
		public void setAppProduto_AtendidoPor(AppProduto item); 
		
		public void addListaAppProduto_AtendidoPor(AppProduto item); 
		public AppProduto getCorrenteAppProduto_AtendidoPor(); 
		
		
	
	// SemChaveEstrangeira
	
		public OportunidadeDia getCorrenteOportunidadeDia_Possui();
		public void addListaOportunidadeDia_Possui(OportunidadeDia item);
		//public List<OportunidadeDia> getListaOportunidadeDia_Possui();
		public List<OportunidadeDia> getListaOportunidadeDia_PossuiOriginal();
		//public List<OportunidadeDia> getListaOportunidadeDia_Possui(int qtde);
		//public void setListaOportunidadeDia_Possui(List<OportunidadeDia> lista); 
		//public void setListaOportunidadeDia_PossuiByDao(List<OportunidadeDia> lista); 
		//public void criaVaziaListaOportunidadeDia_Possui();
		public boolean existeListaOportunidadeDia_Possui();
 		
		public UsuarioPesquisa getCorrenteUsuarioPesquisa_PesquisadoPor();
		public void addListaUsuarioPesquisa_PesquisadoPor(UsuarioPesquisa item);
		//public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor();
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPorOriginal();
		//public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor(int qtde);
		//public void setListaUsuarioPesquisa_PesquisadoPor(List<UsuarioPesquisa> lista); 
		//public void setListaUsuarioPesquisa_PesquisadoPorByDao(List<UsuarioPesquisa> lista); 
		//public void criaVaziaListaUsuarioPesquisa_PesquisadoPor();
		public boolean existeListaUsuarioPesquisa_PesquisadoPor();
 		
		public PalavraChavePesquisa getCorrentePalavraChavePesquisa_Possui();
		public void addListaPalavraChavePesquisa_Possui(PalavraChavePesquisa item);
		//public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_Possui();
		public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_PossuiOriginal();
		//public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_Possui(int qtde);
		//public void setListaPalavraChavePesquisa_Possui(List<PalavraChavePesquisa> lista); 
		//public void setListaPalavraChavePesquisa_PossuiByDao(List<PalavraChavePesquisa> lista); 
		//public void criaVaziaListaPalavraChavePesquisa_Possui();
		public boolean existeListaPalavraChavePesquisa_Possui();
 		
		public ProdutoCliente getCorrenteProdutoCliente_Possui();
		public void addListaProdutoCliente_Possui(ProdutoCliente item);
		//public List<ProdutoCliente> getListaProdutoCliente_Possui();
		public List<ProdutoCliente> getListaProdutoCliente_PossuiOriginal();
		//public List<ProdutoCliente> getListaProdutoCliente_Possui(int qtde);
		//public void setListaProdutoCliente_Possui(List<ProdutoCliente> lista); 
		//public void setListaProdutoCliente_PossuiByDao(List<ProdutoCliente> lista); 
		//public void criaVaziaListaProdutoCliente_Possui();
		public boolean existeListaProdutoCliente_Possui();
 		
	
	// UmPraUm
	
}
