package com.ameer.doordashlite.restaurants

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ameer.doordashlite.networking.DoorDashApi
import com.ameer.doordashlite.restaurants.data.models.RestaurantDetail
import com.ameer.doordashlite.restaurants.data.models.Restaurants

import com.ameer.doordashlite.restaurants.repository.RestaurantRepository
import com.ameer.doordashlite.restaurants.repository.Result
import com.ameer.doordashlite.restaurants.repository.Result.Error
import com.ameer.doordashlite.utils.Coroutines
import kotlinx.coroutines.*


import kotlin.coroutines.CoroutineContext


class RestaurantsViewModel() : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val api = DoorDashApi()
    private val repository:RestaurantRepository = RestaurantRepository(api)
    private lateinit var job: Job
    private val _restaurants = MutableLiveData<List<Restaurants>>()
    private val _restaurantDetails = MutableLiveData<RestaurantDetail>()
    val restaurants: LiveData<List<Restaurants>>
        get() = _restaurants
    val restaurantDetails: MutableLiveData<RestaurantDetail>
            get() = _restaurantDetails

    /**
     * Implemented in two different approaches and hence the redundant method
     */

    fun fetchRestaurants(lat: String, long: String) {
        scope.launch {
           supervisorScope {
                val task = async {
                    val restaurants = repository.getListRestaurants(lat, long)
                    _restaurants.postValue(restaurants)
                  /*  when(restaurants) {
                        is Result.NetworkError -> {
                            Log.d("viewmodel","caught exception in when?")
                        }
                        is Error ->  {
                            Log.d("viewmodel","caught exception in when?")
                        }
                    }*/

                }
                try{
                } catch (e: Throwable) {
                }
            }

        }
    }

    fun getNearByRestaurants(lat: String, long: String) {
        job = Coroutines.ioThenMain(
            { repository.getRestaurants(lat,long) },
            { _restaurants.value = it }
        )
    }

    fun getRestaurantDetails(id: String) {
        job = Coroutines.ioThenMain(
            { repository.getRestaurantDetails(id) },
            { _restaurantDetails.value = it }
        )
    }
}