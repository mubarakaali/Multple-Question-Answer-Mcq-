package fivesol.networklibrary.domain.models.sub_category


import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import fivesol.networklibrary.data.utils.ApiConstants

@Serializable
@Entity(tableName = ApiConstants.MCQ_TABLE_NAME,primaryKeys = ["id", "categoryId"])
data class Mcq(
    @SerialName("id")
    val id: Int,
    @SerialName("ans")
    val ans: String?,
    @SerialName("cate_ab")
    val cateAb: String?,
    @Embedded(prefix = "mcqs_category_")
    @SerialName("category")
    val category: CategoryX?,
    @SerialName("category_id")
    val categoryId: Int,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("date_till")
    val dateTill: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("for_homepage")
    val forHomepage: String?,
    @SerialName("is_liked")
    val isLiked: String?,
    @SerialName("m_status")
    val mStatus: String?,
    @SerialName("option_a")
    val optionA: String?,
    @SerialName("option_b")
    val optionB: String?,
    @SerialName("option_c")
    val optionC: String?,
    @SerialName("option_d")
    val optionD: String?,
    @SerialName("question")
    val question: String?,
    @SerialName("sub_category_id")
    val subCategoryId: Int?,
    @SerialName("submitted_by")
    val submittedBy: String?,
    @SerialName("total_comments")
    val totalComments: Int?,
    @SerialName("total_likes")
    val totalLikes: Int?,
    @SerialName("total_views")
    val totalViews: Int?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @Embedded(prefix = "mcqs_user_")
    @SerialName("user")
    val user: User?,
    @SerialName("user_id")
    val userId: Int?,
    var isItemSelected: Boolean = false
)