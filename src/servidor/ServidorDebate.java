package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ServidorDebate {

    private static final int PUERTO = 5000;
    private static final int TIEMPO_DEBATE = 100;

    private static ArrayList<String> temas = new ArrayList<>(Arrays.asList(
        "¿Qué selección ganará el Mundial 2026?",
        "¿Qué es para ti la libertad?",
        "¿Qué equipo es mejor, el Barcelona o el Real Madrid?",
        "¿Qué opinas de la guerra?"
    ));
    private static List<HiloCliente> clientes = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        // Escoger tema al azar
        Random random = new Random();
        String temaElegido = temas.get(random.nextInt(temas.size()));

        System.out.println("!SERVIDOR DE DEBATE!");
        System.out.println("Tema del debate: " + temaElegido);
        System.out.println("Duración: " + TIEMPO_DEBATE + " segundos");
        System.out.println("Esperando conexiones en el puerto " + PUERTO + "...\n");

        ServerSocket serverSocket = new ServerSocket(PUERTO);

        Thread temporizador = new Thread(() -> {
            try {
                Thread.sleep(TIEMPO_DEBATE * 1000L);
                System.out.println("\n[Servidor] ¡Tiempo agotado! El debate ha finalizado.");
                avisarFinDebate();
                serverSocket.close();
            } catch (InterruptedException | IOException e) {

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

                Thread.sleep(500);
                hilo.enviarMensaje("(= BIENVENIDO AL DEBATE =)");
                hilo.enviarMensaje("Tema: " + temaElegido);
                hilo.enviarMensaje("Tienes " + TIEMPO_DEBATE + " segundos para debatir.");
                hilo.enviarMensaje("Escribe 'salir' para desconectarte.");
                hilo.enviarMensaje("-------------------------------------------");
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
                cliente.enviarMensaje("-------------------------------------------");
                cliente.enviarMensaje("¡El debate ha finalizado! Gracias por participar.");
            }
        }
    }
}
