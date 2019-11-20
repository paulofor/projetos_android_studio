package treinoacademia.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.digicom.Constants;
import br.com.digicom.dao.DBHelperBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import treinoacademia.modelo.Exercicio;
import treinoacademia.dao.base.ExercicioDBHelperBase;
import treinoacademia.dao.base.ItemSerieDBHelperBase;

public class ExercicioDBHelper extends ExercicioDBHelperBase{

	@Override
	protected String orderByColuna() {
		return "titulo asc";
	}

	public List<Exercicio> ListaAtivosNoMomento() {
		String sql = "select " + this.camposOrdenados() + " from " + this.DB_TABLE +
				this.innerJoinItemSerie_Gera() +
				ItemSerieDBHelperBase.innerJoinSerieTreino_PertencenteA() +
				" where ativa = 1 order by " + orderByColuna();
		return this.getListaSql(sql);
	}

  
	
}
