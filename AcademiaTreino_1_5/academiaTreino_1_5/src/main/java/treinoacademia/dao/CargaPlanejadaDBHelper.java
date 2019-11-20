package treinoacademia.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.digicom.Constants;
import br.com.digicom.dao.DBHelperBase;
import br.com.digicom.dao.DaoException;
import br.com.digicom.modelo.DCIObjetoDominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.dao.base.CargaPlanejadaDBHelperBase;
import treinoacademia.dao.base.ItemSerieDBHelperBase;

public class CargaPlanejadaDBHelper extends CargaPlanejadaDBHelperBase{

	@Override
	public List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id) throws DaoException{
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where ativa = 1 and id_item_serie_ra = " + id + " order by ordem_repeticao";
		return this.getListaSql(sql);
	}

	public List<CargaPlanejada> ListaCargaPorExercicio(Long idExercicio) {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				this.innerJoinItemSerie_ReferenteA() +
				ItemSerieDBHelperBase.innerJoinSerieTreino_PertencenteA() +
				" where item_serie.id_exercicio_ed = " + idExercicio +
				" and serie_treino.ativa = 1  order by ordem_repeticao";
		return this.getListaSql(sql);
	}
	public List<CargaPlanejada> ListaCargaPorExercicioSinc(Long idExercicio) {
		String sql = "select " + camposOrdenadosSinc() + " from " + DB_TABLE_SINC +
				this.innerJoinSincItemSerie_ReferenteA() +
				ItemSerieDBHelperBase.innerJoinSincSerieTreino_PertencenteA() +
				" where item_serie_sinc.id_exercicio_ed = " + idExercicio;
		return this.getListaSqlSinc(sql);
	}

	

	

	public List<CargaPlanejada> ListaCargaAtivaPorExercicio(Long idExercicio) {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				this.innerJoinItemSerie_ReferenteA() +
				ItemSerieDBHelperBase.innerJoinSerieTreino_PertencenteA() +
				" where item_serie.id_exercicio_ed = " + idExercicio +
				" and serie_treino.ativa = 1  and carga_planejada.ativa = 1 order by ordem_repeticao";
		return this.getListaSql(sql);
	}

	
   
}
