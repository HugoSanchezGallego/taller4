# Taller4

[Repositorio](https://github.com/HugoSanchezGallego/taller4.git)

## Descripción

Taller4 es una aplicación de diario personal desarrollada en Kotlin para Android. La aplicación permite a los usuarios agregar, ver y eliminar entradas de diario. Además, la aplicación incluye un widget que muestra una entrada aleatoria del diario junto con una frase motivacional. El fondo de la aplicación cambia a un color aleatorio cuando se agita el móvil.

## Características

- **Agregar Entradas**: Los usuarios pueden agregar nuevas entradas al diario.
- **Ver Detalles de Entradas**: Los usuarios pueden ver los detalles de cada entrada.
- **Eliminar Entradas**: Los usuarios pueden eliminar entradas del diario.
- **Sensor de Movimiento**: El fondo de la aplicación cambia a un color random cuando se agita el móvil.
- **Widget**: Un widget que muestra una entrada aleatoria del diario y una frase motivacional. El widget se actualiza al presionar un botón.

## Estructura del Proyecto

### Actividades

- **`MainActivity.kt`**: La actividad principal que muestra un saludo basado en la hora del día y un botón para ir al diario.
- **`DiaryActivity.kt`**: La actividad principal del diario que maneja la lista de entradas y el sensor de movimiento.

### Fragmentos

- **`DiaryListFragment.kt`**: Fragmento que muestra la lista de entradas del diario.
- **`DiaryDetailFragment.kt`**: Fragmento que muestra los detalles de una entrada seleccionada.
- **`NewEntryDialogFragment.kt`**: Fragmento de diálogo para agregar nuevas entradas al diario.

### Widget

- **`DiaryWidgetProvider.kt`**: Proveedor del widget que maneja la actualización del contenido del widget.

### Modelos

- **`DiaryEntry.kt`**: Modelo de datos para las entradas del diario.

## Layouts

- **`activity_main.xml`**: Layout para `MainActivity`.
- **`activity_diary.xml`**: Layout para `DiaryActivity`.
- **`fragment_diary_list.xml`**: Layout para `DiaryListFragment`.
- **`fragment_diary_detail.xml`**: Layout para `DiaryDetailFragment`.
- **`fragment_new_entry_dialog.xml`**: Layout para `NewEntryDialogFragment`.
- **`widget_diary.xml`**: Layout para el widget del diario.

## Recursos

- **`colors.xml`**: Definiciones de colores utilizados en la aplicación.
- **`strings.xml`**: Definiciones de cadenas de texto utilizadas en la aplicación.
- **`styles.xml`**: Definiciones de estilos utilizados en la aplicación.
- **`themes.xml`**: Definiciones de temas utilizados en la aplicación.
- **`widget_diary_info.xml`**: Configuración del widget del diario.

## Uso

1. Abre la aplicación y navega a la sección del diario.
2. Agrega nuevas entradas utilizando el botón "Agregar Entrada".
3. Visualiza y elimina entradas desde la lista de entradas.
4. Agita el móvil para cambiar el fondo de la aplicación a un color aleatorio.
5. Añade el widget a tu pantalla de inicio para ver una entrada aleatoria y una frase motivacional. Presiona el botón del widget para actualizar el contenido.
