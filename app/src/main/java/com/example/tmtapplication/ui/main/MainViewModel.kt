package com.example.tmtapplication.ui.main

import androidx.lifecycle.*
import com.example.tmtapplication.api.Resource
import com.example.tmtapplication.api.datasource.TDataSource
import com.example.tmtapplication.utils.TLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private val TAG = MainViewModel::class.java.simpleName

/**
 * <h1>MainViewModel</h1>
 * MainViewModel is used to do operation for the Cards Screen.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val dataSource: TDataSource)  : ViewModel() {

    /**
     * Load cards list from the server [Livedata]
     * @return LiveData<List<TMTCard>>
     */
    //live data use case
     fun fetchCardstLiveData()= liveData {
        TLog.d(TAG, "fetchCardstLiveData called")
        emit(Resource.Loading())
        emit(dataSource.getTMTCards())
    }

}
