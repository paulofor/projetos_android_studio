package br.com.digicom.log;

import android.util.Log;
import android.view.View;
import br.com.digicom.dao.DataSource;
import br.com.digicom.log.erro.ErroException;
import br.com.digicom.log.erro.ErroExceptionDBHelper;
import br.com.digicom.log.erro.ErroExceptionVo;

public class DCLog {
	
	public static final String OBJETO_INTERNO = "ObjetoInterno";
	
	public static final String INICIALIZACAO_OBJETO_TELA = "InicializacaoObjetoTela";
	public static final String DATABASE_ADM = "DatabaseAdm"; // insercoes e updates na base de dados
	public static final String DATABASE_ERRO = "DatabaseAdm";
	public static final String DATABASE_ERRO_CORE = "DatabaseAdm"; // Erros no DigicomAndroidLib
	
	public static final String DATABASE_COM_STACK = "DatabaseStack";
	
	public static final String SERVICO_OPERACAO = "ListService";
	public static final String SERVICO_QUADRO_LISTA = "ListService";
	public static final String SERVICO_OPERACAO_PADRAO = "ListService"; // metodos de servico padronizados
	public static final String SERVICO_TRATAMENTO_DADOS_TELA = "ListServiceTela";
	
	public static final String LAZY_LOADER = "LazyLoader";
	
	public static final String BACK_STACK_TRACE = "BackStackTrace";
	
	//public static final String SINCRONIZACAO_MOBILE2SERVER = "Sincronizacao";
	//public static final String SINCRONIZACAO_SERVER2MOBILE = "Sincronizacao";
	//public static final String SINCRONIZACAO_GERAL = "Sincronizacao";
	public static final String ITEM_NULL = "ItemNull";
	
	public static final String SINCRONIZACAO_DAO = "DaoSinc";
	public static final String SINCRONIZACAO_SINCRONIZADOR = "Sincronizacao";
	public static final String SINCRONIZACAO_JSON = "JsonSinc";
	
	public static final String MULTIMIDIA = "Multimidia";
	public static final String MODELO = "Modelo";
	public static final String DISPLAY = "Display";
	
	private static final String STACK = "Stack";
	
	public static final String CICLO_VIEW = "CicloView";
	public static final String ANIMACAO = "Animacao";
	
	public static final String CRIA_FRAGMENT = "CriaFragment";
	
	public static void ciclo(View classe, String metodo) {
		Log.d(CICLO_VIEW, classe.toString() + " " + metodo);
	}
	
	public static void d(String tag, Object classe, String mensagem) {
		Log.d(tag, classe.getClass().getSimpleName() + ":" + mensagem);
	}
	public static void dStack(String tag, Object classe, String mensagem, int niveis) {
		niveis += 5;
		String trace = "";
		for (int i=5;i<niveis;i++) {
			StackTraceElement ste = Thread.currentThread().getStackTrace()[i];
			trace += " " + ste;
		}
		d(tag,classe, mensagem + " ( " + trace + " ) ");
	}
	public static void dStack(String tag, Object classe, String mensagem) {
		dStack(tag,classe,mensagem,1);
		/*
		int niveis = 4;
		d(tag,classe,mensagem);
		for (int i=3;i<(niveis+3);i++) {
			StackTraceElement ste = Thread.currentThread().getStackTrace()[i];
			d(tag,classe," ---> " + ste);
		}
		*/
	}
	
	public static void e(String tag, Object classe, String mensagem) {
		Log.e(tag, classe.getClass().getSimpleName() + ":" + mensagem);
	}
	public static void e(String tag, Object classe, Exception e) {
		Log.e(tag, "Erro em " + classe.getClass().getSimpleName(), e);
		e.printStackTrace();
	}
	
	

}
