package dev.mary.infrastructure.dto

import dev.mary.compat.SerialOffsetDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    val id: Int,
    val name: String,
    val area: Area? = null,
    @SerialName("has_test")
    val hasTest: Boolean? = null,
    @SerialName("published_at")
    val publishedAt: SerialOffsetDateTime? = null,
    @SerialName("created_at")
    val createdAt: SerialOffsetDateTime? = null,
    val snippet: Snippet = Snippet(),
    val schedule: Schedule = Schedule(),
    val salary: Salary = Salary(),
    val address: Address = Address(),
    val contacts: Contacts = Contacts(),
    @SerialName("alternate_url")
    val alternateUrl: String? = null,
    @SerialName("apply_alternate_url")
    val applyAlternateUrl: String? = null
) {

    @Serializable
    data class Area(
        val id: String,
        val name: String,
        val url: String
    )

    @Serializable
    data class Snippet(
        val requirement: String? = null,
        val responsibility: String? = null
    )

    @Serializable
    data class Schedule(
        val name: String? = null
    )

    @Serializable
    data class Salary(
        val from: Int? = null,
        val to: Int? = null,
        val currency: String? = null
    )

    @Serializable
    data class Address(
        val city: String? = "",
        val street: String? = "",
        val building: String? = "",
        val description: String? = "",
        val metro: Metro? = null
    ) {

        @Serializable
        data class Metro(
            val line: String? = "",
            val station: String? = ""
        )

    }

    @Serializable
    data class Contacts(
        val email: String? = null,
        val name: String? = null,
        val phones: List<Phones>? = null
    ) {

        @Serializable
        data class Phones(
            val city: String? = null,
            val comment: String? = null,
            val country: String? = null,
            val number: String? = null
        )

    }

}
