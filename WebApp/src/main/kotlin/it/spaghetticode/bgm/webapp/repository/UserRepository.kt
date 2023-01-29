package it.spaghetticode.bgm.webapp.repository

import it.spaghetticode.bgm.webapp.entity.User
import jakarta.transaction.TransactionScoped
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@Transactional
interface UserRepository: JpaRepository<User, Long>{
    fun findUserByUsername(username: String): User?

    @Modifying
    @Query("UPDATE User u SET u.username = (:username) WHERE u.id = (:user)")
    fun updateUserUsername(
        @Param ("user") user: Long,
        @Param("username") username: String
    )

    @Modifying
    @Query("UPDATE User u SET u.password = (:hash) WHERE u.id = (:user)")
    fun updateUserPasswordHash(
        @Param ("user") user: Long,
        @Param ("hash") hash: String
    )
}