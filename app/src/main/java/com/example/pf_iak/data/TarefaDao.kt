package com.example.pf_iak.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefas")
    fun getTodas(): LiveData<List<Tarefa>>

    @Insert
    suspend fun inserir(item: Tarefa)

    @Update
    suspend fun atualizar(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)
}