package kit.astar

interface AStarGraph<N> {
    val start: N
    val end: N
    fun neighborsOf(currentNode: N): Iterable<N>
}
