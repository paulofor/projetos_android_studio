
package repcom.servico.sqlite.impl;

import java.util.List;

import repcom.dao.CategoriaProdutoProdutoDBHelper;
import repcom.dao.FabricaDao;
import repcom.dao.ProdutoDBHelper;
import repcom.modelo.Produto;
import repcom.servico.sqlite.base.ProdutoServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;

public class ProdutoServicoSqliteImpl extends ProdutoServicoSqliteBase {

	@Override
	protected List<Produto> ListaPorIdCategoriaImpl(DigicomContexto contexto) {
		List<Produto> saida = null; 
		CategoriaProdutoProdutoDBHelper dao = FabricaDao.getInstancia().getCategoriaProdutoProdutoDBHelper();
		try {
			
			saida = dao.getProdutoPorReferenteACategoriaProduto(contexto.getContext(), getFiltro().getIdCategoria());
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}

	@Override
	protected List<Produto> PesquisaTrechoNomeImpl(DigicomContexto contexto) {
		List<Produto> saida = null;
		ProdutoDBHelper dao = FabricaDao.getInstancia().getProdutoDBHelper();
		String nome = this.getFiltro().validaItem().getNome();
		saida = dao.PesquisaTrechoNome(nome);
		return saida;
	}

}