package it.spaghetticode.bgm.webapp.repository

import it.spaghetticode.bgm.webapp.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository: JpaRepository<User, Long>