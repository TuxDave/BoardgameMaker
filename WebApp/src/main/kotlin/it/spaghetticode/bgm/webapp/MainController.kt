package it.spaghetticode.bgm.webapp

import it.spaghetticode.bgm.webapp.repository.UserRepository
import it.spaghetticode.bgm.webapp.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.awt.PageAttributes.MediaType

@Controller("/")
class MainController {
    @Autowired lateinit var userService: UserService

    @GetMapping("/","index/","index")
    fun index(): String {
        return "pages/index"
    }

    @GetMapping("login")
    fun loginPage(
        @RequestParam("usernameIncorrect") usernameIncorrect: Int?,
        @RequestParam("passwordIncorrect") passwordIncorrect: Int?,
        request: HttpServletRequest
    ): String{
        request.setAttribute("usernameIncorrect", usernameIncorrect)
        return "pages/login"
    }

    @PostMapping("loginAction")
    fun loginAction(
        @RequestParam username: String,
        @RequestParam password: String,
        response: HttpServletResponse
    ): Unit{
        val user = userService.findByUsername(username)
        if(user == null){
            response.sendRedirect("/login?usernameIncorrect=1")
        }else{
            // TODO: decode password and check (and encode) 
        }
    }
}