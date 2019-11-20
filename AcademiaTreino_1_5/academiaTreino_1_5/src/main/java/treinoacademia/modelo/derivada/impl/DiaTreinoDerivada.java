package treinoacademia.modelo.derivada.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.modelo.derivada.DiaTreinoDerivadaI;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ItemSerieServico;
import br.com.digicom.log.DCLog;
import br.com.digicom.util.UtilDatas;

public class DiaTreinoDerivada implements DiaTreinoDerivadaI{ 
  
  	private DiaTreino principal;
  	private ExecucaoItemSerie primeiraDia = null;
  	private ExecucaoItemSerie ultimaDia = null;
  	
  	
  
  	private SerieTreino serieDia = null;
  	private final ExecucaoItemSerieServico execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
  	private final ItemSerieServico itemSerieSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
  	
  	private ExecucaoItemSerie getUltimaDia() {
  		if (ultimaDia==null) {
  			execucaoSrv.getFiltro().setIdDia(principal.getIdDiaTreino());
  			ultimaDia = execucaoSrv.UltimaPorDia(null);
  		}
  		return ultimaDia;
  	}
  	
  	private ExecucaoItemSerie getPrimeiraDia() {
  		if (primeiraDia==null) {
  			execucaoSrv.getFiltro().setIdDia(principal.getIdDiaTreino());
  			primeiraDia = execucaoSrv.PrimeiraPorDia(null);
  		}
  		return primeiraDia;
  	}
  	
  	public DiaTreinoDerivada(DiaTreino item) {
  		principal = item;
  	}

  	private long horaInicio = 0;
  	private long horaFim = 0;
  	
	

	

	@Override
	public void setDiaSemana(String valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDiaSemana() {
		return UtilDatas.getDiaSemana(principal.getData());
	}

	

	@Override
	public Timestamp getTempoConsumido() {
		Timestamp ultima = this.getUltimaDia().getDataHoraFinalizacao();
		Timestamp primeira = this.getPrimeiraDia().getDataHoraInicio();
		if (ultima==null || primeira==null) {
			DCLog.d("TESTE", this, "Erro");
		}
		long dif = ultima.getTime() - primeira.getTime();
		Timestamp saida = UtilDatas.getTimestamp(dif);
		return saida;
	}

	@Override
	public void setListaExercicio(List valor) {
		throw new RuntimeException("Set nao permitido");
		
	}

	@Override
	public List getListaExercicio() {
		List<Exercicio> listaExercicio = new ArrayList<Exercicio>();
		Exercicio corrente = null;
		if (principal.getListaExecucaoItemSerie_FoiRealizadoOriginal()==null) {
			carregaListaExercicioRealizado();
		}
		List<ExecucaoItemSerie> listaExecucao = principal.getListaExecucaoItemSerie_FoiRealizadoOriginal();
		for (ExecucaoItemSerie exec : listaExecucao) {
			if (corrente==null || corrente.getId()!=exec.getIdExercicioRa()) {
				if (corrente!=null) {
					listaExercicio.add(corrente);
				}
				corrente = exec.getCorrenteExercicio_ReferenteA();
			}
			corrente.addListaExecucaoItemSerie_Executado(exec);
		}
		listaExercicio.add(corrente);
		return listaExercicio;
	}
	
	private void carregaListaExercicioRealizado() {
		ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		srv.getFiltro().setIdDia(principal.getId());
		principal.setListaExecucaoItemSerie_FoiRealizado(srv.CarregaCompletoPorDia(principal.getContexto()));
	}
	
	@Override
	public String getTituloTela() {
		
		List<ExecucaoItemSerie> listaExecucao = principal.getListaExecucaoItemSerie_FoiRealizado();
		return principal.getDataDDMMAAAA() + " ( " + this.getDiaSemana() + " )";
	}
	
	

	@Override
	public void setHoraInicio(Timestamp valor) {
	}

	@Override
	public void setHoraFim(Timestamp valor) {
	}

	@Override
	public void setTempoConsumido(Timestamp valor) {
	}

	@Override
	public Timestamp getHoraInicio() {
		return this.getPrimeiraDia().getDataHoraInicio();
	}

	@Override
	public Timestamp getHoraFim() {
		return this.getUltimaDia().getDataHoraFinalizacao();
	}

	@Override
	public void setQuantidadeExecutado(long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getQuantidadeExecutado() {
		itemSerieSrv.getFiltro().setDia(principal);
		List<ItemSerie> lista = itemSerieSrv.ListaPorDiaComExecucao(principal.getContexto());
		long conta = 0;
		for (ItemSerie item : lista) {
			if (item.getConcluidoNoDia()) conta++;
		}
		return conta;
	}

	@Override
	public void setQuantidadeTotal(long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getQuantidadeTotal() {
		List listaItemSerie = principal.getSerieTreino_SerieDia().getListaItemSerie_Possui();
		return listaItemSerie.size();
	}
}