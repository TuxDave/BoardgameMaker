package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.entity.Game
import it.spaghetticode.bgm.webapp.service.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller("/")
class MainController {

    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/", "index/", "index")
    fun index(
        request: HttpServletRequest,
        @RequestParam("name") name: String?,
        @RequestParam("scope") scope: String?,
        @RequestParam("page") page: Int?
    ): String {
        request.setAttribute("title","Boardgame Maker")
        request.setAttribute("content","contents/index")

        val page: Int = page ?: 1
        var pages: Int;

        var gameList: List<Game> = if(name != null && scope != null){
            if(scope == "game"){ //search for game name
                val temp = gameService.searchByName(name)
                pages = temp.getPageCount()
                temp.getPage(page)
            }else{
                val temp = gameService.searchByAuthorUsername(name)
                pages = temp.getPageCount()
                temp.getPage(page)
            }
        }else{
            val temp = gameService.findAll()
            pages = temp.getPageCount()
            temp.getPage(page)
        }

        val user = userService.findById((request.session.getAttribute("userId") ?: -1L) as Long)
        user?.let {
            request.setAttribute("user", it)
        }

        request.setAttribute("games", gameList)
        request.setAttribute("pages", maxOf(1, pages))
        request.setAttribute("selectedPage", page)
        request.session.setAttribute("savedURL", "${request.requestURI}?${request.queryString}")

        return "pages/base"
    }

    @GetMapping("do-undo-like")
    fun doUndoLikeAction(
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestParam("gameId") gameId: Long,
        @RequestParam("userId") userId: Long
    ): String {
        val user = userService.findById(request.session.getAttribute("userId") as Long? ?: -1) ?: return "redirect:/login"
        val requiredUser = userService.findById(userId)
        if(user == requiredUser){
            val game = gameService.findById(gameId)
            game?.let {
                if(game.likes.contains(user))
                    gameService.removeLike(user, game)
                else
                    gameService.addLike(user, game)
            }
            return "redirect:" + request.session.getAttribute("savedURL")
        }else{
            return "redirect:/login"
        }
    }
}