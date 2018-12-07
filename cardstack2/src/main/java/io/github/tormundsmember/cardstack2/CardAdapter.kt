package io.github.tormundsmember.cardstack2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

  val items = (0..1000).toMutableList()
  var listener: Listener? = null


  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardViewHolder {
    return CardViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_card, p0, false))
  }

  override fun onBindViewHolder(p0: CardViewHolder, p1: Int) {
    p0.textView.text = items[p1].toString()


    p0.itemView.setOnClickListener {
      listener?.resetPosition()
    }
  }

  override fun getItemCount() = items.size


  class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textView: TextView = itemView.findViewById(R.id.textView)

  }

  interface Listener {
    fun resetPosition()
  }
}