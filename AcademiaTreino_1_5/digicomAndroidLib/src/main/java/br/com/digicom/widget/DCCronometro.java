package br.com.digicom.widget;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.widget.Chronometer;
import br.com.digicom.log.DCLog;


public class DCCronometro extends Chronometer {

	private long inicio = 0 ;
	private long alarme = 0;
	private IDCCronometro listener = null;
	private boolean desligaAlarme = false;
	
	private int qtdeAlarme = 1;
	private int contaAlarme = 0;
	private Vibrator v = null;
	private int mLimiteSegundos = 0;
	
	public DCCronometro(Context context) {
		super(context);
		inicializa(context);
	}
	public DCCronometro(Context context, AttributeSet attr) {
		super(context, attr);
		inicializa(context);
	}
	public DCCronometro(Context context, AttributeSet attr, int defStyleAttr) {
		super(context, attr, defStyleAttr);
		inicializa(context);
	}
	
	
	
	private void inicializa(Context context) {
		if (!this.isInEditMode()) {
			this.v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		}
	}
	public void setListener(IDCCronometro valor) {
		listener = valor;
	}
	
	public void zeraRelogio() {
		super.stop();
		this.setText("00:00");
	}
	public void disparaRelogio() {
		this.setText("00:00");
		this.setBase(SystemClock.elapsedRealtime());
		this.start();
	}
	
	public void setTempoBeepSegundos(int limiteSegundos) {
		this.mLimiteSegundos = limiteSegundos;
		this.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer tempo) {
				verificaTempo(tempo);
			}
		});
	}
	
	public void verificaTempo(Chronometer tempo) {
		long segundos = (SystemClock.elapsedRealtime() - tempo.getBase()) / 1000;
		if (segundos == mLimiteSegundos) {
			v.vibrate(500);
			try {
				beep();
			} catch (InterruptedException e) {
				DCLog.e(DCLog.MULTIMIDIA, this, e);
			}
		}
	}
	
	
	private boolean beep() throws InterruptedException {
		AudioManager manager = (AudioManager)this.getContext().getSystemService(Context.AUDIO_SERVICE);
		int volume = manager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
	    manager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, 0);
		final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
		Thread.sleep(300);
	    tg.startTone(ToneGenerator.TONE_PROP_BEEP,2000);
	    Thread.sleep(100);
	    tg.startTone(ToneGenerator.TONE_PROP_BEEP,2000);
	    manager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
		return true;
	}
	
	
	/*
	private void setup() {
		this.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer tempo) {
				if (!desligaAlarme) {
					if ((SystemClock.elapsedRealtime() - inicio) >= alarme) {
						listener.OnChegouTempo();
						contaAlarme++;
						if (contaAlarme>=qtdeAlarme) desligaAlarme = true;
					}
				}
			}
		});
	}
	*/

	
	
}
