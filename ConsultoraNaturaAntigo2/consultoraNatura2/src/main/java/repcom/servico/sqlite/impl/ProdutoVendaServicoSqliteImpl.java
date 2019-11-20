
package repcom.servico.sqlite.impl;

import repcom.dao.ProdutoVendaDBHelper;
import repcom.modelo.ProdutoVenda;
import repcom.servico.sqlite.base.ProdutoVendaServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;

public class ProdutoVendaServicoSqliteImpl extends ProdutoVendaServicoSqliteBase {

	@Override
	protected ProdutoVenda inicializaItemTelaImpl(ProdutoVenda itemTela, DigicomContexto contexto) {
		return itemTela;
	}

	@Override
	protected void finalizaItemTelaImpl(ProdutoVenda itemTela, boolean insercao, DigicomContexto contexto) {
		ProdutoVendaDBHelper dao = getDao();
		if (insercao) {
			dao.insertSinc(itemTela);
		} else {
			dao.updateSinc(itemTela);
		}
	}

	
	
}