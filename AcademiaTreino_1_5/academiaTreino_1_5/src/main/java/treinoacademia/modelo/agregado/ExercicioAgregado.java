package treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;

public class ExercicioAgregado implements ExercicioAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ExercicioCarregador carregador = null;
	private ExercicioCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ExercicioCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.GrupoMuscular_Para != null)
			vo.GrupoMuscular_Para.setConexaoCarregador(conexao);
		
	}
	*/
	
	private Exercicio vo;
	public ExercicioAgregado(Exercicio item) {
		vo = item;
	}
	
	
	
	private GrupoMuscular grupoMuscularPara;
	public void setGrupoMuscular_Para(GrupoMuscular valor)
	{	
		vo.setIdGrupoMuscularP(0);
		grupoMuscularPara = valor;
	} 
	public GrupoMuscular getGrupoMuscular_Para() 
	{	
		
		if (grupoMuscularPara==null &&
				vo.getIdGrupoMuscularPLazyLoader() !=0)
		{
			GrupoMuscularServico srv = FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);
			grupoMuscularPara = srv.getById(vo.getIdGrupoMuscularPLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Exercicio.getGrupoMuscular_Para()",QTDE_LOG_LAZY_LOADER);
  		}
		return grupoMuscularPara;
	} 
	
	public void addListaGrupoMuscular_Para(GrupoMuscular value)
	{	
		grupoMuscularPara = value;
	} 
	public GrupoMuscular getCorrenteGrupoMuscular_Para()
	{	
		return grupoMuscularPara;
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
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Exercicio.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
 	
 	
 	
 	
 	
	public boolean existeListaItemSerie_Gera() {
		return listaitemSerieGera!= null; 
	}
	private List<ItemSerie> listaitemSerieGera;
	private boolean daoListaitemSerieGera = false;
	public void setListaItemSerie_Gera(List<ItemSerie> value)
	{	
		listaitemSerieGera = value;
	}
	public void setListaItemSerie_GeraByDao(List<ItemSerie> value)
	{	
		listaitemSerieGera = value;
		daoListaitemSerieGera = (value!=null);
	}  
	public List<ItemSerie> getListaItemSerie_Gera()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaitemSerieGera)
        {
        ItemSerieServico srv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaitemSerieGera = srv.getPorExecucaoDeExercicio(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Exercicio.getListaItemSerie_Gera()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaitemSerieGera==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Exercicio.getListaItemSerie_Gera() est? null");
		//}
		return listaitemSerieGera;
	} 
	public List<ItemSerie> getListaItemSerie_GeraOriginal()
	{	
		return listaitemSerieGera;
	} 
	public List<ItemSerie> getListaItemSerie_Gera(int qtde)
	{	
        ItemSerieServico srv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaitemSerieGera = srv.getPorExecucaoDeExercicio(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Exercicio.getListaItemSerie_Gera()",QTDE_LOG_LAZY_LOADER);
		return listaitemSerieGera;
	} 
	public void addListaItemSerie_Gera(ItemSerie value) 
	{	
		criaVaziaListaItemSerie_Gera();
		listaitemSerieGera.add(value);
		daoListaitemSerieGera = true;
	} 
	public ItemSerie getCorrenteItemSerie_Gera()
	{	
		return listaitemSerieGera.get(listaitemSerieGera.size()-1);
	} 
	public void criaVaziaListaItemSerie_Gera() {
		if (listaitemSerieGera == null)
        {
        	listaitemSerieGera = new ArrayList<ItemSerie>();
        }
	}
	
	public boolean existeListaExecucaoItemSerie_Executado() {
		return listaexecucaoItemSerieExecutado!= null; 
	}
	private List<ExecucaoItemSerie> listaexecucaoItemSerieExecutado;
	private boolean daoListaexecucaoItemSerieExecutado = false;
	public void setListaExecucaoItemSerie_Executado(List<ExecucaoItemSerie> value)
	{	
		listaexecucaoItemSerieExecutado = value;
	}
	public void setListaExecucaoItemSerie_ExecutadoByDao(List<ExecucaoItemSerie> value)
	{	
		listaexecucaoItemSerieExecutado = value;
		daoListaexecucaoItemSerieExecutado = (value!=null);
	}  
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaexecucaoItemSerieExecutado)
        {
        ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaexecucaoItemSerieExecutado = srv.getPorReferenteAExercicio(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Exercicio.getListaExecucaoItemSerie_Executado()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaexecucaoItemSerieExecutado==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Exercicio.getListaExecucaoItemSerie_Executado() est? null");
		//}
		return listaexecucaoItemSerieExecutado;
	} 
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_ExecutadoOriginal()
	{	
		return listaexecucaoItemSerieExecutado;
	} 
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado(int qtde)
	{	
        ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaexecucaoItemSerieExecutado = srv.getPorReferenteAExercicio(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Exercicio.getListaExecucaoItemSerie_Executado()",QTDE_LOG_LAZY_LOADER);
		return listaexecucaoItemSerieExecutado;
	} 
	public void addListaExecucaoItemSerie_Executado(ExecucaoItemSerie value) 
	{	
		criaVaziaListaExecucaoItemSerie_Executado();
		listaexecucaoItemSerieExecutado.add(value);
		daoListaexecucaoItemSerieExecutado = true;
	} 
	public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Executado()
	{	
		return listaexecucaoItemSerieExecutado.get(listaexecucaoItemSerieExecutado.size()-1);
	} 
	public void criaVaziaListaExecucaoItemSerie_Executado() {
		if (listaexecucaoItemSerieExecutado == null)
        {
        	listaexecucaoItemSerieExecutado = new ArrayList<ExecucaoItemSerie>();
        }
	}
	
}
