package za.co.discovery.sample.screen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import za.co.discovery.sample.data.SampleInteractor
import za.co.discovery.sample.domain.Content
import za.co.discovery.sample.screen.viewmodel.contract.IMainViewModel

class MainViewModel : ViewModel(), IMainViewModel {
    override val model: MutableLiveData<List<Content>> = MutableLiveData<List<Content>>(emptyList())
    override val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)

    override fun requestData() {
        viewModelScope.launch {
            generateContent()
        }
    }

    private suspend fun generateContent() {
        loading.postValue(true)
        // simulate some request happening
        delay(1000)
        loading.postValue(false)
        val data = SampleInteractor.getContentList(30)
        model.postValue(data)
    }
}