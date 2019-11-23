package org.dagger.mvp.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Branch(
    var id: Int,
    @SerializedName("name_uz")
    var nameUz: String,
    @SerializedName("name_ru")
    var nameRu: String,
    var address: String,
    var phone: String
) {
    constructor() : this(1, "", "", "", "")
}