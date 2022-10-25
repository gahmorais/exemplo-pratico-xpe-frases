package br.com.gabrielmorais.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.gabrielmorais.frases.databinding.ActivityMainBinding
import br.com.gabrielmorais.frases.ui.incluirFrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configurarListeners()
    }

    private fun configurarListeners() {
        configurarFabListener()
    }

    private fun configurarFabListener() {
        binding.fabNovaFrase.setOnClickListener {
            startActivity(Intent(this, IncluirFraseActivity::class.java))
        }
    }
}