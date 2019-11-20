package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.Exercicio;
import android.content.Context;
import treinoacademia.servico.filtro.ExercicioFiltro;

public interface ExercicioServicoLocal {

	public void dropCreate(Context contexto);
	public void insertAll(List<Exercicio> lista,Context contexto);
	public List<Exercicio> listaSincronizadaAlteracao(Context contexto);
}