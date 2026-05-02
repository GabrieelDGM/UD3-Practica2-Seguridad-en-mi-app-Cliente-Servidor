# Ejercicio Uno.

## ¿Qué se hizo?
Se creó la clase ServidorDebate.java con la estructura básica de un servidor TCP. En este primer paso, el servidor solo abre un puerto y acepta una conexión de un cliente, sin hilos ni temporizador.

## Pasos realizados
    • Se importaron las librerías java.net y java.io.
    • Se definió el puerto 5000 como constante.
    • Se creó un ArrayList con los temas del debate.
    • Se usó ServerSocket para abrir el servidor.
    • Se llamó a serverSocket.accept() para esperar al primer cliente.

## Fragmento clave

`ServerSocket serverSocket = new ServerSocket(PUERTO);`
`Socket socketCliente = serverSocket.accept();`

## Clase principal
ServidorDebate.java — ubicada en src/servidor/

[Volver al README](../../README.md)