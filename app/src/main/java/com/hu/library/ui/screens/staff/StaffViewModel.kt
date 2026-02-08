package com.hu.library.ui.screens.staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hu.library.data.model.Staff
import com.hu.library.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StaffViewModel(private val repository: LibraryRepository) : ViewModel() {
    private val _staffState = MutableStateFlow<List<Staff>>(emptyList())
    val staffState: StateFlow<List<Staff>> = _staffState

    init {
        loadStaff()
    }

    private fun loadStaff() {
        viewModelScope.launch {
            try {
                _staffState.value = repository.getStaff()
            } catch (e: Exception) {
                // معالجة الأخطاء
            }
        }
    }
}