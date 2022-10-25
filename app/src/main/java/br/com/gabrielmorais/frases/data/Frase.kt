package br.com.gabrielmorais.frases.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Frase(
    val autor: String,
    val frase: String
) : Parcelable