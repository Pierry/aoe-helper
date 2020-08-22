package com.gjithub.pierry.aoehelper.core

import android.os.Bundle
import com.gjithub.pierry.aoehelper.R
import com.gjithub.pierry.aoehelper.core.presentation.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}