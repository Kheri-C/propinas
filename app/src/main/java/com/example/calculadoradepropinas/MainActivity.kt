package com.example.calculadoradepropinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculadoradepropinas.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
        binding.button15.setOnClickListener {
            interfaceCalculateTip(.15)
            Toast.makeText(this,"Tip Percentage: 15%",Toast.LENGTH_LONG).show()
        }
        binding.button25.setOnClickListener {
            interfaceCalculateTip(.25)
            Toast.makeText(this,"Tip Percentage: 25%",Toast.LENGTH_LONG).show()
        }
        binding.button35.setOnClickListener {
            interfaceCalculateTip(.35)
            Toast.makeText(this,"Tip Percentage: 35%",Toast.LENGTH_LONG).show()
        }
        binding.buttonRound.setOnClickListener {
            interfaceRoundTip()
        }
    }

    private fun interfaceRoundTip(){
        var roundTip = binding.totalTip.text.toString()
        var roundTipD = roundTip.toDoubleOrNull()
        var roundAmount = binding.totalAmount.text.toString()
        var roundAmountD = roundAmount.toDoubleOrNull()

        if(roundTipD == null || roundAmountD == null){
            return
        }
        roundTipD = round(roundTipD)
        binding.totalTip.text = "${roundTipD}"
        roundAmountD = round(roundAmountD)
        binding.totalTip.text = "${roundAmountD}"
    }

    private fun interfaceCalculateTip(percentage: Double){
        val amount = binding.editTextNumberDecimal.text.toString()
        val amountValue = amount.toDoubleOrNull()
        if(amountValue == null){
            binding.totalTip.text = "$ 0.00"
            binding.totalAmount.text = "$ 0.00"
            return
        }
        if(amountValue == 0.0 || amountValue < 0.0){
            Toast.makeText(this,"Invalid amount, try again",Toast.LENGTH_LONG).show()
        }
        binding.totalTip.text = "$ ${amountValue*percentage}"
        binding.totalAmount.text = "$ ${amountValue*(1+percentage)}"
    }
}