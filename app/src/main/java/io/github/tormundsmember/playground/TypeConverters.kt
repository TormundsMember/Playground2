package io.github.tormundsmember.playground

import android.arch.persistence.room.TypeConverter

object TypeConverters {


  object GenderConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(string: String) = Gender.valueOf(string)

    @TypeConverter
    @JvmStatic
    fun asString(gender: Gender) = gender.name

  }

  object TournamentStateConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(string: String) = TournamentState.valueOf(string)

    @TypeConverter
    @JvmStatic
    fun asString(state: TournamentState) = state.name

  }

  object UserStateConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(string: String) = UserState.valueOf(string)

    @TypeConverter
    @JvmStatic
    fun asString(state: UserState) = state.name

  }

  object TournamentTypeConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(string: String) = TournamentType.valueOf(string)

    @TypeConverter
    @JvmStatic
    fun asString(type: TournamentType) = type.name

  }
}