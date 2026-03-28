**Evidencia de Aprendizaje – Unidad 3**
**Contexto: Sistema de Gestión de Barbería**
**Descripción del contexto**

Se desarrolla un sistema para una barbería que permite a los clientes registrarse, seleccionar un profesional, elegir un servicio (visualizando su precio en pesos colombianos) y agendar una cita según la disponibilidad.

El sistema administra:

Horarios del establecimiento
Disponibilidad de los profesionales
Servicios ofrecidos (duración y precio)
Reservas de citas

_____________________________________
**1. Clases, subclases y relaciones**
**Clases principales**
Usuario
Profesional
Servicio
Cita
Barberia (Sistema)
**Subclase**
Cliente (hereda de Usuario)
**Relaciones**
Un Cliente agenda una o varias Citas
Un Profesional tiene múltiples Citas
Una Cita contiene un Servicio
La Barbería administra profesionales, servicios y citas

_________________________
**2. Patrones de diseño**
Singleton: Para la clase Barberia (única instancia del sistema)
Factory Method: Para crear servicios dinámicamente

_____________________________
**3. Programación funcional**

Uso de:

stream()
filter()
forEach()
collect()

_________________________
**4. Gestión de errores**

Uso de:

try-catch-finally
Validaciones de datos de entrada
Manejo de excepciones personalizadas