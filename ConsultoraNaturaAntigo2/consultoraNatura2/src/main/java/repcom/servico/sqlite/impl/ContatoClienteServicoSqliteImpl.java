
package repcom.servico.sqlite.impl;

import repcom.dao.ContatoClienteDBHelper;
import repcom.modelo.ContatoCliente;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.sqlite.base.ContatoClienteServicoSqliteBase;
import repcom.tela.listaedicao.ContatoClienteListaEdicao;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;

public class ContatoClienteServicoSqliteImpl extends ContatoClienteServicoSqliteBase {

	@Override
	protected ContatoCliente inicializaItemTelaImpl(ContatoCliente contato, DigicomContexto contexto) {
		return contato;
	}

	@Override
	protected void finalizaItemTelaImpl(ContatoCliente itemTela, boolean insercao, DigicomContexto contexto){
		ContatoClienteDBHelper dao = getDao();
		if (insercao) {
			dao.insertSinc(itemTela);
		} else {
			dao.updateSinc(itemTela);
		}
	}
	
}