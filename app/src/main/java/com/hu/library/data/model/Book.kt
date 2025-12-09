package com.hu.library.data.model

data class Book(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val coverUrl: String = "",
    val category: String = "",        // التخصص: IT, هندسة, طب
    val language: String = "عربي",     // عربي / إنجليزي
    val isbn: String = "",
    val publishYear: Int = 0,
    val status: BookStatus = BookStatus.Available,
    val shelfLocation: String = "",   // موقع الرف
    val floor: Int = 1                // الطابق (1 أو 2)
)

enum class BookStatus {
    Available,   // متوفر
    Borrowed,    // مُعار
    Reserved     // محجوز
}
