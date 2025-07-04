package com.example.pf_iak.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TarefaDaoDao {
    @Query("SELECT * FROM itens")
    fun getTodos(): LiveData<List<Tarefa>>

    @Insert
    suspend fun inserir(item: Tarefa)

    @Update
    suspend fun atualizar(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)
}