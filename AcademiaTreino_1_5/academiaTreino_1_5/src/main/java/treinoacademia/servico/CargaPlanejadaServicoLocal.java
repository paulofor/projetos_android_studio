package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.CargaPlanejada;
import android.content.Context;
import treinoacademia.servico.filtro.CargaPlanejadaFiltro;

public interface CargaPlanejadaServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<CargaPlanejada> lista,Context contexto);
	public List<CargaPlanejada> listaSincronizadaAlteracao(Context contexto);
}