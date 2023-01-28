package it.spaghetticode.bgm.webapp.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller("/")
class MainController {
    @GetMapping("/", "index/", "index")
    fun index(
        request: HttpServletRequest
    ): String {
        request.setAttribute("title","Boardgame Maker")
        request.setAttribute("content","contents/index")
        return "pages/base"
    }
}