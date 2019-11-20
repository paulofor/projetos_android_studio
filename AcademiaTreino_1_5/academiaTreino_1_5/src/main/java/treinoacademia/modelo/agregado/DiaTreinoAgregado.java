package treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;

public class DiaTreinoAgregado implements DiaTreinoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private DiaTreinoCarregador carregador = null;
	private DiaTreinoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new DiaTreinoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.SerieTreino_SerieDia != null)
			vo.SerieTreino_SerieDia.setConexaoCarregador(conexao);
		
	}
	*/
	
	private DiaTreino vo;
	public DiaTreinoAgregado(DiaTreino item) {
		vo = item;
	}
	
	
	
	private Usuario usuarioSincroniza;
	public void setUsuario_Sincroniza(Usuario valor)
	{	
		vo.setIdUsuarioS(0);
		usuarioSincroniza = valor;
	} 
	public Usuario getUsuario_Sincroniza() 
	{	
		
		if (usuarioSincroniza==null &&
				vo.getIdUsuarioSLazyLoader() !=0)
		{
			UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			usuarioSincroniza = srv.getById(vo.getIdUsuarioSLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "DiaTreino.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
  		}
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
	
	
	
	private SerieTreino serieTreinoSerieDia;
	public void setSerieTreino_SerieDia(SerieTreino valor)
	{	
		vo.setIdSerieTreinoSd(0);
		serieTreinoSerieDia = valor;
	} 
	public SerieTreino getSerieTreino_SerieDia() 
	{	
		
		if (serieTreinoSerieDia==null &&
				vo.getIdSerieTreinoSdLazyLoader() !=0)
		{
			SerieTreinoServico srv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
			serieTreinoSerieDia = srv.getById(vo.getIdSerieTreinoSdLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "DiaTreino.getSerieTreino_SerieDia()",QTDE_LOG_LAZY_LOADER);
  		}
		return serieTreinoSerieDia;
	} 
	
	public void addListaSerieTreino_SerieDia(SerieTreino value)
	{	
		serieTreinoSerieDia = value;
	} 
	public SerieTreino getCorrenteSerieTreino_SerieDia()
	{	
		return serieTreinoSerieDia;
	} 
	
	
 	
 	
 	
 	
 	
	public boolean existeListaExecucaoItemSerie_FoiRealizado() {
		return listaexecucaoItemSerieFoiRealizado!= null; 
	}
	private List<ExecucaoItemSerie> listaexecucaoItemSerieFoiRealizado;
	private boolean daoListaexecucaoItemSerieFoiRealizado = false;
	public void setListaExecucaoItemSerie_FoiRealizado(List<ExecucaoItemSerie> value)
	{	
		listaexecucaoItemSerieFoiRealizado = value;
	}
	public void setListaExecucaoItemSerie_FoiRealizadoByDao(List<ExecucaoItemSerie> value)
	{	
		listaexecucaoItemSerieFoiRealizado = value;
		daoListaexecucaoItemSerieFoiRealizado = (value!=null);
	}  
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaexecucaoItemSerieFoiRealizado)
        {
        ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaexecucaoItemSerieFoiRealizado = srv.getPorEmDiaTreino(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "DiaTreino.getListaExecucaoItemSerie_FoiRealizado()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaexecucaoItemSerieFoiRealizado==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "DiaTreino.getListaExecucaoItemSerie_FoiRealizado() est? null");
		//}
		return listaexecucaoItemSerieFoiRealizado;
	} 
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizadoOriginal()
	{	
		return listaexecucaoItemSerieFoiRealizado;
	} 
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado(int qtde)
	{	
        ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaexecucaoItemSerieFoiRealizado = srv.getPorEmDiaTreino(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "DiaTreino.getListaExecucaoItemSerie_FoiRealizado()",QTDE_LOG_LAZY_LOADER);
		return listaexecucaoItemSerieFoiRealizado;
	} 
	public void addListaExecucaoItemSerie_FoiRealizado(ExecucaoItemSerie value) 
	{	
		criaVaziaListaExecucaoItemSerie_FoiRealizado();
		listaexecucaoItemSerieFoiRealizado.add(value);
		daoListaexecucaoItemSerieFoiRealizado = true;
	} 
	public ExecucaoItemSerie getCorrenteExecucaoItemSerie_FoiRealizado()
	{	
		return listaexecucaoItemSerieFoiRealizado.get(listaexecucaoItemSerieFoiRealizado.size()-1);
	} 
	public void criaVaziaListaExecucaoItemSerie_FoiRealizado() {
		if (listaexecucaoItemSerieFoiRealizado == null)
        {
        	listaexecucaoItemSerieFoiRealizado = new ArrayList<ExecucaoItemSerie>();
        }
	}
	
}
