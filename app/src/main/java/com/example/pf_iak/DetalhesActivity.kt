package com.example.pf_iak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.room.Room

class DetalhesActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var dao: TarefaDao
    private var tarefaId: Int = -1
    private var tituloAtual: String = ""
    private var descricaoAtual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        tarefaId = intent.getIntExtra("tarefaId", -1)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tarefas.db"
        ).allowMainThreadQueries().build()

        dao = db.tarefaDao()

        val textTitulo = findViewById<TextView>(R.id.etTitulo)
        val textDescricao = findViewById<TextView>(R.id.etDescricao)
        val btnEditar = findViewById<Button>(R.id.btnSalvarEdicao)

        dao.getTodas().observe(this, Observer { lista ->
            val tarefa = lista.find { it.id == tarefaId }
            tarefa?.let {
                tituloAtual = it.titulo
                descricaoAtual = it.descricao
                textTitulo.text = it.titulo
                textDescricao.text = it.descricao
            }
        })

        btnEditar.setOnClickListener {
            val intent = Intent(this, EditarTarefaActivity::class.java)
            intent.putExtra("id", tarefaId)
            intent.putExtra("titulo", tituloAtual)
            intent.putExtra("descricao", descricaoAtual)
            startActivity(intent)
        }
    }
}
