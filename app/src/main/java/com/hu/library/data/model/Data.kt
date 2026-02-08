package com.hu.library.data.model

class Data {
}

val staffList = listOf(
    Staff(1, "بلال خليل أحمد أبو ديه", "نائب المدير", "إدارة المكتبة", "0797002046", "Rfd-library@hu.edu.jo", "الطابق الأول"),
    Staff(2, "سالي عزمي محمد عمرو", "سكرتيرة / طابعة", "الديوان", "0791125352", "sally@hu.edu.jo", "الديوان", imageUrl = "", supervisorId = 1),
    Staff(3, "نادر محمد راجي البطاينه", "كاتب", "الديوان", "0780500271", "naderm@hu.edu.jo", "الديوان", imageUrl = "", supervisorId = 1),
    Staff(4, "تيسير محمد سلامة الخلايله", "مأمور فرز بريد", "الديوان", "0788563483", "Tayseer@hu.edu.jo", "الديوان", imageUrl = "", supervisorId = 1),

    // دائرة المعلومات [cite: 5]
    Staff(5, "أشرف توفيق عبد الرحمن عمرو", "مدير دائرة", "دائرة المعلومات", "0799277624", "ashraf76@hu.edu.jo", "دائرة المعلومات", supervisorId = 1),
    Staff(6, "سعاد أحمد سمير العموش", "إداري", "شعبة المصادر", "0792938868", "suada@hu.edu.jo", "", imageUrl = "", supervisorId = 5),
    Staff(7, "حسام الدين عابد موسى عابد", "كاتب", "شعبة النظم المكتبية", "0795778551", "Husameddin@hu.edu.jo", "", imageUrl = "", supervisorId = 5),
    Staff(8, "وفاء حسّان سليمان العطاونة", "كاتب", "شعبة الارشفة", "0796479616", "wafah@hu.edu.jo", "", imageUrl = "", supervisorId = 5),

    // الدائرة الفنية [cite: 9]
    Staff(9, "فؤاد فايز عمر المولا", "مدير دائرة", "الدائرة الفنية", "0780330775", "Acq-library@hu.edu.jo", "", supervisorId = 1),
    Staff(10, "موسى محمد عبد الرحيم القطاونه", "رئيس شعبة", "شعبة الفهرسة", "0796548647", "mousakarak@hu.edu.jo", "", imageUrl = "", supervisorId = 9),
    Staff(11, "غرام متعب خليف المعايطه", "إداري", "شعبة الفهرسة", "0776107800", "", "", imageUrl = "", supervisorId = 9),

    // دائرة الخدمات المكتبية [cite: 12]
    Staff(15, "شريف غالب أحمد الشياب", "مدير دائرة", "دائرة الخدمات", "0798400756", "sharief200088@hu.edu.jo", "", supervisorId = 1),
    Staff(16, "بسام عبد الله خلف الخلايله", "إداري", "شعبة الإعارة", "0795466762", "bassamkh@hu.edu.jo", "", imageUrl = "", supervisorId = 15),
    Staff(17, "فادي محمد عواد الشرع", "إداري", "شعبة الإعارة", "0796100500", "fadim@hu.edu.jo", "", imageUrl = "", supervisorId = 15),
    Staff(18, "أمامه موسى مصطفى أبو قرق", "إداري", "دائرة الخدمات", "0790935747", "omama@hu.edu.jo", "", imageUrl = "", supervisorId = 15)
)