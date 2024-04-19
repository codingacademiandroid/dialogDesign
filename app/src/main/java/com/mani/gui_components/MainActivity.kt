package com.mani.gui_components

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class MainActivity : Activity(), View.OnClickListener {

    lateinit var tvAlertDialog : TextView
    lateinit var tvInputDialog : TextView
    lateinit var tvDateDialog : TextView
    lateinit var tvTimeDialog : TextView
    lateinit var tvProgressDialog : TextView
    lateinit var tvCustomDialog : TextView

    lateinit var calendar : Calendar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar = Calendar.getInstance()

        tvAlertDialog = findViewById(R.id.tvAlertDialog)
        tvInputDialog = findViewById(R.id.tvInputDialog)
        tvDateDialog = findViewById(R.id.tvDatePicker)
        tvTimeDialog = findViewById(R.id.tvTimePicker)
        tvProgressDialog = findViewById(R.id.tvProgressDialog)
        tvCustomDialog = findViewById(R.id.tvCustomDialog)

        tvAlertDialog.setOnClickListener(this)
        tvInputDialog.setOnClickListener(this)
        tvDateDialog.setOnClickListener(this)
        tvTimeDialog.setOnClickListener(this)
        tvProgressDialog.setOnClickListener(this)
        tvCustomDialog.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when(view?.id) {

            R.id.tvAlertDialog -> {
                performAlertDialogOperation()
            }

            R.id.tvInputDialog -> {
                performInputDialogOperation()
            }

            R.id.tvDatePicker -> {
                performDateDialogOperation()
            }

            R.id.tvTimePicker -> {
                performTimeDialogOperation()
            }

            R.id.tvProgressDialog -> {
                performProgressDialogOperation()
            }

            R.id.tvCustomDialog -> {
                performCustomDialogOperation()
            }

        }
    }

    private fun performCustomDialogOperation() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.findViewById<EditText>(R.id.etUserName)
        dialog.findViewById<EditText>(R.id.etPassword)

        dialog.show()
    }

    private fun performProgressDialogOperation() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait its Loading...")
        progressDialog.setCancelable(false)
        val runnable = Runnable { progressDialog.dismiss() }
        val pdCanceller = Handler()
        pdCanceller.postDelayed(runnable, 3000)

        progressDialog.show()
    }

    private fun performTimeDialogOperation() {
        val timePickerDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hour, minute ->



        },calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }

    private fun performDateDialogOperation() {

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val dateFormat = "" + dayOfMonth + " " + monthOfYear + 1 + ", " + year
            Toast.makeText(this, "Date : $dateFormat",Toast.LENGTH_SHORT).show()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()

    }

    private fun performInputDialogOperation() {

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Input Dialog")
        alertDialog.setCancelable(false)
        val editText = EditText(this)
        alertDialog.setView(editText)
        alertDialog.setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, i ->
            val message = editText.text.toString()
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        })
        alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()


    }

    private fun performAlertDialogOperation() {

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Login Alert")
        alertDialog.setCancelable(false)
        alertDialog.setMessage("Are You Sure You Want to Continue?")
        alertDialog.setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(this,"I CLicked on Alert Dialog",Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        })
        alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()


    }
}