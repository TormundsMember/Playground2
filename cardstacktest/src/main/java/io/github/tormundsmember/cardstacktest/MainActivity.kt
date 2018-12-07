package io.github.tormundsmember.cardstacktest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val manager = CardStackLayoutManager(3)
    val adapter = CardStackAdapter()

    listCards.adapter = adapter
    listCards.layoutManager = manager

    adapter.updateItems(IntRange(1, 20).map { it.toString() })

  }


  class CardStackAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<String>()
    private var disposable: Disposable? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
      return object : RecyclerView.ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.card, p0, false)) {

      }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
      val txt: TextView = p0.itemView.findViewById(R.id.txt)
      txt.text = items[p1]
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
      super.onViewRecycled(holder)
      disposable?.dispose()
      disposable = null
    }

    fun updateItems(newItems: List<String>) {
      items = newItems
      notifyDataSetChanged()
    }
  }
}

fun <T : View> T.height(function: (Int) -> Unit) {
  if (height == 0)
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        viewTreeObserver.removeOnGlobalLayoutListener(this)
        function(height)
      }
    })
  else function(height)
}

fun <T : View> T.width(function: (Int) -> Unit) {
  if (width == 0)
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        viewTreeObserver.removeOnGlobalLayoutListener(this)
        function(width)
      }
    })
  else function(width)
}