package treinoacademia.dao;

import java.util.List;

import treinoacademia.dao.base.DiaTreinoDBHelperBase;
import treinoacademia.dao.base.ExecucaoItemSerieDBHelperBase;
import treinoacademia.dao.montador.DiaTreinoMontador;
import treinoacademia.dao.montador.ExecucaoItemSerieMontador;
import treinoacademia.modelo.DiaTreino;
import br.com.digicom.dao.DaoException;
import br.com.digicom.dao.MontadorDaoComposite;
import br.com.digicom.util.UtilDatas;

public class DiaTreinoDBHelper extends DiaTreinoDBHelperBase{

   
	
	
	

	@Override
	protected String orderByColuna() {
		return " data desc ";
	}

	public DiaTreino ObtemPorData(String data) {
		String sql = " select " + camposOrdenados() + " from " + DB_TABLE +
				" where data = " + UtilDatas.converteDD_MM_AAAA(data);
		return (DiaTreino) this.geObjetoSql(sql);
	}

	public List<DiaTreino> getListaSemTreino() {
		String sql = " select " + camposOrdenados() + " from " + DB_TABLE +
				" where id_dia_treino not in (select distinct id_dia_treino_e from execucao_item_serie) ";
		return this.getListaSql(sql);
	}

	public List<DiaTreino> HistoricoExecucaoPorIdExercicio(long idExercicio) throws DaoException {
		String sql = "select " + camposOrdenados() +  " , " +
				ExecucaoItemSerieDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE +
				this.innerJoinExecucaoItemSerie_FoiRealizado() +
				" where id_exercicio_ra = " + idExercicio + " order by data desc ";
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new DiaTreinoMontador(), null);
		montador.adicionaMontador(new ExecucaoItemSerieMontador(), "ExecucaoItemSerie_FoiRealizado");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}

	public List<DiaTreino> TreinosPosDataPesquisa(long dataNum) throws DaoException{
		String sql = " select " + camposOrdenados() + " from " + DB_TABLE +
				" where data >= " + dataNum + " order by " +  orderByColuna();
		return this.getListaSql(sql);
	}
}
