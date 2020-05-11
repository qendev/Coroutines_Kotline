package com.example.coroutines_kotline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //make a contant
    private val RESULT_1 = "Result #1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }

        }

    }
    private fun setNewText(input : String){
        val newText=text.text.toString() + "\n$input"
        text.text = newText
    }

    private suspend fun setOnMainThread(input: String){
        withContext(Main){
            setNewText(input)
        }

    }
    private suspend fun fakeApiRequest(){
        val result1 =getResult1FromApi()
        println("debug: $result1")
        setOnMainThread(result1)

    }
    private suspend fun getResult1FromApi(): String{
        logThread("getResult1FromApi")
        delay(1000)
        return RESULT_1

    }

    private fun logThread(methodName : String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }
}
