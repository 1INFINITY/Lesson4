package ru.mirea.ivashechkinav.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


class MyLooper(
    val mainHandler: Handler,
): Thread() {
    var currentHandler: Handler? = null

    override fun run() {
        Log.d("MyLooper", "run")
        Looper.prepare()
        currentHandler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                val age = msg.data.getInt("AGE")
                val workPlace: String = msg.data.getString("WORK_PLACE") ?: ""
                Log.d("MyLooper get message: ", workPlace)
                val message = Message()
                val bundle = Bundle()
                bundle.putString(
                    "result",
                    "Delayed in seconds($age)"
                )
                message.data = bundle
                // Количество лет соответствует времени задержки.
                Thread.sleep(age * 1000L)
                // Send the message back to main thread message queue use main thread message Handler.
                mainHandler.sendMessage(message)
            }
        }
        Looper.loop()
    }
}