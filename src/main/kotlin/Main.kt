import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var contrasenaVisible by remember { mutableStateOf(false) }

    // Condición para habilitar el botón de login
    val buttonEnabled = usuario.isNotEmpty() && contrasena.isNotEmpty()

    val purpleColor = Color(0xFF6200EE) // Define el color morado que vamos a utilizar

    // Estilos personalizados para TextField y Button
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = purpleColor,
        unfocusedBorderColor = purpleColor
    )

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = purpleColor
    )

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") }, // Texto del label modificado a español
                colors = textFieldColors
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") }, // Texto del label modificado a español
                visualTransformation = if (contrasenaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(checked = contrasenaVisible, onCheckedChange = { contrasenaVisible = it }) {
                        Icon(
                            imageVector = if (contrasenaVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (contrasenaVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                colors = textFieldColors
            )
            Button(
                onClick = { usuario = ""; contrasena = "" },
                enabled = buttonEnabled,
                colors = buttonColors,
                modifier = Modifier.padding(top = 20.dp).size(80.dp, 40.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Login") {
        App()
    }
}
