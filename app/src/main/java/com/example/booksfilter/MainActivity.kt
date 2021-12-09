package com.example.booksfilter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button=findViewById<TextView>(R.id.button)
        val author=findViewById<TextInputLayout>(R.id.author)
        val country=findViewById<TextInputLayout>(R.id.country)
        val result=findViewById<TextView>(R.id.Result)

        button.setOnClickListener{
            var count=0
            val bookApplication=application as MyApplication
            val bookService=bookApplication.httpApiService

            CoroutineScope(Dispatchers.IO).launch {
                val decodedBook=bookService.getBooks()

                withContext(Dispatchers.Main)
                {
                    val myStringBuilder = StringBuilder()
                    for (myData in decodedBook) {
                        if (author.toString() == myData.author && country.toString() == myData.country) {
                            if(count<3) {
                                myStringBuilder.append("Result : "+myData.title)
                                myStringBuilder.append("\n")
                            }
                            count++
                        }
                    }
                    result.text = "Results : "+count+"\n$myStringBuilder"

                }
            }
        }


    }
}