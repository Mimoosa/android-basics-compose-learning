package fi.monami.lab_net_coroutine.ui.screens

import android.graphics.Bitmap
import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fi.monami.lab_net_coroutine.PhotoApplication
import fi.monami.lab_net_coroutine.data.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface PhotoUiState{
    data class Success(val photo: Bitmap): PhotoUiState
    object Error: PhotoUiState
    object Loading: PhotoUiState
}

class PhotoViewModel(private val photoRepository: PhotoRepository): ViewModel() {
    var photoUiState: PhotoUiState by mutableStateOf(PhotoUiState.Loading)
        private set

    init {
        getPhoto()
    }

    fun getPhoto(){
        viewModelScope.launch(Dispatchers.IO)  {
            photoUiState = PhotoUiState.Loading
            photoUiState = try {
                PhotoUiState.Success(
                    photoRepository.getPhoto()
                )
            } catch (e: IOException){
                PhotoUiState.Error
            } catch (e: HttpException){
                PhotoUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PhotoApplication)
                val photoRepository = application.container.PhotoRepository
                PhotoViewModel(photoRepository = photoRepository)
            }
        }
    }
}