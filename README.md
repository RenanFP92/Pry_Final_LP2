MANUAL DE USUARIO BÁSICO
Sistema Web de Gestión de Inventario

📌 Requisitos Previos:
Tener instalado Java JDK 17 o superior.

Tener instalado MySQL Server.

Tener configurado el IDE Spring Tool Suite o Eclipse.

Tener acceso al repositorio del sistema:
https://github.com/RenanFP92/Pry_Final_LP2

📥 Instalación del Sistema:
*Clonar el proyecto desde GitHub con la siguiente linea de codigo:
```
  git clone https://github.com/RenanFP92/Pry_LP2.git
```
*Importar el proyecto en Spring Tool Suite o Eclipse.

*Crear la base de datos ejecutando el script SQL proporcionado en el proyecto (database.sql) en MySQL.

*Configurar el archivo application.properties con los datos de conexión de MySQL:
```
  spring.datasource.url=jdbc:mysql://localhost:3306/pry_final_lp2
  spring.datasource.username=TU_USUARIO
  spring.datasource.password=TU_CONTRASEÑA 
```  
*Ejecutar el proyecto desde el IDE con la siguiente linea de código:

 ``` mvn spring-boot:run ```

🚪 Acceso al Sistema:
Abrir el navegador e ingresar a:
```
  http://localhost:8080/
```
👨‍💼 Funcionalidades Principales:

1. Login de Usuario
Ingresar con el nombre de usuario y contraseña asignada.

***Solo usuarios con cuentas activas podrán ingresar.

2. Gestión de Productos
Agregar Producto: Registrar nuevo producto con datos como nombre, proveedor, stock, tipo, estado (Activo, Inactivo, Desconocido).

Editar Producto: Modificar datos de un producto existente.

Eliminar Producto: Quitar productos (según permisos).

Listar Productos: Ver listado de todos los productos registrados.

3. Gestión de Inventario
Registrar ingresos de productos al inventario.

Consultar movimientos de inventario.

4. Ventas y Boletas
Registrar ventas seleccionando productos y cantidades.

El sistema genera automáticamente la boleta electrónica.

Descargar la boleta electrónica en PDF (generada con JasperReports).

5. Estados de Productos
Marcar productos como Activo, Inactivo o Desconocido para tener mejor control de stock.

📝 Notas Adicionales:
Los roles de usuario permiten asignar distintos niveles de acceso.

Para cambiar la contraseña o el usuario, es necesario contactar al administrador del sistema.

Los reportes se generan automáticamente y pueden descargarse directamente desde la opción correspondiente.

📌 Contacto y Soporte:
Para soporte o consultas adicionales, contactar con el equipo de desarrollo o el coordinador del proyecto.

*Informe de este proyecto 

https://cibertecedu-my.sharepoint.com/:w:/g/personal/i202332634_cibertec_edu_pe/EVQdv48cFvhIiv7tqI2aSIgBeXSMPYXkipO0uQU9IiRjDw?e=cNFbGq

*Biblioteca de datos

https://cibertecedu-my.sharepoint.com/:x:/g/personal/i202332634_cibertec_edu_pe/EbrAAxdv4upCg4oEcKw72tEBedLzCNUR17bpPPWwOV9YJA?e=AMED4V

*Presentación PPT del proyecto

https://cibertecedu-my.sharepoint.com/:p:/g/personal/i202332634_cibertec_edu_pe/ESQUZ_ZXdm9Jkn_7yS-_018BxicIzZVOLFNyyQPzccHYWA?e=1a9jP5
