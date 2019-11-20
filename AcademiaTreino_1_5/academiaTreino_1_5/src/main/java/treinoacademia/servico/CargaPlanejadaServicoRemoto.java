
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.CargaPlanejada;
import android.content.Context;
import treinoacademia.servico.filtro.CargaPlanejadaFiltro;

public interface CargaPlanejadaServicoRemoto {

	public List<CargaPlanejada> listaSincronizada(Context contexto);
	public List<CargaPlanejada> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<CargaPlanejada> listaSinc,Context contexto);
}