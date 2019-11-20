package lojadigicom.com.br.capitalexternomodulo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import br.com.lojadigicom.capitalexterno.framework.tela.DCActivityBase;
import br.com.lojadigicom.capitalexterno.framework.tela.ResourceObj;

public class BasicActivity extends DCActivityBase {

    private ViewGroup quadroAnimacao = null;
    private FloatingActionButton fab = null;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quadroAnimacao = (ViewGroup) findViewById(R.id.fragmentEdicao);
        quadroAnimacao.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mostraFormEntrada();
            }
        });
    }*/


    public void mostraFormEntrada() {
        Animation animacao = AnimationUtils.loadAnimation(this, R.anim.entrar_subindo);
        animacao.setDuration(600);
        animacao.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                quadroAnimacao.bringToFront();
                fab.setVisibility(View.INVISIBLE);
                quadroAnimacao.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        quadroAnimacao.startAnimation(animacao);
    }


    @Override
    protected void onCreateComplemento(Bundle savedInstanceState) {
        quadroAnimacao = (ViewGroup) findViewById(R.id.fragmentEdicao);
        quadroAnimacao.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mostraFormEntrada();
            }
        });
    }

    @Override
    protected ResourceObj getLayoutResource() {
        return new ResourceObj(R.layout.activity_basic,"R.layout.activity_basic");
    }
}

