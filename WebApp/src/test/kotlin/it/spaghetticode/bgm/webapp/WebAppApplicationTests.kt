package it.spaghetticode.bgm.webapp

import it.spaghetticode.bgm.webapp.entity.Game
import it.spaghetticode.bgm.webapp.repository.GameRepository
import it.spaghetticode.bgm.webapp.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class WebAppApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var gameRepository: GameRepository
    @Test
    fun contextLoads() {
//        val user1 = User()
//        user1.username = "TuxDave"
//        user1.password = "wewewewewe"
//        user1.games = listOf()
//
//        userRepository.saveAndFlush(user1)

//        val game1 = Game()
//        game1.gameData = "megaCioane".toByteArray()
//        game1.description = "bella pe egli"
//        game1.admin = userRepository.findByIdOrNull(1)!!
//
//        gameRepository.saveAndFlush(game1)

        println(gameRepository.findByAdminId(1))
    }

}
