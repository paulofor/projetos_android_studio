package coletapreco.dao;

import java.util.List;

import android.content.Context;
import br.com.digicom.dao.DaoException;
import coletapreco.dao.base.OportunidadeDiaDBHelperBase;
import coletapreco.modelo.OportunidadeDia;

public class OportunidadeDiaDBHelper extends OportunidadeDiaDBHelperBase{

	@Override
	protected String orderByColuna() {
		return " percentual_ajuste_venda desc ";
	}

	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id) throws DaoException{
		return getListaQuery(DB_TABLE, COLS, "id_natureza_produto_pa = " + id, null, null, null, orderByColuna());
	}
   
}
