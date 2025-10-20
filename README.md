# BlackJack - Aplicación Android

Una aplicación completa de BlackJack desarrollada con Kotlin y Jetpack Compose, siguiendo la arquitectura MVVM y las directrices de Material Design 3.

## 📱 Características

- **Juego completo de BlackJack** con reglas estándar de casino
- **Interfaz moderna** usando Jetpack Compose y Material Design 3
- **Arquitectura MVVM** para separación de responsabilidades
- **Gestión de estado reactiva** con StateFlow
- **Animaciones fluidas** para mejorar la experiencia de usuario
- **Rotación de pantalla** completamente soportada
- **Sistema de apuestas** con gestión de saldo
- **Tema oscuro** inspirado en mesas de casino reales

## 🎮 Reglas del Juego

### Objetivo
Obtener una mano con un valor lo más cercano posible a 21 sin pasarse, y vencer al dealer.

### Valores de las Cartas
- **Números (2-10)**: Valor nominal
- **Figuras (J, Q, K)**: Valor de 10
- **As**: Valor de 11 o 1 (se ajusta automáticamente)

### Cómo Jugar
1. **Apostar**: Selecciona tu apuesta al inicio de cada ronda
2. **Cartas iniciales**: Recibes 2 cartas boca arriba, el dealer recibe 1 carta boca arriba y 1 boca abajo
3. **Tu turno**: 
   - **Pedir**: Solicita otra carta
   - **Plantarse**: Te quedas con tus cartas actuales
4. **Turno del dealer**: El dealer debe pedir carta hasta llegar a 17 o más
5. **Determinación del ganador**:
   - Si te pasas de 21, pierdes automáticamente
   - Si el dealer se pasa de 21, ganas
   - Si ninguno se pasa, gana quien tenga el valor más cercano a 21
   - **BlackJack** (As + carta de 10 con solo 2 cartas) paga 3:2

## 🏗️ Arquitectura

### Estructura del Proyecto

```
app/
├── src/main/java/com/example/blackjack/
│   ├── model/                 # Modelos de datos
│   │   ├── Card.kt           # Representa una carta
│   │   ├── Deck.kt           # Representa el mazo
│   │   ├── Hand.kt           # Representa una mano de cartas
│   │   └── GameState.kt      # Estado completo del juego
│   ├── viewmodel/             # Lógica de negocio
│   │   └── BlackJackViewModel.kt
│   ├── ui/                    # Componentes de UI
│   │   ├── components/
│   │   │   ├── CardView.kt   # Visualización de cartas
│   │   │   ├── GameArea.kt   # Áreas de juego
│   │   │   ├── GameControls.kt # Controles del juego
│   │   │   └── MessageDisplay.kt # Mensajes de estado
│   │   ├── theme/             # Tema y colores
│   │   │   ├── Color.kt
│   │   │   ├── Theme.kt
│   │   │   └── Type.kt
│   │   └── BlackJackGameScreen.kt # Pantalla principal
│   └── MainActivity.kt        # Actividad principal
```

### Patrón MVVM

- **Model**: Clases de datos (`Card`, `Deck`, `Hand`, `GameState`)
- **View**: Composables de Jetpack Compose
- **ViewModel**: `BlackJackViewModel` gestiona la lógica del juego y el estado

### Gestión de Estado

El estado del juego se gestiona usando `StateFlow` de Kotlin Coroutines:
- Estado inmutable y reactivo
- Actualizaciones automáticas de la UI
- Sobrevive a cambios de configuración (rotación)

## 🎨 Diseño y Tema

### Material Design 3
- Esquema de colores personalizado inspirado en mesas de casino
- Tipografía clara y legible
- Componentes Material 3 (Cards, Buttons, etc.)

### Colores Principales
- **Verde oscuro**: Fondo (simula mesa de casino)
- **Dorado**: Color primario
- **Blanco/Negro**: Texto y cartas

### Animaciones
- Entrada suave de cartas
- Transiciones de estado
- Efectos visuales para puntuaciones especiales (21, BlackJack)

## 🔧 Requisitos Técnicos

- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 34 (Android 14)
- **Kotlin**: 1.9.20
- **Compose BOM**: 2023.10.01
- **Gradle**: 8.2.0

## 📦 Dependencias Principales

```kotlin
- androidx.core:core-ktx:1.12.0
- androidx.lifecycle:lifecycle-runtime-ktx:2.6.2
- androidx.activity:activity-compose:1.8.1
- androidx.compose.ui:ui
- androidx.compose.material3:material3
- androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2
```

## 🚀 Compilación y Ejecución

### Requisitos Previos
- Android Studio Hedgehog o superior
- JDK 17 o superior
- Android SDK actualizado

### Pasos
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/gael-marquez/BlackJack.git
   ```

2. Abrir el proyecto en Android Studio

3. Sincronizar Gradle:
   - El proyecto descargará automáticamente todas las dependencias

4. Compilar y ejecutar:
   - Conecta un dispositivo Android o inicia un emulador
   - Haz clic en "Run" o presiona Shift+F10

### Compilación desde línea de comandos
```bash
# Compilar la APK de debug
./gradlew assembleDebug

# Compilar la APK de release
./gradlew assembleRelease

# Ejecutar tests
./gradlew test
```

## 🎯 Funcionalidades Implementadas

- [x] Modelos de datos (Card, Deck, Hand, GameState)
- [x] ViewModel con lógica completa del juego
- [x] Gestión de estado con StateFlow
- [x] Composables para visualización de cartas
- [x] Composables para áreas de juego (jugador/dealer)
- [x] Composables para controles del juego
- [x] Sistema de apuestas y gestión de saldo
- [x] Puntuación automática de manos
- [x] Manejo especial de Ases
- [x] Detección de BlackJack
- [x] Turno automático del dealer
- [x] Determinación de ganador
- [x] Animaciones de entrada de cartas
- [x] Efectos visuales para eventos especiales
- [x] Soporte para rotación de pantalla
- [x] Tema Material Design 3
- [x] Código comentado en español
- [x] README completo en español

## 📝 Notas de Implementación

### Manejo de Ases
El valor del As se ajusta automáticamente entre 11 y 1 según sea necesario para evitar pasarse de 21.

### Carta Oculta del Dealer
La segunda carta del dealer permanece boca abajo durante el turno del jugador y se revela cuando el jugador se planta o cuando el juego termina.

### Sistema de Apuestas
- Saldo inicial: $1000
- Apuestas disponibles: $10, $25, $50, $100, $250, $500
- BlackJack paga 3:2
- Victorias normales pagan 1:1
- En caso de empate se devuelve la apuesta

### Reinicio Automático
Si el jugador se queda sin fichas, el juego se reinicia automáticamente con $1000.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:
1. Haz fork del repositorio
2. Crea una rama para tu característica (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👨‍💻 Autor

Gael Marquez

## 🙏 Agradecimientos

- Google por Jetpack Compose y Material Design 3
- La comunidad de Android por los excelentes recursos y documentación