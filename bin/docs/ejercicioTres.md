# Ejercicio 3
## Modificación del cliente y servidor para usar cifrado

## Objetivo
Integrar la clase CifradoAES en el cliente y el servidor para que toda la información que viaja por el socket esté cifrada.

## Cambios en el servidor - ServidorDebate.java
Se añadió un método privado encriptar() que cifra los mensajes antes de enviarlos a los clientes:
javaprivate static String encriptar(String mensaje) {
    try {
        return CifradoAES.encriptar(mensaje);
    } catch (Exception e) {
        return mensaje;
    }
}
Todos los mensajes del servidor (bienvenida, tema, fin de debate) ahora se envían cifrados:
javahilo.enviarMensaje(encriptar("=== BIENVENIDO AL DEBATE (conexión segura AES) ==="));
hilo.enviarMensaje(encriptar("Tema: " + temaElegido));

## Cambios en HiloCliente.java
El nombre del cliente llega cifrado y se descifra al recibirlo:
javaString nombreEncriptado = entrada.readLine();
nombre = CifradoAES.desencriptar(nombreEncriptado);
Los mensajes de los participantes también llegan cifrados y se descifran antes de retransmitirlos:
javaString mensaje = CifradoAES.desencriptar(mensajeEncriptado);
enviarATodos(encriptar("[" + nombre + "]: " + mensaje));

## Cambios en el cliente - ClienteDebate.java

El nombre se envía cifrado al servidor:
javasalida.println(CifradoAES.encriptar(nombre));
Los mensajes escritos por el usuario se cifran antes de enviarlos:
javasalida.println(CifradoAES.encriptar(linea));
Los mensajes recibidos del servidor se descifran antes de mostrarlos por pantalla:
javaString mensajeDescifrado = CifradoAES.desencriptar(mensajeEncriptado);
System.out.println(mensajeDescifrado);

## Resumen del flujo completo
Cliente escribe mensaje
    → se cifra con AES
    → se envía por el socket (ilegible en la red)
    → el servidor lo recibe y descifra
    → el servidor lo vuelve a cifrar
    → lo envía a todos los clientes
    → cada cliente lo descifra y lo muestra por pantalla


[Volver al README](../../README.md)