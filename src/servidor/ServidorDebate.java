package servidor;

import java.io.*;
import java.net.*;
import java.util.*;
import seguridad.CifradoAES;

public class ServidorDebate {

    private static final int PUERTO = 5000;
    private static final int TIEMPO_DEBATE = 60;

    private static ArrayList<String> temas = new ArrayList<>(Arrays.asList(
        "¿Debería reducirse la semana laboral a 4 días?",
        "¿Es la inteligencia artificial una amenaza para el empleo?",
        "¿Deberían los videojuegos considerarse un deporte?",
        "¿Es mejor vivir en el campo o en la ciudad?",
        "¿Debería ser obligatorio el servicio militar?",
        "¿Las redes sociales hacen más daño que bien?",
        "¿Debería prohibirse el uso del móvil en los institutos?"
    ));

    private static List<HiloCliente> clientes = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        String temaElegido = temas.get(random.nextInt(temas.size()));

        System.out.println("=== SERVIDOR DE DEBATE (AES activado) ===");
        System.out.println("Tema: " + temaElegido);
        System.out.println("Duración: " + TIEMPO_DEBATE + " segundos");
        System.out.println("Esperando conexiones en el puerto " + PUERTO + "...\n");

        ServerSocket serverSocket = new ServerSocket(PUERTO);

        // Hilo temporizador
        Thread temporizador = new Thread(() -> {
            try {
                Thread.sleep(TIEMPO_DEBATE * 1000L);
                System.out.println("\n[Servidor] ¡Tiempo agotado! El debate ha finalizado.");
                avisarFinDebate();
                serverSocket.close();
            } catch (InterruptedException | IOException e) {
                // Cierre normal
            }
        });
        temporizador.setDaemon(true);
        temporizador.start();

        try {
            while (true) {
                Socket socketCliente = serverSocket.accept();
                HiloCliente hilo = new HiloCliente(socketCliente, clientes);
                clientes.add(hilo);
                hilo.start();

                Thread.sleep(200);

            
                hilo.enviarMensaje(encriptar("=== BIENVENIDO AL DEBATE  ==="));
                hilo.enviarMensaje(encriptar("Tema: " + temaElegido));
                hilo.enviarMensaje(encriptar("Tienes " + TIEMPO_DEBATE + " segundos para debatir."));
                hilo.enviarMensaje(encriptar("Escribe 'salir' para desconectarte."));
                hilo.enviarMensaje(encriptar("-------------------------------------------"));
            }
        } catch (SocketException e) {
            System.out.println("[Servidor] Servidor cerrado correctamente.");
        } catch (InterruptedException e) {
            System.out.println("[Servidor] Interrumpido.");
        } finally {
            serverSocket.close();
        }
    }

    private static void avisarFinDebate() {
        synchronized (clientes) {
            for (HiloCliente cliente : clientes) {
                cliente.enviarMensaje(encriptar("-------------------------------------------"));
                cliente.enviarMensaje(encriptar("¡El debate ha finalizado! Gracias por participar."));
            }
        }
    }

    private static String encriptar(String mensaje) {
        try {
            return CifradoAES.encriptar(mensaje);
        } catch (Exception e) {
            return mensaje;
        }
    }
}