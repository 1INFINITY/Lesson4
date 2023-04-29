package ru.mirea.ivashechkinav.lesson4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.ivashechkinav.lesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.editTextMirea.text = "Мой номер по списку №7"
        setContentView(binding.root)
    }
}

