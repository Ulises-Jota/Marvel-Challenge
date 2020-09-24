package com.example.marvelchallenge.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelchallenge.R
import com.example.marvelchallenge.base.BaseViewHolder
import com.example.marvelchallenge.data.model.Personaje
import kotlinx.android.synthetic.main.personajes_row.view.*

class PersonajesAdapter(
    private val context: Context,
    private val personajesList: List<Personaje>,
    private val itemClickListener: OnPersonajeClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPersonajeClickListener {
        fun onPersonajeClick(personaje: Personaje, datosPersonaje: Bundle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return PersonajesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.personajes_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return personajesList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PersonajesViewHolder -> holder.bind(personajesList[position], position)
        }
    }


    inner class PersonajesViewHolder(itemView: View) : BaseViewHolder<Personaje>(itemView) {
        override fun bind(item: Personaje, position: Int) {
            // Armar string con el path completo a la imagen
            val imageConfigured =
                "${item.thumbnailResponse.path}/standard_xlarge.${item.thumbnailResponse.extension}"
            Glide.with(context).load(imageConfigured).into(itemView.character_image)
            val nombrePersonaje = item.name
            itemView.nombre_personaje.text = nombrePersonaje
            val biografia = item.description
            itemView.character_bio.text = biografia
            val lista_detalles = item.comicList.items as ArrayList<String>

            // Agregar datos al Bundle para enviar a DetalleFragment
            val datosPersonaje = Bundle().apply {
                putString("imagen", imageConfigured)
                putString("nombrePersonaje", nombrePersonaje)
                putString("biografia", biografia)
                putStringArrayList("listaDetalles", lista_detalles)
            }

            itemView.setOnClickListener {
                itemClickListener.onPersonajeClick(item, datosPersonaje)
            }
        }
    }
}
