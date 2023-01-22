package it.spaghetticode.bgm.webapp

import it.spaghetticode.bgm.webapp.repository.GameRepository
import it.spaghetticode.bgm.webapp.repository.UserRepository
import it.spaghetticode.bgm.webapp.service.GameService
import it.spaghetticode.bgm.webapp.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebAppApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var gameRepository: GameRepository
    @Autowired
    lateinit var gameService: GameService
    @Autowired
    lateinit var userService: UserService
    @Test
    fun contextLoads() {
//        val user1 = User()
//        user1.username = "TuxDave"
//        user1.password = "wewewewewe"
//        user1.games = listOf()
//
//        userRepository.saveAndFlush(user1)
//
//        val game1 = Game()
//        game1.gameData = "megaCioane".toByteArray()
//        game1.description = "bella pe egli"
//        game1.admin = userRepository.findByIdOrNull(1)!!
//
//        gameRepository.saveAndFlush(game1)

//        println(userService.findByGameNullable(gameService.findById(1)))
    }

}
