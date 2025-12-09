package com.hu.library.data.remote

import com.hu.library.data.model.Announcement
import com.hu.library.data.model.Book
import com.hu.library.data.model.Project
import com.hu.library.data.model.RequestForm
import com.hu.library.data.model.ResponseStatus
import com.hu.library.data.model.Staff
import com.hu.library.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    // 🔵 جلب الإعلانات (Announcements)
    @GET("announcements")
    suspend fun getAnnouncements(): List<Announcement>

    // 🟢 جلب الكتب
    @GET("books")
    suspend fun getBooks(): List<Book>

    @GET("books/new")
    suspend fun getNewArrivals(): List<Book>

    // 🔍 بحث عن كتاب
    @GET("books/search")
    suspend fun searchBooks(
        @Query("query") query: String
    ): List<Book>

    // 🟡 جلب الموظفين
    @GET("staff")
    suspend fun getStaff(): List<Staff>

    // 🔴 جلب المشاريع
    @GET("projects")
    suspend fun getProjects(): List<Project>

    // 🔵 جلب مشروع محدد
    @GET("projects/{id}")
    suspend fun getProjectById(
        @Path("id") id: Int
    ): Project

    // 📝 إرسال طلب (RequestForm)
    @POST("requests")
    suspend fun submitRequest(
        @Body request: RequestForm
    ): ResponseStatus

    // 👤 تسجيل الدخول
    @POST("login")
    suspend fun loginUser(
        @Body user: User
    ): User

}
