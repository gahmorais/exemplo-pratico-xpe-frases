package br.com.gabrielmorais.frases.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabrielmorais.frases.data.Frase
import br.com.gabrielmorais.frases.data.repositorio.MemoryRepository

class MainViewModel : ViewModel() {
    private val memoryRepository: MemoryRepository by lazy {
        MemoryRepository(mutableListOf())
    }
    private val _listaDeFrases = MutableLiveData<List<Frase>>()

    fun iniciarDados() {
        atualizarListaDeFrases()
    }

    fun salvarFrase(frase: Frase) {
        Log.i("IGTI Info", "Frase recebida:  $frase")
        memoryRepository.salvar(frase)

    }

    fun atualizarListaDeFrases() {
        _listaDeFrases.value = memoryRepository.retornarLista()
    }

    fun limparListaDeFrases() {
        Log.i("IGTI Info", "Iniciando processo de limpeza do repositório")
        memoryRepository.limparLista()
        atualizarListaDeFrases()
    }
}