package com.example.pf_iak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pf_iak.data.Tarefa

class TarefaAdapter(
    private val lista: List<Tarefa>,
    private val onItemClick: (Tarefa) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    inner class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvDescricao: TextView = itemView.findViewById(R.id.tvDescricao)

        init {
            itemView.setOnClickListener {
                val tarefa = lista[adapterPosition]
                onItemClick(tarefa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = lista[position]
        holder.tvTitulo.text = tarefa.titulo
        holder.tvDescricao.text = tarefa.descricao
    }

    override fun getItemCount(): Int = lista.size

    fun atualizarLista(novaLista: List<Tarefa>) {
        lista = novaLista
        notifyDataSetChanged()
    }
}