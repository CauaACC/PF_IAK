package com.example.pf_iak

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pf_iak.data.AppDatabase
import com.example.pf_iak.data.Tarefa
import kotlinx.coroutines.launch

class TarefaViewModel(application : Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).tarefaDao()
    val todas: LiveData<List<Tarefa>> = dao.getTodas()

    fun inserir(tarefa : Tarefa) = viewModelScope.launch {
        dao.inserir(tarefa)
    }

    fun atualizar(tarefa : Tarefa) = viewModelScope.launch {
        dao.atualizar(tarefa)
    }

    fun deletar(tarefa : Tarefa) = viewModelScope.launch {
        dao.deletar(tarefa)
    }
}