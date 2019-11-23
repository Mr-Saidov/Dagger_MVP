package org.dagger.mvp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Region(
    @PrimaryKey(autoGenerate = true)
    var regionId: Int,
    var id: Int,
    @SerializedName("name_uz")
    var nameUz: String,
    @SerializedName("name_ru")
    var nameRu: String,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("update_at")
    var updateAt: String?
) {
    constructor() : this(1, 1, "", "", "", "")
}