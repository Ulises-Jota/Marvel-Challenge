package com.example.marvelchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.marvelchallenge.R
import kotlinx.android.synthetic.main.fragment_detalle.*

class DetalleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).setSupportActionBar(toolbar_detalle)
        return inflater.inflate(R.layout.fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asignarle un comportamiento al ícono de navegación (la X)
        toolbar_detalle.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        var imagen: String? = ""
        var nombrePersonaje: String? = ""
        var biografia: String? = ""
        var listaDetalles: ArrayList<String>? = arrayListOf()

        // Recuperar bundle
        arguments?.apply {
            imagen = getString("imagen")
            nombrePersonaje = getString("nombrePersonaje")
            biografia = getString("biografia")
            listaDetalles = getStringArrayList("listaDetalles")
        }

        // Cargar imagen y datos enviados en el bundle
        Glide.with(this).load(imagen).into(character_image)
        character_bio.text = biografia
        nombre_personaje.text = nombrePersonaje

        // La lista de comics en los que aparece el personaje
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listaDetalles as List<String>
        )

        list_detalles_personaje.adapter = adapter
    }
}
