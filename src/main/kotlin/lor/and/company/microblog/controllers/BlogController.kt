package lor.and.company.microblog.controllers

import lor.and.company.microblog.models.BlogPost
import lor.and.company.microblog.repository.BlogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@EnableCaching
class BlogController {

    @Autowired
    lateinit var blogRepo: BlogRepository

    @GetMapping("/", "/index")
    fun index(model: Model): String {
        model.addAttribute("blogposts", blogRepo.getAllBlogPosts().sortedByDescending { it.dateCreated })
        return "index"
    }

    @GetMapping("/blog/{id}")
    fun blogpostView(@PathVariable(value = "id") id: String, model: Model): String {
        val blog = blogRepo.getBlogPostById(id)
        model.addAttribute("blog", blog)
        return "blogpost"
    }

    @GetMapping("/admin/blog")
    fun blogpostForm(model: Model): String {
        model.addAttribute("post", BlogPost())
        model.addAttribute("action", "create")
        return "blogpost_form"
    }

    @PostMapping("/admin/blog")
    fun blogpostSubmit(@ModelAttribute blogPost: BlogPost): String {
        with (blogPost.copy(
            id = BlogPost.randomId(),
            dateCreated = System.currentTimeMillis(),
            dateEdited = null
        )) {
            blogRepo.save(this)
            return "redirect:/blog/${this.id}"
        }
    }

    @GetMapping("/admin/blog/{id}/edit")
    fun blogpostEdit(@PathVariable(value = "id") id: String, model: Model): String {
        val blog = blogRepo.getBlogPostById(id)
        model.addAttribute("post", blog)
        model.addAttribute("action", "edit")
        return "blogpost_form"
    }

    @PostMapping("/admin/blog/{id}/edit")
    fun blogpostEditSubmit(@PathVariable(value = "id") id: String, @ModelAttribute blogPost: BlogPost): String {
        with (blogPost.copy(
            id = id,
            dateCreated = blogRepo.getBlogPostById(id).dateCreated,
            dateEdited = System.currentTimeMillis()
        )) {
            blogRepo.save(this)
            return "redirect:/blog/${this.id}"
        }
    }

    @GetMapping("/admin/blog/{id}/delete")
    fun blogpostDelete(@PathVariable(value = "id") id: String, model: Model): String {
        val blog = blogRepo.getBlogPostById(id)
        model.addAttribute("post", blog)
        return "warning"
    }

    @PostMapping("/admin/blog/{id}/delete")
    fun blogpostDeleteCommit(@PathVariable(value = "id") id: String, @ModelAttribute blogPost: BlogPost): String {
        blogRepo.removeBlogPostById(id)
        return "redirect:/"
    }
}
