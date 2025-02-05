package com.example.fragmentweatherdemok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment
    lateinit var toFinishTask: Button
    lateinit var toCurrentTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    suspend fun loadWeather() {
        val API_KEY = "" // TODO: ключ загрузить из строковых ресурсов
        // TODO: в строку подставлять API_KEY и город (выбирается из списка или вводится в поле)
        val weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=Irkutsk&appid=d6843ab8ee963f5d372296dfff62aed7&units=metric";
        val stream = URL(weatherURL).getContent() as InputStream
        // JSON отдаётся одной строкой,
        val data = Scanner(stream).nextLine()
        // TODO: предусмотреть обработку ошибок (нет сети, пустой ответ)
        Log.d("mytag", data)
    }
    public fun onClick(v: View) {

        // Используем IO-диспетчер вместо Main (основного потока)
        lifecycleScope.launch (Dispatchers.IO) {
            loadWeather()
        }
    }
}