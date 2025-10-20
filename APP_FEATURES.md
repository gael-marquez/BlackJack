# Características de la Aplicación BlackJack

## Resumen Ejecutivo

Aplicación completa de BlackJack para Android desarrollada con las últimas tecnologías de Android:
- **Lenguaje:** Kotlin
- **UI Framework:** Jetpack Compose
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Diseño:** Material Design 3
- **Gestión de Estado:** StateFlow (Kotlin Coroutines)

## Pantallas de la Aplicación

### 1. Pantalla de Apuestas
```
┌─────────────────────────────────────┐
│         🃏 BlackJack 🃏            │
├─────────────────────────────────────┤
│                                     │
│           Dealer                    │
│        [Área vacía]                 │
│                                     │
├─────────────────────────────────────┤
│  ¡Bienvenido al BlackJack!         │
├─────────────────────────────────────┤
│                                     │
│        [Área vacía]                 │
│          Jugador                    │
│                                     │
├─────────────────────────────────────┤
│   Saldo: $1000                     │
│                                     │
│ Selecciona tu apuesta:             │
│  [$10] [$25] [$50]                │
│  [$100] [$250] [$500]             │
└─────────────────────────────────────┘
```

### 2. Turno del Jugador
```
┌─────────────────────────────────────┐
│         🃏 BlackJack 🃏            │
├─────────────────────────────────────┤
│           Dealer                    │
│            [17]                     │
│         [A♥] [🎴]                  │
│      (carta oculta)                 │
├─────────────────────────────────────┤
│  Tu turno. ¿Pedir carta o          │
│  plantarse?                         │
├─────────────────────────────────────┤
│         [K♠] [7♦]                  │
│            [17]                     │
│          Jugador                    │
├─────────────────────────────────────┤
│ Saldo: $950    Apuesta: $50        │
│                                     │
│   [  PEDIR  ]  [ PLANTARSE ]       │
└─────────────────────────────────────┘
```

### 3. Resultado - Victoria
```
┌─────────────────────────────────────┐
│         🃏 BlackJack 🃏            │
├─────────────────────────────────────┤
│           Dealer                    │
│            [23]                     │
│      [A♥] [K♣] [Q♦]               │
│        (Se pasó)                    │
├─────────────────────────────────────┤
│  ¡El dealer se pasó!               │
│  Ganaste $50 fichas                │
├─────────────────────────────────────┤
│         [K♠] [7♦]                  │
│            [17]                     │
│          Jugador                    │
├─────────────────────────────────────┤
│ Saldo: $1000   Apuesta: $50        │
│                                     │
│      ✨ ¡GANASTE! ✨               │
│     [  NUEVA RONDA  ]              │
└─────────────────────────────────────┘
```

### 4. BlackJack Natural
```
┌─────────────────────────────────────┐
│         🃏 BlackJack 🃏            │
├─────────────────────────────────────┤
│           Dealer                    │
│            [20]                     │
│         [K♥] [Q♣]                  │
├─────────────────────────────────────┤
│  ¡BlackJack! Ganaste $75 fichas    │
│         (Pago 3:2)                  │
├─────────────────────────────────────┤
│         [A♠] [K♦]                  │
│            [21]                     │
│          Jugador                    │
├─────────────────────────────────────┤
│ Saldo: $1075   Apuesta: $50        │
│                                     │
│     🏆 ¡BLACKJACK! 🏆             │
│     [  NUEVA RONDA  ]              │
└─────────────────────────────────────┘
```

## Características Técnicas Implementadas

### 🎮 Funcionalidades del Juego

#### Sistema de Apuestas
- ✅ Apuestas predefinidas: $10, $25, $50, $100, $250, $500
- ✅ Validación de saldo disponible
- ✅ Gestión automática de fondos
- ✅ Reinicio automático si el saldo llega a 0

#### Mecánicas de Juego
- ✅ Reparto inicial automático (2 cartas jugador, 2 dealer)
- ✅ Segunda carta del dealer oculta
- ✅ Botón "Pedir" para solicitar cartas adicionales
- ✅ Botón "Plantarse" para finalizar turno
- ✅ Turno automático del dealer (pide hasta 17+)
- ✅ Detección automática de BlackJack natural
- ✅ Detección de "busted" (>21)
- ✅ Cálculo automático de ganador

