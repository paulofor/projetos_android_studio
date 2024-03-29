package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class PedidoFornecedorAgregado implements PedidoFornecedorAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PedidoFornecedorCarregador carregador = null;
	private PedidoFornecedorCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PedidoFornecedorCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private PedidoFornecedor vo;
	public PedidoFornecedorAgregado(PedidoFornecedor item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaProdutoPedidoFornecedor_Associada() {
		return listaprodutoPedidoFornecedorAssociada!= null; 
	}
	private List<ProdutoPedidoFornecedor> listaprodutoPedidoFornecedorAssociada;
	private boolean daoListaprodutoPedidoFornecedorAssociada = false;
	public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> value)
	{	
		listaprodutoPedidoFornecedorAssociada = value;
	}
	public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> value)
	{	
	//	listaprodutoPedidoFornecedorAssociada = value;
	//	daoListaprodutoPedidoFornecedorAssociada = (value!=null);
	}  
	public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada()
	{	
	//	if (!daoListaprodutoPedidoFornecedorAssociada)
    //    {
    //    ProdutoPedidoFornecedorServico srv = FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoPedidoFornecedorAssociada = srv.getPorAssociadaPedidoFornecedor(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "PedidoFornecedor.getListaProdutoPedidoFornecedor_Associada()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaprodutoPedidoFornecedorAssociada;
	} 
	public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal()
	{	
		return listaprodutoPedidoFornecedorAssociada;
	} 
	public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde)
	{	
    //    ProdutoPedidoFornecedorServico srv = FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoPedidoFornecedorAssociada = srv.getPorAssociadaPedidoFornecedor(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "PedidoFornecedor.getListaProdutoPedidoFornecedor_Associada()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoPedidoFornecedorAssociada;
	} 
	public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor value) 
	{	
		criaVaziaListaProdutoPedidoFornecedor_Associada();
		listaprodutoPedidoFornecedorAssociada.add(value);
	//	daoListaprodutoPedidoFornecedorAssociada = true;
	} 
	public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada()
	{	
		if (listaprodutoPedidoFornecedorAssociada==null) return null;
		return listaprodutoPedidoFornecedorAssociada.get(listaprodutoPedidoFornecedorAssociada.size()-1);
	} 
	public void criaVaziaListaProdutoPedidoFornecedor_Associada() {
		if (listaprodutoPedidoFornecedorAssociada == null)
        {
        	listaprodutoPedidoFornecedorAssociada = new ArrayList<ProdutoPedidoFornecedor>();
        }
	}
	
}
