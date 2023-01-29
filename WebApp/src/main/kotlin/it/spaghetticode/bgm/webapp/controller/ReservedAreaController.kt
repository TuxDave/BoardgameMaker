package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.service.GameService
import it.spaghetticode.bgm.webapp.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("reserved/")
class ReservedAreaController {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var gameService: GameService

    @GetMapping("overview")
    fun overviewPage(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String {
        val user = userService.findById(request.session.getAttribute("userId") as Long? ?: -1) ?: return "redirect:/login"

        request.setAttribute("title","Overview")
        request.setAttribute("content","contents/reserved_area/overview")

        request.setAttribute("username",user.username)
        request.setAttribute("games", user.games)

        return "pages/base"
    }
}