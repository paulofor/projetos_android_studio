package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

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
		vo.setIdLojaVirtualLe(0);
		lojaVirtualLidoEm = valor;
	} 
	public LojaVirtual getLojaVirtual_LidoEm() 
	{	
		
		if (lojaVirtualLidoEm==null &&
				vo.getIdLojaVirtualLeLazyLoader() !=0)
		{
			LojaVirtualServico srv = FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
			lojaVirtualLidoEm = srv.getById(vo.getIdLojaVirtualLeLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getLojaVirtual_LidoEm()",QTDE_LOG_LAZY_LOADER);
  		}
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
		vo.setIdMarcaP(0);
		marcaPossui = valor;
	} 
	public Marca getMarca_Possui() 
	{	
		
		if (marcaPossui==null &&
				vo.getIdMarcaPLazyLoader() !=0)
		{
			MarcaServico srv = FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_SQLITE);
			marcaPossui = srv.getById(vo.getIdMarcaPLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getMarca_Possui()",QTDE_LOG_LAZY_LOADER);
  		}
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
		listamodeloProdutoProdutoReferenteA = value;
		daoListamodeloProdutoProdutoReferenteA = true;
	}  
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListamodeloProdutoProdutoReferenteA)
        {
        ModeloProdutoProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listamodeloProdutoProdutoReferenteA==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaModeloProdutoProduto_ReferenteA() est? null");
		//}
		return listamodeloProdutoProdutoReferenteA;
	} 
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal()
	{	
		return listamodeloProdutoProdutoReferenteA;
	} 
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde)
	{	
        ModeloProdutoProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
		return listamodeloProdutoProdutoReferenteA;
	} 
	public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto value) 
	{	
		criaVaziaListaModeloProdutoProduto_ReferenteA();
		listamodeloProdutoProdutoReferenteA.add(value);
		daoListamodeloProdutoProdutoReferenteA = true;
	} 
	public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA()
	{	
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
		listaprecoProdutoPossui = value;
		daoListaprecoProdutoPossui = true;
	}  
	public List<PrecoProduto> getListaPrecoProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaprecoProdutoPossui)
        {
        PrecoProdutoServico srv = FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		listaprecoProdutoPossui = srv.getPorPertenceAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPrecoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaprecoProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaPrecoProduto_Possui() est? null");
		//}
		return listaprecoProdutoPossui;
	} 
	public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal()
	{	
		return listaprecoProdutoPossui;
	} 
	public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde)
	{	
        PrecoProdutoServico srv = FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		listaprecoProdutoPossui = srv.getPorPertenceAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPrecoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaprecoProdutoPossui;
	} 
	public void addListaPrecoProduto_Possui(PrecoProduto value) 
	{	
		criaVaziaListaPrecoProduto_Possui();
		listaprecoProdutoPossui.add(value);
		daoListaprecoProdutoPossui = true;
	} 
	public PrecoProduto getCorrentePrecoProduto_Possui()
	{	
		return listaprecoProdutoPossui.get(listaprecoProdutoPossui.size()-1);
	} 
	public void criaVaziaListaPrecoProduto_Possui() {
		if (listaprecoProdutoPossui == null)
        {
        	listaprecoProdutoPossui = new ArrayList<PrecoProduto>();
        }
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
		listacategoriaLojaProdutoPossui = srv.getPorReferenteAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaCategoriaLojaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaLojaProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaCategoriaLojaProduto_Possui() est? null");
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
		listacategoriaLojaProdutoPossui = srv.getPorReferenteAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaCategoriaLojaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
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
	
	public boolean existeListaOportunidadeDia_PodePossuir() {
		return listaoportunidadeDiaPodePossuir!= null; 
	}
	private List<OportunidadeDia> listaoportunidadeDiaPodePossuir;
	private boolean daoListaoportunidadeDiaPodePossuir = false;
	public void setListaOportunidadeDia_PodePossuir(List<OportunidadeDia> value)
	{	
		listaoportunidadeDiaPodePossuir = value;
	}
	public void setListaOportunidadeDia_PodePossuirByDao(List<OportunidadeDia> value)
	{	
		listaoportunidadeDiaPodePossuir = value;
		daoListaoportunidadeDiaPodePossuir = true;
	}  
	public List<OportunidadeDia> getListaOportunidadeDia_PodePossuir()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaoportunidadeDiaPodePossuir)
        {
        OportunidadeDiaServico srv = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		listaoportunidadeDiaPodePossuir = srv.getPorReferenteAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaOportunidadeDia_PodePossuir()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaoportunidadeDiaPodePossuir==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaOportunidadeDia_PodePossuir() est? null");
		//}
		return listaoportunidadeDiaPodePossuir;
	} 
	public List<OportunidadeDia> getListaOportunidadeDia_PodePossuirOriginal()
	{	
		return listaoportunidadeDiaPodePossuir;
	} 
	public List<OportunidadeDia> getListaOportunidadeDia_PodePossuir(int qtde)
	{	
        OportunidadeDiaServico srv = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		listaoportunidadeDiaPodePossuir = srv.getPorReferenteAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaOportunidadeDia_PodePossuir()",QTDE_LOG_LAZY_LOADER);
		return listaoportunidadeDiaPodePossuir;
	} 
	public void addListaOportunidadeDia_PodePossuir(OportunidadeDia value) 
	{	
		criaVaziaListaOportunidadeDia_PodePossuir();
		listaoportunidadeDiaPodePossuir.add(value);
		daoListaoportunidadeDiaPodePossuir = true;
	} 
	public OportunidadeDia getCorrenteOportunidadeDia_PodePossuir()
	{	
		return listaoportunidadeDiaPodePossuir.get(listaoportunidadeDiaPodePossuir.size()-1);
	} 
	public void criaVaziaListaOportunidadeDia_PodePossuir() {
		if (listaoportunidadeDiaPodePossuir == null)
        {
        	listaoportunidadeDiaPodePossuir = new ArrayList<OportunidadeDia>();
        }
	}
	
	public boolean existeListaPalavraProduto_Possui() {
		return listapalavraProdutoPossui!= null; 
	}
	private List<PalavraProduto> listapalavraProdutoPossui;
	private boolean daoListapalavraProdutoPossui = false;
	public void setListaPalavraProduto_Possui(List<PalavraProduto> value)
	{	
		listapalavraProdutoPossui = value;
	}
	public void setListaPalavraProduto_PossuiByDao(List<PalavraProduto> value)
	{	
		listapalavraProdutoPossui = value;
		daoListapalavraProdutoPossui = true;
	}  
	public List<PalavraProduto> getListaPalavraProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListapalavraProdutoPossui)
        {
        PalavraProdutoServico srv = FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		listapalavraProdutoPossui = srv.getPorRelaciondoAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPalavraProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listapalavraProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaPalavraProduto_Possui() est? null");
		//}
		return listapalavraProdutoPossui;
	} 
	public List<PalavraProduto> getListaPalavraProduto_PossuiOriginal()
	{	
		return listapalavraProdutoPossui;
	} 
	public List<PalavraProduto> getListaPalavraProduto_Possui(int qtde)
	{	
        PalavraProdutoServico srv = FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		listapalavraProdutoPossui = srv.getPorRelaciondoAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPalavraProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listapalavraProdutoPossui;
	} 
	public void addListaPalavraProduto_Possui(PalavraProduto value) 
	{	
		criaVaziaListaPalavraProduto_Possui();
		listapalavraProdutoPossui.add(value);
		daoListapalavraProdutoPossui = true;
	} 
	public PalavraProduto getCorrentePalavraProduto_Possui()
	{	
		return listapalavraProdutoPossui.get(listapalavraProdutoPossui.size()-1);
	} 
	public void criaVaziaListaPalavraProduto_Possui() {
		if (listapalavraProdutoPossui == null)
        {
        	listapalavraProdutoPossui = new ArrayList<PalavraProduto>();
        }
	}
	
}
