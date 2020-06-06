package com.b_lam.resplash.ui.photo.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.b_lam.resplash.data.photo.model.Photo
import com.b_lam.resplash.domain.photo.PhotoRepository
import com.b_lam.resplash.ui.base.BaseViewModel
import com.b_lam.resplash.util.Result
import com.b_lam.resplash.util.livedata.lazyMap
import kotlinx.coroutines.launch

class PhotoDetailViewModel(private val photoRepository: PhotoRepository) : BaseViewModel() {

    private val photoDetailsMutableLiveData: Map<String, LiveData<Photo>> = lazyMap {
        val liveData = MutableLiveData<Photo>()
        viewModelScope.launch {
            val result = photoRepository.getPhotoDetails(it)
            when (result) {
                is Result.Success -> liveData.postValue(result.value)
            }
        }
        return@lazyMap liveData
    }

    fun photoDetailsLiveData(id: String): LiveData<Photo> = photoDetailsMutableLiveData.getValue(id)
}