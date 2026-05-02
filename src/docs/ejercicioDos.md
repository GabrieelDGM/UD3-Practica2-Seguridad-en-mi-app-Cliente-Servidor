## Ejercicio 2 – Hilos para múltiples clientes

## ¿Qué se hizo?
Se creó la clase HiloCliente.java, que extiende Thread. Cada vez que un cliente se conecta, el servidor crea un nuevo HiloCliente y lo arranca. Así, varios clientes pueden estar conectados y debatiendo al mismo tiempo sin bloquearse entre sí.

**Pasos realizados**
    • Se creó HiloCliente extendiendo la clase Thread.
    • Se pasó el Socket y la lista de clientes al constructor.
    • En el método run(), se leen los mensajes del cliente y se retransmiten a todos.
    • Se usó Collections.synchronizedList para que la lista de clientes sea segura.
    • En el servidor, se añadió un bucle while(true) que acepta clientes y crea un hilo por cada uno.

## Fragmento clave
*HiloCliente hilo = new HiloCliente(socketCliente, clientes);
*clientes.add(hilo);
*hilo.start();`

## Clases implicadas
ServidorDebate.java y HiloCliente.java — ambas en src/servidor/

[Volver al README](../../README.md)