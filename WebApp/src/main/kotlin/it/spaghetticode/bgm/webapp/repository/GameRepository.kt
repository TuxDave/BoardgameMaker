package it.spaghetticode.bgm.webapp.repository

import it.spaghetticode.bgm.webapp.entity.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository: JpaRepository<Game, Long>{
    fun findByAdminId(admin: Long): List<Game>
}