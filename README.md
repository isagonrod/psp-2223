# psp-2223
Repositorio creado para guardar las actividades y proyectos de clase relacionados con la asignatura de PSP (Programación de Servicios y Procesos).

## Inicializar un repositorio GIT
1. Entrar en la carpeta que se quiera subir al repositorio
2. Hacer clic en el botón derecho del ratón
3. Pulsar la opción `Git Bash Here` (que abre un CMD o terminal)
4. Escribir:
<br>`$ git init -b master`
<br>`$ git status`
<br>`$ git remote add origin git@github.com:isagonrod/psp-2223.git`
<br>`$ git add .`
<br>`$ git commit -m "Initial commit"`
<br>`$ git status`
<br>`$ git push --set-upstream origin master`
<br>`$ git pull`
<br>`$ git fetch`</br>

## Crear clave pública-privada SSH
1. `$ ssh-keygen -o -t rsa -C "correo electrónico"`
2. Ponerle una contraseña cuando la pida (Enter passphrase (empty for no passphrase): **** | Enter same passphrase again: ****)
3. Ir a la carpeta .ssh
4. Abrir el archivo rsa y copiar el código
5. Ir a github > code > ssh > new key
6. Ponerle un título, pegar el código y guardar los cambios

## Clonar un repositorio con comandos
1. Ir a github > code > ssh
2. Copiar la dirección que aparece.
3. Abrir terminal (o PowerShell) y situarnos en la carpeta donde vamos a clonar el repositorio. Este paso también se puede hacer desde el terminal del propio IDE.
4. `$ git clone "dirección copiada de github"`
