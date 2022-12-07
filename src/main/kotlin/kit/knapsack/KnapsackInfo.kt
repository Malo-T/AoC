package kit.knapsack

data class KnapsackInfo<T>(val value: Int, val items: List<T>) {
    override fun toString(): String {
        return "{v=$value;${items.joinToString("")}}"
    }
}
