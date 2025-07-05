package com.example.pf_iak

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class DetalhesActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var dao: TarefaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val tarefaId = intent.getIntExtra("tarefaId", -1)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tarefas.db"
        ).allowMainThreadQueries().build()

        dao = db.tarefaDao()

        val textTitulo = findViewById<TextView>(R.id.etTitulo)
        val textDescricao = findViewById<TextView>(R.id.etDescricao)

        dao.getTodas().observe(this, Observer { lista ->
            val tarefa = lista.find { it.id == tarefaId }
            tarefa?.let {
                textTitulo.text = it.titulo
                textDescricao.text = it.descricao
            }
        })
    }
}
