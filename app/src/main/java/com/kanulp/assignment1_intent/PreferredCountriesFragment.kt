package com.kanulp.assignment1_intent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PreferredCountriesFragment : Fragment() {

    var rv_preffered : RecyclerView? = null
    private var viewModel: CountriesViewModel? = null
    var prefferedAdapter : AllCountiesAdapter? = null
    var tv_total : TextView? = null
    var total = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_preffered_country, container, false)
        rv_preffered = v.findViewById(R.id.rv_preffered) as RecyclerView
        tv_total = v.findViewById(R.id.tv_total) as TextView
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CountriesViewModel::class.java)
        setupCount()
        setupListUpdate()
    }

    private fun setupCount(){
        viewModel?.MAX_COUNTRY?.observe(viewLifecycleOwner,{
            tv_total?.text = "Total Limit : $it"
        })
    }


    private fun setupListUpdate() {
        viewModel?.prefferedCountryLiveData?.observe(viewLifecycleOwner, {
            if(it!=null) {
                prefferedAdapter = AllCountiesAdapter(requireActivity(),it, object : AllCountiesAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int?) {

                    }
                })
                rv_preffered?.layoutManager = LinearLayoutManager(context);
                rv_preffered?.adapter = prefferedAdapter;
            }else {
                Toast.makeText(activity, "No Data found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}