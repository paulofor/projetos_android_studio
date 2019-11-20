package treinoacademia.dao;

import java.util.List;

import treinoacademia.dao.base.DiaTreinoDBHelperBase;
import treinoacademia.dao.base.ExecucaoItemSerieDBHelperBase;
import treinoacademia.dao.base.ExercicioDBHelperBase;
import treinoacademia.dao.base.ItemSerieDBHelperBase;
import treinoacademia.dao.montador.DiaTreinoMontador;
import treinoacademia.dao.montador.ExecucaoItemSerieMontador;
import treinoacademia.dao.montador.ExercicioMontador;
import treinoacademia.modelo.ExecucaoItemSerie;
import br.com.digicom.dao.DaoException;
import br.com.digicom.dao.MontadorDaoComposite;

public class ExecucaoItemSerieDBHelper extends ExecucaoItemSerieDBHelperBase{

	public List obtemPorDiaItemSerie(long idDia, long idItemSerie) {
		String sql = "select " + camposOrdenados() + " from " +
				this.DB_TABLE + " where id_dia_treino_e = " + idDia + " and id_item_serie_ra = " + idItemSerie +
				" order by numero_serie";
		return this.getListaSql(sql);
	}

	public List<ExecucaoItemSerie> ultimasPorUsuarioExercicio(long idExercicio, int qtde) throws DaoException {
		String sql = "select " + camposOrdenados() + " , " +
				DiaTreinoDBHelperBase.camposOrdenados() +
				" from " +
				this.DB_TABLE + 
				this.innerJoinDiaTreino_Em() +
				" where id_exercicio_ra = " + idExercicio + 
				" order by dia_treino.data desc limit " + qtde;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ExecucaoItemSerieMontador(), null);
		montador.adicionaMontador(new DiaTreinoMontador(), "DiaTreino_Em");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}

	public List<ExecucaoItemSerie> ultimasPorItemSerie(long idItemSerie, int qtde) throws DaoException {
		String sql = "select " + camposOrdenados() + " , " +
				DiaTreinoDBHelperBase.camposOrdenados() +
				" from " +
				this.DB_TABLE + 
				this.innerJoinDiaTreino_Em() +
				" where id_item_serie_ra = " + idItemSerie + 
				" order by dia_treino.data desc limit " + qtde;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ExecucaoItemSerieMontador(), null);
		montador.adicionaMontador(new DiaTreinoMontador(), "DiaTreino_Em");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}

	public List<ExecucaoItemSerie> ultimasPorItemSerieGeral(long idItemSerie, int qtde) throws DaoException {
		String sql = "select " + camposOrdenados() + " , " +
				DiaTreinoDBHelperBase.camposOrdenados() +
				" from " +
				ItemSerieDBHelperBase.DB_TABLE + 
				ItemSerieDBHelperBase.innerJoinExercicio_ExecucaoDe() +
				ExercicioDBHelper.innerJoinExecucaoItemSerie_Executado() +
				this.innerJoinDiaTreino_Em() +
				" where id_item_serie = " + idItemSerie + 
				" order by dia_treino.data desc limit " + qtde;
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ExecucaoItemSerieMontador(), null);
		montador.adicionaMontador(new DiaTreinoMontador(), "DiaTreino_Em");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}
	
	public List<ExecucaoItemSerie> CarregaCompletoPorDia(Long idDia) throws DaoException {
		String sql = "select " + camposOrdenados() + " , " +
				ExercicioDBHelperBase.camposOrdenados() +
				" from " + 
				this.DB_TABLE +
				this.innerJoinExercicio_ReferenteA() +
				" where id_dia_treino_e = " + idDia +
				" order by data_hora_inicio ";
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ExecucaoItemSerieMontador(), null);
		montador.adicionaMontador(new ExercicioMontador(), "Exercicio_ReferenteA");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}

	public ExecucaoItemSerie PrimeiraPorSerie(long codigoSerie)  throws DaoException {
		String sql = "select " + camposOrdenados() + " from " + this.DB_TABLE +
				this.innerJoinItemSerie_ReferenteA() +
				" where id_serie_treino_pa = " + codigoSerie + 
				" order by data_hora_inicio asc limit 1";
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
	}

	public ExecucaoItemSerie UltimaPorSerie(long codigoSerie) throws DaoException {
		String sql = "select " + camposOrdenados() + " from " + this.DB_TABLE +
				this.innerJoinItemSerie_ReferenteA() +
				" where id_serie_treino_pa = " + codigoSerie + 
				" order by data_hora_inicio desc limit 1";
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
	}

	public ExecucaoItemSerie PrimeiraPorDia(long codigo) {
		String sql = "select " + camposOrdenados() + " from " + this.DB_TABLE +
				" where id_dia_treino_e = " + codigo + 
				" order by data_hora_inicio asc limit 1";
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
	}
	public ExecucaoItemSerie UltimaPorDia(long codigo) {
		String sql = "select " + camposOrdenados() + " from " + this.DB_TABLE +
				" where id_dia_treino_e = " + codigo + 
				" order by data_hora_inicio desc limit 1";
		return (ExecucaoItemSerie) this.geObjetoSql(sql);
	}

   
}
