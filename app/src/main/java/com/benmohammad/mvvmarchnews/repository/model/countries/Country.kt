package com.benmohammad.mvvmarchnews.repository.model.countries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("countryName") var countryName: String? = null,
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("countryKey") var countryKey: String? = null,
    @SerializedName("countryFlagUrl") var countryFlagUrl: String? = null
)