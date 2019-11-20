package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class CategoriaLojaAgregado implements CategoriaLojaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CategoriaLojaCarregador carregador = null;
	private CategoriaLojaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CategoriaLojaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.CategoriaLoja_Filho != null)
			vo.CategoriaLoja_Filho.setConexaoCarregador(conexao);
		if (vo.NaturezaProduto_ReferenteA != null)
			vo.NaturezaProduto_ReferenteA.setConexaoCarregador(conexao);
		if (vo.LojaVirtual_PertenceA != null)
			vo.LojaVirtual_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private CategoriaLoja vo;
	public CategoriaLojaAgregado(CategoriaLoja item) {
		vo = item;
	}
	
	
	
	private CategoriaLoja categoriaLojaFilho;
	public void setCategoriaLoja_Filho(CategoriaLoja valor)
	{	
		vo.setIdCategoriaLojaF(0);
		categoriaLojaFilho = valor;
	} 
	public CategoriaLoja getCategoriaLoja_Filho() 
	{	
		
		if (categoriaLojaFilho==null &&
				vo.getIdCategoriaLojaFLazyLoader() !=0)
		{
			CategoriaLojaServico srv = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
			categoriaLojaFilho = srv.getById(vo.getIdCategoriaLojaFLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getCategoriaLoja_Filho()",QTDE_LOG_LAZY_LOADER);
  		}
		return categoriaLojaFilho;
	} 
	
	/*  AutoRelacionamento
	
	public void addListaCategoriaLoja_Filho(CategoriaLoja value)
	{	
		categoriaLojaFilho = value;
	} 
	public CategoriaLoja getCorrenteCategoriaLoja_Filho()
	{	
		return categoriaLojaFilho;
	} 
	
	*/
	
	
	
	private NaturezaProduto naturezaProdutoReferenteA;
	public void setNaturezaProduto_ReferenteA(NaturezaProduto valor)
	{	
		vo.setIdNaturezaProdutoRa(0);
		naturezaProdutoReferenteA = valor;
	} 
	public NaturezaProduto getNaturezaProduto_ReferenteA() 
	{	
		
		if (naturezaProdutoReferenteA==null &&
				vo.getIdNaturezaProdutoRaLazyLoader() !=0)
		{
			NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
			naturezaProdutoReferenteA = srv.getById(vo.getIdNaturezaProdutoRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getNaturezaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return naturezaProdutoReferenteA;
	} 
	
	public void addListaNaturezaProduto_ReferenteA(NaturezaProduto value)
	{	
		naturezaProdutoReferenteA = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA()
	{	
		return naturezaProdutoReferenteA;
	} 
	
	
	
	private LojaVirtual lojaVirtualPertenceA;
	public void setLojaVirtual_PertenceA(LojaVirtual valor)
	{	
		vo.setIdLojaVirtualPa(0);
		lojaVirtualPertenceA = valor;
	} 
	public LojaVirtual getLojaVirtual_PertenceA() 
	{	
		
		if (lojaVirtualPertenceA==null &&
				vo.getIdLojaVirtualPaLazyLoader() !=0)
		{
			LojaVirtualServico srv = FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
			lojaVirtualPertenceA = srv.getById(vo.getIdLojaVirtualPaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getLojaVirtual_PertenceA()",QTDE_LOG_LAZY_LOADER);
  		}
		return lojaVirtualPertenceA;
	} 
	
	public void addListaLojaVirtual_PertenceA(LojaVirtual value)
	{	
		lojaVirtualPertenceA = value;
	} 
	public LojaVirtual getCorrenteLojaVirtual_PertenceA()
	{	
		return lojaVirtualPertenceA;
	} 
	
	
 	
 	
 	
 	
 	
	public boolean existeListaCategoriaLojaProduto_Possui() {
		return listacategoriaLojaProdutoPossui!= null; 
	}
	private List<CategoriaLojaProduto> listacategoriaLojaProdutoPossui;
	private boolean daoListacategoriaLojaProdutoPossui = false;
	public void setListaCategoriaLojaProduto_Possui(List<CategoriaLojaProduto> value)
	{	
		listacategoriaLojaProdutoPossui = value;
	}
	public void setListaCategoriaLojaProduto_PossuiByDao(List<CategoriaLojaProduto> value)
	{	
		listacategoriaLojaProdutoPossui = value;
		daoListacategoriaLojaProdutoPossui = true;
	}  
	public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListacategoriaLojaProdutoPossui)
        {
        CategoriaLojaProdutoServico srv = FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_SQLITE);
		listacategoriaLojaProdutoPossui = srv.getPorReferenteACategoriaLoja(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getListaCategoriaLojaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaLojaProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "CategoriaLoja.getListaCategoriaLojaProduto_Possui() est? null");
		//}
		return listacategoriaLojaProdutoPossui;
	} 
	public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_PossuiOriginal()
	{	
		return listacategoriaLojaProdutoPossui;
	} 
	public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui(int qtde)
	{	
        CategoriaLojaProdutoServico srv = FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_SQLITE);
		listacategoriaLojaProdutoPossui = srv.getPorReferenteACategoriaLoja(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getListaCategoriaLojaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listacategoriaLojaProdutoPossui;
	} 
	public void addListaCategoriaLojaProduto_Possui(CategoriaLojaProduto value) 
	{	
		criaVaziaListaCategoriaLojaProduto_Possui();
		listacategoriaLojaProdutoPossui.add(value);
		daoListacategoriaLojaProdutoPossui = true;
	} 
	public CategoriaLojaProduto getCorrenteCategoriaLojaProduto_Possui()
	{	
		return listacategoriaLojaProdutoPossui.get(listacategoriaLojaProdutoPossui.size()-1);
	} 
	public void criaVaziaListaCategoriaLojaProduto_Possui() {
		if (listacategoriaLojaProdutoPossui == null)
        {
        	listacategoriaLojaProdutoPossui = new ArrayList<CategoriaLojaProduto>();
        }
	}
	
	public boolean existeListaCategoriaLoja_Filho() {
		return listacategoriaLojaFilho!= null; 
	}
	private List<CategoriaLoja> listacategoriaLojaFilho;
	private boolean daoListacategoriaLojaFilho = false;
	public void setListaCategoriaLoja_Filho(List<CategoriaLoja> value)
	{	
		listacategoriaLojaFilho = value;
	}
	public void setListaCategoriaLoja_FilhoByDao(List<CategoriaLoja> value)
	{	
		listacategoriaLojaFilho = value;
		daoListacategoriaLojaFilho = true;
	}  
	public List<CategoriaLoja> getListaCategoriaLoja_Filho()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListacategoriaLojaFilho)
        {
        CategoriaLojaServico srv = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		listacategoriaLojaFilho = srv.getPorFilhoCategoriaLoja(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getListaCategoriaLoja_Filho()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaLojaFilho==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "CategoriaLoja.getListaCategoriaLoja_Filho() est? null");
		//}
		return listacategoriaLojaFilho;
	} 
	public List<CategoriaLoja> getListaCategoriaLoja_FilhoOriginal()
	{	
		return listacategoriaLojaFilho;
	} 
	public List<CategoriaLoja> getListaCategoriaLoja_Filho(int qtde)
	{	
        CategoriaLojaServico srv = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		listacategoriaLojaFilho = srv.getPorFilhoCategoriaLoja(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaLoja.getListaCategoriaLoja_Filho()",QTDE_LOG_LAZY_LOADER);
		return listacategoriaLojaFilho;
	} 
	public void addListaCategoriaLoja_Filho(CategoriaLoja value) 
	{	
		criaVaziaListaCategoriaLoja_Filho();
		listacategoriaLojaFilho.add(value);
		daoListacategoriaLojaFilho = true;
	} 
	public CategoriaLoja getCorrenteCategoriaLoja_Filho()
	{	
		return listacategoriaLojaFilho.get(listacategoriaLojaFilho.size()-1);
	} 
	public void criaVaziaListaCategoriaLoja_Filho() {
		if (listacategoriaLojaFilho == null)
        {
        	listacategoriaLojaFilho = new ArrayList<CategoriaLoja>();
        }
	}
	
}
