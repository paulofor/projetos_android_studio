package br.com.lojadigicom.coletorprecocliente.gcm;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.gcm.RegistrationIntentServiceBase;
import br.com.lojadigicom.coletorprecocliente.framework.storage.ExternalStorage;
import br.com.lojadigicom.coletorprecocliente.framework.storage.StorageUtil;
import br.com.lojadigicom.coletorprecocliente.framework.telefonia.Telefone;
import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;
import br.com.lojadigicom.coletorprecocliente.modelo.DispositivoUsuario;
import br.com.lojadigicom.coletorprecocliente.modelo.DispositivoUsuarioVo;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.DispositivoUsuarioMontador;
import br.com.lojadigicom.coletorprecocliente.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.NaturezaProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.OportunidadeDiaSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioPesquisaSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;

/**
 * Created by Paulo on 04/01/16.
 */
public class RegistrationIntentService extends RegistrationIntentServiceBase {


    @Override
    protected void alteraDispositivo(String token) {
        sincronizaUsuarioDispositivo();

        Uri uriDispositivo = DispositivoUsuarioContract.buildAll();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uriDispositivo, null, null, null, null);
        DispositivoUsuario dispositivoUsuario = null;
        if (cursor.moveToFirst()) {
            DispositivoUsuarioMontador montador = new DispositivoUsuarioMontador();
            dispositivoUsuario = (DispositivoUsuario) montador.getItem(cursor);
            dispositivoUsuario.setTokenGcm(token);
//            if (ExternalStorage.isAvailable()) {
//                dispositivoUsuario.setMicroSd(1);
//            } else {
//                dispositivoUsuario.setMicroSd(0);
//            }
            //String maisEspaco = StorageUtil.getPathMaisEspaco();
            //dispositivoUsuario.setMelhorPath(maisEspaco);
            dispositivoUsuario.setCodigoDispositivo(Telefone.getModelo(this));
            uriDispositivo = DispositivoUsuarioContract.buildDispositivoUsuarioUri(dispositivoUsuario.getIdObj());
            resolver.update(uriDispositivo, dispositivoUsuario.getContentValues(), null, null);
            DispositivoUsuarioSincronismo dispositivoUsuarioSincronismo = new DispositivoUsuarioSincronismo();
            dispositivoUsuarioSincronismo.sincroniza(this, true);

          } else {
            // Criou sozinho usuario e dispostivo

            /*// Criando novo dispositivo
            DispositivoUsuario dispositivo = new DispositivoUsuarioVo();
            dispositivoUsuario.setTokenGcm(token);
            dispositivoUsuario.setNumeroCelular(Telefone.getNumero(this));
            dispositivoUsuario.setMelhorPath(StorageUtil.getPathMaisEspaco());
            if (ExternalStorage.isAvailable()) {
                dispositivoUsuario.setMicroSd(1);
            } else {
                dispositivoUsuario.setMicroSd(0);
            }
            uriDispositivo = DispositivoUsuarioContract.buildInsereSinc();
            resolver.insert(uriDispositivo, dispositivo.getContentValues());*/
        }
        //sincronizacaoInicial();

    }

    private void sincronizacaoInicial() {
        Uri uriNatureza = NaturezaProdutoContract.buildAll();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uriNatureza, null, null, null, null);
        if (!cursor.moveToFirst()) {
            UsuarioPesquisaSincronismo usuarioPesquisaSincronismo = new UsuarioPesquisaSincronismo();
            usuarioPesquisaSincronismo.sincroniza(this,true,true, Sincronizador.getCodigoAplicacao());

            NaturezaProdutoSincronismo naturezaProdutoSincronismo = new NaturezaProdutoSincronismo();
            naturezaProdutoSincronismo.sincroniza(this,true, true, Sincronizador.getCodigoAplicacao());

            OportunidadeDiaSincronismo oportunidadeDiaSincronismo = new OportunidadeDiaSincronismo();
            oportunidadeDiaSincronismo.sincroniza(this,true, true, Sincronizador.getCodigoAplicacao());

            //FileUtil.copiaArquivo();
        }
    }

    private void sincronizaUsuarioDispositivo() {
        // Primeiro atualizar dispositivo para criar automaticamente o usuario.
        DispositivoUsuarioSincronismo dispositivoUsuario = new DispositivoUsuarioSincronismo();
        dispositivoUsuario.sincroniza(this,true, Sincronizador.getCodigoAplicacao());
        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(this,true, false, Sincronizador.getCodigoAplicacao());
    }
}
