package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class CategoriaLojaProdutoAgregado implements CategoriaLojaProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CategoriaLojaProdutoCarregador carregador = null;
	private CategoriaLojaProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CategoriaLojaProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.CategoriaLoja_ReferenteA != null)
			vo.CategoriaLoja_ReferenteA.setConexaoCarregador(conexao);
		if (vo.Produto_ReferenteA != null)
			vo.Produto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private CategoriaLojaProduto vo;
	public CategoriaLojaProdutoAgregado(CategoriaLojaProduto item) {
		vo = item;
	}
	
	
	
	private CategoriaLoja categoriaLojaReferenteA;
	public void setCategoriaLoja_ReferenteA(CategoriaLoja valor)
	{	
		vo.setIdCategoriaLojaRa(0);
		categoriaLojaReferenteA = valor;
	} 
	public CategoriaLoja getCategoriaLoja_ReferenteA() 
	{	
		
		if (categoriaLojaReferenteA==null &&
				vo.getIdCategoriaLojaRaLazyLoader() !=0)
		{
			CategoriaLojaServico srv = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
			categoriaLojaReferenteA = srv.getById(vo.getIdCategoriaLojaRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLojaProduto.getCategoriaLoja_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return categoriaLojaReferenteA;
	} 
	
	public void addListaCategoriaLoja_ReferenteA(CategoriaLoja value)
	{	
		categoriaLojaReferenteA = value;
	} 
	public CategoriaLoja getCorrenteCategoriaLoja_ReferenteA()
	{	
		return categoriaLojaReferenteA;
	} 
	
	
	
	private Produto produtoReferenteA;
	public void setProduto_ReferenteA(Produto valor)
	{	
		vo.setIdProdutoRa(0);
		produtoReferenteA = valor;
	} 
	public Produto getProduto_ReferenteA() 
	{	
		
		if (produtoReferenteA==null &&
				vo.getIdProdutoRaLazyLoader() !=0)
		{
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
			produtoReferenteA = srv.getById(vo.getIdProdutoRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLojaProduto.getProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
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
