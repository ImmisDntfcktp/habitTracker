package com.example.android.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.habittracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = HabitAdapter()
    private var createCardLauncher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        createCardLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    adapter.addHabit(
                        it.data?.getSerializableExtra(
                            resources.getText(R.string.key).toString()
                        ) as Habit
                    )
                }
            }
    }

    private fun init() {
        binding.apply {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, 1) // создаем сетку для размещения эл-ов списка
            recyclerView.adapter = adapter // запускаем адаптер, связывем data class habit с компонентом
            add.setOnClickListener {
                createCardLauncher?.launch(Intent(this@MainActivity, CardActivity::class.java))
            }
        }
    }
}