package com.example.roomdbapp.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "_notes")
class Note() :Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0

    @ColumnInfo(name = "_identifier")
    var identifier: String? = null

    @ColumnInfo(name = "_name")
    var name: String? = null

    @ColumnInfo(name = "_isChecked")
    var isChecked: Boolean = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        identifier = parcel.readString()
        name = parcel.readString()
        isChecked = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(identifier)
        parcel.writeString(name)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}