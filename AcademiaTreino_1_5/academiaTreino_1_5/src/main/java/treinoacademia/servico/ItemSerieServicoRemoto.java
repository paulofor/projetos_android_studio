
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.ItemSerie;
import android.content.Context;
import treinoacademia.servico.filtro.ItemSerieFiltro;

public interface ItemSerieServicoRemoto {

	public List<ItemSerie> listaSincronizada(Context contexto);
	public List<ItemSerie> listaSincronizadaUsuario(Context contexto);
	public void listaSincronizadaAlteracao(List<ItemSerie> listaSinc,Context contexto);
}