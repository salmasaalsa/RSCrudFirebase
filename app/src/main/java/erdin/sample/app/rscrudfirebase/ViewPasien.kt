package erdin.sample.app.rscrudfirebase

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.view_main.*

class ViewPasien : AppCompatActivity() {
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)

        val nama = intent.getStringExtra("nama")
        val alamat = intent.getStringExtra("alamat")
        val umur = intent.getStringExtra("umur")
        val tanggal_lahir = intent.getStringExtra("tanggal_lahir")

        tvNama.setText(nama)
        tvAlamat.setText(alamat)
        tvUmur.setText(umur)
        tvTanggalLahir.setText(tanggal_lahir)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val uid = intent.getStringExtra("uid")
        ref = FirebaseDatabase.getInstance().reference

        return when (item.itemId) {
            R.id.delete_action -> {
                ref.child("Pasien").child(uid).removeValue { error, reference ->
                    if (error != null) {
                        Toast.makeText(this, "Error, " + error.toException().localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
                return true
            }
            R.id.edit_action -> {
                val intent = Intent(this, InputPasien::class.java)
                intent.putExtra("uid", uid)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}