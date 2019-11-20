
package treinoacademia.servico.sqlite.impl;

import java.util.List;

import treinoacademia.dao.ExecucaoItemSerieDBHelper;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.servico.sqlite.base.ExecucaoItemSerieServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;

public class ExecucaoItemSerieServicoSqliteImpl extends ExecucaoItemSerieServicoSqliteBase {

	final int TAMANHO_SERIE = 3;
	
	@Override
	protected List<ExecucaoItemSerie> ObtemPorDiaItemSerieImpl(DigicomContexto contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		ExecucaoItemSerie execucao = null;
		long idDia = getFiltro().getIdDia();
		long idItemSerie = getFiltro().getIdItemSerie();
		if (idDia==0) throw new RuntimeException("IdDia em ExecucaoItemSerieServicoSqliteImpl.ObtemPorDiaExercicioImpl = 0");
		if (idItemSerie==0) throw new RuntimeException("idItemSerie em ExecucaoItemSerieServicoSqliteImpl.ObtemPorDiaExercicioImpl = 0");
		return dao.obtemPorDiaItemSerie(idDia, idItemSerie);

	}

	@Override
	protected List<ExecucaoItemSerie> UltimasExecucoesUsuarioExercicioImpl(DigicomContexto contexto) {
		int qtde = getFiltro().validaQuantidadeUltimasExecucoes().intValue();
		long idExercicio = getFiltro().validaIdExercicio();
		ExecucaoItemSerieDBHelper dao = getDao();
		try {
			return dao.ultimasPorUsuarioExercicio( idExercicio,qtde*TAMANHO_SERIE);
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			return null;
		}
	}

	@Override
	protected List<ExecucaoItemSerie> UltimasExecucoesItemSerieImpl(DigicomContexto contexto) {
		if (getFiltro().getQuantidadeUltimasExecucoes()==null) {
			throw new RuntimeException("Preencher a quantidade de cargas passadas a serem pesquisadas.");
		}
		int qtde = getFiltro().getQuantidadeUltimasExecucoes().intValue();
		ExecucaoItemSerieDBHelper dao = getDao();
		long idItemSerie = getFiltro().getIdItemSerie();
		if (idItemSerie==0) throw new RuntimeException("idItemSerie em ExecucaoItemSerieServicoSqliteImpl.UltimasUltimasExecucoesItemSerieImplExecucoesUsuarioExercicioImpl = 0");
		try {
			return dao.ultimasPorItemSerieGeral(idItemSerie, qtde*TAMANHO_SERIE);
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			return null;
		}

	}

	@Override
	protected List<ExecucaoItemSerie> CarregaCompletoPorDiaImpl(DigicomContexto contexto) throws DaoException {
		this.getFiltro().validaIdDia();
		ExecucaoItemSerieDBHelper dao = getDao();
		List<ExecucaoItemSerie> listaSaida = null;
		listaSaida = dao.CarregaCompletoPorDia(getFiltro().getIdDia());
		return listaSaida;
	}

	@Override
	protected ExecucaoItemSerie PrimeiraPorDiaImpl(DigicomContexto contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		long codigo = this.getFiltro().validaIdDia();
		return dao.PrimeiraPorDia(codigo);
	}

	@Override
	protected ExecucaoItemSerie UltimaPorDiaImpl(DigicomContexto contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		long codigo = this.getFiltro().validaIdDia();
		return dao.UltimaPorDia(codigo);
	}

	
	@Override
	protected ExecucaoItemSerie PrimeiraPorSerieImpl(DigicomContexto contexto) throws DaoException {
		ExecucaoItemSerie saida = null;
		long codigoSerie = this.getFiltro().validaIdSerieTreino();
		ExecucaoItemSerieDBHelper dao = getDao();
		saida = dao.PrimeiraPorSerie(codigoSerie);
		return saida;
	}
	

	/*
	@Override
	protected ExecucaoItemSerie UltimaPorSerieImpl(DigicomContexto contexto) {
		ExecucaoItemSerie saida = null;
		long codigoSerie = this.getFiltro().validaIdSerieTreino();
		ExecucaoItemSerieDBHelper dao = getDao();
		try {
			saida = dao.UltimaPorSerie(codigoSerie);
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	*/
	
	
	

}