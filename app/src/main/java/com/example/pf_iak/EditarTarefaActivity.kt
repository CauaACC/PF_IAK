package com.example.pf_iak

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class EditarTarefaActivity : AppCompatActivity() {

    private lateinit var viewModel: TarefaViewModel
    private var tarefaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tarefa)

        viewModel = ViewModelProvider(this)[TarefaViewModel::class.java]

        val etEditarTitulo = findViewById<EditText>(R.id.etEditarTitulo)
        val etEditarDescricao = findViewById<EditText>(R.id.etEditarDescricao)
        val btnSalvarEdicao = findViewById<Button>(R.id.btnSalvarEdicao)

        tarefaId = intent.getIntExtra("id", 0)
        val titulo = intent.getStringExtra("titulo") ?: ""
        val descricao = intent.getStringExtra("descricao") ?: ""

        etEditarTitulo.setText(titulo)
        etEditarDescricao.setText(descricao)

        btnSalvarEdicao.setOnClickListener {
            val novoTitulo = etEditarTitulo.text.toString()
            val novaDescricao = etEditarDescricao.text.toString()

            if (novoTitulo.isNotBlank() && novaDescricao.isNotBlank()) {
                val tarefaAtualizada = Tarefa(id = tarefaId, titulo = novoTitulo, descricao = novaDescricao)

                viewModel.atualizar(tarefaAtualizada)

                Toast.makeText(this, "Tarefa atualizada!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}