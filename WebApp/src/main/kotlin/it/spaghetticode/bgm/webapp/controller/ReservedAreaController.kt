package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.service.GameService
import it.spaghetticode.bgm.webapp.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

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

        if(usernameInput == ""){
            return "redirect:/reserved/edit-account?usernameIncorrect=1"
        }else{
            if(usernameInput.lowercase() != user.username.lowercase()){
                userService.updateUsername(user, usernameInput)
            }
        }

        if(password1 != ""){
            if(password2 == password1){
                userService.updatePassword(user, password1)
            }else{
                return "redirect:/reserved/edit-account?passwordIncorrect=1"
            }
        }
        return "redirect:/reserved/overview"
    }

    @GetMapping("edit-game")
    fun editGamePage(
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestParam("gameId") gameId: Long
    ): String {
        val user = userService.findById(request.session.getAttribute("userId") as Long? ?: -1) ?: return "redirect:/login"

        val game = gameService.findById(gameId)
        game?.let {
            if(game.admin == user){
                request.setAttribute("title","Edit Game")
                request.setAttribute("content","contents/reserved_area/game_edit")
                println(game.description)
                request.session.setAttribute("editingGameId", game.id)

                request.setAttribute("game", game)
                return "pages/base"
            } else return "redirect:/reserved/overview"
        } ?: return "redirect:/reserved/overview"
    }

    @PostMapping("edit-game")
    fun editGameAction(
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam blob: MultipartFile?
    ): String {
        val user = userService.findById(request.session.getAttribute("userId") as Long? ?: -1) ?: return "redirect:/login"

        val game = gameService.findById(request.session.getAttribute("editingGameId") as Long)
        return game?.let {
            var edit = false
            if(game.name != name && name != ""){
                game.name = name
                edit = true
            }
            if(description != game.description){
                game.description = description
                edit = true
            }
            blob?.let {
                //TODO: check if is a ZIP within a game inside
                game.gameData = blob.bytes
                edit = true
            }
            if(edit){
                gameService.save(game)
                request.session.removeAttribute("editingGameId")
            }
            return "redirect:/reserved/overview"
        } ?: "redirect:/reserved/overview"
    }
}