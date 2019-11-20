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
import repcom.modelo.Produto;
import repcom.dao.base.ProdutoDBHelperBase;

public class ProdutoDBHelper extends ProdutoDBHelperBase{

	public List<Produto> PesquisaTrechoNome(String trecho) {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where nome LIKE '" + trecho + "%' order by nome";
		return this.getListaSql(sql);
	}


   
}
