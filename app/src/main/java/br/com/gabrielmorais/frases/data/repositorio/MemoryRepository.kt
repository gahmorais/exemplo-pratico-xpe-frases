package br.com.gabrielmorais.frases.data.repositorio

import br.com.gabrielmorais.frases.data.Frase

class MemoryRepository(novaLista: MutableList<Frase>) {
    private val listDb: MutableList<Frase> = novaLista

    fun salvar(frase: Frase) {
        listDb.add(frase)
    }

    fun limparLista() {
        listDb.clear()
    }

    fun retornarLista() = listDb.toList()
}