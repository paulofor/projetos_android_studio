package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class LinhaProdutoAgregado implements LinhaProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private LinhaProdutoCarregador carregador = null;
	private LinhaProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new LinhaProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private LinhaProduto vo;
	public LinhaProdutoAgregado(LinhaProduto item) {
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
	//	listaprodutoPossui = srv.getPorPertenceALinhaProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "LinhaProduto.getListaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
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
	//	listaprodutoPossui = srv.getPorPertenceALinhaProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "LinhaProduto.getListaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
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
