package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.*;


public class ModeloProdutoAgregado implements ModeloProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ModeloProdutoCarregador carregador = null;
	private ModeloProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ModeloProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private ModeloProduto vo;
	public ModeloProdutoAgregado(ModeloProduto item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaModeloProdutoProduto_ReferenteA() {
		return listamodeloProdutoProdutoReferenteA!= null; 
	}
	private List<ModeloProdutoProduto> listamodeloProdutoProdutoReferenteA;
	private boolean daoListamodeloProdutoProdutoReferenteA = false;
	public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> value)
	{	
		listamodeloProdutoProdutoReferenteA = value;
	}
	public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> value)
	{	
	//	listamodeloProdutoProdutoReferenteA = value;
	//	daoListamodeloProdutoProdutoReferenteA = (value!=null);
	}  
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA()
	{	
	//	if (!daoListamodeloProdutoProdutoReferenteA)
    //    {
    //    ModeloProdutoProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAModeloProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProduto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listamodeloProdutoProdutoReferenteA;
	} 
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal()
	{	
		return listamodeloProdutoProdutoReferenteA;
	} 
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde)
	{	
    //    ModeloProdutoProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAModeloProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProduto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
		return listamodeloProdutoProdutoReferenteA;
	} 
	public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto value) 
	{	
		criaVaziaListaModeloProdutoProduto_ReferenteA();
		listamodeloProdutoProdutoReferenteA.add(value);
	//	daoListamodeloProdutoProdutoReferenteA = true;
	} 
	public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA()
	{	
		if (listamodeloProdutoProdutoReferenteA==null) return null;
		return listamodeloProdutoProdutoReferenteA.get(listamodeloProdutoProdutoReferenteA.size()-1);
	} 
	public void criaVaziaListaModeloProdutoProduto_ReferenteA() {
		if (listamodeloProdutoProdutoReferenteA == null)
        {
        	listamodeloProdutoProdutoReferenteA = new ArrayList<ModeloProdutoProduto>();
        }
	}
	
	public boolean existeListaProdutoPesquisa_Viabiliza() {
		return listaprodutoPesquisaViabiliza!= null; 
	}
	private List<ProdutoPesquisa> listaprodutoPesquisaViabiliza;
	private boolean daoListaprodutoPesquisaViabiliza = false;
	public void setListaProdutoPesquisa_Viabiliza(List<ProdutoPesquisa> value)
	{	
		listaprodutoPesquisaViabiliza = value;
	}
	public void setListaProdutoPesquisa_ViabilizaByDao(List<ProdutoPesquisa> value)
	{	
	//	listaprodutoPesquisaViabiliza = value;
	//	daoListaprodutoPesquisaViabiliza = (value!=null);
	}  
	public List<ProdutoPesquisa> getListaProdutoPesquisa_Viabiliza()
	{	
	//	if (!daoListaprodutoPesquisaViabiliza)
    //    {
    //    ProdutoPesquisaServico srv = FabricaServico.getInstancia().getProdutoPesquisaServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoPesquisaViabiliza = srv.getPorReferenteAModeloProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProduto.getListaProdutoPesquisa_Viabiliza()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaprodutoPesquisaViabiliza;
	} 
	public List<ProdutoPesquisa> getListaProdutoPesquisa_ViabilizaOriginal()
	{	
		return listaprodutoPesquisaViabiliza;
	} 
	public List<ProdutoPesquisa> getListaProdutoPesquisa_Viabiliza(int qtde)
	{	
    //    ProdutoPesquisaServico srv = FabricaServico.getInstancia().getProdutoPesquisaServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoPesquisaViabiliza = srv.getPorReferenteAModeloProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProduto.getListaProdutoPesquisa_Viabiliza()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoPesquisaViabiliza;
	} 
	public void addListaProdutoPesquisa_Viabiliza(ProdutoPesquisa value) 
	{	
		criaVaziaListaProdutoPesquisa_Viabiliza();
		listaprodutoPesquisaViabiliza.add(value);
	//	daoListaprodutoPesquisaViabiliza = true;
	} 
	public ProdutoPesquisa getCorrenteProdutoPesquisa_Viabiliza()
	{	
		if (listaprodutoPesquisaViabiliza==null) return null;
		return listaprodutoPesquisaViabiliza.get(listaprodutoPesquisaViabiliza.size()-1);
	} 
	public void criaVaziaListaProdutoPesquisa_Viabiliza() {
		if (listaprodutoPesquisaViabiliza == null)
        {
        	listaprodutoPesquisaViabiliza = new ArrayList<ProdutoPesquisa>();
        }
	}
	
}
