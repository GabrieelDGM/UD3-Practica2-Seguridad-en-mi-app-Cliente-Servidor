package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDebate {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {

        System.out.println("(= CLIENTE DE DEBATE =)");
        System.out.print("Introduce tu nombre: ");
        Scanner teclado = new Scanner(System.in);
        String nombre = teclado.nextLine();

        try {

            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado al servidor de debate.");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true);

            salida.println(nombre);

            Thread receptor = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("[Conexión cerrada por el servidor]");
                }
            });
            receptor.setDaemon(true);
            receptor.start();

            String linea;
            while ((linea = teclado.nextLine()) != null) {
                salida.println(linea);
                if (linea.equalsIgnoreCase("salir")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Has salido del debate.");

        } catch (ConnectException e) {
            System.out.println("Error: No se pudo conectar al servidor. ¿Está encendido?");
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
