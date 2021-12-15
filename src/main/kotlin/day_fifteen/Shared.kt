package dev.schlaubi.aoc.day_fifteen

import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines
import org.xguzm.pathfinding.PathFinderOptions
import org.xguzm.pathfinding.grid.GridCell
import org.xguzm.pathfinding.grid.NavigationGrid
import org.xguzm.pathfinding.grid.finders.AStarGridFinder
import org.xguzm.pathfinding.grid.finders.GridFinderOptions

fun Any.runTask(context: TaskContext, expand: List<List<Int>>.() -> List<List<Int>> = { this }) {
    val matrix = readLines().map { it.map { char -> char.digitToInt() } }.expand()
    val maxY = matrix.size
    val maxX = matrix.first().size

    val cells = Array(maxX) { x ->
        Array(maxY) { y ->
            GridCell(x, y)
        }
    }

    val grid = SafetyNavigationGrid(matrix, cells)

    val options = GridFinderOptions().apply {
        allowDiagonal = false
    }
    val pathFinder = AStarGridFinder(GridCell::class.java, options)
    val path = pathFinder.findPath(0, 0, maxY - 1, maxX - 1, grid)

    val sum = path.sumOf { matrix[it.x][it.y] }

    context.output(sum)
}

private class SafetyNavigationGrid(val safetyMatrix: List<List<Int>>, cells: Array<Array<GridCell>>) : NavigationGrid<GridCell>(cells, true) {
    override fun getMovementCost(node1: GridCell, node2: GridCell, opt: PathFinderOptions): Float {
        return safetyMatrix[node2.x][node2.y].toFloat()
    }
}