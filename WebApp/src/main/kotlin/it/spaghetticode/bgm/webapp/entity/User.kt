package it.spaghetticode.bgm.webapp.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import lombok.EqualsAndHashCode
import lombok.ToString

@Entity(name =  "User")
@Table(name = "USER")
@EqualsAndHashCode
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(unique = true)
    @NotNull(message = "The username can't be null")
    @NotBlank(message = "The username can't be blank")
    @Min(6, message = "The username must be at least 6 longer")
    lateinit var username: String

    @NotNull
    @NotBlank
    lateinit var password: String

    @OneToMany(
        mappedBy = "admin",
        fetch = FetchType.LAZY
    )
    @ToString.Exclude
    var games: List<Game> = listOf()
}