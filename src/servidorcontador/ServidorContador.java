package servidorcontador;

import java.io.*;
import java.net.*;

/**
 * @author ernt
 */
public class ServidorContador {
    
    static final int PUERTO=5000;
    
    public static void main(String[] args) {
        inicializarServidor();
    }
    
    public static void inicializarServidor(){
        try {
            ServerSocket ss = new ServerSocket(PUERTO);
            Socket s = new Socket();
            System.out.println("Esperando conexión!!");
            
            s=ss.accept();
            
            System.out.println("Un cliente conectado!!");
            
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            DataOutputStream salida = new DataOutputStream(s.getOutputStream());
            
            String cadena = entrada.readUTF();
            int numVocales=0;
            int numConsonantes=0;
            
            for (int i = 0; i < cadena.length(); i++) {
                if (cadena.charAt(i)>='a' && cadena.charAt(i)<='z') {
                    switch (cadena.charAt(i)){
                        case 'a': case 'e': case 'i': case 'o': case 'u':
                            numVocales++;
                            break;
                        default:
                            numConsonantes++;
                    }
                }
            }
            
            salida.writeUTF("Numero de vocales: "+numVocales+", Numero de consonantes: "+numConsonantes+"\n");
            
            s.close();
            ss.close();
        } catch (Exception e) {
        }
    }
    
}
