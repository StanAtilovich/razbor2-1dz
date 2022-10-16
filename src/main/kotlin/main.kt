data class Post(
    val id: Int,
    val contentL: String,
    val likes: Int = 0
) {
    fun printContent() {
        println("Post with$id printted it content:$contentL")
    }
}

object WallService {
    private var posts = emptyArray<Post>()
    fun add(post: Post) {
        posts += post
    }

    fun like(postId: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                posts[index] = post.copy(likes = post.likes + 1)
            }
        }
    }

    fun print() {
        for (post in posts) {
            println(post)
        }
        println()
    }
}

fun main() {
    val post = Post(1, "Text")
    post.printContent()
    WallService.add(post)
    WallService.add(Post(2, "Another text"))
    WallService.print()
    WallService.like(1)
    WallService.print()
}