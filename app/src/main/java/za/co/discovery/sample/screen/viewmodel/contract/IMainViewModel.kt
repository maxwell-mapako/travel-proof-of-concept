package za.co.discovery.sample.screen.viewmodel.contract

import androidx.lifecycle.LiveData
import za.co.discovery.sample.domain.Content

interface IMainViewModel {
    val model: LiveData<List<Content>>
    val loading: LiveData<Boolean>

    fun requestData()
}