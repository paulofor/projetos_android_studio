
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.Exercicio;
import android.content.Context;
import treinoacademia.servico.filtro.ExercicioFiltro;

public interface ExercicioServicoRemoto {

	public List<Exercicio> listaSincronizada(Context contexto);
	public List<Exercicio> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<Exercicio> listaSinc,Context contexto);
}