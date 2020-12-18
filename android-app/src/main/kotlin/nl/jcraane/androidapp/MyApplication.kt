package nl.jcraane.androidapp

import android.app.Application
import com.example.project.localizationContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // localizationContext is defined in the KMMResourcesLocalization.kt file which contains the actual implementations of the resource functions.
        localizationContext = this
    }
}
