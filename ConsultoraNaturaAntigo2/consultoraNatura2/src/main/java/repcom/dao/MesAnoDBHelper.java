package repcom.dao;

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
import repcom.modelo.MesAno;
import repcom.dao.base.MesAnoDBHelperBase;

public class MesAnoDBHelper extends MesAnoDBHelperBase{

	public MesAno obtemPorReferencia(long mesAnoCorrente) {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where data_referencia = " + mesAnoCorrente;
		return (MesAno) this.geObjetoSql(sql);
	}

   
}
