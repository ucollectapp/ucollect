package br.edu.infnet.ucollect.apresentacao.fragmentos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.adapters.EmpresaAdapter
import br.edu.infnet.ucollect.apresentacao.viewmodel.EmpresaViewModel
import kotlinx.android.synthetic.main.fragment_empresas.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EmpresasFragment : Fragment() {

    private lateinit var empresaViewModel: EmpresaViewModel

    val empresaAdapter: EmpresaAdapter by lazy {
        EmpresaAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inicia o ViewModel
        activity?.let {
            empresaViewModel = ViewModelProviders
                .of(it)
                .get(EmpresaViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_empresas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        subscribe()
    }


    /*private fun setUpListeners(){
        send_message_button.setOnClickListener {
            val message = chat_edittext.text.toString()
            // limpa o campo de mensagens
            chat_edittext.setText("")
            val oldMessages = chatViewModel.messages.value
            chatViewModel.messages.value = oldMessages?.plus(
                ChatMessage(message, Date().time, true))
        }
    }*/
    // Define a RecycleView
    private fun setUpRecyclerView(){
        recycler_view_id.adapter = empresaAdapter

        activity?.let {
            recycler_view_id.layoutManager = LinearLayoutManager(it)
        }
    }

    // Observa o estado do viewModel
    private fun subscribe(){
        empresaViewModel.getEmpresas().observe(this, Observer {empresas->
            val empresaAdapter = recycler_view_id.adapter
            if (empresaAdapter is EmpresaAdapter){
                if(empresas.isEmpty()){
                    Toast.makeText(context, "Nenhuma Empresa cadastrada.", Toast.LENGTH_SHORT).show()
                }else{
                    empresaAdapter.setData(empresas)
                }
            }
        })
    }


}
