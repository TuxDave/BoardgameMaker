package it.spaghetticode.bgm.webapp.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity(name = "Game")
@Table(name = "GAME")
data class Game @JvmOverloads constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @NotNull(message = "The game can't be null")
    var gameData: ByteArray = byteArrayOf(),

    var description: String? = null,

    @Size(min = 2, max = 50, message = "The game's name must be 2-50 length")
    var name: String? = null
) {
    @NotNull(message = "The admin of the game can't be null")
    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = false
    )
    @JoinColumn(name = "idUserAdmin", nullable = false)
    lateinit var admin: User

    @ManyToMany(
        mappedBy = "likes"
    )
    var likes: List<User> = listOf()
}