package it.spaghetticode.bgm.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller("/")
class MainController {
    @GetMapping("/","index/","index")
    fun index(): String {
        return "index"
    }
}