package com.dicoding.etrash.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.etrash.R
import com.dicoding.etrash.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> get() = _categories
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState

    init {
        // Load categories when the ViewModel is created
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val categories = withContext(Dispatchers.IO) {
                    getCategoriesFromDataSource() //
                }
                _categories.value = categories
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading categories", e)
                _errorState.value = "Error loading categories: ${e.message}"
            }
        }
    }

    private fun getCategoriesFromDataSource(): List<Category> {
        return listOf(
            Category(R.drawable.ic_location, R.string.category_location),
            Category(R.drawable.ic_scan, R.string.category_scan),
            Category(R.drawable.ic_recycle, R.string.category_recycle),
            Category(R.drawable.ic_transaction, R.string.category_transaction),
        )
    }
}