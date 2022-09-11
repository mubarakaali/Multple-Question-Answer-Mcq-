package fivesol.networklibrary.domain.models.main_category


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainCategoryResponse(
    @SerialName("data")
    val `data`: List<Data>?,
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: Int
)