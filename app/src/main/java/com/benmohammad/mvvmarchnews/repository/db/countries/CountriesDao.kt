package com.benmohammad.mvvmarchnews.repository.db.countries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benmohammad.mvvmarchnews.repository.model.countries.Country

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<Country>): List<Long>

    @Query("SELECT * FROM countries_table")
    fun getCountries(): LiveData<List<Country>>

    @Query("DELETE FROM countries_table")
    abstract fun deleteAllCountries()
}