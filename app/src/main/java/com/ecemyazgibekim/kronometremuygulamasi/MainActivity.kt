package com.ecemyazgibekim.kronometremuygulamasi

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ecemyazgibekim.kronometremuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var zamaniDurdur:Long =0
        binding.btnStart.setOnClickListener{
            binding.kronometre.base=SystemClock.elapsedRealtime() + zamaniDurdur//sistem saatini alıyo + ile önceden başlatıp durdurmuşsak onu üstüne ekliyoruz
            binding.kronometre.start()
            binding.btnStart.visibility= View.GONE // tıklayınca pause görünsün
            binding.btnPause.visibility= View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))
        }

        binding.btnPause.setOnClickListener{
            zamaniDurdur= binding.kronometre.base-SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            binding.btnPause.visibility= View.GONE
            binding.btnStart.visibility= View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }

        binding.btnReset.setOnClickListener{
            binding.kronometre.base=SystemClock.elapsedRealtime() //reset sonrası tekrar starta basarsa diye
            binding.kronometre.stop()
            zamaniDurdur=0
            binding.btnPause.visibility= View.GONE
            binding.btnStart.visibility= View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }
    }
}