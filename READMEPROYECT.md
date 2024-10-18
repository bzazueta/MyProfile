

imagenes descargar de pexels
iconos descar de flat icons

1.-agregamos las carpetas o paquetes
2.-instalamos dependencias
3.-configuramos hilt
4.-crear clase que extiende o hereda de Application()
5.-agregar la clase al manifest application android:name=".MyProfile"
6.-empezamos por la capa de dominio
7.-creamos los modelos
8.-creamos la interface en la carpeta dominio en repository
9.-creamos la clase sellada resource para manejar el estado de nuestra respuesta Resource
10.-continuamos con capa de data implementamos los metodos que definimos en dominio en el repository
11.-data creamos el paquete repository
12.-creamos el paquete datasource y creamos las interfaces y e implementaciones que van dentro de esta carpeta
13.-creamos la interface service con el metodo de la api
14.-creamos el repositoryimpl
14.-nos pasamos a configurar la carpeta di para proveer las dependencias
15.-creamos dentro de la carpeta di el objeto networkmodule para poder hacer peticiones http con retrofit nos va a proveer retrofit para poder inyectarlo
16.-creamos dentro de la carpeta di el objeto remotedatamodule que nos proveera los metodos que de remotedatasource y authservice
17.-creamos los casos de usos para realizar la petcion al repositoryimpl
18.-creamos dentro de la carpeta di usecasemodule para porveer los casos de usos
18.-creamos dentro de la carpeta di repositorymodule para porveer los repositorysimpl

Recyclersviews

Drawables
Uso de iconos 

Mvvm
patron de diseño mvvm

Daguer Hilt 
Para inyección de dependencias

Retrofit
Para petciones http a servidores y api's

Gson
Para modelar  nuestras respuestas a formato json

Manejo de estados
Para la persistencia de datos en pantallas
Buenas practicas

Clase Resource
la clase resource nos sirve para recibir las respuestas de manera generica <T>
y manejar la respuesta del servidor.para evaluar si la respuesta en un succes regresar el modelo de datos. si es failure 
nos regresa una excepcion

Moshi
Nos permite modelar los objetos o respuestas del servidor a un formato json

Data store
Jetpack DataStore es una solución de almacenamiento de datos que te permite almacenar pares clave-valor
Datastore usa corrutinas y Flow de Kotlin para almacenar datos de manera asíncrona, coherente y transaccional

Coil
Biblioteca de carga de imágenes url para Android respaldada por Kotlin Coroutines
Coil realiza una serie de optimizaciones que incluyen almacenamiento en caché de memoria y disco, reducción de resolución de la imagen en la memoria, pausa/cancelación automática de solicitudes
