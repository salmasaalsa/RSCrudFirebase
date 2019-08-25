package erdin.sample.app.rscrudfirebase

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        Handler().postDelayed({
            run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 5000)
    }
}