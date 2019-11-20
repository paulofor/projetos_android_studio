package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class AppProdutoAgregado implements AppProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private AppProdutoCarregador carregador = null;
	private AppProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new AppProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private AppProduto vo;
	public AppProdutoAgregado(AppProduto item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaNaturezaProduto_Atende() {
		return listanaturezaProdutoAtende!= null; 
	}
	private List<NaturezaProduto> listanaturezaProdutoAtende;
	private boolean daoListanaturezaProdutoAtende = false;
	public void setListaNaturezaProduto_Atende(List<NaturezaProduto> value)
	{	
		listanaturezaProdutoAtende = value;
	}
	public void setListaNaturezaProduto_AtendeByDao(List<NaturezaProduto> value)
	{	
	//	listanaturezaProdutoAtende = value;
	//	daoListanaturezaProdutoAtende = (value!=null);
	}  
	public List<NaturezaProduto> getListaNaturezaProduto_Atende()
	{	
	//	if (!daoListanaturezaProdutoAtende)
    //    {
    //    NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listanaturezaProdutoAtende = srv.getPorAtendidoPorAppProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "AppProduto.getListaNaturezaProduto_Atende()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listanaturezaProdutoAtende;
	} 
	public List<NaturezaProduto> getListaNaturezaProduto_AtendeOriginal()
	{	
		return listanaturezaProdutoAtende;
	} 
	public List<NaturezaProduto> getListaNaturezaProduto_Atende(int qtde)
	{	
    //    NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listanaturezaProdutoAtende = srv.getPorAtendidoPorAppProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "AppProduto.getListaNaturezaProduto_Atende()",QTDE_LOG_LAZY_LOADER);
		return listanaturezaProdutoAtende;
	} 
	public void addListaNaturezaProduto_Atende(NaturezaProduto value) 
	{	
		criaVaziaListaNaturezaProduto_Atende();
		listanaturezaProdutoAtende.add(value);
	//	daoListanaturezaProdutoAtende = true;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_Atende()
	{	
		if (listanaturezaProdutoAtende==null) return null;
		return listanaturezaProdutoAtende.get(listanaturezaProdutoAtende.size()-1);
	} 
	public void criaVaziaListaNaturezaProduto_Atende() {
		if (listanaturezaProdutoAtende == null)
        {
        	listanaturezaProdutoAtende = new ArrayList<NaturezaProduto>();
        }
	}
	
	public boolean existeListaDispositivoUsuario_UsadoPor() {
		return listadispositivoUsuarioUsadoPor!= null; 
	}
	private List<DispositivoUsuario> listadispositivoUsuarioUsadoPor;
	private boolean daoListadispositivoUsuarioUsadoPor = false;
	public void setListaDispositivoUsuario_UsadoPor(List<DispositivoUsuario> value)
	{	
		listadispositivoUsuarioUsadoPor = value;
	}
	public void setListaDispositivoUsuario_UsadoPorByDao(List<DispositivoUsuario> value)
	{	
	//	listadispositivoUsuarioUsadoPor = value;
	//	daoListadispositivoUsuarioUsadoPor = (value!=null);
	}  
	public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPor()
	{	
	//	if (!daoListadispositivoUsuarioUsadoPor)
    //    {
    //    DispositivoUsuarioServico srv = FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
	//	listadispositivoUsuarioUsadoPor = srv.getPorUsaAppProduto(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "AppProduto.getListaDispositivoUsuario_UsadoPor()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listadispositivoUsuarioUsadoPor;
	} 
	public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPorOriginal()
	{	
		return listadispositivoUsuarioUsadoPor;
	} 
	public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPor(int qtde)
	{	
    //    DispositivoUsuarioServico srv = FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
	//	listadispositivoUsuarioUsadoPor = srv.getPorUsaAppProduto(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "AppProduto.getListaDispositivoUsuario_UsadoPor()",QTDE_LOG_LAZY_LOADER);
		return listadispositivoUsuarioUsadoPor;
	} 
	public void addListaDispositivoUsuario_UsadoPor(DispositivoUsuario value) 
	{	
		criaVaziaListaDispositivoUsuario_UsadoPor();
		listadispositivoUsuarioUsadoPor.add(value);
	//	daoListadispositivoUsuarioUsadoPor = true;
	} 
	public DispositivoUsuario getCorrenteDispositivoUsuario_UsadoPor()
	{	
		if (listadispositivoUsuarioUsadoPor==null) return null;
		return listadispositivoUsuarioUsadoPor.get(listadispositivoUsuarioUsadoPor.size()-1);
	} 
	public void criaVaziaListaDispositivoUsuario_UsadoPor() {
		if (listadispositivoUsuarioUsadoPor == null)
        {
        	listadispositivoUsuarioUsadoPor = new ArrayList<DispositivoUsuario>();
        }
	}
	
}
