package com.daeseong.ndk_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daeseong.ndk_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv1.text = stringFromJNI()
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("ndk_test")
        }
    }
}