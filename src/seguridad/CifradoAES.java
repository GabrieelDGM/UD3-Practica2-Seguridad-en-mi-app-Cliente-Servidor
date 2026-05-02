package seguridad;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class CifradoAES {

   
    private static final String ALGORITMO = "AES";

  
    private static final String CLAVE_SECRETA = "ClaveDebate12345";

    private static SecretKey obtenerClave() {
        byte[] claveBytes = CLAVE_SECRETA.getBytes();
        return new SecretKeySpec(claveBytes, ALGORITMO);
    }

   
    public static String encriptar(String mensaje) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, obtenerClave());
        byte[] mensajeEncriptado = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(mensajeEncriptado);
    }


     

    public static String desencriptar(String mensajeEncriptado) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, obtenerClave());
        byte[] mensajeDecodificado = Base64.getDecoder().decode(mensajeEncriptado);
        byte[] mensajeOriginal = cipher.doFinal(mensajeDecodificado);
        return new String(mensajeOriginal);
    }
}