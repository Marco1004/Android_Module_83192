package com.example.myapplication
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var number= ""
    var n= "2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { number = number + "0" }
        binding.button1.setOnClickListener { number = number + "1" }
        binding.button2.setOnClickListener { number = number + "2" }
        binding.button3.setOnClickListener { number = number + "3" }
        binding.button4.setOnClickListener { number = number + "4" }
        binding.button5.setOnClickListener { number = number + "5" }
        binding.button6.setOnClickListener { number = number + "6" }
        binding.button7.setOnClickListener { number = number + "7" }
        binding.button8.setOnClickListener { number = number + "8" }
        binding.button9.setOnClickListener { number = number + "9" }
        binding.delete.setOnClickListener { number = number.dropLast(1) }
        print(n)

        number= binding.textView2.text.toString().trim()
    }
}