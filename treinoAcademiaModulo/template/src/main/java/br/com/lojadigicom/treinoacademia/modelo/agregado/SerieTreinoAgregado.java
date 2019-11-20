package br.com.lojadigicom.treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.modelo.*;


public class SerieTreinoAgregado implements SerieTreinoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private SerieTreinoCarregador carregador = null;
	private SerieTreinoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new SerieTreinoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private SerieTreino vo;
	public SerieTreinoAgregado(SerieTreino item) {
		vo = item;
	}
	
	
	
	private Usuario usuarioSincroniza;
	public void setUsuario_Sincroniza(Usuario valor)
	{	
		usuarioSincroniza = valor;
	} 
	public Usuario getUsuario_Sincroniza() 
	{	
	//	if (usuarioSincroniza==null &&
	//			vo.getIdUsuarioSLazyLoader() !=0)
	//	{
	//		UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
	//		usuarioSincroniza = srv.getById(vo.getIdUsuarioSLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "SerieTreino.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return usuarioSincroniza;
	} 
	
	public void addListaUsuario_Sincroniza(Usuario value)
	{	
		usuarioSincroniza = value;
	} 
	public Usuario getCorrenteUsuario_Sincroniza()
	{	
		return usuarioSincroniza;
	} 
	
	
 	
 	
 	
 	
 	
	public boolean existeListaItemSerie_Possui() {
		return listaitemSeriePossui!= null; 
	}
	private List<ItemSerie> listaitemSeriePossui;
	private boolean daoListaitemSeriePossui = false;
	public void setListaItemSerie_Possui(List<ItemSerie> value)
	{	
		listaitemSeriePossui = value;
	}
	public void setListaItemSerie_PossuiByDao(List<ItemSerie> value)
	{	
	//	listaitemSeriePossui = value;
	//	daoListaitemSeriePossui = (value!=null);
	}  
	public List<ItemSerie> getListaItemSerie_Possui()
	{	
	//	if (!daoListaitemSeriePossui)
    //    {
    //    ItemSerieServico srv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
	//	listaitemSeriePossui = srv.getPorPertencenteASerieTreino(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "SerieTreino.getListaItemSerie_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaitemSeriePossui;
	} 
	public List<ItemSerie> getListaItemSerie_PossuiOriginal()
	{	
		return listaitemSeriePossui;
	} 
	public List<ItemSerie> getListaItemSerie_Possui(int qtde)
	{	
    //    ItemSerieServico srv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
	//	listaitemSeriePossui = srv.getPorPertencenteASerieTreino(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "SerieTreino.getListaItemSerie_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaitemSeriePossui;
	} 
	public void addListaItemSerie_Possui(ItemSerie value) 
	{	
		criaVaziaListaItemSerie_Possui();
		listaitemSeriePossui.add(value);
	//	daoListaitemSeriePossui = true;
	} 
	public ItemSerie getCorrenteItemSerie_Possui()
	{	
		if (listaitemSeriePossui==null) return null;
		return listaitemSeriePossui.get(listaitemSeriePossui.size()-1);
	} 
	public void criaVaziaListaItemSerie_Possui() {
		if (listaitemSeriePossui == null)
        {
        	listaitemSeriePossui = new ArrayList<ItemSerie>();
        }
	}
	
	public boolean existeListaDiaTreino_SerieDia() {
		return listadiaTreinoSerieDia!= null; 
	}
	private List<DiaTreino> listadiaTreinoSerieDia;
	private boolean daoListadiaTreinoSerieDia = false;
	public void setListaDiaTreino_SerieDia(List<DiaTreino> value)
	{	
		listadiaTreinoSerieDia = value;
	}
	public void setListaDiaTreino_SerieDiaByDao(List<DiaTreino> value)
	{	
	//	listadiaTreinoSerieDia = value;
	//	daoListadiaTreinoSerieDia = (value!=null);
	}  
	public List<DiaTreino> getListaDiaTreino_SerieDia()
	{	
	//	if (!daoListadiaTreinoSerieDia)
    //    {
    //    DiaTreinoServico srv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
	//	listadiaTreinoSerieDia = srv.getPorSerieDiaSerieTreino(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "SerieTreino.getListaDiaTreino_SerieDia()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listadiaTreinoSerieDia;
	} 
	public List<DiaTreino> getListaDiaTreino_SerieDiaOriginal()
	{	
		return listadiaTreinoSerieDia;
	} 
	public List<DiaTreino> getListaDiaTreino_SerieDia(int qtde)
	{	
    //    DiaTreinoServico srv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
	//	listadiaTreinoSerieDia = srv.getPorSerieDiaSerieTreino(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "SerieTreino.getListaDiaTreino_SerieDia()",QTDE_LOG_LAZY_LOADER);
		return listadiaTreinoSerieDia;
	} 
	public void addListaDiaTreino_SerieDia(DiaTreino value) 
	{	
		criaVaziaListaDiaTreino_SerieDia();
		listadiaTreinoSerieDia.add(value);
	//	daoListadiaTreinoSerieDia = true;
	} 
	public DiaTreino getCorrenteDiaTreino_SerieDia()
	{	
		if (listadiaTreinoSerieDia==null) return null;
		return listadiaTreinoSerieDia.get(listadiaTreinoSerieDia.size()-1);
	} 
	public void criaVaziaListaDiaTreino_SerieDia() {
		if (listadiaTreinoSerieDia == null)
        {
        	listadiaTreinoSerieDia = new ArrayList<DiaTreino>();
        }
	}
	
}
