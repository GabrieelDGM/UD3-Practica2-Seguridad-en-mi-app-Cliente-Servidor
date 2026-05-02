# Ejercicio 3 – Cliente de debate
## ¿Qué se hizo?
Se creó la clase ClienteDebate.java. El cliente se conecta al servidor, envía su nombre, y a partir de ahí puede escribir mensajes. Para poder recibir mensajes de otros participantes mientras escribe, se usa un hilo secundario que escucha el socket en segundo plano.

## Pasos realizados
    • Se creó un Socket hacia localhost:5000.
    • Se crearon los flujos BufferedReader y PrintWriter.
    • Se envió el nombre del usuario al servidor.
    • Se creó un hilo receptor (setDaemon(true)) para leer mensajes entrantes.
    • El hilo principal lee el teclado y envía mensajes al servidor.

## Fragmento clave
Thread receptor = new Thread(() -> {
    String msg;
    while ((msg = entrada.readLine()) != null) {
        System.out.println(msg);
    }
});
receptor.setDaemon(true);
receptor.start();

## Clase principal
ClienteDebate.java — ubicada en src/cliente/


[Volver al README](../../README.md)