package br.edu.infnet.ucollect.apresentacao.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.ucollect.R
import br.edu.infnet.ucollect.apresentacao.activities.MainActivity
import br.edu.infnet.ucollect.dominio.modelos.Empresa
import kotlinx.android.synthetic.main.empresa_card.view.*

class EmpresaAdapter(var empresas: List<Empresa> = mutableListOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.empresa_card, parent, false)

        itemView.setOnClickListener {

            val dadosEmpresa = arrayListOf(
                itemView.textView_card_nome.text.toString(),
                itemView.textView_card_email.text.toString(),
                itemView.textView_card_telefone.text.toString(),
                itemView.textView_card_cnpj.text.toString(),
                itemView.textView_card_endereco.text.toString()
                //itemView.imageView_card_empresa.getTag().toString()
                )

            MainActivity.self.detalhesEmpresa(dadosEmpresa)
        }
        return EmpresaViewHolder(itemView)
    }

    override fun getItemCount() = empresas.size

    fun setData(newEmpresas: List<Empresa>){
        empresas = newEmpresas
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val empresa = empresas[position]

        if (holder is EmpresaViewHolder){
            holder.nome.text = empresa.razaoSocial
            holder.cnpj.text = empresa.cnpj
            holder.endereco.text = empresa.endereco
            holder.telefone.text = empresa.telefone
            holder.email.text = empresa.email
            holder.imagem.setImageResource(empresa.imagemEmpresa)
        }
    }

    class EmpresaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nome: TextView = itemView.findViewById(R.id.textView_card_nome)
        val cnpj: TextView = itemView.findViewById(R.id.textView_card_cnpj)
        val endereco: TextView = itemView.findViewById(R.id.textView_card_endereco)
        val telefone: TextView = itemView.findViewById(R.id.textView_card_telefone)
        val email: TextView = itemView.findViewById(R.id.textView_card_email)
        val imagem: ImageView = itemView.findViewById(R.id.imageView_card_empresa)
    }

}