package com.ameer.doordashlite.utils


import org.junit.Test

import org.junit.Assert.*

class LocationUtilsTest {

    @Test
    fun calculateDistanceInKilometer() {
        var distanceInKms = LocationUtils.calculateDistanceInKilometer(37.3942753,-121.9497694,37.4155418,-121.8994449)
        assertEquals(distanceInKms,5)
    }

    @Test
    fun convertKilometerToMiles() {
        assertEquals(3.10,LocationUtils.convertKilometerToMiles(5),0.0)
        assertEquals(0.00,LocationUtils.convertKilometerToMiles(0),0.0)
    }
}