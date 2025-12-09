package com.hu.library.data.model

data class Staff(
    val id: Int = 0,
    val name: String = "",
    val position: String = "",          // الوظيفة: مدير، رئيس قسم، موظف...
    val department: String = "",        // قسم الموظف
    val phone: String = "",             // رقم الهاتف الداخلي
    val email: String = "",             // البريد الجامعي
    val officeLocation: String = "",     // موقع المكتب (رف 3 – طابق 2 – غرفة 205...)
    val imageUrl: String = "",           // صورة الموظف
    val supervisorId: Int? = null        // لربط الموظفين ضمن هيكل هرمي
)
