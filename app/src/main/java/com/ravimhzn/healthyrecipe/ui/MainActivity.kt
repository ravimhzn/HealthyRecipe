package com.ravimhzn.healthyrecipe.ui

import android.os.Bundle
import android.view.View
import com.ravimhzn.healthyrecipe.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTest.setOnClickListener {
            println("debug -> Clicked")
            if (mProgressBar?.visibility == View.VISIBLE) {
                showProgressBar(false)
            } else {
                showProgressBar(true)
            }
        }
    }
}
