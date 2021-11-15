package com.example.android.habittracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.RecyclerView
import com.example.android.habittracker.databinding.ItemOnMainActivityBinding

//реализуем adapter, наследуясь от RecyclerView.Adapter. В качестве параметра наш Holder.
class HabitAdapter : RecyclerView.Adapter<HabitAdapter.HabitHolder>() {

    val habitList =
        ArrayList<Habit>() // создаем список в который будут размещаться создаваемые эл-ты

    //создаем Holder, перечисляем компоненты для отдельного эл-та списка и
    // инициализируем через свойства data class habit
    class HabitHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemOnMainActivityBinding.bind(item)
        fun bind(habit: Habit) = with(binding) {
            titleHabit.text = habit.title
            visibleItem(titleHabit)
            descriptionHabit.text = habit.description
            visibleItem(descriptionHabit)
            priorityHabit.text = habit.priority
            visibleItem(priorityHabit)
            typeHabit.text = habit.type
            visibleItem(typeHabit)
            numberHabit.text = habit.number.toString()
            visibleItem(numberHabit)
            periodHabit.text = habit.period
            if (periodHabit.text.toString().isNotEmpty()) numberHabit.text =
                "${numberHabit.text} ${textBetweenNumberAndPeriod.text} ${periodHabit.text}"
            itemOnMainActivity.setBackgroundColor(habit.colorId)
        }

        fun visibleItem(item: TextView) {
            if (item.text.toString().isNotEmpty()) item.visibility = View.VISIBLE
        }

    }

    //Android studio попросит реализовать три метода:
    //1:  Нужно указать идентификатор макета для отдельного элемента списка на основном экране
    // наш xml item_on_main
    // Возвращаем объект нашего класса ViewHolder c параматером view нашего эл-та на осносновном экране
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_on_main_activity, parent, false)
        return HabitHolder(view)
    }

    //2: Связываем эл-т проинициализированный во ViewHolder со позицией в списке адапатера
    override fun onBindViewHolder(holder: HabitHolder, position: Int) {
        holder.bind(habitList[position])
    }

    //3:Адаптеру нужно знать, сколько элементов нужно предоставить компоненту,
    // чтобы распределить ресурсы и подготовиться к отображению на экране
    override fun getItemCount(): Int {
        return habitList.size
    }

    //fun добавляет эл-т в список адаптера, отслеживаем изменения в эл-ах

    fun addHabit(habit: Habit) {
        habitList.add(habit)
        notifyDataSetChanged()
    }

}

