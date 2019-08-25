package erdin.sample.app.rscrudfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val mPasienList = ArrayList<Pasien>()

    lateinit var ref: DatabaseReference
    lateinit var show_progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show_progress = findViewById(R.id.progress_bar)
        ref = FirebaseDatabase.getInstance().reference.child("Pasien")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mPasienList.clear()

                for (pasien in dataSnapshot.children) {
                    val value = pasien.getValue(Pasien::class.java)
                    mPasienList.add(value!!)
                }

                val pasienAdapter = PasienAdapter(mPasienList)

                rvMain.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = pasienAdapter
                }

                pasienAdapter.notifyDataSetChanged()
                show_progress.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("msg", "Failed to read value.", error.toException())
            }
        })

        fab.setOnClickListener {
            startActivity(Intent(this, InputPasien::class.java))
        }
    }
}
