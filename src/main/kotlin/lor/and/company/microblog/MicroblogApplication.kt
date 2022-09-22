package lor.and.company.microblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableMongoRepositories
@EnableWebMvc
class MicroblogApplication

fun main(args: Array<String>) {
    runApplication<MicroblogApplication>(*args)
}
