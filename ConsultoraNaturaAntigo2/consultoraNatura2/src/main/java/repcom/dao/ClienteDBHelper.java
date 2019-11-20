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
import repcom.modelo.Cliente;
import repcom.dao.base.ClienteDBHelperBase;

public class ClienteDBHelper extends ClienteDBHelperBase{

	public Cliente obtemPorIdAgendaTel(String codigoAgendaTel) {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where codigo_lista_contato = '" + codigoAgendaTel + "' ";
		return (Cliente) this.geObjetoSql(sql);
	}

	public List<Cliente> listaAtivos() {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where ativo = 1 order by nome";
		return this.getListaSql(sql);
	}

	public List<Cliente> PesquisaTrechoNome(String trecho) {
		String sql = "select " + camposOrdenados() + " from " + DB_TABLE +
				" where nome LIKE '" + trecho + "%' order by nome";
		return this.getListaSql(sql);
	}

   
}
