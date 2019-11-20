package br.com.lojadigicom.coletorprecocliente.framework.storage;

import java.io.File;

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;


public class StorageUtil {

    private static long maiorEspaco = 0;
    private static File maiorArquivo = null;




    public static String getPathMaisEspaco() {
        File f = new File("/");
        pesquisaMaiorInterno(f, 0);
        return maiorArquivo.getAbsolutePath();
    }

    private static void pesquisaMaiorInterno(File dir, int nivel) {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File inFile : files) {
            if (inFile.isDirectory()) {
                // is directory
                long total = inFile.getTotalSpace();
                String nome = inFile.getAbsolutePath();
                if (nome.indexOf("dev") == -1 && nome.indexOf("proc") == -1 &&
                        nome.indexOf("sys") == -1 && nome.indexOf("data") == -1 && nome.indexOf("etc") == -1 &&
                        nome.indexOf("asec") == -1 && nome.indexOf("obb") == -1 && nome.indexOf("cache") == -1) {
                    //DCLog.d("PesquisaStorage", nome + ":" + total + "(" + maiorArquivo + ":" + maiorEspaco + ")");
                    DCLog.d(DCLog.STORAGE,StorageUtil.class,nome + ":" + total + "(" + maiorArquivo + ":" + maiorEspaco + ")");
                    if (total == 0) {
                        // Pesquisar subdiretorio
                        if (nivel <= 2)
                            pesquisaMaiorInterno(inFile, (nivel + 1));
                    } else {
                        if (total > maiorEspaco) {
                            maiorEspaco = total;
                            maiorArquivo = inFile;
                        }
                    }
                }
            }
        }

    }
}