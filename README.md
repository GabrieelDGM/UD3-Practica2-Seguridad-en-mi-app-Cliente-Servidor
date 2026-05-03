# Aplicación de Debate – Cliente/Servidor con Sockets

## Rutas de documentacion del ejercicio
[EjercicioUno](src/docs/ejercicioUno.md)
[EjercicioDos](src/docs/ejercicioDos.md)
[EjercicioTres](src/docs/ejercicioTres.md)
[EjercicioCuatro](src/docs/ejercicioCuatro.md)
[EjercicioCinco](src/docs/ejercicioCinco.md)


## Descripción
Esta práctica amplía la aplicación de debate de la Práctica 1 añadiendo cifrado AES a todas las comunicaciones entre el cliente y el servidor. El objetivo es proteger la información transmitida para que no pueda ser interceptada y leída por terceros, usando cifrado simétrico de 128 bits.

## 📁 Estructura del proyecto
![Estructura](image.png)

## ⚙️ ¿Qué se ha añadido respecto a la Práctica 1?
Se ha creado una nueva clase CifradoAES.java con dos métodos principales:

encriptar() → cifra el mensaje antes de enviarlo por el socket.
desencriptar() → descifra el mensaje al recibirlo.

Tanto el cliente como el servidor usan esta clase para que toda la información que viaja por la red esté cifrada y sea ilegible para cualquier persona que intente interceptarla.

## ¿Cómo funciona el cifrado?
Cliente escribe mensaje
    → se cifra con AES
    → viaja por el socket (ilegible en la red)
    → el servidor lo recibe y descifra
    → el servidor lo vuelve a cifrar
    → lo envía a todos los clientes
    → cada cliente lo descifra y lo muestra por pantalla
## Roles de cliente y servidor
Servidor

Abre el puerto y espera conexiones.
Elige el tema del debate al azar.
Crea un hilo por cada cliente que se conecta.
Retransmite los mensajes cifrados a todos los demás.
Gestiona el temporizador y cierra el debate al finalizar.

Cliente

Se conecta al servidor con un nombre de usuario.
Envía todos los mensajes cifrados con AES.
Recibe los mensajes cifrados y los descifra antes de mostrarlos.
Puede desconectarse voluntariamente escribiendo salir.



## Librerías utilizadas para el cifrado

ClasePaquetePara qué se usaCipherjavax.cryptoRealiza el cifrado y descifrado AESSecretKeyjavax.cryptoRepresenta la clave secretaSecretKeySpecjavax.crypto.specCrea la clave a partir del textoBase64java.utilConvierte los bytes cifrados a texto para enviarlos por el socket


## Control de Excepciones

| Excepción                | Dónde ocurre | Por qué                                                             |
| ------------------------ | ------------ | ------------------------------------------------------------------- |
| **ConnectException**     | Cliente      | El servidor no está encendido cuando el cliente intenta conectarse. |
| **SocketException**      | Servidor     | El ServerSocket se cierra al acabar el tiempo del debate.           |
| **IOException**          | Ambos        | Errores generales de red o desconexiones inesperadas.               |
| **InterruptedException** | Hilos        | Un hilo se interrumpe durante `Thread.sleep`.                       |


## Comandos del cliente
Comando -> Cualquier texto / Accion -> Se envia mensaje al debate.

Comando -> Salir / Desconectar al cliente del debate.

# Notas

El servidor escucha en el puerto 5000. Asegúrate de que no está siendo usado por otra aplicación.
Para cambiar la duración del debate, modifica la constante TIEMPO_DEBATE en ServidorDebate.java.
El cliente y el servidor deben estar en la misma red para conectarse. Si quieres probarlo entre dos ordenadores distintos, cambia localhost por la IP del servidor en ClienteDebate.java.

# Aplicacion creada por:
### Gabriel David Gelviz Monterrey

