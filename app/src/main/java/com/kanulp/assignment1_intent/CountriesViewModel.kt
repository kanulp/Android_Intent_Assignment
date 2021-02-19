package com.kanulp.assignment1_intent

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList

class CountriesViewModel(application : Application) : AndroidViewModel(application) {

    var countryLiveData : MutableLiveData<List<CountryModel>>? = MutableLiveData()
    var countryList : ArrayList<CountryModel>? = ArrayList()
    var context : Context? = null
    var MAX_COUNTRY : MutableLiveData<Int> = MutableLiveData()

    var prefferedCountryLiveData : MutableLiveData<List<CountryModel>>? = MutableLiveData()
    var prefferedCountryList : ArrayList<CountryModel>? = ArrayList()

    init {
        context = getApplication<Application>().applicationContext
        MAX_COUNTRY.value = 5
    }

    fun addCountry(countryModel : CountryModel){

        if(prefferedCountryList?.contains(countryModel)!!){

        }else {
            if (prefferedCountryList?.size!! >= MAX_COUNTRY?.value!!) {
                prefferedCountryList?.removeAt(0)


                countryList?.add(countryModel)
                prefferedCountryList?.add(countryModel)

                countryLiveData?.value = countryList
                prefferedCountryLiveData?.value = prefferedCountryList


            }else {

                countryList?.add(countryModel)
                prefferedCountryList?.add(countryModel)

                countryLiveData?.value = countryList
                prefferedCountryLiveData?.value = prefferedCountryList
            }
        }
        Log.d("CountriesViewModel","adding ${prefferedCountryList?.toString()} ${prefferedCountryList?.size}")
    }

    fun getMaxCountry(): Int? {
        return MAX_COUNTRY.value
    }


    fun loadAllCountries(){
        val countryCodes = Locale.getISOCountries()

        var temp = ArrayList<CountryModel>()

        for (countryCode in countryCodes) {
            val locale = Locale("", countryCode)
            val country = locale.displayCountry
            val lang = locale.country

            if (country.isNotEmpty() && lang.isNotEmpty()) {
                temp.add(CountryModel(country, lang))
            }else{
                //Log.d("CountriesViewModel", "empty data:$country:$lang")
            }
        }
        countryList?.clear()
        countryList?.addAll(temp)
        countryLiveData?.value = temp

        Log.d("CountriesViewModel","${countryList?.size}")
    }

}