# Funcionamiento
# Pasos para probarlo
1. Abrir una terminal, compilar y ejecutar el servidor:
cd src/servidor
javac ServidorDebate.java HiloCliente.java
java ServidorDebate

2. Abrir una o más terminales, compilar y ejecutar el cliente:
cd src/cliente
javac ClienteDebate.java
java ClienteDebate

# Resultado esperado
    • El servidor muestra el tema elegido al azar y los clientes que se van conectando.
    • Cada cliente recibe el tema y los mensajes de todos los participantes en tiempo real.
    • Al escribir 'salir', el cliente se desconecta y el resto recibe un aviso.
    • A los 60 segundos, todos los clientes reciben el mensaje de fin de debate y se desconectan.

[Volver al README](../../README.md)