package nl.jcraane.androidapp

import android.app.Application
import com.example.project.localizationContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        localizationContext = this
    }
}