#### Reglas de BlackJack
- ✅ As vale 11 o 1 (ajuste automático)
- ✅ Figuras (J, Q, K) valen 10
- ✅ Números valen su valor nominal
- ✅ BlackJack paga 3:2
- ✅ Victoria normal paga 1:1
- ✅ Empate devuelve apuesta

### 🎨 Diseño y UI

#### Material Design 3
- ✅ Esquema de colores personalizado
- ✅ Componentes Material (Cards, Buttons, Text)
- ✅ Elevaciones y sombras
- ✅ Bordes redondeados

#### Tema Visual
- ✅ Fondo verde tipo mesa de casino
- ✅ Cartas con símbolos de palos (♥ ♦ ♣ ♠)
- ✅ Colores rojos y negros para palos
- ✅ Dorso de cartas con patrón
- ✅ Indicadores visuales de puntuación

#### Animaciones
- ✅ Entrada suave de cartas (spring animation)
- ✅ Transiciones de estado (fade in/out)
- ✅ Efecto de pulso para 21 puntos
- ✅ Animación de escala para botones
- ✅ Slide-in para mensajes

### 💻 Arquitectura y Código

#### MVVM Pattern
```
┌──────────────┐
│    View      │ ← Composables (UI)
│ (Compose)    │
└──────┬───────┘
       │ observes
       ↓
┌──────────────┐
│  ViewModel   │ ← BlackJackViewModel
│ (StateFlow)  │   (Lógica del juego)
└──────┬───────┘
       │ updates
       ↓
┌──────────────┐
│    Model     │ ← Card, Deck, Hand,
│  (Data)      │   GameState
└──────────────┘
```

#### Gestión de Estado
- ✅ StateFlow para estado reactivo
- ✅ Estado inmutable (data classes)
- ✅ Single source of truth
- ✅ Sobrevive rotación de pantalla
- ✅ No pierde datos en cambios de configuración

#### Calidad del Código
- ✅ 100% código en Kotlin
- ✅ Comentarios en español
- ✅ Documentación KDoc
- ✅ Separación de responsabilidades
- ✅ Código limpio y mantenible
- ✅ Reutilización de componentes

### 📱 Compatibilidad

#### Versiones de Android
- **Mínima:** Android 7.0 (API 24)
- **Target:** Android 14 (API 34)
- **Cobertura:** ~94% de dispositivos Android activos

#### Orientaciones
- ✅ Portrait (vertical)
- ✅ Landscape (horizontal)
- ✅ Scroll automático en pantallas pequeñas
- ✅ Layout responsivo

