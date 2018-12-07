package io.github.tormundsmember.playground

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@TypeConverters(value = [io.github.tormundsmember.playground.TypeConverters.GenderConverter::class,
  io.github.tormundsmember.playground.TypeConverters.TournamentStateConverter::class,
  io.github.tormundsmember.playground.TypeConverters.UserStateConverter::class,
  io.github.tormundsmember.playground.TypeConverters.TournamentTypeConverter::class])
@Database(entities = [Tournament::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()