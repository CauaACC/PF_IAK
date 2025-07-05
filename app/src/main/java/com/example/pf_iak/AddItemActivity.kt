package com.example.pf_iak

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddItemActivity : AppCompatActivity() {

    private lateinit var viewModel: TarefaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        viewModel = ViewModelProvider(this)[TarefaViewModel::class.java]

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val descricao = etDescricao.text.toString()

            if (titulo.isNotBlank() && descricao.isNotBlank()) {
                val novaTarefa = Tarefa(titulo = titulo, descricao = descricao)

                viewModel.inserir(novaTarefa)

                Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}