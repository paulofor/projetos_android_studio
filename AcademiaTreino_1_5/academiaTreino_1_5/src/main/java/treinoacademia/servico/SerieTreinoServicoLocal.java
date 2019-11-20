package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.SerieTreino;
import android.content.Context;
import treinoacademia.servico.filtro.SerieTreinoFiltro;

public interface SerieTreinoServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<SerieTreino> lista,Context contexto);
	public List<SerieTreino> listaSincronizadaAlteracao(Context contexto);
}