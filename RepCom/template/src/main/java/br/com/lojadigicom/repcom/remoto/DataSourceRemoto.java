package br.com.lojadigicom.repcom.remoto;


public class DataSourceRemoto {

    public static final String SERVER = "digicom.kinghost.net";
    public static final String APLICACAO = "RepCom";

    //public final String SERVER = "10.0.2.2:52134";
    //public final String APLICACAO = null;


    public static String getServer() {
        return SERVER;
    }
    public static String getAplicacao() {
        return APLICACAO;
    }
}