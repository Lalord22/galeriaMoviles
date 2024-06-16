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

    private val pinturaList = mutableListOf<Pintura>()

    data class Pintura(
        val genero: String,
        val weatherType: String,
        val autor: String,
        val annio: String,
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
        llenarGaleria()

        btBuscar.setOnClickListener {
            val generoInput = edCiudad.text.toString()

            // Obtener los datos del clima actual de la lista
            val tiempoCiudad = pinturaList.find { it.genero == generoInput }
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

    private fun llenarGaleria() {
        pinturaList.apply {
            add(
                Pintura(
                    genero = "Alajuela",
                    weatherType = "Soleado",
                    autor = "Rene Magritte",
                    annio = "1936",
                    descripcion = "Este óleo de pequeño tamaño nos interroga sobre la realidad y sus posibilidades. Una ventana abierta al campo nos refleja un pequeño bosquete, el mismo que adivinamos en los cristales rotos. En este caso, la pregunta es la que sigue: ¿dónde está la realidad? ¿Detrás de la ventana? ¿O acaso esta es el reflejo de los cristales rotos?",
                    nomImagen = "lallavedelcampo"
                )
            )
            add(
                Pintura(
                    genero = "Alajuela",
                    weatherType = "Nublado",
                    autor = "25°C",
                    annio = "80%",
                    descripcion = "Cielo nublado",
                    nomImagen = "nublado"
                )
            )
            add(
                Pintura(
                    genero = "Cartago",
                    weatherType = "Lluvioso",
                    autor = "22°C",
                    annio = "90%",
                    descripcion = "Lluvias intensas",
                    nomImagen = "lluvioso"
                )
            )
            add(
                Pintura(
                    genero = "Guanacaste",
                    weatherType = "Soleado",
                    autor = "32°C",
                    annio = "65%",
                    descripcion = "Día caluroso",
                    nomImagen = "soleado"
                )
            )
            add(
                Pintura(
                    genero = "Holanda",
                    weatherType = "Nevado",
                    autor = "-5°C",
                    annio = "80%",
                    descripcion = "Día nevado",
                    nomImagen = "nevado"
                )
            )
            // Agregar más objetos aquí
        }
    }


    private fun updateUI(pintura: Pintura) {
        tvTemperatura.text = "Autor: ${pintura.autor}"
        tvHumedad.text = "Año: ${pintura.annio}"
        tvDescripcion.text = "Descripción: ${pintura.descripcion}"

        val resourceId = resources.getIdentifier(pintura.nomImagen, "drawable", packageName)
        imgTiempo.setImageResource(resourceId)
    }
}
