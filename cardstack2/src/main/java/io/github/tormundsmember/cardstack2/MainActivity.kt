package io.github.tormundsmember.cardstack2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  val cardAdapter = CardAdapter()
  val cardStackLayoutManager = CardStackLayoutManager(10)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Timber.plant(LinkingDebugTree())

    liste.layoutManager = cardStackLayoutManager
    liste.adapter = cardAdapter
  }

  override fun onStart() {
    super.onStart()
    cardAdapter.listener = object : CardAdapter.Listener {
      override fun resetPosition() {
        cardStackLayoutManager.scrollToPosition(0)
      }

    }
  }


  override fun onStop() {
    super.onStop()
    cardAdapter.listener = null
  }
}
