package lor.and.company.microblog.repository

import lor.and.company.microblog.models.BlogPost
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface BlogRepository: MongoRepository<BlogPost, Int> {

    @Cacheable(value = ["blogCache"], key ="#id")
    @Query("{ '_id' : '?0' }")
    fun getBlogPostById(id: String): BlogPost

    @Query("{ }")
    fun getAllBlogPosts(): List<BlogPost>

    @CachePut(value = ["blogCache"], key = "#blogPost.id")
    fun save(blogPost: BlogPost): BlogPost

    @CacheEvict(value = ["blogCache"], key = "#id")
    @Query("{ '_id' : '?0' }", delete = true)
    fun removeBlogPostById(id: String)
}
