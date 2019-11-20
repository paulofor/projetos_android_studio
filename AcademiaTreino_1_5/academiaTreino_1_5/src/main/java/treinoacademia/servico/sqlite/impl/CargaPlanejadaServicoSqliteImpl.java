
package treinoacademia.servico.sqlite.impl;

import java.util.List;

import treinoacademia.dao.CargaPlanejadaDBHelper;
import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.servico.sqlite.base.CargaPlanejadaServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;

public class CargaPlanejadaServicoSqliteImpl extends CargaPlanejadaServicoSqliteBase {

	@Override
	protected List<CargaPlanejada> ListaCargaPorExercicioImpl(DigicomContexto contexto) {
		CargaPlanejadaDBHelper dao = getDao();
		List<CargaPlanejada> lista = dao.ListaCargaPorExercicio(this.getFiltro().getIdExercicio());
		return lista;
	}

	@Override
	protected List<CargaPlanejada> ListaCargaAtivaPorExercicioImpl(DigicomContexto contexto) {
		CargaPlanejadaDBHelper dao = getDao();
		List<CargaPlanejada> lista = dao.ListaCargaAtivaPorExercicio(this.getFiltro().getIdExercicio());
		return lista;
	}

}