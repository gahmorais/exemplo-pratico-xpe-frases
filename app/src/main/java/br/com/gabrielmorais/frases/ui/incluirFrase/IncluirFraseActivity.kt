package br.com.gabrielmorais.frases.ui.incluirFrase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isEmpty
import br.com.gabrielmorais.frases.R
import br.com.gabrielmorais.frases.databinding.ActivityIncluirFraseBinding

class IncluirFraseActivity : AppCompatActivity() {
    lateinit var binding: ActivityIncluirFraseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncluirFraseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configurarListeners()
    }

    private fun configurarListeners() {
        configurarListenerBtnCancelar()
        configurarListenerBtnSalvar()
    }

    private fun configurarListenerBtnSalvar() {
        binding.btnSalvar.setOnClickListener {
            salvarFrase()
        }
    }

    private fun salvarFrase() {
        binding.apply {
            val autor = autoresFraseED.text.toString()
            val frase = fraseET.text.toString()
            autoresFraseTIL.error = if (autor.isEmpty()) {
                getString(R.string.err_sem_autor)
            } else {
                null
            }

            fraseTIL.error = if (frase.isEmpty()) {
                getString(R.string.err_sem_frase)
            } else {
                null
            }

            if (autor.isNotEmpty() && frase.isNotEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Autor: $autor Frase: $frase",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }

        }
    }

    private fun configurarListenerBtnCancelar() {
        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}