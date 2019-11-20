package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.ItemSerie;
import android.content.Context;
import treinoacademia.servico.filtro.ItemSerieFiltro;

public interface ItemSerieServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<ItemSerie> lista,Context contexto);
	public List<ItemSerie> listaSincronizadaAlteracao(Context contexto);
}