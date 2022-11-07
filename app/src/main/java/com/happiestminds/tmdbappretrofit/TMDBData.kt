package com.happiestminds.tmdbappretrofit

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    var id:Int, var title: String?,
    var vote_average:Double, var release_date: String?,
    var poster_path: String?, var overview: String?,
    var adult:Boolean):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeDouble(vote_average)
        parcel.writeString(release_date)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeByte(if (adult) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}


data class PopularMovies(val results: List<Movie>)