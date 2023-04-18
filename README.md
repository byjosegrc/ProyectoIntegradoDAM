 <h1 align="center"><b> PROYETO INTEGRADO DAM</b><img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="35"></h1>
<h1 align="center"><b> INFO PHOTO</b></h1>
<br>

## <picture><img src = "https://github.com/0xAbdulKhalid/0xAbdulKhalid/raw/main/assets/mdImages/about_me.gif" width = 50px></picture> **INSTALACIÓN PROYECTO**

<picture> <img align="right" src="https://github.com/0xAbdulKhalid/0xAbdulKhalid/raw/main/assets/mdImages/Right_Side.gif" width = 250px></picture>

<br>


## Para montar el proyecto seguiremos los siguientes pasos:

### 1 Para empezar, será necesario tener creado un proyecto en firebase.

### 2 Agrega un proyecto con el nombre que quieras. Una vez creado entra en autentication -> Sign-in method y habilita el login por Google.

<br>

### 3. Tras esto configurar las reglas de Realtime y Storage en firebase

#### 3.1 En reglas de realtime firebase:
#### read": "auth.uid!=null" y ".write": "auth.uid!=null"

#### 3.2 En reglas de firebase storage: 
#### rules_version = '2'; service firebase.storage { match /b/{bucket}/o { match /{allPaths=**} { allow read, write; } } }

#### "Todas estas reglas son públicas para poner utilizarla sin preocupaciones, en caso de querer sacarla a producción deberán ser cambiadas"


### 4 Borra el archivo app -> Google-services.json

### 5 Después, en nuestro proyecto ya creado deberemos ir a Tools -> Firebase y sincronizamos con Authentication, Realtime Database y Cloud storage for Firebase.

### 6 Una vez hecho ejecutamos en el gradle el signingreport.

### 7 Y copiamos la clave SHA1.
<br>

### 8 De vuelta en firebase vamos a configuración del proyecto.

### 9 Y aquí agregamos nuestra SHA1.

### 10 Ya lo único que quedaría seria ir por el código y cambiar aquellas rutas del storage y del realtime database por tus propias rutas y ya estaría listo para buildear el proyecto.

<br><br>


<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif"><br><br>

## <img src="https://media2.giphy.com/media/QssGEmpkyEOhBCb7e1/giphy.gif?cid=ecf05e47a0n3gi1bfqntqmob8g9aid1oyj2wr3ds3mg700bl&rid=giphy.gif" width ="25"><b>  LENGUAJES Y TEGNOLOGIAS USADAS</b>
### TEGNOLOGIAS:
#### ANDROID STUDIO -> PARA DESAROLLAR LA APP EN CODIGO USANDO KOTLIN COMO LENGUAJE PRINCIPAL
#### FIREBASE -> PARA LA AUTENTICACIÓN DE GOOGLE PARA ACCEDER A LA APP Y TAMBIEN EL ALOJAMIENTO DE LAS BASE DE DATOS DE REALTIME Y STORAGE
<br>

### LEGUAJE DE PROGRAMACION:
#### KOTLIN
<br>
## Proyecto Integrado creado por José Manuel Garcia Travé
