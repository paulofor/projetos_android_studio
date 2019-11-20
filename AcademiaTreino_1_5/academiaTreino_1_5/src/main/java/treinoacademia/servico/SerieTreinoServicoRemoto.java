
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.SerieTreino;
import android.content.Context;
import treinoacademia.servico.filtro.SerieTreinoFiltro;

public interface SerieTreinoServicoRemoto {

	public List<SerieTreino> listaSincronizada(Context contexto);
	public List<SerieTreino> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<SerieTreino> listaSinc,Context contexto);
}