package io.github.tormundsmember.cardstack2

import android.opengl.ETC1.getWidth
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import timber.log.Timber
import kotlin.math.absoluteValue

class CardStackLayoutManager(private val cardCount: Int) : RecyclerView.LayoutManager() {

  private var horizontalScroll = 0
  private var verticalScroll = 0

  override fun onScrollStateChanged(state: Int) {
    if (state == RecyclerView.SCROLL_STATE_SETTLING)
      super.onScrollStateChanged(RecyclerView.SCROLL_STATE_IDLE)
    else
      super.onScrollStateChanged(state)

    when (state) {
      RecyclerView.SCROLL_STATE_IDLE -> Timber.d("onScrollStateChanged SCROLL_STATE_IDLE ")
      RecyclerView.SCROLL_STATE_DRAGGING -> Timber.d("onScrollStateChanged SCROLL_STATE_DRAGGING ")
      RecyclerView.SCROLL_STATE_SETTLING -> Timber.d("onScrollStateChanged SCROLL_STATE_SETTLING ")
    }

  }

  override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
    return RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
  }

  override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
    if (recycler != null && state != null) {
      val oldTop = paddingTop
      detachAndScrapAttachedViews(recycler)
      var bottom: Int
      val left = paddingLeft
      val right = width - paddingRight
      val visibleViews = Math.min(state.itemCount, cardCount)
      for (i in visibleViews - 1 downTo 0) {
        val v = recycler.getViewForPosition(i)
        addView(v)
        measureChildWithMargins(v, 0, 0)
        bottom = oldTop + getDecoratedMeasuredHeight(v)
        layoutDecorated(v, left, oldTop, right, bottom)

        when (i) {
          0 -> {
            v.translationX = horizontalScroll.toFloat()
            v.translationY = verticalScroll.toFloat()
            val modifier: Float = verticalScroll.clamp(-1, 1).toFloat()

            val height4: Float = v.height * 4F

            val clamp = (v.translationY / height4).clamp(-height4, height4) * -10F


            v.rotation = Math.min(v.translationX / (v.width * 4F), v.width * 4F) * 90F * clamp.clamp(-6F, 6F)
          }
          1 -> {

            val sideScroll: Float = v.width.toFloat() / 4F


            val scrollPercentage = (horizontalScroll.absoluteValue.toFloat() / sideScroll) / 10F
            val scale = 0.9F + Math.max(0F, Math.min(0.1F, scrollPercentage))

            v.scaleX = scale
            v.scaleY = scale

          }
          else -> {
            v.scaleX = 0.8F
            v.scaleY = 0.8F
          }
        }
      }
    }
  }


  override fun scrollToPosition(position: Int) {
    super.scrollToPosition(position)
  }

  override fun canScrollHorizontally() = true

  override fun canScrollVertically() = true

  override fun offsetChildrenHorizontal(dx: Int) {
    super.offsetChildrenHorizontal(dx)
    Log.d("asd", "$dx")
  }

  override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    verticalScroll -= dy
    onLayoutChildren(recycler, state)
    return dy
  }

  override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    horizontalScroll -= dx
    onLayoutChildren(recycler, state)
    return dx
  }
}


fun Int.clamp(lower: Int, upper: Int): Int {
  return when {
    this < lower -> lower
    this > upper -> upper
    else -> this
  }
}

fun Float.clamp(lower: Float, upper: Float): Float {
  return when {
    this < lower -> lower
    this > upper -> upper
    else -> this
  }
}

fun Double.clamp(lower: Double, upper: Double): Double {
  return when {
    this < lower -> lower
    this > upper -> upper
    else -> this
  }
}

fun Long.clamp(lower: Long, upper: Long): Long {
  return when {
    this < lower -> lower
    this > upper -> upper
    else -> this
  }
}