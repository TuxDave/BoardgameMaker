package it.spaghetticode.bgm.webapp.controller

import it.spaghetticode.bgm.webapp.entity.Game
import it.spaghetticode.bgm.webapp.service.GameService
import it.spaghetticode.bgm.webapp.service.getPage
import it.spaghetticode.bgm.webapp.service.getPageCount
import it.spaghetticode.bgm.webapp.service.limit
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller("/")
class MainController {

    @Autowired
    lateinit var gameService: GameService

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
                println(temp)
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
        request.setAttribute("games", gameList)
        request.setAttribute("pages", maxOf(1, pages))
        request.setAttribute("selectedPage", page)

        return "pages/base"
    }
}