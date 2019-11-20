package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.*;


public class ProdutoPesquisaAgregado implements ProdutoPesquisaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoPesquisaCarregador carregador = null;
	private ProdutoPesquisaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoPesquisaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.ModeloProduto_ReferenteA != null)
			vo.ModeloProduto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ProdutoPesquisa vo;
	public ProdutoPesquisaAgregado(ProdutoPesquisa item) {
		vo = item;
	}
	
	
	
	private Usuario usuarioSincroniza;
	public void setUsuario_Sincroniza(Usuario valor)
	{	
		usuarioSincroniza = valor;
	} 
	public Usuario getUsuario_Sincroniza() 
	{	
	//	if (usuarioSincroniza==null &&
	//			vo.getIdUsuarioSLazyLoader() !=0)
	//	{
	//		UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
	//		usuarioSincroniza = srv.getById(vo.getIdUsuarioSLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoPesquisa.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return usuarioSincroniza;
	} 
	
	public void addListaUsuario_Sincroniza(Usuario value)
	{	
		usuarioSincroniza = value;
	} 
	public Usuario getCorrenteUsuario_Sincroniza()
	{	
		return usuarioSincroniza;
	} 
	
	
	
	private ModeloProduto modeloProdutoReferenteA;
	public void setModeloProduto_ReferenteA(ModeloProduto valor)
	{	
		modeloProdutoReferenteA = valor;
	} 
	public ModeloProduto getModeloProduto_ReferenteA() 
	{	
	//	if (modeloProdutoReferenteA==null &&
	//			vo.getIdModeloProdutoRaLazyLoader() !=0)
	//	{
	//		ModeloProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_SQLITE);
	//		modeloProdutoReferenteA = srv.getById(vo.getIdModeloProdutoRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoPesquisa.getModeloProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return modeloProdutoReferenteA;
	} 
	
	public void addListaModeloProduto_ReferenteA(ModeloProduto value)
	{	
		modeloProdutoReferenteA = value;
	} 
	public ModeloProduto getCorrenteModeloProduto_ReferenteA()
	{	
		return modeloProdutoReferenteA;
	} 
	
	
 	
 	
 	
 	
 	
}
