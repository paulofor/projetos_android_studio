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
import repcom.modelo.CategoriaProduto;
import repcom.dao.base.CategoriaProdutoDBHelperBase;

public class CategoriaProdutoDBHelper extends CategoriaProdutoDBHelperBase{

	public List<CategoriaProduto> ListaNivel0() {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where categoria_produto.id_categoria_produto_p = 0";
		return this.getListaSql(sql);
	}

   
}
