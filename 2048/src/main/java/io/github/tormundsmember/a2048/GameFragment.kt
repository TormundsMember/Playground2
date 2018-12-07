package io.github.tormundsmember.a2048

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*

class GameFragment : Fragment() {

  private val adapter = GameAdapter()


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_game, container, false)
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


    listGameTiles.layoutManager = object : GridLayoutManager(view.context, 4) {
      override fun canScrollVertically(): Boolean {
        return false
      }
    }
    listGameTiles.adapter = adapter
    val grid = createGrid()
    adapter.updateGrid(grid)

    var initialPoint = Point(-1F, -1F)

    listGameTiles.setOnTouchListener { _, event ->

      when (event.action) {
        MotionEvent.ACTION_DOWN -> {
          initialPoint = Point(event.x, event.y)
        }
        MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
          if (initialPoint.x != -1F && initialPoint.y != -1F) {

            val currentPoint = Point(event.x, event.y)
            if (initialPoint.distanceTo(currentPoint) >= DipConversionUtil.dpToPx(100)) {

              val direction = determineDirection(initialPoint, currentPoint)
              moveGrid(direction)

            }
            initialPoint = Point(-1F, -1F)
          }
        }
      }

      true
    }
  }

  private fun createGrid(): List<Cell> {
    val grid = MutableList(16) { Cell(it / 4, it % 4, null) }
    val copy = grid.asSequence().withIndex().map {
      it.index
    }.toMutableList()

    val random = Random()
    val initialValueCount = random.let {
      1 + it.nextInt(3)
    }

    for (i in 0..initialValueCount) {
      val idx = random.nextInt(copy.size)
      val cell = grid[copy[idx]]
      grid[copy[idx]] = cell.copy(value = 2)
      copy.removeAt(idx)
    }

    return grid
  }


  fun determineDirection(initialPoint: Point, currentPoint: Point): Direction {
    val dpToPx = DipConversionUtil.dpToPx(100)

    return if (Math.abs(initialPoint.x - currentPoint.x) >= dpToPx) {
      if (initialPoint.x < currentPoint.x)
        Direction.RIGHT
      else
        Direction.LEFT
    } else {
      if (Math.abs(initialPoint.y - currentPoint.y) >= dpToPx) {
        if (initialPoint.y < currentPoint.y) {
          Direction.DOWN
        } else
          Direction.UP
      } else {
        Direction.NONE
      }
    }
  }

  fun moveGrid(direction: Direction) {

    fun shiftUp(grid: MutableList<Cell>): MutableList<Cell> {
      val cols = grid.groupBy { it.col }
      return grid
    }


    fun shiftDown(grid: MutableList<Cell>): MutableList<Cell> {
      val cols = grid.groupBy { it.col }
      return grid
    }

    fun shiftLeft(grid: MutableList<Cell>): MutableList<Cell> {
      val rows = grid.groupBy { it.row }

      return grid
    }

    fun shiftRight(grid: MutableList<Cell>): MutableList<Cell> {
      val rows = grid.asSequence().groupBy { it.row }.map { it.value.reversed() }.toList()



      listOf(1,2,null,null).partition {
        it != null
      }

      return grid
    }

    val grid = adapter.getGrid()


    when (direction) {

      GameFragment.Direction.UP -> shiftUp(grid)
      GameFragment.Direction.DOWN -> shiftDown(grid)
      GameFragment.Direction.LEFT -> shiftLeft(grid)
      GameFragment.Direction.RIGHT -> shiftRight(grid)
      GameFragment.Direction.NONE -> grid
    }

  }

  enum class Direction {
    UP, DOWN, LEFT, RIGHT, NONE
  }
}


class Point(val x: Float, val y: Float) {

  fun distanceTo(other: Point): Double {

    val a = Math.abs(x - other.x).toDouble()
    val b = Math.abs(y - other.y).toDouble()

    return Math.sqrt(Math.pow(a, 2.toDouble()) + Math.pow(b, 2.toDouble()))

  }


}

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

  private var grid = List(16) {
    Cell(it / 4, it % 4, null)
  }

  fun updateGrid(grid: List<Cell>) {
    this.grid = grid
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int = grid.size

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_game, p0, false))
  }

  override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

    val i = grid[p1]
    if (i.value == null)
      p0.txtValue.text = ""
    else
      p0.txtValue.text = i.value.toString()
  }

  fun getGrid() = grid.toMutableList()


  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    val card: CardView = itemView.findViewById(R.id.card)
    val txtValue: TextView = itemView.findViewById(R.id.txtValue)

  }


}

data class Cell(val row: Int, val col: Int, val value: Int?)