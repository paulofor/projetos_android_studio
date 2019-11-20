
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.Usuario;
import android.content.Context;
import treinoacademia.servico.filtro.UsuarioFiltro;

public interface UsuarioServicoRemoto {

	public List<Usuario> listaSincronizada(Context contexto);
	public List<Usuario> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<Usuario> listaSinc,Context contexto);
}