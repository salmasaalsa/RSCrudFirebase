package erdin.sample.app.rscrudfirebase

class Pasien {
    var uid: String? = null
    var nama: String? = null
    var alamat: String? = null
    var umur: String? = null
    var tanggal_lahir: String? = null

    constructor() : this("", "", "", "", "") {

    }

    constructor(uid: String?, nama: String?, alamat: String?, umur: String?, tanggal_lahir: String?) {
        this.uid = uid
        this.nama = nama
        this.alamat = alamat
        this.umur = umur
        this.tanggal_lahir = tanggal_lahir
    }
}