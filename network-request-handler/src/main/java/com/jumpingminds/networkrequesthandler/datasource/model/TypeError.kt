package com.jumpingminds.networkrequesthandler.datasource.model

/**
@author: Nitin Prakash
@date: 12-Feb-2022

This class helps to choose the type on error to be shown to the UI
 */
sealed class TypeError(val name: String) {
    object Toast : TypeError("toast")
    object Snack : TypeError("snack")
}

