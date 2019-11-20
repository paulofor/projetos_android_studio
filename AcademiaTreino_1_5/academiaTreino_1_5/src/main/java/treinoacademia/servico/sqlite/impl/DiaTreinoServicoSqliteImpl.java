
package treinoacademia.servico.sqlite.impl;

import java.util.List;

import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.SerieTreinoServico;
import treinoacademia.servico.UsuarioServico;
import treinoacademia.servico.sqlite.base.DiaTreinoServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;
import br.com.digicom.util.UtilDatas;

public class DiaTreinoServicoSqliteImpl extends DiaTreinoServicoSqliteBase {

	
	
	


	@Override
	protected DiaTreino LimpezaBaseImpl(DigicomContexto contexto) {
		List<DiaTreino> lista = null; 
		lista = getDao().getListaSemTreino();
		for (DiaTreino dia : lista) {
			getDao().deleteSinc(dia);
		}
		return null;
	}



	@Override
	protected void finalizaItemTelaImpl(DiaTreino itemTela, boolean insercao,  DigicomContexto contexto) {
		this.getFiltro().setDiaFinalizado(itemTela);
		this.EncerraDiaTreinoImpl(contexto);
	}


	

	@Override
	protected DiaTreino inicializaItemTelaImpl(DiaTreino itemTela, DigicomContexto contexto) {
		return inicializaItemTelaImpl(contexto);
	}



	@Override
	protected DiaTreino inicializaItemTelaImpl(DigicomContexto contexto) {
		Usuario usuario = null;
		UsuarioServico usuarioSrv = null;
		
		this.getFiltro().setDataPesquisa(UtilDatas.getDataAtual());
		SerieTreinoServico serieSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		DiaTreino saida = ObtemPorData(contexto);
		if (saida==null) {
			usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			usuario = usuarioSrv.getCorrente();
			
			saida = FabricaVo.criaDiaTreino();
			saida.setData(UtilDatas.getDataTimestampAtual());
			saida.setIdUsuarioS(usuario.getIdUsuario());
			
			SerieTreino serie = serieSrv.ObtemProxima(contexto);
			saida.setSerieTreino_SerieDia(serie);
			saida.setIdSerieTreinoSd(serie.getId());
			getDao().insertSinc(saida);
			DCLog.d(DCLog.INICIALIZACAO_OBJETO_TELA, this, "DiaTreino criado, persistido e com serie agrupada");
		} else {
			serieSrv.getFiltro().setIdSerieTreino(saida.getIdSerieTreinoSd());
			SerieTreino serie = serieSrv.ObtemMontadaPorId(contexto);
			saida.setSerieTreino_SerieDia(serie);
			DCLog.d(DCLog.INICIALIZACAO_OBJETO_TELA, this, "DiaTreino ja existe, montando a serie marcada.");
		}
		return saida;
	}

	

	@Override
	protected DiaTreino ObtemPorDataImpl(DigicomContexto contexto) {
		String data = UtilDatas.getDataAtual();
		return getDao().ObtemPorData(data);
	}



	@Override
	protected DiaTreino EncerraDiaTreinoImpl(DigicomContexto contexto) {
		// TODO Auto-generated method stub
		DiaTreino dia = this.getFiltro().getDiaFinalizado();
		SerieTreino serie = dia.getSerieTreino_SerieDia();
		serie.setDataUltimaExecucao(UtilDatas.getDataTimestampAtual());
		serie.setQtdeExecucao(serie.getQtdeExecucao() + 1);
		SerieTreinoServico serieSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		serieSrv.alteraParaSincronizacao(serie);
		return dia;
	}



	@Override
	protected List<DiaTreino> HistoricoExecucaoPorIdExercicioImpl(DigicomContexto contexto) throws DaoException {
		long idExercicio = this.getFiltro().getIdExercicio();
		List<DiaTreino> lista = null;
		lista = getDao().HistoricoExecucaoPorIdExercicio(idExercicio);
		//try {
		//	lista = getDao().HistoricoExecucaoPorIdExercicio(idExercicio);
		//} catch (DaoException e) {
		//	DCLog.e(DCLog.SERVICO_OPERACAO, this, e);
		//}
		return lista;
	}



	@Override
	protected List<DiaTreino> TreinosPosDataPesquisaImpl(DigicomContexto contexto) throws DaoException {
		List<DiaTreino> lista = null;
		String dataInicial = getFiltro().validaDataPesquisa();
		long dataNum = UtilDatas.converteDD_MM_AAAA(dataInicial);
		lista = getDao().TreinosPosDataPesquisa(dataNum);
		return lista;
	}

	

}