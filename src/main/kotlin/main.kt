data class Post(
    val id: Int,
    val contentL: String,
    val likes: Int = 0,
    val attachments: Array<Attachment> = emptyArray()
) {
    fun printContent() {
        println("Post with$id printted it content:$contentL")
    }
}

data class Audio(
    val id: Int,
    val name: String
)

data class Video(
    val id: Int,
    val lenght: Int
)

interface Attachment {
    val type: String
}

data class AudioAtachment(val audio: Audio) : Attachment {
    override val type = "audio"
}

data class VideoAtachment(val video: Video) : Attachment {
    override val type = "video"
}

object WallService {
    private var posts = emptyArray<Post>()
    fun add(post: Post) {
        posts += post
    }

    fun like(postId: Int) : Boolean{
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                posts[index] = post.copy(likes = post.likes + 1)
                return true
            }
        }
        return false
    }

    fun print() {
        for (post in posts) {
            println(post)
        }
        println()
    }
}

fun main() {
    val post = Post(1, "Text", attachments = arrayOf(AudioAtachment(Audio(1, "song.mp3"))))
    post.printContent()
    println((post.attachments[0] as AudioAtachment).audio)

    WallService.add(post)
    WallService.add(Post(2, "Text", attachments = arrayOf(AudioAtachment(Audio( 2,"anothersong.mp3")), VideoAtachment(Video( 2,60)))))

    WallService.print()
    WallService.like(1)
    WallService.print()
}