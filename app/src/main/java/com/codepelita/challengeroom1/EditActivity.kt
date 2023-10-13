package com.codepelita.challengeroom1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.codepelita.challengeroom1.room.Constant
import com.codepelita.challengeroom1.room.dbsmksa
import com.codepelita.challengeroom1.room.tbsiswa
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class EditActivity : AppCompatActivity() {
    private val db by lazy { dbsmksa(this) }
    private var tbsisnis : Int = 0
    //private var tbsisnama : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolPerintah()
        setupView()
        tbsisnis = intent.getIntExtra("intent_nis", tbsisnis)
        Toast.makeText(this, tbsisnis.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                BtnSimpan.visibility= View.GONE
                BtnUpdate.visibility= View.GONE
                tampilsiswa()
            }
            Constant.TYPE_UPDATE -> {
                BtnSimpan.visibility= View.GONE

                tampilsiswa()
            }

        }
    }
    private fun tombolPerintah() {
        BtnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               db.tbsisDao().addtbsiswa(
                   tbsiswa(ETnis.text.toString().toInt(),ETnama.text.toString(),ETkelas.text.toString(),ETalamat.text.toString())
               )
                finish()
            }
        }
        //MELAKUKAN UPDATE DATA MENGGUNAKAN FUN DARI DAO YANG DISEMATKAN KE DALAM TOMBOL UPDATE
        BtnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbsisDao().updatetbsiswa(
                    tbsiswa(tbsisnis,ETnama.text.toString(),
                                     ETkelas.text.toString(),
                                     ETalamat.text.toString())
                )
                finish()
            }
        }


    }
    //MENGAMBIL / MENAMPILKAN DATA DARI TABEL KE DALAM LAYOUT BERDASARKAN PK
    fun tampilsiswa(){
        tbsisnis = intent.getIntExtra("intent_nis", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val siswa = db.tbsisDao().tampilid(tbsisnis).get(0)
            ETnis.setText(siswa.nis.toString())
            ETnama.setText(siswa.nama)
            ETkelas.setText(siswa.kelas)
            ETalamat.setText(siswa.alamat)
        }
    }
}