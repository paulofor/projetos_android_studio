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
import treinoacademia.modelo.Usuario;
import treinoacademia.dao.base.UsuarioDBHelperBase;

public class UsuarioDBHelper extends UsuarioDBHelperBase{

	public Usuario obtemPrimeiro() {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE;
		return (Usuario) this.geObjetoSql(sql);
	}
   
}
