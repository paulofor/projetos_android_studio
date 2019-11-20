
package repcom.servico.sqlite.impl;

import java.util.List;

import repcom.dao.CategoriaProdutoDBHelper;
import repcom.modelo.CategoriaProduto;
import repcom.servico.sqlite.base.CategoriaProdutoServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;

public class CategoriaProdutoServicoSqliteImpl extends CategoriaProdutoServicoSqliteBase {

	@Override
	protected List<CategoriaProduto> ListaNivel0Impl(DigicomContexto contexto) {
		CategoriaProdutoDBHelper dao = getDao();
		List<CategoriaProduto> saida = dao.ListaNivel0();
		return saida;
	}

}