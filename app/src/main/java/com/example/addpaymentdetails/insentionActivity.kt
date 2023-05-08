package com.example.addpaymentdetails

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference


class InsertionActivity : AppCompatActivity() {

        private lateinit var pName: EditText
        private lateinit var pNumber: EditText
        private lateinit var pMonth: EditText
        private lateinit var pYear: EditText
        private lateinit var cvv: EditText
        private lateinit var btnAddCardDetails: Button

        private lateinit var dbRef: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_insertion)

            var etpName = findViewById(R.id.etpName)
            etEmpAge = findViewById(R.id.etEmpAge)
            etEmpSalary = findViewById(R.id.etEmpSalary)
            etpName = findViewById(R.id.etEmpName)
            etpName = findViewById(R.id.etEmpName)


            btnSaveData = findViewById(R.id.btnSave)

            dbRef = FirebaseDatabase.getInstance().getReference("Employees")

            btnSaveData.setOnClickListener {
                saveEmployeeData()
            }
        }

        private fun saveEmployeeData() {

            //getting values
            val pName = etpName.text.toString()
            val pNumber = etNumber.text.toString()
            val pMonth = etpMonth.text.toString()
            val pYear = etpYear.text.toString()
            val cvv = etcvv.text.toString()

            if (empName.isEmpty()) {
                etEmpName.error = "Please enter name"
            }
            if (empAge.isEmpty()) {
                etEmpAge.error = "Please enter age"
            }
            if (empSalary.isEmpty()) {
                etEmpSalary.error = "Please enter salary"
            }

            val empId = dbRef.push().key!!

            val employee = EmployeeModel(empId, empName, empAge, empSalary)

            dbRef.child(empId).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    etEmpName.text.clear()
                    etEmpAge.text.clear()
                    etEmpSalary.text.clear()


                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

    }
