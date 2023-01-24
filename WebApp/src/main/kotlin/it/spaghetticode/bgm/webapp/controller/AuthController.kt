package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.entity.User
import it.spaghetticode.bgm.webapp.service.UserService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/")
class AuthController {
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var encoder: BCryptPasswordEncoder

    @GetMapping("login")
    fun loginPage(
        @RequestParam("usernameIncorrect") usernameIncorrect: Int?,
        @RequestParam("passwordIncorrect") passwordIncorrect: Int?,
        request: HttpServletRequest
    ): String {
        request.setAttribute("usernameIncorrect", usernameIncorrect)
        return "pages/login"
    }

    @PostMapping("login")
    fun loginAction(
        @RequestParam username: String,
        @RequestParam password: String,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Unit {
        val user = userService.findByUsername(username)
        if (user == null) {
            response.sendRedirect("/login?usernameIncorrect=1")
        } else {
            if (encoder.matches(password, user.password)) {
                request.session.setAttribute("userId", user.id)
                response.sendRedirect("/")
            } else {
                response.sendRedirect("/login?passwordIncorrect=1")
            }
        }
    }

    @GetMapping("logout")
    fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Unit {
        request.session.removeAttribute("userId")
        response.sendRedirect("/")
    }

    @GetMapping("signin")
    fun loginPage(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String {
        return "pages/signin"
    }

    @PostMapping("signin")
    fun signinPage(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam passwordConfirm: String,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Unit {
        val user = userService.findByUsername(username)
        user?.let {
            response.addCookie(Cookie("signInUsername",user.username))
            response.sendRedirect("/signin?usernameIncorrect=1")
        } ?: run {
            if(password != passwordConfirm || password == ""){
                response.addCookie(Cookie("signInUsername",username))
                response.sendRedirect("/signin?passwordIncorrect=1")
            }else{
                var newUser = User()
                newUser.username = username
                newUser.password = password
                userService.save(newUser)
                newUser = userService.findByUsername(username)!!
                request.cookies.find { it.name == "signInUsername" }?.maxAge = 0
                request.session.setAttribute("userId", newUser.id)
                response.sendRedirect("/")
            }
        }
    }
}