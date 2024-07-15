package com.example.todolistapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var spinnerPriority: Spinner
    private lateinit var buttonAdd: Button
    private lateinit var listViewTasks: ListView
    private lateinit var tasksAdapter: ArrayAdapter<String>
    private val tasksList = ArrayList<String>()
    private var selectedPriority: String = "Low"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        buttonAdd = findViewById(R.id.buttonAdd)
        listViewTasks = findViewById(R.id.listViewTasks)

        // 设置 Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPriority.adapter = adapter

        spinnerPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedPriority = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedPriority = "Low"
            }
        }

        tasksAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasksList)
        listViewTasks.adapter = tasksAdapter

        buttonAdd.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                tasksList.add("$task - $selectedPriority")
                tasksAdapter.notifyDataSetChanged()
                editTextTask.text.clear()
            }
        }
    }
}
