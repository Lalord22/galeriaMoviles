package com.example.clima

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declarar variables para las vistas
    private lateinit var edCiudad: EditText
    private lateinit var btBuscar: Button
    private lateinit var imgTiempo: ImageView
    private lateinit var tvTemperatura: TextView
    private lateinit var tvHumedad: TextView
    private lateinit var tvDescripcion: TextView

    private val climaList = mutableListOf<Clima>()

    data class Clima(
        val nomCiudad: String,
        val weatherType: String,
        val temperatura: String,
        val humedad: String,
        val descripcion: String,
        val nomImagen: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        edCiudad = findViewById(R.id.edNomCiudad)
        btBuscar = findViewById(R.id.btBuscar)
        imgTiempo = findViewById(R.id.imgTiempo)
        tvTemperatura = findViewById(R.id.tvTemperatura)
        tvHumedad = findViewById(R.id.tvHumedad)
        tvDescripcion = findViewById(R.id.tvDescripcion)

        // Rellenar la lista con datos de prueba
        llenarDatosCiudad()

        btBuscar.setOnClickListener {
            val nomCiudad = edCiudad.text.toString()

            // Obtener los datos del clima actual de la lista
            val tiempoCiudad = climaList.find { it.nomCiudad == nomCiudad }
            if (tiempoCiudad != null) {
                updateUI(tiempoCiudad)
            } else {
                tvTemperatura.text = "Ciudad no encontrada"
                tvHumedad.text = ""
                tvDescripcion.text = ""
                imgTiempo.setImageResource(0) // Limpia la imagen
            }
        }

    }

    private fun llenarDatosCiudad() {
        climaList.apply {
            add(
                Clima(
                    nomCiudad = "San José",
                    weatherType = "Soleado",
                    temperatura = "28°C",
                    humedad = "75%",
                    descripcion = "Cielo despejado",
                    nomImagen = "soleado"
                )
            )
            add(
                Clima(
                    nomCiudad = "Alajuela",
                    weatherType = "Nublado",
                    temperatura = "25°C",
                    humedad = "80%",
                    descripcion = "Cielo nublado",
                    nomImagen = "nublado"
                )
            )
            add(
                Clima(
                    nomCiudad = "Cartago",
                    weatherType = "Lluvioso",
                    temperatura = "22°C",
                    humedad = "90%",
                    descripcion = "Lluvias intensas",
                    nomImagen = "lluvioso"
                )
            )
            add(
                Clima(
                    nomCiudad = "Guanacaste",
                    weatherType = "Soleado",
                    temperatura = "32°C",
                    humedad = "65%",
                    descripcion = "Día caluroso",
                    nomImagen = "soleado"
                )
            )
            add(
                Clima(
                    nomCiudad = "Holanda",
                    weatherType = "Nevado",
                    temperatura = "-5°C",
                    humedad = "80%",
                    descripcion = "Día nevado",
                    nomImagen = "nevado"
                )
            )
            // Agregar más objetos aquí
        }
    }


    private fun updateUI(clima: Clima) {
        tvTemperatura.text = "Temperatura: ${clima.temperatura}"
        tvHumedad.text = "Humedad: ${clima.humedad}"
        tvDescripcion.text = "Descripción: ${clima.descripcion}"

        val resourceId = resources.getIdentifier(clima.nomImagen, "drawable", packageName)
        imgTiempo.setImageResource(resourceId)
    }
}
