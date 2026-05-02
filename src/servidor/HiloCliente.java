package servidor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

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
                    new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true);

            nombre = entrada.readLine();
            System.out.println("[Servidor] " + nombre + " se ha unido al debate.");
            enviarATodos("" + nombre + " se ha unido al debate.");

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }
                System.out.println("[" + nombre + "]: " + mensaje);
                enviarATodos("[" + nombre + "]: " + mensaje);
            }

        } catch (IOException e) {
            System.out.println("[Servidor] " + nombre + " se desconectó inesperadamente.");
        } finally {

            listaClientes.remove(this);
            enviarATodos("" + nombre + " ha salido del debate.");
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

    public void enviarATodos(String mensaje) {
        for (HiloCliente cliente : listaClientes) {
            cliente.enviarMensaje(mensaje);
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
