
package treinoacademia.servico.sqlite.impl;

import treinoacademia.dao.UsuarioDBHelper;
import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.sqlite.base.UsuarioServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;

public class UsuarioServicoSqliteImpl extends UsuarioServicoSqliteBase {

	private static Usuario usuario = null;
	
	@Override
	protected Usuario ObtemNoAparelhoImpl(DigicomContexto contexto) {
		UsuarioDBHelper dao = getDao();
		Usuario usuario = dao.obtemPrimeiro();
		if (usuario==null) {
			usuario = getUsuarioFalso();
		}
		return usuario;
	}

	private Usuario getUsuarioFalso() {
		Usuario usuario = FabricaVo.criaUsuario();
		usuario.setIdUsuario(1);
		return usuario;
	}

	

	
}