package br.com.lojadigicom.coletorprecocliente.aplicacao;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;

/**
 * Created by Paulo on 18/06/2016.
 */
public class ContractApp extends AplicacaoContract{
    @Override
    public String getContentAuthority() {
        return "br.com.lojadigicom.coletorprecocliente";
    }
}
