package nl.jcraane.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.project.L
import com.example.project.ok
import dev.capoax.shared.sample.Greeter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.greetingFromCommon).text = Greeter().greet()
    }
}
