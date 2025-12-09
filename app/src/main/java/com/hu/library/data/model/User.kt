package com.hu.library.data.model

data class User(
    val id: String = "",                // رقم المستخدم (UID أو الرقم الجامعي)
    val name: String = "",              // اسم الطالب
    val email: String = "",             // الايميل الجامعي
    val universityId: String = "",      // الرقم الجامعي
    val department: String = "",        // التخصص
    val level: String = "",             // سنة دراسية: أول، ثاني، ثالث، رابع
    val borrowedBooks: List<Int> = emptyList(), // IDs للكتب التي استعارتها
    val fines: Double = 0.0,            // الغرامات
    val profileImage: String = "",      // صورة الطالب
    val createdAt: String = "",         // تاريخ إنشاء الحساب
    val updatedAt: String = ""          // آخر تحديث
)
