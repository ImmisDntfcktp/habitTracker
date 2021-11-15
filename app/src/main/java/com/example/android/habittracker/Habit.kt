package com.example.android.habittracker

import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import java.io.Serializable

data class Habit(
    val title: String,
    val description: String,
    val priority: String,
    val type: String,
    val number: Int,
    val period: String,
    val colorId: Int
) : Serializable {

}
