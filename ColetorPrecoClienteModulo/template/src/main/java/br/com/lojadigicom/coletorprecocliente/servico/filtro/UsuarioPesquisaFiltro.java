package br.com.lojadigicom.coletorprecocliente.servico.filtro;

import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;

/**
 * Created by Paulo on 24/02/16.
 */
public class UsuarioPesquisaFiltro implements  IFiltro{

    private int idNaturezaProduto;
    private int idUsuarioPesquisa;
    private NaturezaProduto item;

    public void setIdNaturezaProduto(int valor) {
        idNaturezaProduto = valor;
    }
    public int getIdNaturezaProduto() {
        return idNaturezaProduto;
    }

    public void setIdUsuarioPesquisa(int valor) {
        idUsuarioPesquisa = valor;
    }
    public int getIdUsuarioPesquisa() {
        return idUsuarioPesquisa;
    }

    public NaturezaProduto getNatureza() {
        return item;
    }
    public void setNatureza(NaturezaProduto val) {
        item = val;
    }
}
