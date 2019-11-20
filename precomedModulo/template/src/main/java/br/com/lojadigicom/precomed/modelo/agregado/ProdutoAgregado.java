package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.*;


public class ProdutoAgregado implements ProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoCarregador carregador = null;
	private ProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.LojaVirtual_LidoEm != null)
			vo.LojaVirtual_LidoEm.setConexaoCarregador(conexao);
		if (vo.Marca_Possui != null)
			vo.Marca_Possui.setConexaoCarregador(conexao);
		
	}
	*/
	
	private Produto vo;
	public ProdutoAgregado(Produto item) {
		vo = item;
	}
	
	
	
	private LojaVirtual lojaVirtualLidoEm;
	public void setLojaVirtual_LidoEm(LojaVirtual valor)
	{	
		lojaVirtualLidoEm = valor;
	} 
	public LojaVirtual getLojaVirtual_LidoEm() 
	{	
	//	if (lojaVirtualLidoEm==null &&
	//			vo.getIdLojaVirtualLeLazyLoader() !=0)
	//	{
	//		LojaVirtualServico srv = FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
	//		lojaVirtualLidoEm = srv.getById(vo.getIdLojaVirtualLeLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getLojaVirtual_LidoEm()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return lojaVirtualLidoEm;
	} 
	
	public void addListaLojaVirtual_LidoEm(LojaVirtual value)
	{	
		lojaVirtualLidoEm = value;
	} 
	public LojaVirtual getCorrenteLojaVirtual_LidoEm()
	{	
		return lojaVirtualLidoEm;
	} 
	
	
	
	private Marca marcaPossui;
	public void setMarca_Possui(Marca valor)
	{	
		marcaPossui = valor;
	} 
	public Marca getMarca_Possui() 
	{	
	//	if (marcaPossui==null &&
	//			vo.getIdMarcaPLazyLoader() !=0)
	//	{
	//		MarcaServico srv = FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_SQLITE);
	//		marcaPossui = srv.getById(vo.getIdMarcaPLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getMarca_Possui()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return marcaPossui;
	} 
	
	public void addListaMarca_Possui(Marca value)
	{	
		marcaPossui = value;
	} 
	public Marca getCorrenteMarca_Possui()
	{	
		return marcaPossui;
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
	//	listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
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
	//	listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
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
	
	public boolean existeListaPrecoProduto_Possui() {
		return listaprecoProdutoPossui!= null; 
	}
	private List<PrecoProduto> listaprecoProdutoPossui;
	private boolean daoListaprecoProdutoPossui = false;
	public void setListaPrecoProduto_Possui(List<PrecoProduto> value)
	{	
		listaprecoProdutoPossui = value;
	}
	public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> value)
	{	
	//	listaprecoProdutoPossui = value;
	//	daoListaprecoProdutoPossui = (value!=null);
	}  
	public List<PrecoProduto> getListaPrecoProduto_Possui()
	{	
	//	if (!daoListaprecoProdutoPossui)
    //    {
    //    PrecoProdutoServico srv = FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listaprecoProdutoPossui = srv.getPorPertenceAProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPrecoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaprecoProdutoPossui;
	} 
	public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal()
	{	
		return listaprecoProdutoPossui;
	} 
	public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde)
	{	
    //    PrecoProdutoServico srv = FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listaprecoProdutoPossui = srv.getPorPertenceAProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPrecoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaprecoProdutoPossui;
	} 
	public void addListaPrecoProduto_Possui(PrecoProduto value) 
	{	
		criaVaziaListaPrecoProduto_Possui();
		listaprecoProdutoPossui.add(value);
	//	daoListaprecoProdutoPossui = true;
	} 
	public PrecoProduto getCorrentePrecoProduto_Possui()
	{	
		if (listaprecoProdutoPossui==null) return null;
		return listaprecoProdutoPossui.get(listaprecoProdutoPossui.size()-1);
	} 
	public void criaVaziaListaPrecoProduto_Possui() {
		if (listaprecoProdutoPossui == null)
        {
        	listaprecoProdutoPossui = new ArrayList<PrecoProduto>();
        }
	}
	
}
