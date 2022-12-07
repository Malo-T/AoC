package kit.astar

class AStarNode<N>(
    val item: N,
    var costFromStart: Float,
    var previous: AStarNode<N>?
)
