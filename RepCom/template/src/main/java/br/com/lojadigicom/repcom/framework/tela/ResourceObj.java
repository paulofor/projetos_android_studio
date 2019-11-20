package br.com.lojadigicom.repcom.framework.tela;

/**
 * Created by Paulo on 11/04/2016.
 */
public class ResourceObj {

    private int codigo;
    private String nome;

    public ResourceObj(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }


    private static final int sizeOfIntInHalfBytes = 8;
    private static final int numberOfBitsInAHalfByte = 4;
    private static final int halfByte = 0x0F;
    private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public String getHexa() {
        int dec = codigo;
        StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
        hexBuilder.setLength(sizeOfIntInHalfBytes);
        for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
            int j = dec & halfByte;
            hexBuilder.setCharAt(i, hexDigits[j]);
            dec >>= numberOfBitsInAHalfByte;
        }
        return "0x" + hexBuilder.toString();
    }

    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }

    public String getMensagemParaErro() {
        return nome + " ( " + getHexa() + " ) ";
    }
}
