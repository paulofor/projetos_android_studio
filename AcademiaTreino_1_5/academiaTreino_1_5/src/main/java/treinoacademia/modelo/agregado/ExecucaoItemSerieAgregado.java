package treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;

public class ExecucaoItemSerieAgregado implements ExecucaoItemSerieAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ExecucaoItemSerieCarregador carregador = null;
	private ExecucaoItemSerieCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ExecucaoItemSerieCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.ItemSerie_ReferenteA != null)
			vo.ItemSerie_ReferenteA.setConexaoCarregador(conexao);
		if (vo.DiaTreino_Em != null)
			vo.DiaTreino_Em.setConexaoCarregador(conexao);
		if (vo.Exercicio_ReferenteA != null)
			vo.Exercicio_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ExecucaoItemSerie vo;
	public ExecucaoItemSerieAgregado(ExecucaoItemSerie item) {
		vo = item;
	}
	
	
	
	private ItemSerie itemSerieReferenteA;
	public void setItemSerie_ReferenteA(ItemSerie valor)
	{	
		vo.setIdItemSerieRa(0);
		itemSerieReferenteA = valor;
	} 
	public ItemSerie getItemSerie_ReferenteA() 
	{	
		
		if (itemSerieReferenteA==null &&
				vo.getIdItemSerieRaLazyLoader() !=0)
		{
			ItemSerieServico srv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
			itemSerieReferenteA = srv.getById(vo.getIdItemSerieRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ExecucaoItemSerie.getItemSerie_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return itemSerieReferenteA;
	} 
	
	public void addListaItemSerie_ReferenteA(ItemSerie value)
	{	
		itemSerieReferenteA = value;
	} 
	public ItemSerie getCorrenteItemSerie_ReferenteA()
	{	
		return itemSerieReferenteA;
	} 
	
	
	
	private DiaTreino diaTreinoEm;
	public void setDiaTreino_Em(DiaTreino valor)
	{	
		vo.setIdDiaTreinoE(0);
		diaTreinoEm = valor;
	} 
	public DiaTreino getDiaTreino_Em() 
	{	
		
		if (diaTreinoEm==null &&
				vo.getIdDiaTreinoELazyLoader() !=0)
		{
			DiaTreinoServico srv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
			diaTreinoEm = srv.getById(vo.getIdDiaTreinoELazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ExecucaoItemSerie.getDiaTreino_Em()",QTDE_LOG_LAZY_LOADER);
  		}
		return diaTreinoEm;
	} 
	
	public void addListaDiaTreino_Em(DiaTreino value)
	{	
		diaTreinoEm = value;
	} 
	public DiaTreino getCorrenteDiaTreino_Em()
	{	
		return diaTreinoEm;
	} 
	
	
	
	private Exercicio exercicioReferenteA;
	public void setExercicio_ReferenteA(Exercicio valor)
	{	
		vo.setIdExercicioRa(0);
		exercicioReferenteA = valor;
	} 
	public Exercicio getExercicio_ReferenteA() 
	{	
		
		if (exercicioReferenteA==null &&
				vo.getIdExercicioRaLazyLoader() !=0)
		{
			ExercicioServico srv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
			exercicioReferenteA = srv.getById(vo.getIdExercicioRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ExecucaoItemSerie.getExercicio_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return exercicioReferenteA;
	} 
	
	public void addListaExercicio_ReferenteA(Exercicio value)
	{	
		exercicioReferenteA = value;
	} 
	public Exercicio getCorrenteExercicio_ReferenteA()
	{	
		return exercicioReferenteA;
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
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ExecucaoItemSerie.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
 	
 	
 	
 	
 	
}
