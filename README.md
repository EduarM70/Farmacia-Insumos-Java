# Farmacia Insumos

Este proyecto es una aplicación para la gestión de insumos médicos, desarrollado utilizando Java y MySQL.

## Proceso de Instalación

Para instalar y ejecutar este proyecto en tu entorno local, sigue estos pasos:

1. **Clonar el Repositorio**

    ```bash
    git clone https://github.com/EduarM70/Farmacia-Insumos-Java
    cd Farmacia-Insumos-Java
    ```

2. **Configuración de los Servidores**

    - Asegúrate de tener Payara 6.17.0 y Tomcat 10.1.20 instalado y configurado.
    - Recuerda configurar ambos servidores en diferentes puertos, para evitar conflictos

3. **Configurar la Base de Datos**

    - Crea la base de datos en tu proveedor de MySQL, con el nombre de `farmacia_dwf` e importa el archivo que aparece dentro del repositorio `db_famarcia.sql`
    - Configura tu base de datos en el archivo `Connection.java` con las credenciales correspondientes

4. **Ejecutar el Servidor WS y la Aplicación Cliente**

    - Inicia el servidor Payara para la aplicación Cliente e inicia el Servidor de Tomcat para el Web Service Rest.
    - Accede a la aplicación desde [http://localhost:8080/insumos-1.0-SNAPSHOT/faces/categoriasApi.xhtml](http://localhost:8080/insumos-1.0-SNAPSHOT/faces/categoriasApi.xhtml). 


## Tecnologías Utilizadas

- Java
- MySQL
- Payara Server
- Tomcat Server
- Bootstrap

## Enlace del Video de Funcionalidad

[![Video demostrativo](Recursos/demostracion.png)]([https://www.youtube.com/watch?v=ID_DEL_VIDEO](https://youtu.be/P_t14aB0rCE))

## Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

