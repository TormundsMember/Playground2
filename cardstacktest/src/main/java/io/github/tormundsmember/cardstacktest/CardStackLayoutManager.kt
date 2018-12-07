package io.github.tormundsmember.cardstacktest

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class CardStackLayoutManager(private val visibleCardCount: Int) : RecyclerView.LayoutManager() {

  override fun generateDefaultLayoutParams() = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

  override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
    if (recycler != null && state != null) {
      val oldTop = paddingTop
      detachAndScrapAttachedViews(recycler)
      var bottom: Int
      val left = paddingLeft
      val right = width - paddingRight
      val visibleViews = Math.min(state.itemCount, visibleCardCount)
      for (i in visibleViews - 1 downTo 0) {
        val v = recycler.getViewForPosition(i)
        addView(v)
        measureChildWithMargins(v, 0, 0)
        bottom = oldTop + getDecoratedMeasuredHeight(v)
        layoutDecorated(v, left, oldTop, right, bottom)
      }
    }
  }
}