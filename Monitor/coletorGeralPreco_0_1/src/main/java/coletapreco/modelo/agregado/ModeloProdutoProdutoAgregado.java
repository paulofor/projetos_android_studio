package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class ModeloProdutoProdutoAgregado implements ModeloProdutoProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ModeloProdutoProdutoCarregador carregador = null;
	private ModeloProdutoProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ModeloProdutoProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.ModeloProduto_ReferenteA != null)
			vo.ModeloProduto_ReferenteA.setConexaoCarregador(conexao);
		if (vo.Produto_ReferenteA != null)
			vo.Produto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ModeloProdutoProduto vo;
	public ModeloProdutoProdutoAgregado(ModeloProdutoProduto item) {
		vo = item;
	}
	
	
	
	private ModeloProduto modeloProdutoReferenteA;
	public void setModeloProduto_ReferenteA(ModeloProduto valor)
	{	
		vo.setIdModeloProdutoRa(0);
		modeloProdutoReferenteA = valor;
	} 
	public ModeloProduto getModeloProduto_ReferenteA() 
	{	
		
		if (modeloProdutoReferenteA==null &&
				vo.getIdModeloProdutoRaLazyLoader() !=0)
		{
			ModeloProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_SQLITE);
			modeloProdutoReferenteA = srv.getById(vo.getIdModeloProdutoRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProdutoProduto.getModeloProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
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
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProdutoProduto.getProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
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
