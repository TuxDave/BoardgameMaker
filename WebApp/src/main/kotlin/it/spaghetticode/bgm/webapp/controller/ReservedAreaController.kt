package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.service.GameService
import it.spaghetticode.bgm.webapp.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

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

    @GetMapping("edit-account")
    fun editAccountPage(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String {
        val user = userService.findById(request.session.getAttribute("userId") as Long? ?: -1) ?: return "redirect:/login"

        request.setAttribute("title","Edit Account")
        request.setAttribute("content","contents/reserved_area/account_edit")

        request.setAttribute("username", user.username)

        return "pages/base"
    }

    @PostMapping("edit-account")
    fun editAccountAction(
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestParam usernameInput: String,
        @RequestParam password1: String,
        @RequestParam password2: String
    ): String {
        val user = userService.findById(request.session.getAttribute("userId") as Long? ?: -1) ?: return "redirect:/login"

        if(usernameInput != "" && usernameInput.lowercase() != user.username.lowercase()){
            user.username
            userService.updateUsername(user, usernameInput)
        }
        if(password1 != "" && password2 == password1){
            userService.updatePassword(user, password1)
        }
        // TODO: finish to refine 
        return "redirect:/reserved/overview"
    }
}