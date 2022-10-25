package br.com.gabrielmorais.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import br.com.gabrielmorais.frases.R
import br.com.gabrielmorais.frases.data.Frase
import br.com.gabrielmorais.frases.databinding.ActivityMainBinding
import br.com.gabrielmorais.frases.ui.incluirFrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    val retornoFrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETORNO)) {
                    val fraseRecebida = it.getParcelableExtra<Frase>(RETORNO)!!
                    Log.i("MainActivity", "Frase Recebida: ${fraseRecebida}")
                    viewModel.salvarFrase(fraseRecebida)
                }
            }
        } else {
            Log.e("IGTI Error", "Não foi possível obter os dados de retorno da activity!")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        iniciarDados()
        configurarListeners()
        configurarObservers()
    }

    private fun configurarObservers() {
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        viewModel.listaDeFrases.observe(this) { lista ->
            Log.i("MainActivity", "configurarAtualizacaoLista: $lista")
            atualizarLista(lista)
        }
    }

    private fun atualizarLista(lista: List<Frase>) {
        if (lista.isEmpty()) {
            binding.tvMensagemListaVazia.visibility = View.VISIBLE
            binding.rvListaFrases.visibility = View.GONE
        } else {
            binding.tvMensagemListaVazia.visibility = View.GONE
            binding.rvListaFrases.visibility = View.VISIBLE
            binding.rvListaFrases.adapter = FrasesAdapter(listaDeFrases = lista)
        }
    }

    private fun iniciarDados() {
        viewModel.iniciarDados()
    }

    private fun configurarListeners() {
        configurarFabListener()
    }

    private fun configurarFabListener() {
        binding.fabNovaFrase.setOnClickListener {
            val intent = Intent(this, IncluirFraseActivity::class.java)
            retornoFrase.launch(intent)
        }

        binding.fabNovaFrase.setOnLongClickListener {
            viewModel.limparListaDeFrases()
            Toast.makeText(applicationContext, R.string.lista_limpa_sucesso, Toast.LENGTH_SHORT)
                .show()
            it.isLongClickable
        }
    }


    companion object {
        const val RETORNO = "retorno_frases"
    }
}