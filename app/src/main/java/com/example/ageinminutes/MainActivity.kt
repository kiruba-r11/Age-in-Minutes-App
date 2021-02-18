package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDate.setOnClickListener {
            showDatePickerDialog(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDatePickerDialog(view: View) {
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this , DatePickerDialog.OnDateSetListener{
            view , selectedYear , selectedMonth , selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)
            val currentDate = simpleDateFormat.parse("$currentDayOfMonth/${currentMonth + 1}/$currentYear")
            val birthDate = simpleDateFormat.parse(selectedDate)

            val ageInMinutes = (currentDate.time - birthDate.time) / (60000)
            if(ageInMinutes <= 0) {
                Toast.makeText(this , "Invalid Date Selected", Toast.LENGTH_SHORT).show()
            }
            else {
                selectedDateTextView.text = selectedDate
                resultant.text = ageInMinutes.toString()
            }

        } , currentYear , currentMonth , currentDayOfMonth).show()

    }
}