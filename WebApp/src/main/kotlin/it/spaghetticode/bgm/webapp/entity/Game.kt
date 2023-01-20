package it.spaghetticode.bgm.webapp.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.EqualsAndHashCode

@Entity(name = "Game")
@Table(name = "GAME")
@EqualsAndHashCode
data class Game @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @NotNull(message = "The game can't be null")
    var gameData: ByteArray = byteArrayOf(),

    var description: String? = null
) {
    @NotNull(message = "The admin of the game can't be null")
    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = false
    )
    @JoinColumn(name = "idUserAdmin", nullable = false)
    lateinit var admin: User
}