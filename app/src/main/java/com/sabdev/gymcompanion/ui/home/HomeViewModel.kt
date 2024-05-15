package com.sabdev.gymcompanion.ui.home

import androidx.lifecycle.ViewModel
import com.sabdev.gymcompanion.domain.model.RoutInfo
import com.sabdev.gymcompanion.domain.model.RoutInfo.Fri
import com.sabdev.gymcompanion.domain.model.RoutInfo.Mon
import com.sabdev.gymcompanion.domain.model.RoutInfo.Thu
import com.sabdev.gymcompanion.domain.model.RoutInfo.Tue
import com.sabdev.gymcompanion.domain.model.RoutInfo.Wed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private var _routineList = MutableStateFlow<List<RoutInfo>>(emptyList())
    val routineList: StateFlow<List<RoutInfo>> = _routineList

    init {
        _routineList.value = listOf(
            Mon, Tue, Wed, Thu, Fri
        )
    }

}