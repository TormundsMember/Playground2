package io.github.tormundsmember.a2048

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, GameFragment()).commit()
  }
}
