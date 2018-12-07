package io.github.tormundsmember.playground

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Tournament(

    @PrimaryKey
    val tournamentId: String,
    val title: String,
    val isCreator: Boolean,
    val usersWriting: Int,
    val usersDone: Int,
    val userState: UserState,
    val tournamentState: TournamentState,
    val timeoutTimestamp: Long?,
    val currentRound: Int?,
    @Embedded
    val owner: Owner?,
    @Embedded
    val winner: Winner?,
    val hasNotifications: Boolean,
    val tournamentType: TournamentType,
    val maxUsers: Int,
    val isProfilesUnlocked: Boolean?

)


class Owner(
    @ColumnInfo(name = "owner_firstname")
    val firstname: String,
    @ColumnInfo(name = "owner_age")
    val age: Int
)

class Winner(
    val userId: String,
    val profilePicUrl: String?,
    val firstname: String,
    val age: Int,
    val gender: Gender
)

enum class UserState {
  WAIT_ANSWER,
  WAIT_RANK,
  DEMAND_RANK,
  DEMAND_QUESTIONS,
  DEMAND_ANSWERS,
  LOST,
  TIMED_OUT,
  TIMED_OUT_ANSWERING,
  TIMED_OUT_RANKING,
  WON,
  ABORTED_TOURNAMENT,
  ABORTED_SLOT,
  CAN_RANK,
  UNDEFINED,
  DEMAND_SELECT_WINNER_STAGE1,
  DEMAND_CHOOSE_WINNER,
  CAN_CHOOSE_WINNER,
  DEMAND_CONFIRM_WINNER,
  REJECTED
}


enum class TournamentState {
  ANSWERING1,
  RANKING1,
  ANSWERING2,
  RANKING2,
  FINISHED,
  EXPIRED,
  ABORTED,
  UNDEFINED,
  CONFIRM_WINNER

}

enum class TournamentType {
  CLASSICAL,
  QUICK,
  RESTARTED_CLASSICAL,
  DYN_CLASSICAL,
  DYN_REST_CLASSICAL
}

enum class Gender {

  Male, Female

}