#### Tamaños de Pantalla
- ✅ Smartphones pequeños (4.0"+)
- ✅ Smartphones medianos (5.5"+)
- ✅ Smartphones grandes (6.5"+)
- ✅ Tablets (7"+ - con scroll)

## Estructura de Componentes

### Jerarquía de Composables
```
MainActivity
└── BlackJackTheme
    └── BlackJackGameScreen
        ├── DealerArea
        │   ├── Text (Dealer)
        │   ├── ScoreDisplay
        │   └── HandView
        │       └── CardView (x N)
        ├── MessageDisplay
        ├── PlayerArea
        │   ├── HandView
        │   │   └── CardView (x N)
        │   ├── ScoreDisplay
        │   └── Text (Jugador)
        └── GameControls
            ├── BalanceDisplay
            └── [Betting|Playing|GameOver]Controls
```

### Componentes Reutilizables
1. **CardView**: Muestra una carta individual
2. **HandView**: Muestra múltiples cartas con disposición en abanico
3. **ScoreDisplay**: Muestra puntuación con efectos especiales
4. **DealerArea**: Área completa del dealer
5. **PlayerArea**: Área completa del jugador
6. **GameControls**: Controles según fase del juego
7. **MessageDisplay**: Mensajes de estado con animación

## Flujo del Juego

```
┌─────────────┐
│   INICIO    │
└──────┬──────┘
       │
       ↓
┌─────────────┐     Apuesta < Saldo
│  APOSTANDO  │───────────────────┐
└──────┬──────┘                   │
       │ Apuesta válida           │
       ↓                          │
┌─────────────┐                   │
│  REPARTO    │                   │
│  INICIAL    │                   │
└──────┬──────┘                   │
       │                          │
       ├── BlackJack? ──→ FIN    │
       │                          │
       ↓ No                       │
┌─────────────┐                   │
│   TURNO     │                   │
│  JUGADOR    │                   │
└──────┬──────┘                   │
       │                          │
       ├── Pedir ──→ Carta        │
       │             │             │
       │             └─→ >21? ─→ FIN
       │                          │
       ├── Plantarse              │
       ↓                          │
┌─────────────┐                   │
│   TURNO     │                   │
│   DEALER    │                   │
└──────┬──────┘                   │
       │                          │
       ├── <17? ──→ Pedir carta   │
       │             │             │
       ↓             └──────┘     │
┌─────────────┐                   │
│  DETERMINAR │                   │
│  GANADOR    │                   │
└──────┬──────┘                   │
       │                          │
       ↓                          │
┌─────────────┐                   │
│ FIN RONDA   │                   │
└──────┬──────┘                   │
       │                          │
       └────────→ NUEVA RONDA ────┘
```

## Casos de Uso

### UC1: Realizar Apuesta
**Actor:** Jugador  
**Flujo:**
1. El jugador ve su saldo actual
2. Selecciona una cantidad de apuesta
3. El sistema valida que tiene saldo suficiente
4. Si es válida, inicia el juego
5. Si no, muestra mensaje de error

### UC2: Pedir Carta
**Actor:** Jugador  
**Flujo:**
1. El jugador hace clic en "PEDIR"
2. El sistema reparte una carta boca arriba
3. El sistema calcula el nuevo total
4. Si >21: fin del juego, jugador pierde
5. Si ≤21: jugador puede seguir pidiendo

### UC3: Plantarse
**Actor:** Jugador  
**Flujo:**
1. El jugador hace clic en "PLANTARSE"
2. El sistema revela la carta oculta del dealer
3. El dealer pide cartas hasta llegar a 17+
4. El sistema determina el ganador
5. Actualiza el saldo del jugador
6. Muestra el resultado

### UC4: Nueva Ronda
**Actor:** Jugador  
**Flujo:**
1. El jugador hace clic en "NUEVA RONDA"
2. El sistema limpia las cartas anteriores
3. Vuelve a la fase de apuestas
4. Si saldo = 0, reinicia con $1000

## Mensajes del Sistema

### Mensajes de Estado
- "¡Bienvenido al BlackJack!"
- "Haz tu apuesta para la siguiente ronda"
- "Tu turno. ¿Pedir carta o plantarse?"
- "Tienes {valor}. ¿Pedir otra carta?"
- "Turno del dealer..."

### Mensajes de Resultado
- "¡BlackJack! Ganaste {cantidad} fichas"
- "¡Ganaste! {jugador} vs {dealer}. +{cantidad} fichas"
- "Perdiste. {jugador} vs {dealer}. -{cantidad} fichas"
- "¡Empate! {valor} vs {valor}. Recuperas tu apuesta"
- "¡Te pasaste! Perdiste {cantidad} fichas"
- "¡El dealer se pasó! Ganaste {cantidad} fichas"

### Mensajes de Error
- "¡Saldo insuficiente!"
- "¡La apuesta debe ser mayor a 0!"
- "¡Te quedaste sin fichas! El juego se reiniciará"

## Próximas Mejoras Sugeridas

### Funcionalidades Adicionales
- [ ] Doblar apuesta (Double Down)
- [ ] Dividir pares (Split)
- [ ] Seguro contra BlackJack del dealer
- [ ] Historial de manos jugadas
- [ ] Estadísticas del jugador
- [ ] Múltiples mazos
- [ ] Sonidos y efectos de audio
- [ ] Vibración en eventos importantes
- [ ] Modo multijugador
- [ ] Logros y recompensas

### Mejoras de UI/UX
- [ ] Más animaciones
- [ ] Efectos de partículas
- [ ] Temas personalizables
- [ ] Avatares de jugador
- [ ] Tutorial interactivo
- [ ] Modo nocturno automático
- [ ] Soporte para tablets optimizado

### Técnicas
- [ ] Tests unitarios
- [ ] Tests de UI
- [ ] Tests de integración
- [ ] Persistencia de datos (Room)
- [ ] Análisis con Firebase
- [ ] Crash reporting
- [ ] Optimización de rendimiento
- [ ] Reducción de tamaño de APK

## Conclusión

Esta aplicación representa una implementación completa y profesional de BlackJack usando las mejores prácticas de desarrollo Android moderno. El código es limpio, mantenible y extensible, siguiendo los principios SOLID y la arquitectura MVVM recomendada por Google.
