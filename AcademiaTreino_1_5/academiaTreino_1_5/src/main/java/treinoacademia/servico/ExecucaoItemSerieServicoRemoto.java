
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.ExecucaoItemSerie;
import android.content.Context;
import treinoacademia.servico.filtro.ExecucaoItemSerieFiltro;

public interface ExecucaoItemSerieServicoRemoto {

	public List<ExecucaoItemSerie> listaSincronizada(Context contexto);
	public List<ExecucaoItemSerie> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<ExecucaoItemSerie> listaSinc,Context contexto);
}