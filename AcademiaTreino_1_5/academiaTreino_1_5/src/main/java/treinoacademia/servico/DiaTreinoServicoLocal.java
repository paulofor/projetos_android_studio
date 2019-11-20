package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.DiaTreino;
import android.content.Context;
import treinoacademia.servico.filtro.DiaTreinoFiltro;

public interface DiaTreinoServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<DiaTreino> lista,Context contexto);
	public List<DiaTreino> listaSincronizadaAlteracao(Context contexto);
}