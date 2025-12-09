package com.hu.library.data.model

data class Announcement(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val createdAt: String = "",
    val type: AnnouncementType = AnnouncementType.General
)

enum class AnnouncementType {
    NewBook,       // كتاب جديد
    Event,         // فعالية أو ورشة
    Notice,        // تنبيه/إعلان رسمي
    Offer,         // عرض أو دورة مجانية
    General        // إعلان عام
}