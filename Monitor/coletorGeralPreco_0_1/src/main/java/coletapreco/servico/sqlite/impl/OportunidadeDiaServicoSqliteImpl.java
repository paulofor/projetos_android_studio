
package coletapreco.servico.sqlite.impl;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;
import coletapreco.dao.OportunidadeDiaDBHelper;
import coletapreco.modelo.OportunidadeDia;
import coletapreco.servico.sqlite.base.OportunidadeDiaServicoSqliteBase;

public class OportunidadeDiaServicoSqliteImpl extends OportunidadeDiaServicoSqliteBase {

	@Override
	protected List<OportunidadeDia> ListaPorNaturezaImpl(DigicomContexto contexto) {
		
		this.getFiltro().validaNatureza();
		long codigoNatureza = this.getFiltro().getNatureza().getIdNaturezaProduto();
		OportunidadeDiaDBHelper dao = getDao();
		List<OportunidadeDia> saida = null;
		try {
			saida = dao.getPorPertenceANaturezaProduto(contexto.getContext(), codigoNatureza);
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ADM, this, e);
		}
		return saida;
	}

}