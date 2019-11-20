package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.Usuario;
import android.content.Context;
import treinoacademia.servico.filtro.UsuarioFiltro;

public interface UsuarioServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<Usuario> lista,Context contexto);
	public List<Usuario> listaSincronizadaAlteracao(Context contexto);
}