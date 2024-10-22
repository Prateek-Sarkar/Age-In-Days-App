package com.ticketo.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val datePickerButton = findViewById<Button>(R.id.datebutton)
        datePickerButton.setOnClickListener { view ->
            datePicker(view);
        }

    }

    fun datePicker(view : View){
        val myCalender = Calendar.getInstance();
        val year = myCalender.get(Calendar.YEAR);
        val month = myCalender.get(Calendar.MONTH);
        val day = myCalender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
            view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->
//                Toast.makeText(this, "Pick a date", Toast.LENGTH_LONG).show()

                // Age Selection Process
                val selectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear";

                val dateId = findViewById<TextView>(R.id.theSelectedDate)
                dateId.text = selectedDate;

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate);

                // Age Display
                val selectedDateInMillis = theDate.time;  // Get selected date in milliseconds
                val currentDateInMillis = System.currentTimeMillis()  // Get current date in milliseconds

                // Calculate the difference in milliseconds
                val differenceInMillis = currentDateInMillis - selectedDateInMillis

                // Convert the difference to days
                val differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis)

                // Displaying the age in days
                val AgeInDays = findViewById<TextView>(R.id.ageInMinutes)  // Assuming a TextView with id ageInDays
                AgeInDays.text = differenceInDays.toString()  // Set text as string

                // UnKnown Thing or libraries used: .time, System., TimeUnit.MILLISECONDS.toDays,
            },
            year,
            month,
            day).show()

    }
}