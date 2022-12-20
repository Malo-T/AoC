package day07


data class File(
    val name: String,
    val size: Int,
)

data class Directory(
    val name: String,
    val directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf(),
) {
    // must not be in the constructor
    lateinit var parent: Directory

    fun size(): Int = files.sumOf { it.size } + directories.sumOf { it.size() }
    fun children(): List<Directory> = directories + directories.flatMap { it.children() }
}

private fun root() = Directory(name = "/").apply { parent = this }

/** Return parent or child directory depending on command */
private fun Directory.cd(cmd: String): Directory =
    when (val dir = cmd.substring(5)) {
        ".." -> parent
        else -> directories.firstOrNull { it.name == dir }
            ?: Directory(name = dir)
                .apply { parent = this@cd }
                .also { directories.add(it) }
    }

/** Create and add file to current directory. Return current directory */
private fun Directory.addFile(path: String): Directory = apply {
    val (size, name) = path.split(' ')
    if (files.none { it.name == name }) {
        files += File(
            name = name,
            size = size.toInt()
        )
    }
}

class Day07 {

    fun parse1(input: String): Directory =
        root().apply {
            input.lines()
                .drop(1) // first line is "$ cd /" -> root
                .fold(this) { dir, line ->
                    when {
                        line == "$ ls" -> dir// do nothing
                        line.startsWith("$ cd ") -> dir.cd(line)// move to directory (create it if necessary)
                        line.startsWith("dir ") -> dir // do nothing (directories are created on 'cd')
                        line[0].isDigit() -> dir.addFile(line)// add file to directory
                        else -> throw IllegalStateException("Invalid line: $line")
                    }
                }
        }

    fun part1(root: Directory): Int =
        root.children()
            .map { it.size() }
            .filter { it < 100_000 }
            .sum()

    fun part2(root: Directory): Int {
        TODO()
    }

}
