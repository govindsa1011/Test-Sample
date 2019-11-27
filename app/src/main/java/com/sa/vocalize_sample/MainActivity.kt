package com.sa.vocalize_sample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sdk.vocalize.Vocalize
import com.sdk.vocalize.VocalizeInitializeListener
import com.sdk.vocalize.models.StateModel

class MainActivity : AppCompatActivity(), VocalizeInitializeListener {

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Vocalize.initialize(this@MainActivity, this)

    }

    override fun onInit(stateModels: ArrayList<StateModel>) {
        // Get the action and data from the intent to handle it.
        val action: String? = intent?.action
        when (action) {
            // When the action is triggered by a deep-link, Intent.Action_VIEW will be used
            Intent.ACTION_VIEW -> callMethod(intent)

            // Otherwise start the app as you would normally do.
            else -> {

            }
        }
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun callMethod(intent: Intent?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        Vocalize.reset()
    }
}