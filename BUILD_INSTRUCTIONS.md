# Instrucciones de Compilación - BlackJack Android

## Requisitos Previos

### Software Necesario
1. **Android Studio** (Hedgehog 2023.1.1 o superior)
   - Descargar desde: https://developer.android.com/studio

2. **Java Development Kit (JDK) 17**
   - Incluido con Android Studio
   - O descargar desde: https://adoptium.net/

3. **Android SDK**
   - SDK 34 (Android 14)
   - SDK 24 como mínimo (Android 7.0)

### Componentes del SDK
- Android SDK Platform 34
- Android SDK Build-Tools 34.0.0
- Android SDK Platform-Tools
- Android SDK Tools

## Método 1: Compilación con Android Studio (Recomendado)

### Pasos:

1. **Abrir Android Studio**

2. **Importar Proyecto**
   - File → Open
   - Seleccionar la carpeta del proyecto `BlackJack`
   - Esperar a que Gradle sincronice (primera vez puede tardar varios minutos)

3. **Sincronizar Gradle**
   - Android Studio lo hará automáticamente
   - Si no: File → Sync Project with Gradle Files
   - Se descargarán todas las dependencias necesarias

4. **Compilar**
   - Build → Make Project (Ctrl+F9 / Cmd+F9)
   - O Build → Rebuild Project para compilación completa

5. **Ejecutar**
   - Run → Run 'app' (Shift+F10)
   - Seleccionar dispositivo o emulador
   - La app se instalará y ejecutará automáticamente

## Método 2: Compilación por Línea de Comandos

### En Linux/macOS:

```bash
# 1. Dar permisos de ejecución al wrapper
chmod +x gradlew

# 2. Compilar versión de debug
./gradlew assembleDebug

# 3. La APK estará en:
# app/build/outputs/apk/debug/app-debug.apk

# 4. Compilar versión de release
./gradlew assembleRelease

# 5. Instalar en dispositivo conectado
./gradlew installDebug
```

### En Windows:

```bash
# 1. Compilar versión de debug
gradlew.bat assembleDebug

# 2. La APK estará en:
# app\build\outputs\apk\debug\app-debug.apk

# 3. Compilar versión de release
gradlew.bat assembleRelease

# 4. Instalar en dispositivo conectado
gradlew.bat installDebug
```

## Método 3: Ejecutar en Emulador

### Crear Emulador:

1. En Android Studio: Tools → Device Manager
2. Click en "Create Device"
3. Seleccionar un dispositivo (ej: Pixel 6)
4. Seleccionar imagen del sistema (API 34 recomendado)
5. Click "Finish"

### Ejecutar en Emulador:

```bash
# Por línea de comandos
./gradlew installDebug

# O en Android Studio
# Run → Run 'app' → Seleccionar emulador
```

## Solución de Problemas Comunes

### Error: "SDK location not found"
**Solución:** Crear archivo `local.properties` con:
```properties
sdk.dir=/ruta/a/Android/Sdk
```

### Error: "Gradle sync failed"
**Solución:**
1. File → Invalidate Caches → Invalidate and Restart
2. Verificar conexión a Internet
3. Sincronizar de nuevo

### Error: "Kotlin not configured"
**Solución:** Android Studio lo configurará automáticamente durante la primera sincronización

### Error de memoria durante compilación
**Solución:** Editar `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

## Verificar Instalación

### Después de compilar exitosamente:

1. **Verificar APK generada:**
   ```bash
   ls -lh app/build/outputs/apk/debug/app-debug.apk
   ```

2. **Instalar en dispositivo:**
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

3. **Ver logs:**
   ```bash
   adb logcat | grep BlackJack
   ```

## Estructura de Archivos Generados

Después de compilar:
```
app/build/
├── outputs/
│   ├── apk/
│   │   ├── debug/
│   │   │   └── app-debug.apk
│   │   └── release/
│   │       └── app-release-unsigned.apk
│   └── logs/
└── intermediates/
```

## Limpieza del Proyecto

### Limpiar archivos de build:
```bash
./gradlew clean
```

### Limpiar completamente (incluye caché):
```bash
./gradlew clean
rm -rf .gradle/
rm -rf build/
rm -rf app/build/
```

## Comandos Útiles de Gradle

```bash
# Ver tareas disponibles
./gradlew tasks

# Ejecutar tests
./gradlew test

# Generar reporte de dependencias
./gradlew dependencies

# Verificar versión de Gradle
./gradlew --version

# Compilar con información detallada
./gradlew assembleDebug --info

# Compilar con stack trace
./gradlew assembleDebug --stacktrace
```

## Notas Importantes

1. **Primera compilación:** Puede tardar 5-10 minutos descargando dependencias
2. **Conexión a Internet:** Necesaria para descargar dependencias la primera vez
3. **Espacio en disco:** Requiere ~2GB para Android SDK y dependencias
4. **RAM:** Mínimo 8GB recomendado para Android Studio

## Soporte

Si encuentras problemas:
1. Verifica que tienes todas las dependencias instaladas
2. Revisa los logs de Gradle
3. Consulta la documentación oficial de Android: https://developer.android.com
4. Revisa el archivo `PROJECT_STATUS.md` para verificar el estado del proyecto

## Recursos Adicionales

- [Guía oficial de Android](https://developer.android.com/studio/build)
- [Documentación de Gradle](https://docs.gradle.org)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin](https://kotlinlang.org)
