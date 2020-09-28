package com.ameer.deliverylite.restaurants.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameer.deliverylite.R
import com.ameer.deliverylite.restaurants.RecyclerViewClickListener
import com.ameer.deliverylite.restaurants.adapters.RestaurantsRecyclerviewAdapter
import com.ameer.deliverylite.restaurants.RestaurantsViewModel
import com.ameer.deliverylite.restaurants.data.models.Restaurants
import com.ameer.deliverylite.restaurants.data.models.PopularItems
import kotlinx.android.synthetic.main.fragment_restaurants_nearby.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantsNearbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantsNearbyFragment : Fragment(), RecyclerViewClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val viewModel: RestaurantsViewModel by viewModels()
    lateinit var recyclerviewAdapter: RestaurantsRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val lat = "37.422740"
        val long = "-122.139956"
        viewModel.fetchRestaurants(lat, long)
        viewModel.restaurants.observe(viewLifecycleOwner, Observer {
            restaurants ->
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            listNearbyRestaurants.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                recyclerviewAdapter = RestaurantsRecyclerviewAdapter(this)
                recyclerviewAdapter.submitList(restaurants)
                recyclerviewAdapter.setHasStableIds(true)
                it.adapter = recyclerviewAdapter
            }
        })
        return inflater.inflate(R.layout.fragment_restaurants_nearby, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantsNearbyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantsNearbyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onRecyclerViewItemClick(view: View, restaurants: Restaurants) {
        when(view.id) {
            R.id.txtRestName -> {
            }
        }
        view.setOnClickListener(View.OnClickListener {
        })
    }

    override fun onItemSelected(position: Int, item: Restaurants) {
        val id = item.id
        val popularItems: List<PopularItems>? = item?.menus?.get(0)?.popular_items
        val action = RestaurantsNearbyFragmentDirections.actionNearByToDetailsFragment()
        val bundle = bundleOf("id" to id,"popularitems" to popularItems)
        view?.findNavController()?.navigate(R.id.actionNearByToDetailsFragment,bundle)
    }

    override fun onItemSelected(position: Int, item: PopularItems) {

    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}