package com.hu.library.data.model

data class Project(
    val id: Int = 0,
    val title: String = "",
    val abstract: String = "",
    val description: String = "",
    val supervisor: String = "",
    val students: List<String> = emptyList(),
    val department: String = "",
    val year: Int = 0,
    val projectFiles: List<String> = emptyList(),    // روابط PDF
    val images: List<String> = emptyList(),          // صور المشروع
    val videoDemoUrl: String = "",                   // فيديو عرض المشروع
    val githubLink: String = "",                     // الرابط إن وجد
    val type: ProjectType = ProjectType.Graduation   // تخرج / رسالة ماجستير
)

enum class ProjectType {
    Graduation,   // مشروع تخرج
    Thesis        // رسالة ماجستير
}
