package erdin.sample.app.rscrudfirebase

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.input_main.*
import java.text.SimpleDateFormat
import java.util.*


class InputPasien : AppCompatActivity() {

    private lateinit var ref: DatabaseReference
    var uid: String? = null
    var calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_main)

        ref = FirebaseDatabase.getInstance().reference

        if (intent.getStringExtra("uid") == null) {
            uid = UUID.randomUUID().toString()
        } else {
            uid = intent.getStringExtra("uid")
            ref.child("Pasien").child(uid.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val data: Pasien = dataSnapshot.getValue(Pasien::class.java)!!

                    inputNama.setText(data.nama.toString())
                    inputAlamat.setText(data.alamat.toString())
                    inputUmur.setText(data.umur.toString())
                    inputTanggalLahir.setText(data.tanggal_lahir.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("msg", "Failed to read value.", error.toException())
                }
            })
        }
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        inputTanggalLahir.setOnClickListener {
            DatePickerDialog(
                this, date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnSave.setOnClickListener { view ->
            val nama = inputNama.text.toString()
            val alamat = inputAlamat.text.toString()
            val umur = inputUmur.text.toString()
            val tanggal_lahir = inputTanggalLahir.text.toString()

            if (nama.isBlank() || alamat.isBlank() || umur.isBlank() || tanggal_lahir.isBlank()) {
                Snackbar.make(view, "Please check your input again.", Snackbar.LENGTH_LONG).show()
            } else {
                val pasien = Pasien(uid, nama, alamat, umur, tanggal_lahir)

                ref.child("Pasien").child(uid!!).setValue(pasien).addOnCompleteListener {
                    Snackbar.make(view, "Success", Snackbar.LENGTH_LONG).show()
                    inputNama.setText("")
                    inputAlamat.setText("")
                    inputUmur.setText("")
                    inputTanggalLahir.setText("")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
    }


    private fun updateLabel() {
        val myFormat = "dd MMMM yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        inputTanggalLahir.setText(sdf.format(calendar.time))
    }
}