
package  br.com.lojadigicom.coletorprecocliente.framework.log;


import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;


public class DCLog {
	
	public static final String OBJETO_INTERNO = "ObjetoInterno";
	
	public static final String INICIALIZACAO_OBJETO_TELA = "InicializacaoObjetoTela";
	public static final String DATABASE_ADM = "DatabaseAdm"; // insercoes e updates na base de dados
	public static final String DATABASE_ERRO = "DatabaseAdm";
	public static final String DATABASE_ERRO_CORE = "DatabaseAdm"; // Erros no DigicomAndroidLib
	
	public static final String DATABASE_CRUD = "DatabaseCrud";
	
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
	public static final String ON_CLICK = "OnClick";
	
	public static final String SINCRONIZACAO_DAO = "DaoSinc";
	public static final String SINCRONIZACAO_SINCRONIZADOR = "Sincronizacao";
	public static final String SINCRONIZACAO_JSON = "JsonSinc";
	
	public static final String MULTIMIDIA = "Multimidia";
	public static final String MODELO = "Modelo";
	public static final String DISPLAY = "DisplayTela";
	
	private static final String STACK = "Stack";
	
	public static final String CICLO_VIEW = "CicloView";
	public static final String ANIMACAO = "Animacao";
	
	public static final String CRIA_FRAGMENT = "CriaFragment";
	public static final String STORAGE = "Storage";
	
	public static final String OPERACAO_DB_TELA = "OperacaoDbTela";
	
	public static void ciclo(View classe, String metodo) {
		Log.d(CICLO_VIEW, classe.toString() + " " + metodo);
	}
	
	public static void d(String tag, Object classe, String mensagem) {
		String msg = tag + " - " + classe.getClass().getSimpleName() + ":" + mensagem;
		salvaLog(msg);
		Log.d(tag, classe.getClass().getSimpleName() + ":" + mensagem);
	}
	public static void dStack(String tag, Object classe, String mensagem, int niveis) {
		niveis += 5;
		String trace = "";
		for (int i=5;i<niveis;i++) {
			StackTraceElement ste = Thread.currentThread().getStackTrace()[i];
			trace += " " + ste;
		}
		d(tag, classe, mensagem + " ( " + trace + " ) ");
	}
	public static void dStack(String tag, Object classe, String mensagem) {
		dStack(tag, classe, mensagem, 1);
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
		String msg = tag + " - " + classe.getClass().getSimpleName() + ":" + mensagem;
		salvaErro(msg);
		Log.e(tag, classe.getClass().getSimpleName() + ":" + mensagem);
	}
	public static void e(String tag, Object classe, Exception e) {
		salvaErro(tag,classe,e);
		Log.e(tag, "Erro em " + classe.getClass().getSimpleName(), e);
		e.printStackTrace();
	}

	private static void salvaLog(String mensagem) {
		//String dir = Environment.getExternalStorageDirectory() + "/log";
		BufferedWriter out = null;
		File Root = new File(Environment.getExternalStorageDirectory() + "/log");
		if(Root.canWrite()){
			File  LogFile = new File(Root, "Debug.log");
			try {
				FileWriter LogWriter = new FileWriter(LogFile, true);
				out = new BufferedWriter(LogWriter);
				Date date = new Date();
				out.write(String.valueOf(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "  "  + mensagem + "\n"));
				out.close();
			} catch (IOException e) {

		}
	}
	}

	private static void salvaErro(String mensagem) {
		//String dir = Environment.getExternalStorageDirectory() + "/log";
		BufferedWriter out = null;
		File Root = new File(Environment.getExternalStorageDirectory() + "/log");
		if(Root.canWrite()){
			File  LogFile = new File(Root, "Erro.log");
			try {
				FileWriter LogWriter = new FileWriter(LogFile, true);
				out = new BufferedWriter(LogWriter);
				Date date = new Date();
				out.write(String.valueOf(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "  "  + mensagem + "\n"));
				out.close();
			} catch (IOException e) {

			}
		}
	}


	private static void salvaErro(String tag, Object classe, Exception e) {
		//String dir = Environment.getExternalStorageDirectory() + "/log";
		BufferedWriter out = null;
		File Root = new File(Environment.getExternalStorageDirectory() + "/log");
		if(Root.canWrite()){

			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();

			File  LogFile = new File(Root, "Erro.log");
			try {
				FileWriter LogWriter = new FileWriter(LogFile, true);
				out = new BufferedWriter(LogWriter);
				Date date = new Date();

				out.write(String.valueOf(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() +
						e.getMessage() + "\n"  + exceptionAsString + "\n"));
				out.close();
			} catch (IOException eio) {

			}
		}
	}




}
