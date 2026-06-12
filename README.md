---
idiomas: ["java", "markdown", "yaml"]
---

# Instrucciones del proyecto Prueba SAN

## Configuración del corrector ortográfico

Este proyecto utiliza terminología propia del ámbito español. Las siguientes palabras se utilizan de forma intencionada y no deben marcarse como errores ortográficos:

### Términos propios del ámbito español
- Cliente (Customer)
- cliente (customer - minúscula)
- infraestructura (nomenclatura específica del proyecto)
- prueba (nombre del proyecto)
- Nombre
- nombre (minúscula)
- Conversiones
- Contrato
- Producto
- segmento

## Estándares de calidad del código

1. **API de flujos**: Utilice `.toList()` en lugar de `.collect(Collectors.toList())` (Java 16+)
2. **Seguridad ante valores nulos**: Anote los parámetros nulos con `@javax.annotation.Nullable`
3. **Arquitectura limpia**: Siga el diseño orientado al dominio con una clara separación de responsabilidades:
   - `domain` - lógica de negocio e interfaces
   - `infraestructura` - implementaciones técnicas
   - `aplicación` - casos de uso y orquestación

## Estructura del proyecto
- **Dominio**: Lógica de negocio pura, sin dependencias
- **Infraestructura**: Spring, JPA, adaptadores de base de datos
- **Aplicación**: Casos de uso y orquestación de servicios
