package lor.and.company.microblog.models

import org.ocpsoft.prettytime.PrettyTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Document("blogposts")
data class BlogPost(
    @Id
    val id: String?,
    val title: String?,
    val header: String?,
    val content: String?,
    val author: String?,
    val dateCreated: Long?,
    val dateEdited: Long?,
): Serializable {
    companion object {
        private val timeFormatter = PrettyTime()

        val random = Random()

        fun randomId(): String {
            val leftLimit = 48 // numeral '0'
            val rightLimit = 122 // letter 'z'
            val length = 8

            return random.ints(leftLimit, rightLimit + 1)
                .filter { i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) }
                .limit(length.toLong())
                .collect({ StringBuilder() }, java.lang.StringBuilder::appendCodePoint, java.lang.StringBuilder::append)
                .toString()
        }
    }

    constructor(): this(null, null, null, null, null, null, null)

    constructor(title: String, header: String, content: String):
            this(randomId(), title, header, content, "Lor", System.currentTimeMillis(), -1)

    fun readableDateCreated() = with(Date(dateCreated!!)) {
        "${SimpleDateFormat("MMMM d, YYYY").format(this)} — ${timeFormatter.format(this)}"
    }

    fun readableDateEdited() = timeFormatter.format(Date(dateEdited!!)).toString()
}
