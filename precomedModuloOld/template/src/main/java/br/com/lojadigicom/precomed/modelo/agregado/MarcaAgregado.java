package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.*;


public class MarcaAgregado implements MarcaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private MarcaCarregador carregador = null;
	private MarcaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new MarcaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Marca vo;
	public MarcaAgregado(Marca item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaProduto_ReferenteA() {
		return listaprodutoReferenteA!= null; 
	}
	private List<Produto> listaprodutoReferenteA;
	private boolean daoListaprodutoReferenteA = false;
	public void setListaProduto_ReferenteA(List<Produto> value)
	{	
		listaprodutoReferenteA = value;
	}
	public void setListaProduto_ReferenteAByDao(List<Produto> value)
	{	
	//	listaprodutoReferenteA = value;
	//	daoListaprodutoReferenteA = (value!=null);
	}  
	public List<Produto> getListaProduto_ReferenteA()
	{	
	//	if (!daoListaprodutoReferenteA)
    //    {
    //    ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoReferenteA = srv.getPorPossuiMarca(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Marca.getListaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaprodutoReferenteA;
	} 
	public List<Produto> getListaProduto_ReferenteAOriginal()
	{	
		return listaprodutoReferenteA;
	} 
	public List<Produto> getListaProduto_ReferenteA(int qtde)
	{	
    //    ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoReferenteA = srv.getPorPossuiMarca(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Marca.getListaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoReferenteA;
	} 
	public void addListaProduto_ReferenteA(Produto value) 
	{	
		criaVaziaListaProduto_ReferenteA();
		listaprodutoReferenteA.add(value);
	//	daoListaprodutoReferenteA = true;
	} 
	public Produto getCorrenteProduto_ReferenteA()
	{	
		if (listaprodutoReferenteA==null) return null;
		return listaprodutoReferenteA.get(listaprodutoReferenteA.size()-1);
	} 
	public void criaVaziaListaProduto_ReferenteA() {
		if (listaprodutoReferenteA == null)
        {
        	listaprodutoReferenteA = new ArrayList<Produto>();
        }
	}
	
}
