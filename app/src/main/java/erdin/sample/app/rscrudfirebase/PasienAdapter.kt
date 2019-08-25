package erdin.sample.app.rscrudfirebase

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PasienAdapter(private val pasien: List<Pasien>) : RecyclerView.Adapter<PasienHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PasienHolder {
        return PasienHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_main, viewGroup, false))
    }

    override fun getItemCount(): Int = pasien.size

    override fun onBindViewHolder(holder: PasienHolder, position: Int) {
        holder.bindPasien(pasien[position])
        holder.itemView.setOnClickListener { v ->
            val pasien = pasien[position]
            val intent = Intent(v.context, ViewPasien::class.java)
            intent.putExtra("uid", pasien.uid.toString())
            intent.putExtra("nama", pasien.nama.toString())
            intent.putExtra("umur", pasien.umur.toString())
            intent.putExtra("alamat", pasien.alamat.toString())
            intent.putExtra("tanggal_lahir", pasien.tanggal_lahir.toString())
            v.context.startActivity(intent)
        }
    }
}