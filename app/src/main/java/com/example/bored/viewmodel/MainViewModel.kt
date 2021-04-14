package com.example.bored.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.bored.source.repository.MainRepository
import com.example.bored.source.responses.ActivityResponse
import kotlinx.coroutines.launch
import com.example.bored.utils.Result

@Suppress("UNCHECKED_CAST")
class MainViewModel(
    private val repo : MainRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<Nothing>>(Result.Idle)
    val uiState : LiveData<Result<Nothing>>
        get() = _uiState

    private val _activity = MutableLiveData<ActivityResponse>()
    val activity : LiveData<ActivityResponse>
        get() = _activity

    fun getRandomActivity() = viewModelScope.launch {
        _uiState.value = Result.Loading
        val response = repo.getRandomActivity()
        when(response) {
            is Result.Success -> {
                _activity.value = response.data!!
                _uiState.value = Result.Idle
            }
            is Result.Error -> {
                _uiState.value = Result.Error(response.errorMsg)
                _uiState.value = Result.Idle
            }
        }
    }

    companion object {
        class MainViewModelProvider : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(MainRepository()) as T
            }
        }

        fun providesMainViewModel(owner: ViewModelStoreOwner) = ViewModelProvider(owner, MainViewModelProvider())[MainViewModel::class.java]
    }
}