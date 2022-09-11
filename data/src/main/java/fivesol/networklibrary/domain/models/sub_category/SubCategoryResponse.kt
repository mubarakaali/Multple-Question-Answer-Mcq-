package fivesol.networklibrary.domain.models.sub_category


import androidx.room.Relation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubCategoryResponse(
    @SerialName("category")
    val category: Category?,
    @SerialName("mcqs")
    val mcqs: List<Mcq>?
)