package com.example.calculagorjeta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculagorjeta.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcular.setOnClickListener{ calculaGorjeta() }
    }

    fun calculaGorjeta() {
        val stringInTextField = binding.consumo.text.toString()
        val consumo = stringInTextField.toDouble()
        val opcao = binding.opcoesDeGorjeta.checkedRadioButtonId
        val percentagem = when (opcao) {
            R.id.vinte_porcento -> 0.20
            R.id.quinze_porcento -> 0.15
            else -> 0.10
        }
        var gorjeta = percentagem * consumo
        val arredondamento = binding.arredondamento.isChecked
        if (arredondamento) {
            gorjeta = kotlin.math.ceil(gorjeta)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(gorjeta)
        binding.resultado.text = getString(R.string.resultado, formattedTip)
    }
}