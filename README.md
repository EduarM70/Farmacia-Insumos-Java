# Farmacia Insumos

Este proyecto es una aplicación para la gestión de insumos médicos, desarrollado utilizando Java y MySQL.

## Proceso de Instalación

Para instalar y ejecutar este proyecto en tu entorno local, sigue estos pasos:

1. **Clonar el Repositorio**

    ```bash
    git clone https://github.com/Javithor360/java-supplies
    cd java-supplies
    ```

2. **Configurar el Servidor**

    - Asegúrate de tener Payara 5.2022 instalado y configurado.
    - Despliega la aplicación en el servidor Payara.

3. **Configurar la Base de Datos**

    - Configura tu base de datos en el archivo `AppConnection.java` con las credenciales correctas.

4. **Ejecutar la Aplicación**

    - Inicia el servidor Payara.
    - Accede a la aplicación desde [http://localhost:8080/insumos-1.0-SNAPSHOT/faces/index.xhtml](http://localhost:8080/insumos-1.0-SNAPSHOT/faces/index.xhtml).

## Observaciones

- **Idioma**: El proyecto está programado y presentado en inglés por motivos de futura difusión y estándares generales.
- **Validaciones**: La aplicación incluye validaciones de formularios para asegurar la integridad de los datos.
- **Estilos**: Todos los formularios y tablas están estilizados utilizando Bootstrap a través de su CDN.
- **Integración**: La aplicación se integra con la base de datos MySQL para almacenar y recuperar los datos.
- **Web Service**: La aplicación consume un REST Web Service interno para obtener la información.

## Tecnologías Utilizadas

- Java
- MySQL
- Payara Server
- Bootstrap

## Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

