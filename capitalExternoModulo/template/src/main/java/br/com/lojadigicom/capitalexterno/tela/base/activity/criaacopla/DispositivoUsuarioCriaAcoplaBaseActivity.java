
package br.com.lojadigicom.capitalexterno.tela.base.activity.criaacopla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import android.support.v7.app.AppCompatActivity;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.lojadigicom.capitalexterno.template.R;

public abstract class DispositivoUsuarioCriaAcoplaBaseActivity<DCIObjetoDominio> extends AppCompatActivity  {

	@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DCLog.d(DCLog.TRACE_DISPLAY,this,"");
       	//DCLog.d(DCLog.TRACE_DISPLAY,this,getClass().getSimpleName() + " (" + getLayoutResource().getNome() + ")" );
    }
    
    protected void carregaImagemCache(String urlImagem, ImageView viewImagem) {
        Glide.with(this)
                .load(urlImagem)
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewImagem);
    }
}