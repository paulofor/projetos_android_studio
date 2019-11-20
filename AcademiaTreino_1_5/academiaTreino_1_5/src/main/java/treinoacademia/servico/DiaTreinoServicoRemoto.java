
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.DiaTreino;
import android.content.Context;
import treinoacademia.servico.filtro.DiaTreinoFiltro;

public interface DiaTreinoServicoRemoto {

	public List<DiaTreino> listaSincronizada(Context contexto);
	public List<DiaTreino> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<DiaTreino> listaSinc,Context contexto);
}