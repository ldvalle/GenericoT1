package servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ftpSRV {
    private  static FTPClient ftpClient = new FTPClient();

    public boolean getConnection(String server, String usr, String pass){
        if(!ftpClient.isConnected()){
            try{
                ftpClient.connect(server, 21);
                ftpClient.login(usr, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            }catch (IOException ex){
                System.out.println("Error. No se abrió conexión FTP\n" + ex.getMessage());
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean sendFile(File file, String sDestino){
        String sNombreArchivo = file.getName();

        if(!ftpClient.isConnected()){
            System.out.println("Conexión FTP cerrada");
            System.out.println("Debe establecer primero la conexión");
            return false;
        }

        try{
            InputStream iStream = new FileInputStream(file);
            ftpClient.changeWorkingDirectory("/home/ldvalle/");
            OutputStream oStream = ftpClient.storeFileStream(sDestino);

            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = iStream.read(bytesIn)) != -1) {
                oStream.write(bytesIn, 0, read);
            }
            iStream.close();
            oStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (!completed) {
                System.out.println("El archivo " + sNombreArchivo + " No se pudo transferir FTP");
            }

        }catch (IOException ex){
            System.out.println("Error FTP con archivo " + sNombreArchivo + " : " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean getFile(File file, String sOrigen){
        String sNombreArchivo = file.getName();

        if(!ftpClient.isConnected()){
            System.out.println("Conexión FTP cerrada");
            System.out.println("Debe establecer primero la conexión");
            return  false;
        }

        try{
            ftpClient.changeWorkingDirectory("/home/ldvalle/");
            OutputStream oStream = new BufferedOutputStream(new FileOutputStream(file));
            InputStream iStream = ftpClient.retrieveFileStream(sOrigen);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = iStream.read(bytesArray)) != -1) {
                oStream.write(bytesArray, 0, bytesRead);
            }

            iStream.close();
            oStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (!completed) {
                System.out.println("El archivo " + sNombreArchivo + " No se pudo transferir FTP");
            }

        }catch (IOException ex){
            System.out.println("Error FTP con archivo " + sNombreArchivo + " : " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
