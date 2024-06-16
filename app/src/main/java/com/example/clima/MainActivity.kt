package com.example.clima

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declarar variables para las vistas
    private lateinit var edCiudad: EditText
    private lateinit var btBuscar: Button
    private lateinit var resultsLayout: LinearLayout

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
        resultsLayout = findViewById(R.id.resultsLayout)

        // Rellenar la lista con datos de prueba
        llenarGaleria()

        btBuscar.setOnClickListener {
            val generoInput = edCiudad.text.toString()
            buscarPinturas(generoInput)
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
                    autor = "Salvador Dali",
                    annio = "1931",
                    descripcion = "Dalí (con graves problemas de autoestima e, incluso, mentales recordemos) nos presenta un fondo onírico a más no poder con relojes que se derriten y no pueden mantenerse en pie. Sin duda, es una metáfora de la inmaterialidad e inconsistencia del tiempo.",
                    nomImagen = "lapersistenciadelamemoria"
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

    private fun buscarPinturas(genero: String) {
        resultsLayout.removeAllViews()
        val matchingPinturas = pinturaList.filter { it.genero == genero }

        if (matchingPinturas.isNotEmpty()) {
            matchingPinturas.forEach { pintura ->
                addPinturaView(pintura)
            }
        } else {
            val noResultTextView = TextView(this).apply {
                text = "Género no encontrado"
                textSize = 18f
                setPadding(0, 10, 0, 10)
            }
            resultsLayout.addView(noResultTextView)
        }
    }

    private fun addPinturaView(pintura: Pintura) {
        val view = layoutInflater.inflate(R.layout.pintura_item, resultsLayout, false)

        val tvAutor = view.findViewById<TextView>(R.id.tvAutor)
        val tvAnnio = view.findViewById<TextView>(R.id.tvAnnio)
        val tvDescripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val imgTiempo = view.findViewById<ImageView>(R.id.imgTiempo)

        tvAutor.text = "Autor: ${pintura.autor}"
        tvAnnio.text = "Año: ${pintura.annio}"
        tvDescripcion.text = "Descripción: ${pintura.descripcion}"

        val resourceId = resources.getIdentifier(pintura.nomImagen, "drawable", packageName)
        imgTiempo.setImageResource(resourceId)

        resultsLayout.addView(view)
    }
}
