package br.com.digicom.multimidia;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
import br.com.digicom.DigicomException;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;

public class DigicomAudioPlayer implements  OnCompletionListener, OnErrorListener, OnPreparedListener{

	public static int getVolume(DigicomContexto ctx) {
		AudioManager am = (AudioManager) ctx.getContext().getSystemService(Context.AUDIO_SERVICE);
		int volume_level= am.getStreamVolume(AudioManager.STREAM_MUSIC);
		return volume_level;
	}
	public static void setVolume(DigicomContexto ctx, int nivel) {
		
	}
	public static void setControleVolume(DigicomContexto ctx) {
		ctx.getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
	
	private IFragment quadroCorrente;
	private AudioRecurso recursoCorrente;
	private MediaPlayer mediaPlayer = null;
	public void reproduzAudioRaw(DigicomContexto ctx, AudioRecurso recurso) {
		try {
			mediaPlayer = MediaPlayer.create(ctx.getContext(), recurso.getRecurso());
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnErrorListener(this);
			
			
			quadroCorrente = ctx.getQuadro();
			recursoCorrente = recurso;
			Log.d("DigicomLib", "Executando audio" + recurso.getTipoAudio());
			mediaPlayer.start();
		} catch (Exception e) {
			Log.d("DigicomLib", "Erro: " + e.getMessage());
			DCIObjetoDominio objErro = recurso.getObjeto();
			throw new DigicomException("Objeto classe:" + objErro.getNomeClasse() + " - Id:" + objErro.getId() + " - Recurso: " + recurso.getRecurso());
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.d("DigicomLib", "Concluindo audio" + (recursoCorrente!=null?recursoCorrente.getTipoAudio():"Recruso = null"));
		mp.release();
		mp = null;
		quadroCorrente.audioRawConcluido(recursoCorrente);
	}

	@Override
	public boolean onError(MediaPlayer mp, int arg1, int arg2) {
		Log.d("DigicomLib", "Concluindo erro audio" + (recursoCorrente!=null?recursoCorrente.getTipoAudio():"Recruso = null"));
		mp.release();
		quadroCorrente.audioRawConcluido(recursoCorrente);
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		Log.d("DigicomLib", "Prepared audio ");
		
	}

}
