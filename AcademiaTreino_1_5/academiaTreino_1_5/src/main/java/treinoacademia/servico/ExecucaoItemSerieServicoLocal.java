package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.ExecucaoItemSerie;
import android.content.Context;
import treinoacademia.servico.filtro.ExecucaoItemSerieFiltro;

public interface ExecucaoItemSerieServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<ExecucaoItemSerie> lista,Context contexto);
	public List<ExecucaoItemSerie> listaSincronizadaAlteracao(Context contexto);
}