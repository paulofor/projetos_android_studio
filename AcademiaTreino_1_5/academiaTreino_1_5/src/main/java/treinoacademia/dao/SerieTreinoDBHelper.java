package treinoacademia.dao;

import java.util.List;

import treinoacademia.dao.base.SerieTreinoDBHelperBase;
import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.servico.CargaPlanejadaServico;
import treinoacademia.servico.FabricaServico;

public class SerieTreinoDBHelper extends SerieTreinoDBHelperBase{

	public SerieTreino obtemProxima() {
		String sql = "select " + camposOrdenados() + " from " +
				DB_TABLE + " where ativa = 1 order by data_ultima_execucao asc limit 1";
		SerieTreino saida = (SerieTreino) this.geObjetoSql(sql);
		return saida;
	}
   
	public void atualizaData(long idSerie, long dataExecucao) {
		String sql = "update " + DB_TABLE + " set data_ultima_execucao = " + dataExecucao +
				" where id_serie_treino = " + idSerie;
		this.executeSql(sql);
	}

	/*
	@Override
	protected void listaPosGetAllSinc(List<SerieTreino> saida) {
		CargaPlanejadaDBHelper dao = FabricaDao.getInstancia().getCargaPlanejadaDBHelper();
		for (SerieTreino serie : saida) {
			if (serie.getListaItemSerie_Possui()!=null) {
				for (ItemSerie item : serie.getListaItemSerie_Possui()) {
					List<CargaPlanejada> listaCarga = dao.ListaCargaPorExercicioSinc(item.getIdExercicioEd());
					item.setListaCargaPlanejada_Possui(listaCarga);
				}
			}
		}
	}
	*/
	@Override
	protected String orderByColuna() {
		return " data_criacao desc ";
	}
	
	
	
}
