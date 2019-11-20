package br.com.lojadigicom.naturarepcom.task;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import br.com.lojadigicom.repcom.data.contract.ClienteContract;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.modelo.ClienteVo;

/**
 * Created by Paulo on 30/03/2016.
 */
public class ClienteImportaAgendaAsyncTask extends AsyncTask<Context, Void, Void> {


    @Override
    protected Void doInBackground(Context... params) {
        String lookupKey = null;
        String nome = null;
        String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection       = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = '1'";
        //List<Cliente> listaContato = new ArrayList<Cliente>();
        Context contexto = params[0];
        ContentResolver cr = contexto.getContentResolver();
        Cursor cursor = contexto.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, selection, null, null);
        while (cursor.moveToNext()) {
            //Cliente cliente = FabricaVo.criaCliente();
            //cliente.setContexto(contexto);
            try {
                lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY ));
                nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                DCLog.d(DCLog.DISPLAY,this,nome);
                boolean existe = existePorIdContato(contexto,lookupKey);
                if (!existe) {
                    // Insercao
                    Cliente cliente = new ClienteVo();
                    cliente.setNome(nome);
                    cliente.setCodigoListaContato(lookupKey);
                    Uri uriInsert = ClienteContract.buildInsereSinc();
                    contexto.getContentResolver().insert(uriInsert, cliente.getContentValues());
                }
                //String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //cliente.setCodigoListaContato(lookupKey);
                //cliente.setNome(nome);
                //listaContato.add(cliente);
            } catch (Exception e) {
                DCLog.e(DCLog.DISPLAY,this,e);
            }
        }
        return null ;
    }

    private boolean existePorIdContato(Context context, String idContato) {
        Uri uri = ClienteContract.getContentUri();
        String selection = "codigo_lista_contato = '" + idContato + "' ";

        Cursor cursor = context.getContentResolver().query(uri,null,selection,null,null);
        return cursor.moveToFirst();

    }
}
