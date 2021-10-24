package com.example.tmtapplication.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmtapplication.R
import com.example.tmtapplication.api.Resource
import com.example.tmtapplication.model.TMTCard
import com.example.tmtapplication.ui.CardsListAdapter
import com.example.tmtapplication.utils.TLog
import com.example.tmtapplication.utils.TUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

private val TAG = MainFragment::class.java.simpleName

/**
 *  This fragment will show the TMT Cards
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var rootView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.main_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        var listAdapter = setAdapter()
        checkNetworkAndGetData(listAdapter)
        retryBtn.setOnClickListener {
            checkNetworkAndGetData(listAdapter)
        }
    }

    /**
     * initialzed Adapter
     * @return CardsAdpter
     */

    private fun setAdapter(): CardsListAdapter {
        var listAdapter = CardsListAdapter(activity as Context)
        cards_list_view.adapter = listAdapter
        cards_list_view.layoutManager = LinearLayoutManager(activity)
        return listAdapter
    }

    /**
     * Checks networks connection
     * and initate cards api call
     */

    private fun checkNetworkAndGetData(listAdapter: CardsListAdapter) {
        if (checkNetworkConnectivity()) {
            getTMTCards(listAdapter)
        } else {
            TUtil.setSnackBar(
                rootView,
                getString(R.string.no_internet)
            )
        }
    }

    /**
     * Checking the network connectivity
     *
     * @return Boolean - is network available
     */
    private fun checkNetworkConnectivity(): Boolean {
        return TUtil.isNetworkConnectionAvailable(requireContext()).also {
            no_network_group.visibility = if(!it) View.VISIBLE else View.INVISIBLE
            data_group.visibility = if(it) View.VISIBLE else View.INVISIBLE
            no_results.text = getString(R.string.network_error)
        }
    }

    /**
     * cards call initiated and observe data
     * @param listAdapter: CardsListAdapter - CardsAdapter
     */

    private fun getTMTCards(listAdapter: CardsListAdapter) {
        viewModel.fetchCardstLiveData().observe(viewLifecycleOwner,
            Observer { resource ->
                when (resource) {
                    is Resource.Loading<*> -> {
                        TLog.d(TAG, "Cards - Loading")
                        simpleProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success<*> -> {
                        TLog.d(TAG, "Cards - Success")
                        listAdapter.setList(resource.data as List<TMTCard>)
                        simpleProgressBar.visibility = View.INVISIBLE
                    }
                    is Resource.Failure -> {
                        TLog.d(TAG, "Cards - Failure")
                        simpleProgressBar.hide()
                        simpleProgressBar.visibility = View.INVISIBLE
                    }

                }

            })
    }

}
