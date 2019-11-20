package br.com.lojadigicom.naturarepcom;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.lojadigicom.naturarepcom.task.ClienteImportaAgendaAsyncTask;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public class ListaClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Importar contatos da agenda", Snackbar.LENGTH_LONG)
                        .setAction("Confirma", new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                DCLog.d(DCLog.DISPLAY, this, "Confirma snackbar");
                                importaAgenda();
                            }
                        }).show();
            }
        });
    }

    public void importaAgenda() {
        ClienteImportaAgendaAsyncTask task = new ClienteImportaAgendaAsyncTask();
        task.execute(this);
    }

}
