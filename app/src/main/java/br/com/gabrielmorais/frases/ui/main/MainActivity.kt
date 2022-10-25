package br.com.gabrielmorais.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
    configurarAtualizacaoLista()
  }

  private fun configurarAtualizacaoLista() {
    viewModel.listaDeFrases.observe(this) { lista ->
      Log.i("MainActivity", "configurarAtualizacaoLista: $lista")
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
  }


  companion object {
    const val RETORNO = "retorno_frases"
  }
}