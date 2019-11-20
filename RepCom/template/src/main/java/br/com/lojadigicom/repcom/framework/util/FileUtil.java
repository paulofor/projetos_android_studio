package br.com.lojadigicom.repcom.framework.util;

import android.app.Activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Paulo on 25/02/2016.
 */
public class FileUtil {

    public static void copiaArquivo() {
        String sourceFile = "/data/data/br.com.lojadigicom.naturarepcom/databases/repcom.db";
        String destFile = "/sdcard/teste/repcom.db";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[4096];
            int noOfBytes = 0;
            //System.out.println("Copying file using streams");
            while ((noOfBytes = fis.read(buffer)) != -1) {

                fos.write(buffer, 0, noOfBytes);
            }
            fis.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while copying file " + ioe);
        }
        finally {

        }
    }
}
