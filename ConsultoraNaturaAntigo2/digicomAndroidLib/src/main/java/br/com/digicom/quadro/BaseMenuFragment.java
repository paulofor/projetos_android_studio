package br.com.digicom.quadro;


import br.com.digicom.modelo.DCIObjetoDominio;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public abstract class BaseMenuFragment extends Fragment implements IFragment{
	
	private IFragment chamador;
	
	public void setChamador(IFragment chamador) {
		this.chamador = chamador;
	}
	protected IFragment getChamador() {
		return chamador;
	}
	
	protected OnButtonClick mCallback;
	 // The container Activity must implement this interface so the frag can deliver messages
    public interface OnButtonClick {
        public void onButtonClickMenu(String acao);
        public void onButtonClickLista(String acao, DCIObjetoDominio obj);
    }
    
    public void onStart() {
		super.onStart();
		setEscutadores();
	}
    
    protected abstract void setEscutadores();

    
    protected void setOnClick(int idBotao, final String palavraChaveMenu) {
    	Button btn = (Button) getActivity().findViewById(idBotao);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mCallback.onButtonClickMenu(palavraChaveMenu);
			}
		});
    }
    
   
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallback = (OnButtonClick) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
}
