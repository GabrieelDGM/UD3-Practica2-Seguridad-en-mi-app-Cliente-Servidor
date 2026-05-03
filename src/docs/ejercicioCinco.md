## Ejercicio 5 

# Esquema de seguridad basado en roles

# Roles propuestos para la app de debate

**ADMINISTRADOR**
Es el rol con más permisos. Se encarga de gestionar toda la aplicación.
Permisos:

Crear y eliminar salas de debate.
Añadir, editar y eliminar temas del ArrayList.
Expulsar usuarios de una sala.
Ver los logs de todos los debates.
Cambiar la duración del debate.
Asignar roles a otros usuarios.

**MODERADOR**
Gestiona el orden dentro de una sala de debate concreta.
Permisos:

Silenciar a un participante temporalmente.
Expulsar a un participante de la sala.
Cambiar el tema del debate en curso.
Ver quién está conectado en su sala.

**PARTICIPANTE** 
Es el rol por defecto de cualquier usuario que se conecta.
Permisos:

Conectarse a una sala de debate.
Enviar mensajes durante el debate.
Desconectarse voluntariamente.

**INVITADO** 
Puede ver el debate pero no participar en él.
Permisos:

Conectarse a una sala en modo lectura.
Ver los mensajes de los demás en tiempo real.
No puede enviar mensajes.

### Esquema visual
┌─────────────────────────────────────────────┐
│               APLICACIÓN DE DEBATE          │
├──────────────┬──────────────────────────────┤
│     ROL      │          PERMISOS            │
├──────────────┼──────────────────────────────┤
│ ADMINISTRADOR│ Todo                         │
│ MODERADOR    │ Gestionar sala               │
│ PARTICIPANTE │ Leer + Escribir              │
│ INVITADO     │ Solo leer                    │
└──────────────┴──────────────────────────────┘

# ¿Cómo se implementaría en el código?
En el servidor, al recibir la conexión de un cliente, se le asignaría un rol. Dependiendo del rol, el HiloCliente permitiría o bloquearía ciertas acciones:
java// Ejemplo de cómo se comprobaría el rol antes de enviar un mensaje
if (cliente.getRol().equals("INVITADO")) {
    cliente.enviarMensaje("No tienes permiso para enviar mensajes.");
    return;
}
Los roles se podrían almacenar en una base de datos o en un fichero de configuración, asociados al nombre de usuario o a un sistema de login con contraseña.


[Volver al README](../../README.md)
