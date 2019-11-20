package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

public class ProdutoPedidoFornecedorAgregado implements ProdutoPedidoFornecedorAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoPedidoFornecedorCarregador carregador = null;
	private ProdutoPedidoFornecedorCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoPedidoFornecedorCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.PedidoFornecedor_Associada != null)
			vo.PedidoFornecedor_Associada.setConexaoCarregador(conexao);
		if (vo.Produto_Associada != null)
			vo.Produto_Associada.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ProdutoPedidoFornecedor vo;
	public ProdutoPedidoFornecedorAgregado(ProdutoPedidoFornecedor item) {
		vo = item;
	}
	
	
	
	private PedidoFornecedor pedidoFornecedorAssociada;
	public void setPedidoFornecedor_Associada(PedidoFornecedor valor)
	{	
		vo.setIdPedidoFornecedorA(0);
		pedidoFornecedorAssociada = valor;
	} 
	public PedidoFornecedor getPedidoFornecedor_Associada() 
	{	
		
		if (pedidoFornecedorAssociada==null &&
				vo.getIdPedidoFornecedorALazyLoader() !=0)
		{
			PedidoFornecedorServico srv = FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
			pedidoFornecedorAssociada = srv.getById(vo.getIdPedidoFornecedorALazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoPedidoFornecedor.getPedidoFornecedor_Associada()",QTDE_LOG_LAZY_LOADER);
  		}
		return pedidoFornecedorAssociada;
	} 
	
	public void addListaPedidoFornecedor_Associada(PedidoFornecedor value)
	{	
		pedidoFornecedorAssociada = value;
	} 
	public PedidoFornecedor getCorrentePedidoFornecedor_Associada()
	{	
		return pedidoFornecedorAssociada;
	} 
	
	
	
	private Produto produtoAssociada;
	public void setProduto_Associada(Produto valor)
	{	
		vo.setIdProdutoA(0);
		produtoAssociada = valor;
	} 
	public Produto getProduto_Associada() 
	{	
		
		if (produtoAssociada==null &&
				vo.getIdProdutoALazyLoader() !=0)
		{
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
			produtoAssociada = srv.getById(vo.getIdProdutoALazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoPedidoFornecedor.getProduto_Associada()",QTDE_LOG_LAZY_LOADER);
  		}
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
	
	
 	
 	
 	
 	
 	
}
