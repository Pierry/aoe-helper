package com.gjithub.pierry.aoehelper.core.presentation

interface IBaseView {
    fun noInternet()
    fun hasInternet()
    fun noContent()
    fun hasContent()
    fun serverUnavailable()
    fun serverOk()
}