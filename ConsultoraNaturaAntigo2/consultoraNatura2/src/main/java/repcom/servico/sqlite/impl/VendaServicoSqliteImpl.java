
package repcom.servico.sqlite.impl;

import br.com.digicom.activity.DigicomContexto;
import repcom.modelo.Venda;
import repcom.servico.sqlite.base.*;

public class VendaServicoSqliteImpl extends VendaServicoSqliteBase {

	@Override
	protected Venda inicializaItemTelaImpl(Venda itemTela, DigicomContexto contexto) {
		return itemTela;
	}

	
	
}