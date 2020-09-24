package com.example.marvelchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelchallenge.R
import com.example.marvelchallenge.data.DataSource
import com.example.marvelchallenge.data.model.Personaje
import com.example.marvelchallenge.domain.RepoImpl
import com.example.marvelchallenge.ui.viewmodel.PersonajesViewModel
import com.example.marvelchallenge.ui.viewmodel.ViewModelFactory
import com.example.marvelchallenge.vo.Resource
import kotlinx.android.synthetic.main.fragment_personajes.*

class PersonajesFragment : Fragment(), PersonajesAdapter.OnPersonajeClickListener {

    // Instancia del viewModel
    private val viewModel by viewModels<PersonajesViewModel> {
        ViewModelFactory(RepoImpl(DataSource()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).setSupportActionBar(toolbar_personajes)
        return inflater.inflate(R.layout.fragment_personajes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchPersonajesList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress_bar_personajes.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar_personajes.visibility = View.GONE
                    recycler_personajes.adapter = PersonajesAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    progress_bar_personajes.visibility = View.GONE
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
        recycler_personajes.layoutManager = LinearLayoutManager(requireContext())
        // Agregar líneas separadoras entre los ítems
        recycler_personajes.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    // Cuando el usuario hace click sobre un personaje, navega hacia la pantalla Detalles.
    override fun onPersonajeClick(personaje: Personaje, datosPersonaje: Bundle) {
        findNavController().navigate(
            R.id.action_personajesFragment_to_detalleFragment,
            datosPersonaje
        )
    }
}
