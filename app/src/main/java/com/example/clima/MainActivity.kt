package com.example.clima

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declarar variables para las vistas
    private lateinit var btAlajuela: Button
    private lateinit var btRomanticismo: Button
    private lateinit var btRenacentismo: Button
    private lateinit var btPaisajismo: Button
    private lateinit var resultsLayout: LinearLayout

    private val pinturaList = mutableListOf<Pintura>()

    data class Pintura(

        val obra: String,
        val genero: String,
        val autor: String,
        val annio: String,
        val descripcion: String,
        val nomImagen: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        btAlajuela = findViewById(R.id.btAlajuela)
        btRomanticismo = findViewById(R.id.btRomanticismo)
        btRenacentismo = findViewById(R.id.btRenacentismo)
        btPaisajismo = findViewById(R.id.btPaisajismo)
        resultsLayout = findViewById(R.id.resultsLayout)

        // Rellenar la lista con datos de prueba
        llenarGaleria()

        // Set onClick listeners for each button
        btAlajuela.setOnClickListener { buscarPinturas("Surrealismo") }
        btRomanticismo.setOnClickListener { buscarPinturas("Romanticismo") }
        btRenacentismo.setOnClickListener { buscarPinturas("Renacentismo") }
        btPaisajismo.setOnClickListener { buscarPinturas("Paisajismo") }
    }

    private fun llenarGaleria() {
        pinturaList.apply {
            add(
                Pintura(
                    obra ="La llave del campo",
                    genero = "Surrealismo",
                    autor = "Rene Magritte",
                    annio = "1936",
                    descripcion = "Este óleo de pequeño tamaño nos interroga sobre la realidad y sus posibilidades. Una ventana abierta al campo nos refleja un pequeño bosquete, el mismo que adivinamos en los cristales rotos. En este caso, la pregunta es la que sigue: ¿dónde está la realidad? ¿Detrás de la ventana? ¿O acaso esta es el reflejo de los cristales rotos?",
                    nomImagen = "lallavedelcampo"
                )
            )
            add(
                Pintura(
                    obra ="La persistencia de la memoria",
                    genero = "Surrealismo",
                    autor = "Salvador Dali",
                    annio = "1931",
                    descripcion = "Dalí (con graves problemas de autoestima e, incluso, mentales recordemos) nos presenta un fondo onírico a más no poder con relojes que se derriten y no pueden mantenerse en pie. Sin duda, es una metáfora de la inmaterialidad e inconsistencia del tiempo.",
                    nomImagen = "lapersistenciadelamemoria"
                )
            )
            add(
                Pintura(
                    obra ="El beso",
                    genero = "Romanticismo",
                    autor = "Francesco Hayez",
                    annio = "1859",
                    descripcion = "Si bien a primera vista parece la personificación de la pasión juvenil a través de dos amantes entrelazados en un beso apasionado, la profundidad de esta sugerente obra va mucho más allá, ya que está cargada de símbolos alegóricos que representan la unidad nacional, el patriotismo y el compromiso político y militar.",
                    nomImagen = "elbeso"
                )
            )
            add(
                Pintura(
                    obra ="Mona Lisa",
                    genero = "Renacentismo",
                    autor = "Leonardo Da Vinci",
                    annio = "1503",
                    descripcion = "Conocido por los locales como La Gioconda (“la feliz”), este juego de palabras jovial proviene del apellido de Mona LisaEl comisario de Francesco del Giocondo. Hasta hace poco, la identidad de la mujer en la pintura era un misterio, pero desde entonces se ha confirmado como una retrato de la mujer noble italiana Lisa Gherardini, esposa de Giocondo.",
                    nomImagen = "monalisa"
                )
            )
            add(
                Pintura(
                    obra ="La Vista de Toledo",
                    genero = "Paisajismo",
                    autor = "El Greco",
                    annio = "1599",
                    descripcion = "El cuadro adopta un punto de vista bajo y se toma algunas libertades, ya que algunos edificios de Toledo están representados en posiciones diferentes a las de su verdadera ubicación. A la izquierda se ve el castillo de San Servando y —debajo de él— una estructura inacabada, con minúsculas figuras humanas, que tal vez sea un convento con iglesia y claustro.",
                    nomImagen = "vistatoledo"
                )
            )
            add(
                Pintura(
                    obra ="The Fighting Temeraire",
                    genero = "Romanticismo",
                    autor = "William Turner",
                    annio = "1839",
                    descripcion = "La composición de este cuadro es inusual: el objeto más significativo, el antiguo buque de guerra, no está en el centro del cuadro sino en el cuadrante inferior izquierdo, desde donde, con majestuoso esplendor, y caracterizado con una llamativa y escueta gama de colores casi espectrales, se eleva contra un triángulo de cielo azul y niebla ascendente que lo realza. El decadente esplendor de la vieja nave contrasta con el sucio remolcador que le precede y que, ennegrecido con su alta chimenea, agita a su paso la superficie del río.",
                    nomImagen = "fightingtemeraire"
                )
            )
            add(
                Pintura(
                    obra ="Juicio final",
                    genero = "Renacentismo",
                    autor = "Michelangelo",
                    annio = "1541",
                    descripcion = "En 1535, el papa Paulo III encargó a Miguel Ángel el más grande fresco jamás pintado, que trataría sobre el Juicio Final y que se ubicaría en la pared del altar de la Capilla Sixtina. El tema estaba relacionado con lo que había sucedido en la Iglesia en los años precedentes: la Reforma Protestante y el saqueo de Roma. Por eso se intentaba representar a la humanidad haciendo frente a su salvación.",
                    nomImagen = "juiciofinal"
                )
            )
            add(
                Pintura(
                    obra ="El estanque de los nenúfares",
                    genero = "Paisajismo",
                    autor = "Claude Monet",
                    annio = "1899",
                    descripcion = "Los nenúfares (en francés, Les nymphéas) es una serie de pinturas al óleo que ejecutó el pintor impresionista Claude Monet (aproximadamente 250 obras) al final de su vida, sobre amplios paneles, como el de la ilustración, que mide 219 × 602 cm, y que actualmente se exhiben en el Museo de la Orangerie de las Tullerías, en París, Francia.",
                    nomImagen = "estanque"
                )
            )
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

        val tvObra = view.findViewById<TextView>(R.id.tvObra)
        val tvAutor = view.findViewById<TextView>(R.id.tvAutor)
        val tvAnnio = view.findViewById<TextView>(R.id.tvAnnio)
        val tvDescripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val imgTiempo = view.findViewById<ImageView>(R.id.imgTiempo)

        tvObra.text = "Obra: ${pintura.obra}"
        tvAutor.text = "Autor: ${pintura.autor}"
        tvAnnio.text = "Año: ${pintura.annio}"
        tvDescripcion.text = "Descripción: ${pintura.descripcion}"

        val resourceId = resources.getIdentifier(pintura.nomImagen, "drawable", packageName)
        imgTiempo.setImageResource(resourceId)

        resultsLayout.addView(view)
    }
}
