package fivesol.networklibrary.domain.models.sub_category


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("photo")
    val photo: String?,
    @SerialName("remember_token")
    val rememberToken: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("theme")
    val theme: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("user_role")
    val userRole: String?
)