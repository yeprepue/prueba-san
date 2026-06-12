---
languages: ["java", "markdown", "yaml"]
---

# Prueba SAN Project Instructions

## Spell Check Configuration

This project uses Spanish domain terminology. The following words are intentional and should not be flagged as misspellings:

### Spanish Domain Terms
- Cliente (Customer)
- cliente (customer - lowercase)
- infraestructure (infrastructure - project-specific naming)
- prueba (test/proof - project name)
- Nombre (Name)
- nombre (name - lowercase)
- Conversiones (Conversions)
- Contrato (Contract)
- Producto (Product)
- segmento (segment)

## Code Quality Standards

1. **Stream API**: Use `.toList()` instead of `.collect(Collectors.toList())` (Java 16+)
2. **Null Safety**: Annotate nullable parameters with `@javax.annotation.Nullable`
3. **Clean Architecture**: Follow domain-driven design with clear separation of concerns:
   - `domain` - business logic and interfaces
   - `infraestructure` - technical implementations
   - `application` - use cases and orchestration

## Project Structure
- **Domain**: Pure business logic, no dependencies
- **Infrastructure**: Spring, JPA, Database adapters
- **Application**: Use cases and service orchestration
