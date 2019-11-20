package br.com.lojadigicom.coletorprecocliente.servico.filtro;

import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;

/**
 * Created by Paulo on 23/04/2016.
 */
public class InteresseProdutoFiltro implements IFiltro{

    private ProdutoCliente produtoCliente = null;
    private long nota = 0;

    public ProdutoCliente getProdutoCliente() {
        return produtoCliente;
    }

    public void setProdutoCliente(ProdutoCliente valor) {
        produtoCliente = valor;
    }

    public long getNota() {
        return nota;
    }
    public void setNota(long valor) {
        nota = valor;
    }
}
