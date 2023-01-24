package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.entity.User
import it.spaghetticode.bgm.webapp.service.UserService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller("/")
class MainController {
    @GetMapping("/", "index/", "index")
    fun index(): String {
        return "pages/index"
    }
}