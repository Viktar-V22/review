package com.data.base.db.images

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.base.db.images.Image.Companion.IMAGES_TABLE

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(image: Image): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(verbs: List<Image>)

    @Query("select * from $IMAGES_TABLE")
    suspend fun getAll(): List<Image>

    @Query("select * from $IMAGES_TABLE where ${Image.ID} = :id")
    suspend fun byId(id: Long): List<Image>

    @Query("select count(*) from $IMAGES_TABLE")
    suspend fun count(): Int
}