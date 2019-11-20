
package treinoacademia.servico.sqlite.impl;

import java.util.List;

import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.sqlite.base.ExercicioServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;

public class ExercicioServicoSqliteImpl extends ExercicioServicoSqliteBase {

	@Override
	protected Exercicio inicializaItemTelaImpl(Exercicio item, DigicomContexto contexto) {
		return item;
	}

	@Override
	protected void finalizaItemTelaImpl(Exercicio itemTela, boolean insercao, DigicomContexto contexto) {
		// TODO Auto-generated method stub
		if (insercao)
			this.insereParaSincronizacao(itemTela);
		else
			this.alteraParaSincronizacao(itemTela);
	}

	@Override
	protected List<Exercicio> ListaAtivosNoMomentoImpl(DigicomContexto contexto) {
		List<Exercicio> saida = null; 
		saida = getDao().ListaAtivosNoMomento();
		return saida;
	}

	
	
}