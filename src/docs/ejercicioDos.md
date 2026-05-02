# Ejercicio 2

## Clase de encriptación y desencriptación-AES


## AES
es un algoritmo de cifrado simétrico, lo que significa que se usa la misma clave tanto para cifrar como para descifrar la información. Es uno de los algoritmos más usados y seguros en la actualidad.
En nuestra aplicación, tanto el cliente como el servidor conocen la misma clave secreta, que usan para cifrar los mensajes antes de enviarlos y descifrarlos al recibirlos.

    • Clase creada: CifradoAES.java
    • Ubicación: src/seguridad/CifradoAES.java
   
## Clave secreta
private static final String CLAVE_SECRETA = "ClaveDebate12345";
Es una cadena de 16 caracteres (128 bits), que es el tamaño mínimo que requiere AES. En una aplicación real esta clave no estaría escrita en el código, sino que se gestionaría de forma segura (por ejemplo, con un fichero de configuración cifrado o un gestor de secretos).

## Método encriptar
public static String encriptar(String mensaje) throws Exception
Recibe un texto plano y devuelve el mensaje cifrado en formato Base64, que es una representación en texto de los bytes cifrados. 

## Método encriptar
public static String desencriptar(String mensajeEncriptado) throws Exception
Recibe un mensaje cifrado en Base64 y devuelve el texto original. Hace el proceso inverso al método encriptar.

## Librerías utilizadas

ClasePaquetePara qué se usaCipherjavax.cryptoRealiza el cifrado y descifrado AESSecretKeyjavax.cryptoRepresenta la clave secretaSecretKeySpecjavax.crypto.specCrea la clave a partir de los bytes del textoBase64java.utilConvierte los bytes cifrados a texto y viceversa

## Flujo del cifrado
Mensaje original → encriptar() → Bytes cifrados → Base64 → Texto cifrado (se envía por el socket)
Texto cifrado (llega por el socket) → Base64 → Bytes cifrados → desencriptar() → Mensaje original


[Volver al README](../../README.md)