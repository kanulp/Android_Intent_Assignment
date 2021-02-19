package com.kanulp.assignment1_intent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AllCountriesFragment : Fragment() {

    var rv_all : RecyclerView? = null
    private var viewModel: CountriesViewModel? = null
    var countriesAdapter : AllCountiesAdapter? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CountriesViewModel::class.java)
        viewModel?.loadAllCountries()
        setupListUpdate()

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_all_countries, container, false)
        rv_all = v.findViewById(R.id.rv_all) as RecyclerView // Add this
        return v
    }

    private fun setupListUpdate() {

        viewModel?.countryLiveData?.observe(viewLifecycleOwner, {
            if(it!=null) {
                countriesAdapter = AllCountiesAdapter(requireActivity(),it, object : AllCountiesAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int?) {
                        val bundle = bundleOf("position" to position)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
                        viewModel?.addCountry(it[position!!])
                    }
                })
                rv_all?.layoutManager = LinearLayoutManager(context);
                rv_all?.adapter = countriesAdapter;
            }else {
                    Toast.makeText(activity, "No Data found", Toast.LENGTH_SHORT).show()
                }

        })
    }


}