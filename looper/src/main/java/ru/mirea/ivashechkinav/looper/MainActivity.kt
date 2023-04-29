package ru.mirea.ivashechkinav.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.ivashechkinav.looper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initHandlers()
        setContentView(binding.root)
    }

    private fun initHandlers() {
        val mainThreadHandler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(
                    MainActivity::class.java.simpleName,
                    "Task execute. This is result: " + msg.data.getString("result")
                )
            }
        }
        val myLooper = MyLooper(mainThreadHandler)
        myLooper.start()
        binding.edMirea.setText( "Мой номер по списку №7" )
        binding.btnMirea.setOnClickListener {
            val age = binding.edAge.text.toString().toIntOrNull() ?: return@setOnClickListener
            val workPlace = binding.edAge.text.toString()
            val msg = Message.obtain()
            val bundle = Bundle()
            bundle.putString("WORK_PLACE", workPlace)
            bundle.putInt("AGE", age)
            msg.data = bundle
            myLooper.currentHandler!!.sendMessage(msg)
        }
    }
}