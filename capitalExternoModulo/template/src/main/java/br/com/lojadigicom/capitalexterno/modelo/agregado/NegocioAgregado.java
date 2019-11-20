package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class NegocioAgregado implements NegocioAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private NegocioCarregador carregador = null;
	private NegocioCarregador getCarregador() {
		if (carregador==null) {
			carregador = new NegocioCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Negocio vo;
	public NegocioAgregado(Negocio item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaCaracteristicaMercado_Possui() {
		return listacaracteristicaMercadoPossui!= null; 
	}
	private List<CaracteristicaMercado> listacaracteristicaMercadoPossui;
	private boolean daoListacaracteristicaMercadoPossui = false;
	public void setListaCaracteristicaMercado_Possui(List<CaracteristicaMercado> value)
	{	
		listacaracteristicaMercadoPossui = value;
	}
	public void setListaCaracteristicaMercado_PossuiByDao(List<CaracteristicaMercado> value)
	{	
	//	listacaracteristicaMercadoPossui = value;
	//	daoListacaracteristicaMercadoPossui = (value!=null);
	}  
	public List<CaracteristicaMercado> getListaCaracteristicaMercado_Possui()
	{	
	//	if (!daoListacaracteristicaMercadoPossui)
    //    {
    //    CaracteristicaMercadoServico srv = FabricaServico.getInstancia().getCaracteristicaMercadoServico(FabricaServico.TIPO_SQLITE);
	//	listacaracteristicaMercadoPossui = srv.getPorPertenceANegocio(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Negocio.getListaCaracteristicaMercado_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listacaracteristicaMercadoPossui;
	} 
	public List<CaracteristicaMercado> getListaCaracteristicaMercado_PossuiOriginal()
	{	
		return listacaracteristicaMercadoPossui;
	} 
	public List<CaracteristicaMercado> getListaCaracteristicaMercado_Possui(int qtde)
	{	
    //    CaracteristicaMercadoServico srv = FabricaServico.getInstancia().getCaracteristicaMercadoServico(FabricaServico.TIPO_SQLITE);
	//	listacaracteristicaMercadoPossui = srv.getPorPertenceANegocio(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Negocio.getListaCaracteristicaMercado_Possui()",QTDE_LOG_LAZY_LOADER);
		return listacaracteristicaMercadoPossui;
	} 
	public void addListaCaracteristicaMercado_Possui(CaracteristicaMercado value) 
	{	
		criaVaziaListaCaracteristicaMercado_Possui();
		listacaracteristicaMercadoPossui.add(value);
	//	daoListacaracteristicaMercadoPossui = true;
	} 
	public CaracteristicaMercado getCorrenteCaracteristicaMercado_Possui()
	{	
		if (listacaracteristicaMercadoPossui==null) return null;
		return listacaracteristicaMercadoPossui.get(listacaracteristicaMercadoPossui.size()-1);
	} 
	public void criaVaziaListaCaracteristicaMercado_Possui() {
		if (listacaracteristicaMercadoPossui == null)
        {
        	listacaracteristicaMercadoPossui = new ArrayList<CaracteristicaMercado>();
        }
	}
	
}
