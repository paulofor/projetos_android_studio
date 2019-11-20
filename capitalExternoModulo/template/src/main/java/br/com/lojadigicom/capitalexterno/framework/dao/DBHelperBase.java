
package  br.com.lojadigicom.capitalexterno.framework.dao;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;

public class DBHelperBase {
	
	/*
	private MontadorDaoI montador;
	private DataSource dataSource;
	
	
	protected abstract ContentValues montaValores(DCIObjetoDominio item);
	
	public void erroException(Exception e, DBHelperBase dao) {
	}
	
	protected ContentValues montaValoresSinc(DCIObjetoDominio item) {
		ObjetoSinc sinc = (ObjetoSinc) item;
		ContentValues valores = montaValores((DCIObjetoDominio)item);
		putValor(valores,"operacao_sinc", sinc.getOperacaoSinc());
		return valores;
	}
	
	protected MontadorDaoI getMontador() {
		if (montador==null) {
			montador = criaMontadorPadrao();
		}
		return montador;
	}
	protected void setMontador(MontadorDaoI montadorAlternativo) {
		montador = montadorAlternativo;
	}
	protected void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected DCIObjetoDominio getItemQuery(boolean distinct, String tabela, String[] colunas, String selecao, String[] argSelecao, String groupBy, String having, String orderBy, String limite) {
		DCIObjetoDominio ret = null;
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().query(distinct, tabela, colunas, selecao, argSelecao, groupBy, having, orderBy, limite);
	        //int numRows = c.getCount();
	        c.moveToFirst();
	        if (c.getCount() > 0) {
                c.moveToFirst();
                ret = getMontador().getItem(c);
            }
	        getMontador().desligaSinc();
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    return ret;
	}
	
	// Muito mais intuitivo do qualquer outro tipo de acesso sinc.
	protected DCIObjetoDominio getItemQuerySinc(boolean distinct, String tabela, String[] colunas, String selecao, String[] argSelecao, String groupBy, String having, String orderBy, String limite) {
		DCIObjetoDominio ret = null;
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().query(distinct, tabela, colunas, selecao, argSelecao, groupBy, having, orderBy, limite);
	        //int numRows = c.getCount();
	        c.moveToFirst();
	        if (c.getCount() > 0) {
                c.moveToFirst();
                ret = getMontador().getItemSinc(c);
            }
	        getMontador().desligaSinc();
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    return ret;
	}
	
	protected List getListaQuery(String tabela, String[] colunas, String selecao, String[] argSelecao, String groupBy, String having, String orderBy) {
		ArrayList<DCIObjetoDominio> ret = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().query(tabela, colunas, selecao, argSelecao, groupBy, having, orderBy);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        for (int i = 0; i < numRows; ++i) {
	        	ret.add(getMontador().getItem(c));
	            c.moveToNext();
	        }
	        getMontador().desligaSinc();
	    } catch (Exception e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	//this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    return ret;
	}
	protected List getListaQuerySinc(String tabela, String[] colunas, String selecao, String[] argSelecao, String groupBy, String having, String orderBy) {
		ArrayList<DCIObjetoDominio> ret = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().query(tabela, colunas, selecao, argSelecao, groupBy, having, orderBy);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        for (int i = 0; i < numRows; ++i) {
	        	ret.add(getMontador().getItemSinc(c));
	            c.moveToNext();
	        }
	        getMontador().desligaSinc();
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    return ret;
	}
	
	
	protected long getNumeroSql(String sql) {
		long saida = 0;
		Cursor c = null;
		try {
			c = dataSource.getDb().rawQuery(sql, null);
			c.moveToFirst();
			saida = c.getLong(0);
		} catch (SQLException e) {
			DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
			this.erroException(e, this);
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
		}
		return saida;
	}
	protected float getValorFloat(String sql) {
		float saida = 0;
		Cursor c = null;
		try {
			c = dataSource.getDb().rawQuery(sql, null);
			c.moveToFirst();
			saida = c.getFloat(0);
		} catch (SQLException e) {
			DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
			this.erroException(e, this);
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
		}
		return saida;
	}
	

	protected DCIObjetoDominio geObjetoSql(String sql) {
		DCIObjetoDominio ret = null;
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().rawQuery(sql, null);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        if (numRows>0) {
	        	ret  = getMontador().getItem(c);
	        }
	        getMontador().desligaSinc();
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    return ret;
	}
	
	protected List getListaSqlListaInterna(String sql) throws DaoException {
		ArrayList<DCIObjetoDominio> listaSaida = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
		c = dataSource.getDb().rawQuery(sql, null);
		int numRows = c.getCount();
        //c.moveToFirst();
		boolean insere = false;
		DCIObjetoDominio objeto = null;
		DaoItemRetorno retorno = null;
		while (c.moveToNext()) {
			try {
				retorno = getMontador().extraiRegistroListaInterna(c, objeto);
				insere = retorno.getInsere();
				objeto = retorno.getObjeto();
				//c.moveToNext();
			} catch (Exception e) {
				DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
				this.erroException(e, this);
				throw new DaoException(e,sql);
			}
			if (insere) {
				listaSaida.add(objeto);
			}
		}
		getMontador().desligaSinc();
		DCLog.d(DCLog.DATABASE_ADM,this,"getListaSqlListaInterna com " + listaSaida.size() + " itens, SQL:" + sql);
		return listaSaida;
	}
	
	// Mais intuitivo do que alterar o montador
	protected List getListaSqlSinc(String sql) {
		ArrayList<DCIObjetoDominio> ret = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().rawQuery(sql, null);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        for (int i = 0; i < numRows; ++i) {
	        	ret.add(getMontador().getItemSinc(c));
	            c.moveToNext();
	        }
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    getMontador().desligaSinc();
	    DCLog.d(DCLog.DATABASE_ADM,this,"getListaSql com " + ret.size() + " itens, SQL:" + sql);
	    return ret;
	}
	
	protected List getListaSql(String sql) {
		ArrayList<DCIObjetoDominio> ret = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().rawQuery(sql, null);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        for (int i = 0; i < numRows; ++i) {
	        	ret.add(getMontador().getItem(c));
	            c.moveToNext();
	        }
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    	getMontador().desligaSinc();
	    }
	    
	    DCLog.d(DCLog.DATABASE_ADM,this,"getListaSql com " + ret.size() + " itens, SQL:" + sql);
	    return ret;
	}
	protected List getListaSqlQuery(String sql, String[] argSelecao) {
		ArrayList<DCIObjetoDominio> ret = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().rawQuery(sql, argSelecao);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        for (int i = 0; i < numRows; ++i) {
	        	ret.add(getMontador().getItem(c));
	            c.moveToNext();
	        }
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    getMontador().desligaSinc();
	    return ret;
	}
	
	protected List getListaSqlQuerySinc(String sql, String[] argSelecao) {
		ArrayList<DCIObjetoDominio> ret = new ArrayList<DCIObjetoDominio>();
		Cursor c = null;
	    try {
	    	c = dataSource.getDb().rawQuery(sql, argSelecao);
	        int numRows = c.getCount();
	        c.moveToFirst();
	        for (int i = 0; i < numRows; ++i) {
	        	ret.add(getMontador().getItemSinc(c));
	            c.moveToNext();
	        }
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } finally {
	    	if (c != null && !c.isClosed()) {
	    		c.close();
	        }
	    }
	    getMontador().desligaSinc();
	    return ret;
	}
	
	protected void executeSql(String sql) {
	    try {
	    	dataSource.getDb().rawQuery(sql, null);
	    } catch (SQLException e) {
	    	DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
	    	this.erroException(e, this);
	    } 
	    return;
	}
	
	
	protected abstract MontadorDaoI criaMontadorPadrao();
	protected SQLiteDatabase getDb() {
		return dataSource.getDb();
	}
	
	protected void putValor(ContentValues valores, String tipo, String campo) {
		valores.put(tipo, campo);
	}
	protected void putValor(ContentValues valores, String tipo, int campo) {
		valores.put(tipo, campo);
	}
	protected void putValor(ContentValues valores, String tipo, float campo) {
		valores.put(tipo, campo);
	}
	protected void putValor(ContentValues valores, String tipo, boolean campo) {
		valores.put(tipo, campo);
	}
	protected void putValor(ContentValues valores, String tipo, Timestamp campo) {
		long num = campo.getTime();
		valores.put(tipo, num);
	}
	
	protected long getValorData(Timestamp data) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		long num = Long.parseLong(formato.format(data));
		return num;
	}
	protected long getValorData(String data) {
		String dataNum = data.substring(6) + data.substring(3, 5) + data.substring(0, 2) + "000000";
		long num = Long.parseLong(dataNum);
		return num;
	}
	
	
	// DD-MM-AAAA HH:MM:SS
	// 0123456789012345678
	protected long getValorDataHora(String dataHora) {
		String dataNum = dataHora.substring(6,10) + dataHora.substring(3, 5) + dataHora.substring(0, 2);
		String horaNum = dataHora.substring(11,13) + dataHora.substring(14,16) + dataHora.substring(17);
		long num = Long.parseLong(dataNum + horaNum);
		return num;
	}
	
	
	protected void putValorData(ContentValues valores, String tipo, Timestamp campo) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		long num = 0;
		if (campo!=null)
			num = Long.parseLong(formato.format(campo));
		valores.put(tipo, num);
	}
	protected void putValorDataHora(ContentValues valores, String tipo, Timestamp campo) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
		
		long num = 0;
		if (campo!=null) {
			String texto = formato.format(campo);
			num = Long.parseLong(formato.format(campo));
		}
		valores.put(tipo, num);
	}
	
	*/

	public static List getListaSqlListaInterna(Cursor c, MontadorDaoI montador, Object cliente) throws DaoException {
		ArrayList<DCIObjetoDominio> listaSaida = new ArrayList<DCIObjetoDominio>();
		int numRows = c.getCount();
		boolean insere = false;
		DCIObjetoDominio objeto = null;
		DaoItemRetorno retorno = null;
		DCLog.d(DCLog.OPERACAO_DB_TELA,cliente,"ResultSet: " + numRows + " elementos");
		while (c.moveToNext()) {
			try {
				retorno = montador.extraiRegistroListaInterna(c, objeto);
				insere = retorno.getInsere();
				objeto = retorno.getObjeto();
			} catch (Exception e) {
				DCLog.e(DCLog.DATABASE_ERRO_CORE,cliente, e);
				//this.erroException(e, this);
				//throw new DaoException(e,sql);
			}
			if (insere) {
				listaSaida.add(objeto);
			}
		}
		DCLog.d(DCLog.OPERACAO_DB_TELA,cliente,"Lista: " + listaSaida.size() + " elementos");
		return listaSaida;
	}
}
