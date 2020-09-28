package com.ameer.doordashlite.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

object LocationUtils {

    val AVERAGE_RADIUS_OF_EARTH_KM = 6371.0
    fun calculateDistanceInKilometer(
        userLat: Double, userLng: Double,
        restLat: Double, restLong: Double
    ): Int {
        val latDistance = Math.toRadians(userLat - restLat)
        val lngDistance = Math.toRadians(userLng - restLong)
        val a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + (Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(restLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2)))
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c).toInt()
    }

    fun convertKilometerToMiles(miles: Int) : Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(miles/1.609).toDouble()
    }
}