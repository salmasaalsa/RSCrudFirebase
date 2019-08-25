package erdin.sample.app.rscrudfirebase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.*

class PasienHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val namaPasien = view.namaPasien
    private val alamatPasien = view.alamatPasien
    private val umurPasien = view.umurPasien
    private val tanggal_lahirPasien = view.tanggal_lahirPasien

    fun bindPasien(pasien: Pasien) {
        namaPasien.text = pasien.nama
        alamatPasien.text = pasien.alamat
        umurPasien.text = pasien.umur
        tanggal_lahirPasien.text = pasien.tanggal_lahir
    }
}