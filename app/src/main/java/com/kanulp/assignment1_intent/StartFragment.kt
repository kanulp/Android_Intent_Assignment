package com.kanulp.assignment1_intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment(), View.OnClickListener {

    var btn_country : Button? = null
    var btn_browser : Button? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_start, container, false)
        btn_country = v.findViewById(R.id.btn_country)
        btn_browser = v.findViewById(R.id.btn_browser)

        btn_country?.setOnClickListener(this)
        btn_browser?.setOnClickListener(this)
        return v
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_country->{
                findNavController().navigate(R.id.action_startFragment_to_FirstFragment);

            }
            R.id.btn_browser->{
                findNavController().navigate(R.id.action_startFragment_to_browserActivity);
            }
        }
    }
}