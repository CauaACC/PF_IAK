package com.example.pf_iak

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var dao: TarefaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        // Inicializando o banco de dados
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tarefas.db"
        ).build()

        dao = db.tarefaDao()

        // Pegando as views do layout
        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        // Ao clicar em "Salvar"
        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val descricao = etDescricao.text.toString()

            if (titulo.isNotBlank() && descricao.isNotBlank()) {
                val novaTarefa = Tarefa(titulo = titulo, descricao = descricao)

                // Inserção usando coroutine
                lifecycleScope.launch(Dispatchers.IO) {
                    dao.inserir(novaTarefa)

                    // Mostra Toast na thread principal
                    launch(Dispatchers.Main) {
                        Toast.makeText(this@AddItemActivity, "Tarefa salva!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
