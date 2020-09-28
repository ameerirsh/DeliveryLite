package com.ameer.doordashlite.restaurants.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.ameer.doordashlite.R
import com.ameer.doordashlite.restaurants.adapters.PopularItemsRecyclerViewAdapter
import com.ameer.doordashlite.restaurants.RecyclerViewClickListener
import com.ameer.doordashlite.restaurants.RestaurantsViewModel
import com.ameer.doordashlite.restaurants.data.models.PopularItems
import com.ameer.doordashlite.restaurants.data.models.Restaurants
import com.ameer.doordashlite.utils.LocationUtils
import kotlinx.android.synthetic.main.fragment_restaurant_details.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantDetailsFragment : Fragment(), RecyclerViewClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val viewModel: RestaurantsViewModel by viewModels()
    lateinit var recyclerviewAdapter: PopularItemsRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val id = arguments?.getString("id")
        val lat = 37.422740
        val long = -122.139956
        val popularItems: List<PopularItems>? = arguments?.get("popularitems") as List<PopularItems>?
        //var popularItemsList: List<PopularItems> = popularItems as List<PopularItems>
        viewModel.getRestaurantDetails(id.toString())
        viewModel.restaurantDetails.observe(viewLifecycleOwner, Observer { restaurantDetails ->
            linear.also {
                imgCover.load(restaurantDetails.cover_img_url)
                txtRestName.text = restaurantDetails.name
                txtRating.text = restaurantDetails.average_rating + "* "
                txtTotalRatings.text = restaurantDetails.number_of_ratings + " ratings . "
                var distanceInKms = LocationUtils.calculateDistanceInKilometer(lat, long, restaurantDetails.address.lat, restaurantDetails.address.lng)
                var distanceInMiles = LocationUtils.convertKilometerToMiles(distanceInKms)
                txtDistance.text = distanceInMiles.toString()+" miles"
                if(distanceInMiles == 1.0) {
                    txtDistance.text = distanceInMiles.toString()+" mile"
                }
                listPopularItems.also {
                    it.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    it.setHasFixedSize(true)
                    recyclerviewAdapter = PopularItemsRecyclerViewAdapter(this)
                    if (popularItems != null) {
                        recyclerviewAdapter.submitList(popularItems)
                        recyclerviewAdapter.setHasStableIds(true)
                        it.adapter = recyclerviewAdapter
                    } else {

                    }
                }


            }


        })
        return inflater.inflate(R.layout.fragment_restaurant_details, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onRecyclerViewItemClick(view: View, restaurants: Restaurants) {

    }

    override fun onItemSelected(position: Int, item: Restaurants) {

    }

    override fun onItemSelected(position: Int, item: PopularItems) {

    }
}