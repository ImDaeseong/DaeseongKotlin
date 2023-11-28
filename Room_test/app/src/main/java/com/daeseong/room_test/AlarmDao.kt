package com.daeseong.room_test

import androidx.room.*

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms")
    fun getAll(): List<Alarm>

    @Insert
    fun insert(alarm: Alarm)

    @Update
    fun update(alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)

    @Query("DELETE FROM alarms WHERE id = (SELECT MIN(id) FROM alarms)")
    fun deleteOldest()

    @Query("SELECT * FROM alarms WHERE id = (SELECT MIN(id) FROM alarms)")
    fun getOldest(): Alarm?

    @Query("DELETE FROM alarms")
    fun clearAlarms()

    @Query("SELECT COUNT(*) FROM alarms")
    fun getRowCount(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM alarms WHERE title = :title LIMIT 1)")
    fun isAlarmExists(title: String): Boolean

    @Query("SELECT * FROM alarms WHERE title = :title LIMIT 1")
    fun getAlarmByTitle(title: String): Alarm?
}
