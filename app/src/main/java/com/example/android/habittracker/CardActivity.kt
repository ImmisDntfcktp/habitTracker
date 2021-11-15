package com.example.android.habittracker

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.android.habittracker.databinding.ActivityCardBinding
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener


class CardActivity : AppCompatActivity() {
    lateinit var binding: ActivityCardBinding
    var colorPicked:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()

    }

    private fun initButtons() = with(binding) {
        buttonColor.setOnClickListener {
            val colorPicker = ColorPicker(this@CardActivity)
            colorPicker.setColors(R.array.Colors)
            colorPicker.setColumns(4)
            colorPicker.setTitle(getText(R.string.colorPicker).toString())
            colorPicker.setTitlePadding(8, 8, 8, 8)
            colorPicker.setRoundColorButton(true)
            colorPicker.show()

            colorPicker.setOnChooseColorListener(object : OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    colorPicked = color
                    buttonColor.backgroundTintList = ColorStateList.valueOf(color)
                }

                override fun onCancel() {
                    colorPicked = R.color.white
                }
            })
        }

        send.setOnClickListener {
            val selectedRadioButtonId = radioGroupType.checkedRadioButtonId
            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
            val item = Habit(
                editTextTitle.text.toString(),
                editTextDescription.text.toString(),
                spinnerPriority.selectedItem.toString(),
                selectedRadioButton.text.toString(),
                editNumber.text.toString().toInt(),
                editTextPeriod.text.toString(),
                colorId = colorPicked
            )
            val createIntent = Intent().apply {
                putExtra(resources.getText(R.string.key).toString(), item)
            }
            setResult(RESULT_OK, createIntent)
            finish()
        }
        back.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }


    }
}