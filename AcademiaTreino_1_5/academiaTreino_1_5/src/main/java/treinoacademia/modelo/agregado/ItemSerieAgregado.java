package treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;

public class ItemSerieAgregado implements ItemSerieAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ItemSerieCarregador carregador = null;
	private ItemSerieCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ItemSerieCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Exercicio_ExecucaoDe != null)
			vo.Exercicio_ExecucaoDe.setConexaoCarregador(conexao);
		if (vo.SerieTreino_PertencenteA != null)
			vo.SerieTreino_PertencenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ItemSerie vo;
	public ItemSerieAgregado(ItemSerie item) {
		vo = item;
	}
	
	
	
	private Exercicio exercicioExecucaoDe;
	public void setExercicio_ExecucaoDe(Exercicio valor)
	{	
		vo.setIdExercicioEd(0);
		exercicioExecucaoDe = valor;
	} 
	public Exercicio getExercicio_ExecucaoDe() 
	{	
		
		if (exercicioExecucaoDe==null &&
				vo.getIdExercicioEdLazyLoader() !=0)
		{
			ExercicioServico srv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
			exercicioExecucaoDe = srv.getById(vo.getIdExercicioEdLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getExercicio_ExecucaoDe()",QTDE_LOG_LAZY_LOADER);
  		}
		return exercicioExecucaoDe;
	} 
	
	public void addListaExercicio_ExecucaoDe(Exercicio value)
	{	
		exercicioExecucaoDe = value;
	} 
	public Exercicio getCorrenteExercicio_ExecucaoDe()
	{	
		return exercicioExecucaoDe;
	} 
	
	
	
	private SerieTreino serieTreinoPertencenteA;
	public void setSerieTreino_PertencenteA(SerieTreino valor)
	{	
		vo.setIdSerieTreinoPa(0);
		serieTreinoPertencenteA = valor;
	} 
	public SerieTreino getSerieTreino_PertencenteA() 
	{	
		
		if (serieTreinoPertencenteA==null &&
				vo.getIdSerieTreinoPaLazyLoader() !=0)
		{
			SerieTreinoServico srv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
			serieTreinoPertencenteA = srv.getById(vo.getIdSerieTreinoPaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getSerieTreino_PertencenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return serieTreinoPertencenteA;
	} 
	
	public void addListaSerieTreino_PertencenteA(SerieTreino value)
	{	
		serieTreinoPertencenteA = value;
	} 
	public SerieTreino getCorrenteSerieTreino_PertencenteA()
	{	
		return serieTreinoPertencenteA;
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
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
 	
 	
 	
 	
 	
	public boolean existeListaCargaPlanejada_Possui() {
		return listacargaPlanejadaPossui!= null; 
	}
	private List<CargaPlanejada> listacargaPlanejadaPossui;
	private boolean daoListacargaPlanejadaPossui = false;
	public void setListaCargaPlanejada_Possui(List<CargaPlanejada> value)
	{	
		listacargaPlanejadaPossui = value;
	}
	public void setListaCargaPlanejada_PossuiByDao(List<CargaPlanejada> value)
	{	
		listacargaPlanejadaPossui = value;
		daoListacargaPlanejadaPossui = (value!=null);
	}  
	public List<CargaPlanejada> getListaCargaPlanejada_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListacargaPlanejadaPossui)
        {
        CargaPlanejadaServico srv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		listacargaPlanejadaPossui = srv.getPorReferenteAItemSerie(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getListaCargaPlanejada_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacargaPlanejadaPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "ItemSerie.getListaCargaPlanejada_Possui() est? null");
		//}
		return listacargaPlanejadaPossui;
	} 
	public List<CargaPlanejada> getListaCargaPlanejada_PossuiOriginal()
	{	
		return listacargaPlanejadaPossui;
	} 
	public List<CargaPlanejada> getListaCargaPlanejada_Possui(int qtde)
	{	
        CargaPlanejadaServico srv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		listacargaPlanejadaPossui = srv.getPorReferenteAItemSerie(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getListaCargaPlanejada_Possui()",QTDE_LOG_LAZY_LOADER);
		return listacargaPlanejadaPossui;
	} 
	public void addListaCargaPlanejada_Possui(CargaPlanejada value) 
	{	
		criaVaziaListaCargaPlanejada_Possui();
		listacargaPlanejadaPossui.add(value);
		daoListacargaPlanejadaPossui = true;
	} 
	public CargaPlanejada getCorrenteCargaPlanejada_Possui()
	{	
		return listacargaPlanejadaPossui.get(listacargaPlanejadaPossui.size()-1);
	} 
	public void criaVaziaListaCargaPlanejada_Possui() {
		if (listacargaPlanejadaPossui == null)
        {
        	listacargaPlanejadaPossui = new ArrayList<CargaPlanejada>();
        }
	}
	
	public boolean existeListaExecucaoItemSerie_Gera() {
		return listaexecucaoItemSerieGera!= null; 
	}
	private List<ExecucaoItemSerie> listaexecucaoItemSerieGera;
	private boolean daoListaexecucaoItemSerieGera = false;
	public void setListaExecucaoItemSerie_Gera(List<ExecucaoItemSerie> value)
	{	
		listaexecucaoItemSerieGera = value;
	}
	public void setListaExecucaoItemSerie_GeraByDao(List<ExecucaoItemSerie> value)
	{	
		listaexecucaoItemSerieGera = value;
		daoListaexecucaoItemSerieGera = (value!=null);
	}  
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaexecucaoItemSerieGera)
        {
        ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaexecucaoItemSerieGera = srv.getPorReferenteAItemSerie(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getListaExecucaoItemSerie_Gera()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaexecucaoItemSerieGera==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "ItemSerie.getListaExecucaoItemSerie_Gera() est? null");
		//}
		return listaexecucaoItemSerieGera;
	} 
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_GeraOriginal()
	{	
		return listaexecucaoItemSerieGera;
	} 
	public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera(int qtde)
	{	
        ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		listaexecucaoItemSerieGera = srv.getPorReferenteAItemSerie(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "ItemSerie.getListaExecucaoItemSerie_Gera()",QTDE_LOG_LAZY_LOADER);
		return listaexecucaoItemSerieGera;
	} 
	public void addListaExecucaoItemSerie_Gera(ExecucaoItemSerie value) 
	{	
		criaVaziaListaExecucaoItemSerie_Gera();
		listaexecucaoItemSerieGera.add(value);
		daoListaexecucaoItemSerieGera = true;
	} 
	public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Gera()
	{	
		return listaexecucaoItemSerieGera.get(listaexecucaoItemSerieGera.size()-1);
	} 
	public void criaVaziaListaExecucaoItemSerie_Gera() {
		if (listaexecucaoItemSerieGera == null)
        {
        	listaexecucaoItemSerieGera = new ArrayList<ExecucaoItemSerie>();
        }
	}
	
}
