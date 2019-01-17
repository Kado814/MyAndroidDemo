package com.myapplication.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.myapplication.myandroiddemo.R
import kotlinx.android.synthetic.main.activity_main.*

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printTest("this is kotlin print test")

        tv_text.text = ("哈哈哈哈哈")
    }


    fun printTest(string: String) {
        println(string)
    }

}
