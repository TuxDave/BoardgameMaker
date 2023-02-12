package it.spaghetticode.bgm.webapp.repository

import it.spaghetticode.bgm.webapp.entity.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GameRepository: JpaRepository<Game, Long>{
    fun findByAdminId(admin: Long): List<Game>
    fun searchByNameStartingWith(name: String): List<Game>
    @Query("SELECT game FROM Game game WHERE game.admin.username LIKE CONCAT((:username), '%')")
    fun searchByAdminUsernameStartingWith(@Param("username") username: String): List<Game>

    @Query("INSERT INTO LIKE_USER_GAME VALUE ((:userId), (:gameId))", nativeQuery = true)
    fun addLike(@Param("userId") userId: Long, @Param("gameId") gameId: Long)

    @Query("DELETE FROM LIKE_USER_GAME WHERE idGame = (:gameId) AND idUser = (:userId)", nativeQuery = true)
    fun removeLike(@Param("userId") userId: Long, @Param("gameId") gameId: Long)
}