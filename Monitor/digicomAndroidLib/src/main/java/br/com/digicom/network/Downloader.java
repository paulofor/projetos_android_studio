package br.com.digicom.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.os.Environment;
import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;

public class Downloader extends Thread{

	//private EventoAndroidServico eventoAndroidSrv = FabricaServico.getInstancia().getEventoAndroidServico(FabricaServico.TIPO_SQLITE);
	
	public static final String TAG = "TagDownload";
	
	private final String FTP_USER = "digicom";
	private final String FTP_SENHA = "t9p9k8x7";
	private final String FTP_SERVER = "ftp.digicom.kinghost.net";
	
	private String pathLocal = null;
	private String nomeArquivo = null;
	private String pathFtp = "/video";
	
	private IDownloaderFinalizacao callback = null;
	private DCIObjetoDominio objCallback = null;
	
	public void setCallbackFinalizacao(DCIObjetoDominio obj, IDownloaderFinalizacao callback) {
		objCallback = obj;
		this.callback = callback;
	}
	
	public void setPathLocal(String valor) {
		pathLocal = valor;
	}
	public void setNomeArquivo(String valor) {
		nomeArquivo = valor;
	}
	public void setPathFtp(String valor) {
		pathFtp = valor;
	}
	
	
	private void testarStorage() {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    // Something else is wrong. It may be one of many other states, but all we need
		    //  to know is we can neither read nor write
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	}
	
	
	
	
	public void run() {
		Log.d(TAG, this.getClass().getSimpleName() + ": run()");
		testarStorage();
		//registraRunThread();
		FTPClient ftpClient = new FTPClient();
		OutputStream output = null;
		try {
			Log.d(TAG, this.getClass().getSimpleName() + " Servidor: " + FTP_SERVER);
			ftpClient.connect(FTP_SERVER);
			Log.d(TAG, this.getClass().getSimpleName() + " User: " + FTP_USER);
			ftpClient.login(FTP_USER, FTP_SENHA);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			File arquivo = new File(pathLocal + File.separator + nomeArquivo);
			Log.d(TAG, this.getClass().getSimpleName() + " Arquivo: " + arquivo.getAbsolutePath());
			if (arquivo.exists()) {
				ftpClient.setRestartOffset(arquivo.length());
				Log.d(TAG, "Posicionando em : " + arquivo.length());
			}
            output = new FileOutputStream(arquivo, true);
            ftpClient.enterLocalPassiveMode();
            Log.d(TAG, this.getClass().getSimpleName() + ": Arquivo no servidor:" + pathFtp + File.separator + nomeArquivo);
            ftpClient.retrieveFile(pathFtp + File.separator + nomeArquivo, output);
            Log.d(TAG, this.getClass().getSimpleName() + ": retrieveFile() finalizado");
            output.close();
            Log.d(TAG, this.getClass().getSimpleName() + ": close() finalizado");
			ftpClient.logout();
			Log.d(TAG, this.getClass().getSimpleName() + ": logout() finalizado");
			ftpClient.disconnect();
			if (this.callback!=null)
				this.callback.onFinalizacaoDownload(objCallback);
			Log.d(TAG, this.getClass().getSimpleName() + ": disconnect() finalizado");
			//registraFinalThread();
		} catch (IOException err) {
			//registraErroThread();
			Log.e(TAG, this.getClass().getSimpleName() + ": " + err.getMessage());
		}
	}
	
	
	
	
}
