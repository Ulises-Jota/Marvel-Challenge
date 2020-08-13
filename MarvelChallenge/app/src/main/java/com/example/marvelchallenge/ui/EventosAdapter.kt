package com.example.marvelchallenge.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelchallenge.R
import com.example.marvelchallenge.base.BaseViewHolder
import com.example.marvelchallenge.data.model.Evento
import kotlinx.android.synthetic.main.eventos_row.view.*
import java.text.SimpleDateFormat


class EventosAdapter(
    private val context: Context,
    private val eventosList: List<Evento>,
    private val itemClickListener: OnEventoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnEventoClickListener {
        fun onEventoClick(item: Evento, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return EventosViewHolder(
            LayoutInflater.from(context).inflate(R.layout.eventos_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is EventosViewHolder -> holder.bind(eventosList[position], position)
        }
    }


    inner class EventosViewHolder(itemView: View) : BaseViewHolder<Evento>(itemView) {
        override fun bind(item: Evento, position: Int) {
            val imageConfigured =
                "${item.thumbnailResponse.path}/standard_xlarge.${item.thumbnailResponse.extension}"
            Glide.with(context).load(imageConfigured).into(itemView.foto_evento)
            itemView.nombre_evento.text = item.title
            itemView.fecha_inicio_evento.text = dateToText(item.dateStart)
            itemView.fecha_cierre_evento.text = dateToText(item.dateEnd)
            populateList(item)

            itemView.ic_expand_collapse.setOnClickListener {
                itemClickListener.onEventoClick(item, position)
            }
        }

        // Convierte la fecha en un texto acorde al formato solicitado (Ej.: 23 de Noviembre 2020)
        @SuppressLint("SimpleDateFormat")
        private fun dateToText(date: String?): String {
            try {
                val outputFormat = SimpleDateFormat("dd 'de' MMMM yyyy")
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val fecha = inputFormat.parse(date!!)
                return outputFormat.format(fecha!!)
            } catch (e: Exception) {
//                Toast.makeText(context,
//                    "Ocurrió un error al intentar obtener una fecha: $e",
//                    Toast.LENGTH_LONG).show()
            }
            return "Sin datos"
        }

        // Puebla la lista expandible con los comics a discutir en el evento
        private fun populateList(item: Evento) {
            val listaComics = item.comicList.items as ArrayList<String>

            val adapter = ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                listaComics
            )

            itemView.lista_eventos.adapter = adapter
        }
    }
}

