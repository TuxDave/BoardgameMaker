package it.spaghetticode.bgm.webapp.repository

import it.spaghetticode.bgm.webapp.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>{
    fun findUserByUsername(username: String): User?
}