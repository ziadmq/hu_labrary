package com.hu.library.data.model

data class RequestForm(
    val id: Int = 0,
    val requestType: RequestType = RequestType.BuyArabicBook,

    val title: String = "",             // اسم الكتاب المطلوب
    val author: String = "",            // المؤلف إن وجد
    val description: String = "",       // وصف الطلب أو ملاحظات
    val language: String = "عربي",       // عربي / إنجليزي
    val requesterName: String = "",     // اسم الطالب
    val requesterId: String = "",       // الرقم الجامعي
    val requesterEmail: String = "",    // ايميل الطالب
    val department: String = "",        // القسم الأكاديمي
    val fileAttachment: String = "",    // ملف مرفق (PDF) إن وجد

    val status: RequestStatus = RequestStatus.Pending,
    val createdAt: String = "",
    val updatedAt: String = ""
)

enum class RequestType {
    BuyArabicBook,      // شراء كتاب عربي
    BuyEnglishBook,     // شراء كتاب أجنبي
    SuggestBook,        // اقتراح إضافة كتاب
    RequestArticle,     // طلب مقالة
    DonateBook          // إهداء كتاب للمكتبة
}

enum class RequestStatus {
    Pending,     // قيد المراجعة
    Approved,    // تم القبول
    Rejected     // مرفوض
}
