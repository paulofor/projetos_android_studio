package treinoacademia.modelo.derivada.impl;

import java.sql.Timestamp;
import java.util.List;

import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.modelo.derivada.SerieTreinoDerivadaI;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.FabricaServico;

public class SerieTreinoDerivada implements SerieTreinoDerivadaI{ 
  
  	private SerieTreino principal;
  	private ExecucaoItemSerieServico execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
  	private ExecucaoItemSerie primeiraExecucao = null;
  	
  	private DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
  	private List<DiaTreino> listaDiaExecucao = null;
  	
  	/*
  	public SerieTreinoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public SerieTreinoDerivada() {
		super();
  	}
  	*/
  	
  	public SerieTreinoDerivada(SerieTreino item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em SerieTreinoDerivada";
	}

	
/*
	@Override
	public Timestamp getDataUltimaExecucao() {
		this.execucaoSrv.getFiltro().setIdSerieTreino(principal.getId());
		ExecucaoItemSerie execucao = this.execucaoSrv.PrimeiraPorSerie(null);
		return execucao.getDataHoraInicio();
	}
*/
	@Override
	public void setDataPrimeiraExecucao(Timestamp valor) {
	}

	@Override
	public Timestamp getDataPrimeiraExecucao() {
		return (getPrimeiraExecucao()!=null?getPrimeiraExecucao().getDataHoraInicio():null);
	}

	private ExecucaoItemSerie getPrimeiraExecucao() {
		if (primeiraExecucao==null) {
			this.execucaoSrv.getFiltro().setIdSerieTreino(principal.getId());
			primeiraExecucao = this.execucaoSrv.PrimeiraPorSerie(null);
			//if (primeiraExecucao == null) throw new RuntimeException("Serie ID:" + principal.getId() + " nao tem primeira execucao");
		}
		return primeiraExecucao;
	}
	
	
	

	@Override
	public Timestamp getTempoMedio() {
		this.listaDiaExecucao = this.diaTreinoSrv.getPorSerieDiaSerieTreino(principal.getId());
		if (listaDiaExecucao.size()==0) return new Timestamp(0);
		long tempoTotal = 0;
		for (DiaTreino dia : listaDiaExecucao) {
			tempoTotal += dia.getTempoConsumido().getTime();
		}
		long tempoMedio = Math.round(tempoTotal / listaDiaExecucao.size());
		return new Timestamp(tempoMedio);
	}

	@Override
	public void setTempoMedio(Timestamp valor) {
		// TODO Auto-generated method stub
		
	}

	
}