package cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import seguridad.CifradoAES;


public class ClienteDebate {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {

        System.out.println("=== CLIENTE DE DEBATE ===");
        System.out.print("Introduce tu nombre: ");
        Scanner teclado = new Scanner(System.in);
        String nombre = teclado.nextLine();

        try {
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado al servidor de debate.");

            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter salida = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()), true
            );

           
            salida.println(CifradoAES.encriptar(nombre));

            
            Thread receptor = new Thread(() -> {
                try {
                    String mensajeEncriptado;
                    while ((mensajeEncriptado = entrada.readLine()) != null) {
                        String mensajeDescifrado = CifradoAES.desencriptar(mensajeEncriptado);
                        System.out.println(mensajeDescifrado);
                    }
                } catch (IOException e) {
                    System.out.println("[Conexión cerrada por el servidor]");
                } catch (Exception e) {
                    System.out.println("[Error al descifrar mensaje]");
                }
            });
            receptor.setDaemon(true);
            receptor.start();

            // Leer teclado y enviar mensajes encriptados
            String linea;
            while ((linea = teclado.nextLine()) != null) {
                salida.println(CifradoAES.encriptar(linea));
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
        } catch (Exception e) {
            System.out.println("Error de cifrado: " + e.getMessage());
        }
    }
}