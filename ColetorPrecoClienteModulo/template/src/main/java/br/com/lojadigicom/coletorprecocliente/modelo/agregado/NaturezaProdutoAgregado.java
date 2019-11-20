package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class NaturezaProdutoAgregado implements NaturezaProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private NaturezaProdutoCarregador carregador = null;
	private NaturezaProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new NaturezaProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.AppProduto_AtendidoPor != null)
			vo.AppProduto_AtendidoPor.setConexaoCarregador(conexao);
		
	}
	*/
	
	private NaturezaProduto vo;
	public NaturezaProdutoAgregado(NaturezaProduto item) {
		vo = item;
	}
	
	
	
	private AppProduto appProdutoAtendidoPor;
	public void setAppProduto_AtendidoPor(AppProduto valor)
	{	
		appProdutoAtendidoPor = valor;
	} 
	public AppProduto getAppProduto_AtendidoPor() 
	{	
	//	if (appProdutoAtendidoPor==null &&
	//			vo.getIdAppProdutoApLazyLoader() !=0)
	//	{
	//		AppProdutoServico srv = FabricaServico.getInstancia().getAppProdutoServico(FabricaServico.TIPO_SQLITE);
	//		appProdutoAtendidoPor = srv.getById(vo.getIdAppProdutoApLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getAppProduto_AtendidoPor()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return appProdutoAtendidoPor;
	} 
	
	public void addListaAppProduto_AtendidoPor(AppProduto value)
	{	
		appProdutoAtendidoPor = value;
	} 
	public AppProduto getCorrenteAppProduto_AtendidoPor()
	{	
		return appProdutoAtendidoPor;
	} 
	
	
 	
 	
 	
 	
 	
	public boolean existeListaOportunidadeDia_Possui() {
		return listaoportunidadeDiaPossui!= null; 
	}
	private List<OportunidadeDia> listaoportunidadeDiaPossui;
	private boolean daoListaoportunidadeDiaPossui = false;
	public void setListaOportunidadeDia_Possui(List<OportunidadeDia> value)
	{	
		listaoportunidadeDiaPossui = value;
	}
	public void setListaOportunidadeDia_PossuiByDao(List<OportunidadeDia> value)
	{	
	//	listaoportunidadeDiaPossui = value;
	//	daoListaoportunidadeDiaPossui = (value!=null);
	}  
	public List<OportunidadeDia> getListaOportunidadeDia_Possui()
	{	
	//	if (!daoListaoportunidadeDiaPossui)
    //    {
    //    OportunidadeDiaServico srv = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
	//	listaoportunidadeDiaPossui = srv.getPorPertenceANaturezaProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaOportunidadeDia_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaoportunidadeDiaPossui;
	} 
	public List<OportunidadeDia> getListaOportunidadeDia_PossuiOriginal()
	{	
		return listaoportunidadeDiaPossui;
	} 
	public List<OportunidadeDia> getListaOportunidadeDia_Possui(int qtde)
	{	
    //    OportunidadeDiaServico srv = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
	//	listaoportunidadeDiaPossui = srv.getPorPertenceANaturezaProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaOportunidadeDia_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaoportunidadeDiaPossui;
	} 
	public void addListaOportunidadeDia_Possui(OportunidadeDia value) 
	{	
		criaVaziaListaOportunidadeDia_Possui();
		listaoportunidadeDiaPossui.add(value);
	//	daoListaoportunidadeDiaPossui = true;
	} 
	public OportunidadeDia getCorrenteOportunidadeDia_Possui()
	{	
		if (listaoportunidadeDiaPossui==null) return null;
		return listaoportunidadeDiaPossui.get(listaoportunidadeDiaPossui.size()-1);
	} 
	public void criaVaziaListaOportunidadeDia_Possui() {
		if (listaoportunidadeDiaPossui == null)
        {
        	listaoportunidadeDiaPossui = new ArrayList<OportunidadeDia>();
        }
	}
	
	public boolean existeListaUsuarioPesquisa_PesquisadoPor() {
		return listausuarioPesquisaPesquisadoPor!= null; 
	}
	private List<UsuarioPesquisa> listausuarioPesquisaPesquisadoPor;
	private boolean daoListausuarioPesquisaPesquisadoPor = false;
	public void setListaUsuarioPesquisa_PesquisadoPor(List<UsuarioPesquisa> value)
	{	
		listausuarioPesquisaPesquisadoPor = value;
	}
	public void setListaUsuarioPesquisa_PesquisadoPorByDao(List<UsuarioPesquisa> value)
	{	
	//	listausuarioPesquisaPesquisadoPor = value;
	//	daoListausuarioPesquisaPesquisadoPor = (value!=null);
	}  
	public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor()
	{	
	//	if (!daoListausuarioPesquisaPesquisadoPor)
    //    {
    //    UsuarioPesquisaServico srv = FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
	//	listausuarioPesquisaPesquisadoPor = srv.getPorPesquisaNaturezaProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaUsuarioPesquisa_PesquisadoPor()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listausuarioPesquisaPesquisadoPor;
	} 
	public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPorOriginal()
	{	
		return listausuarioPesquisaPesquisadoPor;
	} 
	public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor(int qtde)
	{	
    //    UsuarioPesquisaServico srv = FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
	//	listausuarioPesquisaPesquisadoPor = srv.getPorPesquisaNaturezaProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaUsuarioPesquisa_PesquisadoPor()",QTDE_LOG_LAZY_LOADER);
		return listausuarioPesquisaPesquisadoPor;
	} 
	public void addListaUsuarioPesquisa_PesquisadoPor(UsuarioPesquisa value) 
	{	
		criaVaziaListaUsuarioPesquisa_PesquisadoPor();
		listausuarioPesquisaPesquisadoPor.add(value);
	//	daoListausuarioPesquisaPesquisadoPor = true;
	} 
	public UsuarioPesquisa getCorrenteUsuarioPesquisa_PesquisadoPor()
	{	
		if (listausuarioPesquisaPesquisadoPor==null) return null;
		return listausuarioPesquisaPesquisadoPor.get(listausuarioPesquisaPesquisadoPor.size()-1);
	} 
	public void criaVaziaListaUsuarioPesquisa_PesquisadoPor() {
		if (listausuarioPesquisaPesquisadoPor == null)
        {
        	listausuarioPesquisaPesquisadoPor = new ArrayList<UsuarioPesquisa>();
        }
	}
	
	public boolean existeListaPalavraChavePesquisa_Possui() {
		return listapalavraChavePesquisaPossui!= null; 
	}
	private List<PalavraChavePesquisa> listapalavraChavePesquisaPossui;
	private boolean daoListapalavraChavePesquisaPossui = false;
	public void setListaPalavraChavePesquisa_Possui(List<PalavraChavePesquisa> value)
	{	
		listapalavraChavePesquisaPossui = value;
	}
	public void setListaPalavraChavePesquisa_PossuiByDao(List<PalavraChavePesquisa> value)
	{	
	//	listapalavraChavePesquisaPossui = value;
	//	daoListapalavraChavePesquisaPossui = (value!=null);
	}  
	public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_Possui()
	{	
	//	if (!daoListapalavraChavePesquisaPossui)
    //    {
    //    PalavraChavePesquisaServico srv = FabricaServico.getInstancia().getPalavraChavePesquisaServico(FabricaServico.TIPO_SQLITE);
	//	listapalavraChavePesquisaPossui = srv.getPorReferenteANaturezaProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaPalavraChavePesquisa_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listapalavraChavePesquisaPossui;
	} 
	public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_PossuiOriginal()
	{	
		return listapalavraChavePesquisaPossui;
	} 
	public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_Possui(int qtde)
	{	
    //    PalavraChavePesquisaServico srv = FabricaServico.getInstancia().getPalavraChavePesquisaServico(FabricaServico.TIPO_SQLITE);
	//	listapalavraChavePesquisaPossui = srv.getPorReferenteANaturezaProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaPalavraChavePesquisa_Possui()",QTDE_LOG_LAZY_LOADER);
		return listapalavraChavePesquisaPossui;
	} 
	public void addListaPalavraChavePesquisa_Possui(PalavraChavePesquisa value) 
	{	
		criaVaziaListaPalavraChavePesquisa_Possui();
		listapalavraChavePesquisaPossui.add(value);
	//	daoListapalavraChavePesquisaPossui = true;
	} 
	public PalavraChavePesquisa getCorrentePalavraChavePesquisa_Possui()
	{	
		if (listapalavraChavePesquisaPossui==null) return null;
		return listapalavraChavePesquisaPossui.get(listapalavraChavePesquisaPossui.size()-1);
	} 
	public void criaVaziaListaPalavraChavePesquisa_Possui() {
		if (listapalavraChavePesquisaPossui == null)
        {
        	listapalavraChavePesquisaPossui = new ArrayList<PalavraChavePesquisa>();
        }
	}
	
	public boolean existeListaProdutoCliente_Possui() {
		return listaprodutoClientePossui!= null; 
	}
	private List<ProdutoCliente> listaprodutoClientePossui;
	private boolean daoListaprodutoClientePossui = false;
	public void setListaProdutoCliente_Possui(List<ProdutoCliente> value)
	{	
		listaprodutoClientePossui = value;
	}
	public void setListaProdutoCliente_PossuiByDao(List<ProdutoCliente> value)
	{	
	//	listaprodutoClientePossui = value;
	//	daoListaprodutoClientePossui = (value!=null);
	}  
	public List<ProdutoCliente> getListaProdutoCliente_Possui()
	{	
	//	if (!daoListaprodutoClientePossui)
    //    {
    //    ProdutoClienteServico srv = FabricaServico.getInstancia().getProdutoClienteServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoClientePossui = srv.getPorReferenteANaturezaProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaProdutoCliente_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaprodutoClientePossui;
	} 
	public List<ProdutoCliente> getListaProdutoCliente_PossuiOriginal()
	{	
		return listaprodutoClientePossui;
	} 
	public List<ProdutoCliente> getListaProdutoCliente_Possui(int qtde)
	{	
    //    ProdutoClienteServico srv = FabricaServico.getInstancia().getProdutoClienteServico(FabricaServico.TIPO_SQLITE);
	//	listaprodutoClientePossui = srv.getPorReferenteANaturezaProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaProdutoCliente_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoClientePossui;
	} 
	public void addListaProdutoCliente_Possui(ProdutoCliente value) 
	{	
		criaVaziaListaProdutoCliente_Possui();
		listaprodutoClientePossui.add(value);
	//	daoListaprodutoClientePossui = true;
	} 
	public ProdutoCliente getCorrenteProdutoCliente_Possui()
	{	
		if (listaprodutoClientePossui==null) return null;
		return listaprodutoClientePossui.get(listaprodutoClientePossui.size()-1);
	} 
	public void criaVaziaListaProdutoCliente_Possui() {
		if (listaprodutoClientePossui == null)
        {
        	listaprodutoClientePossui = new ArrayList<ProdutoCliente>();
        }
	}
	
}
