package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class ProdutoVendaAgregado implements ProdutoVendaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoVendaCarregador carregador = null;
	private ProdutoVendaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoVendaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Produto_Associada != null)
			vo.Produto_Associada.setConexaoCarregador(conexao);
		if (vo.Venda_Associada != null)
			vo.Venda_Associada.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ProdutoVenda vo;
	public ProdutoVendaAgregado(ProdutoVenda item) {
		vo = item;
	}
	
	
	
	private Produto produtoAssociada;
	public void setProduto_Associada(Produto valor)
	{	
		vo.setIdProdutoA(0);
		produtoAssociada = valor;
	} 
	public Produto getProduto_Associada() 
	{	
	//	if (produtoAssociada==null &&
	//			vo.getIdProdutoALazyLoader() !=0)
	//	{
	//		ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	//		produtoAssociada = srv.getById(vo.getIdProdutoALazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoVenda.getProduto_Associada()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return produtoAssociada;
	} 
	
	public void addListaProduto_Associada(Produto value)
	{	
		produtoAssociada = value;
	} 
	public Produto getCorrenteProduto_Associada()
	{	
		return produtoAssociada;
	} 
	
	
	
	private Venda vendaAssociada;
	public void setVenda_Associada(Venda valor)
	{	
		vo.setIdVendaA(0);
		vendaAssociada = valor;
	} 
	public Venda getVenda_Associada() 
	{	
	//	if (vendaAssociada==null &&
	//			vo.getIdVendaALazyLoader() !=0)
	//	{
	//		VendaServico srv = FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
	//		vendaAssociada = srv.getById(vo.getIdVendaALazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoVenda.getVenda_Associada()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return vendaAssociada;
	} 
	
	public void addListaVenda_Associada(Venda value)
	{	
		vendaAssociada = value;
	} 
	public Venda getCorrenteVenda_Associada()
	{	
		return vendaAssociada;
	} 
	
	
 	
 	
 	
 	
 	
}
