package com.example.pf_iak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TarefaViewModel
    private lateinit var adapter: TarefaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TarefaAdapter(emptyList()) { tarefa ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("tarefaId", tarefa.id)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[TarefaViewModel::class.java]
        viewModel.todas.observe(this) { tarefas ->
            adapter.atualizarLista(tarefas)
        }

        findViewById<FloatingActionButton>(R.id.fabAdicionar).setOnClickListener {
            startActivity(Intent(this, AddItemActivity::class.java))
        }
    }
}