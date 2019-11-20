package br.com.digicom.dao;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;



public abstract class DataSource {

	// Esta sendo usado como se fosse uma interface, 
	// parece que a melhor opcao e fazer como o remote
	// transformar em interface e criar um abstract com conteudo
	// decidir ate 02-05-2014

	public abstract SQLiteDatabase getDb();
	public abstract String getPathPadrao();
	
	// Trecho comum para os filhos
	protected final static String CHAVE_DIRETORIO = "chaveDiretorio";
	protected SQLiteDatabase db = null;
	protected static Context context = null;
	protected String usuarioPath = null;
	
	protected void salvaValorPath() {
		SharedPreferences settings = context.getSharedPreferences(CHAVE_DIRETORIO, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuarioPath",usuarioPath);
        editor.commit();
	}
	protected void recuperaValorPath() {
		SharedPreferences settings = context.getSharedPreferences(CHAVE_DIRETORIO, 0);
		usuarioPath = settings.getString("usuarioPath", null);
	}
	public static String getValorPath() {
		SharedPreferences settings = context.getSharedPreferences(CHAVE_DIRETORIO, 0);
		return settings.getString("usuarioPath", null);
	}
}
