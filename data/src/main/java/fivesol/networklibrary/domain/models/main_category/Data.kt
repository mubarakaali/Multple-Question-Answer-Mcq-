package fivesol.networklibrary.domain.models.main_category


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "main_Category")
data class Data(
    @SerialName("counter")
    val counter: Int?,
    @SerialName("icon")
    val icon: String?,
    @PrimaryKey
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("slug")
    val slug: String?
)