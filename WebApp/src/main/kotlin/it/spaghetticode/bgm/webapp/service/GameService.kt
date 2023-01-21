package it.spaghetticode.bgm.webapp.service

import it.spaghetticode.bgm.webapp.entity.Game
import it.spaghetticode.bgm.webapp.repository.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface GameService{
    fun findById(id: Long): Game?
    fun findByAdminId(admin: Long): List<Game>
    fun save(game: Game): Unit
}

@Service
class GameServiceImpl: GameService{

    @Autowired
    lateinit var gameRepository: GameRepository
    override fun findById(id: Long): Game? {
        return gameRepository.findByIdOrNull(id)
    }

    override fun findByAdminId(admin: Long): List<Game> {
        return gameRepository.findByAdminId(admin)
    }

    override fun save(game: Game) {
        gameRepository.saveAndFlush(game)
    }
}