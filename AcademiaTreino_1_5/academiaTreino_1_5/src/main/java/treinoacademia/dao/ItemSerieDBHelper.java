package treinoacademia.dao;

import java.util.List;

import treinoacademia.dao.base.CargaPlanejadaDBHelperBase;
import treinoacademia.dao.base.ExercicioDBHelperBase;
import treinoacademia.dao.base.ItemSerieDBHelperBase;
import treinoacademia.dao.montador.CargaPlanejadaMontador;
import treinoacademia.dao.montador.ExercicioMontador;
import treinoacademia.dao.montador.ItemSerieMontador;
import treinoacademia.modelo.ItemSerie;
import android.content.Context;
import br.com.digicom.dao.DaoException;
import br.com.digicom.dao.MontadorDaoComposite;

public class ItemSerieDBHelper extends ItemSerieDBHelperBase{

	@Override
	public List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id) throws DaoException {
		String sql = "select " + camposOrdenados() +
				" , " + ExercicioDBHelperBase.camposOrdenados() +
				//" , " + CargaPlanejadaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE +
				innerJoinExercicio_ExecucaoDe() +
				//innerJoinCargaPlanejada_Possui() +
				" where id_serie_treino_pa = " + id +
				" order by id_item_serie, id_exercicio, ordem_execucao";
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ItemSerieMontador(), null);
		montador.adicionaMontador(new ExercicioMontador(), "Exercicio_ExecucaoDe");
		//montador.adicionaMontador(new CargaPlanejadaMontador(), "CargaPlanejada_Possui");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}
	
	public List<ItemSerie> getCompletoPorId(long id) throws DaoException {
		String sql = "select " + camposOrdenados() +
				" , " + CargaPlanejadaDBHelperBase.camposOrdenados() +
				" from " + DB_TABLE + 
				this.innerJoinCargaPlanejada_Possui() +
				" where id_item_serie = " + id +
				" and carga_planejada.ativa = 1 " +
				" order by ordem_repeticao ";
		MontadorDaoComposite montador = new MontadorDaoComposite();
		montador.adicionaMontador(new ItemSerieMontador(), null);
		montador.adicionaMontador(new CargaPlanejadaMontador(), "CargaPlanejada_Possui");
		this.setMontador(montador);
		return this.getListaSqlListaInterna(sql);
	}

   
}
