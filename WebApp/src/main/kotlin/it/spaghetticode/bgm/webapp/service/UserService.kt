package it.spaghetticode.bgm.webapp.service

import it.spaghetticode.bgm.webapp.entity.Game
import it.spaghetticode.bgm.webapp.entity.User
import it.spaghetticode.bgm.webapp.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface UserService {
    fun findById(id: Long): User?
    fun findByGameNullable(game: Game?): User?
    fun findByGame(game: Game): User
    fun save(user: User): Unit
}

@Service
class UserServiceImpl: UserService{

    @Autowired lateinit var userRepository: UserRepository

    override fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    override fun findByGameNullable(game: Game?): User? {
        return game?.let {
            findById(game.admin.id)
        }
    }

    override fun findByGame(game: Game): User {
        return findById(game.admin.id)!!
    }

    override fun save(user: User) {
        userRepository.saveAndFlush(user)
    }

}