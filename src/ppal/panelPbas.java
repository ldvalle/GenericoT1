package ppal;

import servicios.*;
import java.io.*;

public class panelPbas {

    public static void main(String[] args){

        pbaFTPsend();

        pbaFTPget();
    }

    private static void pbaFTPsend(){
        String sServer="10.240.0.102";
        String sUsr="ldvalle";
        String sPass="ncc1701";
        String sArchivoOrigen = "C:\\Users\\ar17031095.ENELINT\\Documents\\MAC\\i3_anom_tecni.xls";
        String sArchivoDestino = "i3_anom_tecni.xls";
        File   file=new File(sArchivoOrigen);

        ftpSRV miSrv = new ftpSRV();

        if(miSrv.getConnection(sServer, sUsr, sPass)) {
            System.out.println("Conexión abierta OK");
            if (!miSrv.sendFile(file, sArchivoDestino)) {
                System.out.println("No se envió el archivo");
            } else {
                System.out.println("Envio OK");
            }
        }else{
            System.out.println("No se pudo abrir la conexión FTP");
        }
    }

    private static void pbaFTPget(){
        String sServer="10.240.0.102";
        String sUsr="ldvalle";
        String sPass="ncc1701";
        String sArchivoDestino = "C:\\Users\\ar17031095.ENELINT\\Documents\\MAC\\inspec23.ini";
        String sArchivoOrigen = "inspec23.ini";
        File   file=new File(sArchivoDestino);

        ftpSRV miSrv = new ftpSRV();

        if(miSrv.getConnection(sServer, sUsr, sPass)) {
            System.out.println("Conexión abierta OK");
            if (!miSrv.getFile(file, sArchivoOrigen)){
                System.out.println("No se levanto el archivo");
            } else {
                System.out.println("Recibido OK");
            }
        }else{
            System.out.println("No se pudo abrir la conexión FTP");
        }
    }

}
