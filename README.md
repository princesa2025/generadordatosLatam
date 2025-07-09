# Generador de Datos Ficticios LATAM

Este proyecto genera usuarios ficticios (personas naturales y empresas) con datos aleatorios válidos por país LATAM (ej. Colombia, Perú, México), aplicando principios de Programación Orientada a Objetos, SOLID y patrones de diseño.

los datos generados se utilizan como entrada en pruebas automatizadas de búsqueda de vuelos en el sitio de LATAM Airlines.

---

## Funcionalidades

- Generación de datos únicos (nombre, ciudad, país, documento)
- Exportación automática a archivo CSV
- Lectura posterior de usuarios desde CSV para pruebas
- Pruebas UI integradas con Serenity + Cucumber
- Aplicación de principios SOLID y patrones (Factory, Singleton)
- Persistencia opcional en base de datos local (H2)

---

## Tecnologías utilizadas

- Java 17
- Maven
- JavaFaker
- Serenity BDD + Cucumber
- Base de datos H2
- Git / GitHub

