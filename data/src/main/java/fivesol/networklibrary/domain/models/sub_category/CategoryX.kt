package fivesol.networklibrary.domain.models.sub_category


import androidx.room.Relation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryX(
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("updated_at")
    val updatedAt: String?
)