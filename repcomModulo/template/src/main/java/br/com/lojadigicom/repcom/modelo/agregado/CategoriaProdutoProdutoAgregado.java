package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class CategoriaProdutoProdutoAgregado implements CategoriaProdutoProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CategoriaProdutoProdutoCarregador carregador = null;
	private CategoriaProdutoProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CategoriaProdutoProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.CategoriaProduto_ReferenteA != null)
			vo.CategoriaProduto_ReferenteA.setConexaoCarregador(conexao);
		if (vo.Produto_ReferenteA != null)
			vo.Produto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private CategoriaProdutoProduto vo;
	public CategoriaProdutoProdutoAgregado(CategoriaProdutoProduto item) {
		vo = item;
	}
	
	
	
	private CategoriaProduto categoriaProdutoReferenteA;
	public void setCategoriaProduto_ReferenteA(CategoriaProduto valor)
	{	
		categoriaProdutoReferenteA = valor;
	} 
	public CategoriaProduto getCategoriaProduto_ReferenteA() 
	{	
	//	if (categoriaProdutoReferenteA==null &&
	//			vo.getIdCategoriaProdutoRaLazyLoader() !=0)
	//	{
	//		CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
	//		categoriaProdutoReferenteA = srv.getById(vo.getIdCategoriaProdutoRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProdutoProduto.getCategoriaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return categoriaProdutoReferenteA;
	} 
	
	public void addListaCategoriaProduto_ReferenteA(CategoriaProduto value)
	{	
		categoriaProdutoReferenteA = value;
	} 
	public CategoriaProduto getCorrenteCategoriaProduto_ReferenteA()
	{	
		return categoriaProdutoReferenteA;
	} 
	
	
	
	private Produto produtoReferenteA;
	public void setProduto_ReferenteA(Produto valor)
	{	
		produtoReferenteA = valor;
	} 
	public Produto getProduto_ReferenteA() 
	{	
	//	if (produtoReferenteA==null &&
	//			vo.getIdProdutoRaLazyLoader() !=0)
	//	{
	//		ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	//		produtoReferenteA = srv.getById(vo.getIdProdutoRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProdutoProduto.getProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return produtoReferenteA;
	} 
	
	public void addListaProduto_ReferenteA(Produto value)
	{	
		produtoReferenteA = value;
	} 
	public Produto getCorrenteProduto_ReferenteA()
	{	
		return produtoReferenteA;
	} 
	
	
 	
 	
 	
 	
 	
}
