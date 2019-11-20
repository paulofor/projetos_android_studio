package br.com.digicom.componente;

import android.os.SystemClock;
import br.com.digicom.widget.IDCCronometro;

public interface DCICronometro {

	
	void setListener(IDCCronometro valor);
	void zera();
	void start();
	//void setTempoAlarme(int segundos, int qtdeToques);
	
}
