package ru.mirea.ivashechkinav.serviceapp

import android.Manifest.permission.FOREGROUND_SERVICE
import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.mirea.ivashechkinav.serviceapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val PermissionCode = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.getRoot()
        setContentView(view)
        if (ContextCompat.checkSelfPermission(
                this,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(MainActivity::class.java.simpleName.toString(), "Разрешения получены")
        } else {
            Log.d(MainActivity::class.java.simpleName.toString(), "Нет разрешений!")
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(POST_NOTIFICATIONS, FOREGROUND_SERVICE), PermissionCode
            )
        }
        binding.btnPlay.setOnClickListener {
            val serviceIntent = Intent(this@MainActivity, PlayerService::class.java)
            ContextCompat.startForegroundService(this@MainActivity, serviceIntent)
        }
        binding.btnNext.setOnClickListener {
            stopService(
                Intent(this@MainActivity, PlayerService::class.java)
            )
        }
    }
}