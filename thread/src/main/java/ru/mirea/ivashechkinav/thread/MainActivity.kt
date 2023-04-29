package ru.mirea.ivashechkinav.thread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.ivashechkinav.thread.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initCalculation()
        initThreadCode()
        initSimulationWorkOnThread()
        setContentView(binding.root)
    }

    private fun initCalculation() {
        binding.btnCalculate.setOnClickListener {
            val countOfLessons =
                binding.edCountLessons.text.toString().toIntOrNull() ?: return@setOnClickListener
            val countOfStudyDays =
                binding.edCountStudyDays.text.toString().toIntOrNull() ?: return@setOnClickListener

            if (countOfStudyDays == 0) return@setOnClickListener

            binding.tvResult.text = "Среднее число пар ${countOfLessons / countOfStudyDays}"
        }
    }

    private fun initThreadCode() {
        val infoTextView = binding.tvCurrentThreadName
        val mainThread = Thread.currentThread()
        infoTextView.text = "Имя текущего потока: " + mainThread.name
        mainThread.name =
            "МОЙ НОМЕР ГРУППЫ: БСБО-06-21, НОМЕР ПО СПИСКУ: 7, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Бойцовский клуб"
        infoTextView.append("\nНовое имя потока: ${mainThread.name}")
        Log.d(
            MainActivity::class.java.simpleName,
            "Stack: " + Arrays.toString(mainThread.stackTrace)
        )
    }

    private fun initSimulationWorkOnThread() {
        val lock = java.lang.Object()
        binding.btnMirea.setOnClickListener {
            val endTime = System.currentTimeMillis() + 20 * 1000
            while (System.currentTimeMillis() < endTime) {
                synchronized(lock) {
                    try {
                        lock.wait(endTime - System.currentTimeMillis())
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                }
            }
        }
    }
}