package dev.schlaubi.aoc.day_ten

fun String.findSyntaxErrors(): Pair<Int, List<SyntaxTag>> {
    val openTags: MutableList<SyntaxTag> = mutableListOf()

    forEach {
        val current = SyntaxTag.forTag(it)
        if (it == current.char) { // check if it is an opening tag
            openTags += current
        } else if (openTags.isNotEmpty()) {
            val lastOpenTag = openTags.lastOrNull()
            if (it != lastOpenTag?.closingChar) {
                return current.score to openTags
            } else {
                openTags.removeLast()
            }
        }
    }

    return 0 to openTags
}

enum class SyntaxTag(val char: Char, val closingChar: Char, val score: Int, val closeTagScore: Int) {
    PARENTHESES('(', ')', 3, 1),
    BRACKETS('[', ']', 57, 2),
    CURLY_BRACKETS('{', '}', 1197, 3),
    TAGS('<', '>', 25137, 4);

    companion object {
        val openingTags: List<Char> = values().map(SyntaxTag::char)

        fun forTag(tag: Char) = values().first { it.char == tag || it.closingChar == tag }
    }
}
