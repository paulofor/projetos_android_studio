package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.*;


public class LojaVirtualAgregado implements LojaVirtualAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private LojaVirtualCarregador carregador = null;
	private LojaVirtualCarregador getCarregador() {
		if (carregador==null) {
			carregador = new LojaVirtualCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private LojaVirtual vo;
	public LojaVirtualAgregado(LojaVirtual item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaProduto_Possui() {
		return listaprodutoPossui!= null; 
	}
	private List<Produto> listaprodutoPossui;
	private boolean daoListaprodutoPossui = false;
	public void setListaProduto_Possui(List<Produto> value)
	{	
		listaprodutoPossui = value;
	}
	public void setListaProduto_PossuiByDao(List<Produto> value)
	{	
	//	listaprodutoPossui = value;
	//	daoListaprodutoPossui = (value!=null);
	}  
	public List<Produto> getListaProduto_Possui()
	{	
	//	if (!daoListaprodutoPossui)
    //    {
    //    ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoPossui = srv.getPorLidoEmLojaVirtual(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaprodutoPossui;
	} 
	public List<Produto> getListaProduto_PossuiOriginal()
	{	
		return listaprodutoPossui;
	} 
	public List<Produto> getListaProduto_Possui(int qtde)
	{	
    //    ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoPossui = srv.getPorLidoEmLojaVirtual(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoPossui;
	} 
	public void addListaProduto_Possui(Produto value) 
	{	
		criaVaziaListaProduto_Possui();
		listaprodutoPossui.add(value);
	//	daoListaprodutoPossui = true;
	} 
	public Produto getCorrenteProduto_Possui()
	{	
		if (listaprodutoPossui==null) return null;
		return listaprodutoPossui.get(listaprodutoPossui.size()-1);
	} 
	public void criaVaziaListaProduto_Possui() {
		if (listaprodutoPossui == null)
        {
        	listaprodutoPossui = new ArrayList<Produto>();
        }
	}
	
}
