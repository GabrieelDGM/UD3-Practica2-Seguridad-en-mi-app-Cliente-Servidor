package servidor;

import java.io.*;
import java.net.*;
import java.util.List;
import seguridad.CifradoAES;

public class HiloCliente extends Thread {

    private Socket socket;
    private String nombre;
    private List<HiloCliente> listaClientes;
    private PrintWriter salida;

    public HiloCliente(Socket socket, List<HiloCliente> listaClientes) {
        this.socket = socket;
        this.listaClientes = listaClientes;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            salida = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()), true
            );

            
            String nombreEncriptado = entrada.readLine();
            nombre = CifradoAES.desencriptar(nombreEncriptado);

            System.out.println("[Servidor] " + nombre + " se ha unido al debate.");
            enviarATodos(encriptar(">>> " + nombre + " se ha unido al debate."));

          
            String mensajeEncriptado;
            while ((mensajeEncriptado = entrada.readLine()) != null) {
                String mensaje = CifradoAES.desencriptar(mensajeEncriptado);
                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
                System.out.println("[" + nombre + "]: " + mensaje);
                enviarATodos(encriptar("[" + nombre + "]: " + mensaje));
            }

        } catch (IOException e) {
            System.out.println("[Servidor] " + nombre + " se desconectó inesperadamente.");
        } catch (Exception e) {
            System.out.println("[Servidor] Error de cifrado: " + e.getMessage());
        } finally {
            listaClientes.remove(this);
            try {
                enviarATodos(encriptar(">>> " + nombre + " ha salido del debate."));
                socket.close();
            } catch (Exception e) {
                
            }
        }
    }

    
    private String encriptar(String mensaje) {
        try {
            return CifradoAES.encriptar(mensaje);
        } catch (Exception e) {
            return mensaje;
        }
    }

    public void enviarATodos(String mensajeEncriptado) {
        for (HiloCliente cliente : listaClientes) {
            cliente.enviarMensaje(mensajeEncriptado);
        }
    }

    public void enviarMensaje(String mensaje) {
        if (salida != null) {
            salida.println(mensaje);
        }
    }

    public String getNombre() {
        return nombre;
    }
}