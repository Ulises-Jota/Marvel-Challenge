package com.example.marvelchallenge.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.marvelchallenge.R
import com.example.marvelchallenge.data.DataSource
import com.example.marvelchallenge.data.model.Evento
import com.example.marvelchallenge.domain.RepoImpl
import com.example.marvelchallenge.ui.viewmodel.EventosViewModel
import com.example.marvelchallenge.ui.viewmodel.ViewModelFactory
import com.example.marvelchallenge.vo.Resource
import kotlinx.android.synthetic.main.eventos_row.*
import kotlinx.android.synthetic.main.fragment_eventos.*

class EventosFragment : Fragment(), EventosAdapter.OnEventoClickListener  {

    // Instancia del viewModel
    private val viewModel by viewModels<EventosViewModel> {
        ViewModelFactory(RepoImpl(DataSource()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity) .setSupportActionBar(toolbar_eventos)
        return inflater.inflate(R.layout.fragment_eventos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.fetchEventosList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                   progress_bar_eventos.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar_eventos.visibility = View.GONE
                    recycler_eventos.adapter = EventosAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    progress_bar_eventos.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error al intentar obtener los datos: ${it.exception}",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        recycler_eventos.layoutManager = LinearLayoutManager(requireContext())
        // Agregar líneas separadoras entre los ítems
        recycler_eventos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    // Con esta función, pretendía manejar lo que pasaba cuando un usuario oprime la flechita
    // para expandir el evento, pero no logré resolverlo.
    override fun onEventoClick(item: Evento, position: Int) {
        Log.d("EVENTO y POSICION", "$item $position")
        when (recycler_eventos[position].findViewById<CardView>(R.id.lista_eventos_expanded).visibility){
            View.GONE -> {
                ic_expand_collapse.setImageResource(R.drawable.ic_expand_less)
                lista_eventos_expanded.visibility = View.VISIBLE
            }
            View.VISIBLE -> {
                ic_expand_collapse.setImageResource(R.drawable.ic_expand_more)
                lista_eventos_expanded.visibility = View.GONE
            }
        }
    }
}
