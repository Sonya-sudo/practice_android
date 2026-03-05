package ci.nsu.moble.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.nsu.moble.main.ui.theme.PracticeTheme
import androidx.compose.foundation.background

// Структура данных для хранения цветов
val colorMap = mapOf(
    "red" to Color.Red,
    "green" to Color.Green,
    "blue" to Color.Blue,
    "yellow" to Color.Yellow,
    "cyan" to Color.Cyan,
    "magenta" to Color.Magenta,
    "black" to Color.Black,
    "white" to Color.White,
    "gray" to Color.Gray,
    "darkgray" to Color.DarkGray,
    "lightgray" to Color.LightGray
)

class Compose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColorSearchScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ColorSearchScreen(modifier: Modifier = Modifier) {
    // Состояния
    var inputText by remember { mutableStateOf("") }
    var buttonColor by remember { mutableStateOf(Color(0xFF6200EE)) }

    // Тег для логирования
    val logTag = "ColorSearch"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Поле ввода
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it.lowercase() },
            label = { Text("Введите название цвета") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка поиска
        Button(
            onClick = {
                val color = colorMap[inputText]

                if (color != null) {
                    // Цвет найден
                    buttonColor = color
                    Log.d(logTag, "Цвет '$inputText' найден и применен к кнопке")
                } else {
                    // Цвет не найден
                    Log.d(logTag, "Пользовательский цвет '$inputText' не найден")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Найти цвет",
                color = if (buttonColor == Color.Black ||
                    buttonColor == Color.DarkGray ||
                    buttonColor == Color.Blue)
                    Color.White else Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Заголовок для палитры
        Text(
            text = "Доступные цвета:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Список цветов (палитра)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colorMap.toList()) { (colorName, colorValue) ->
                ColorItem(
                    colorName = colorName,
                    colorValue = colorValue
                )
            }
        }
    }
}
@Composable
fun ColorItem(
    colorName: String,
    colorValue: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Цветной квадратик
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(colorValue, shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Название цвета
            Text(
                text = colorName,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorSearchScreenPreview() {
    PracticeTheme {
        ColorSearchScreen()
    }
}