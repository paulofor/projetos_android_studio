
package repcom.servico.sqlite.impl;

import java.sql.Timestamp;

import repcom.dao.MesAnoDBHelper;
import repcom.modelo.MesAno;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.sqlite.base.MesAnoServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.util.UtilDatas;

public class MesAnoServicoSqliteImpl extends MesAnoServicoSqliteBase {

	
	
	@Override
	protected MesAno inicializaItemTelaImpl(DigicomContexto contexto) {
		MesAno corrente = ObtemMesCorrente(contexto);
		return corrente;
	}


	@Override
	protected MesAno ObtemMesCorrenteImpl(DigicomContexto contexto) {
		MesAnoDBHelper dao = getDao();
		long mesAnoCorrente = UtilDatas.getDataAtual01MesAnoLong();
		MesAno saida = dao.obtemPorReferencia(mesAnoCorrente);		
		return saida;
	}

}