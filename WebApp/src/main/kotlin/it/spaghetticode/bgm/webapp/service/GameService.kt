package it.spaghetticode.bgm.webapp.service

import it.spaghetticode.bgm.webapp.entity.Game
import it.spaghetticode.bgm.webapp.entity.User
import it.spaghetticode.bgm.webapp.repository.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.math.ceil

const val PAGE_SIZE = 15
fun List<Game>.limit(from: Int, to: Int): List<Game>{
    val from = maxOf(from, 0)
    val to = maxOf(to, from)
    return this.subList(from, to)
}

/**
 * @param pageNumber: page 1 is the first
 */
fun List<Game>.getPage(pageNumber: Int): List<Game>{
    val pages = ceil(size / PAGE_SIZE.toDouble()).toInt()
    val pageNumber = maxOf(minOf(pageNumber, pages), 1)
    return this.limit(maxOf(0, PAGE_SIZE * (pageNumber - 1)), minOf(PAGE_SIZE * (pageNumber - 1) + PAGE_SIZE, size))
}
fun List<Game>.getPageCount(): Int{
    return ceil(size / PAGE_SIZE.toDouble()).toInt()
}

interface GameService{
    fun findById(id: Long): Game?
    fun findByAdminId(admin: Long): List<Game>
    fun save(game: Game): Unit
    fun delete(game: Game): Unit
    fun deleteById(id: Long): Unit
    fun searchByName(name: String): List<Game>
    fun searchByAuthorUsername(username: String): List<Game>
    fun findAll(): List<Game>
    fun addLike(user: User, game: Game): Unit
    fun removeLike(user: User, game: Game): Unit
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

    override fun delete(game: Game) {
        gameRepository.delete(game)
    }

    override fun deleteById(id: Long) {
        gameRepository.deleteById(id)
    }

    override fun searchByName(name: String): List<Game> {
        return gameRepository.searchByNameStartingWith(name)
    }

    override fun searchByAuthorUsername(username: String): List<Game> {
        return gameRepository.searchByAdminUsernameStartingWith(username)
    }

    override fun findAll(): List<Game> {
        return gameRepository.findAll()
    }

    override fun addLike(user: User, game: Game) {
        try{
            gameRepository.addLike(user.id, game.id)
        }catch (_: Exception){}
    }

    override fun removeLike(user: User, game: Game) {
        gameRepository.removeLike(user.id, game.id)
    }
}