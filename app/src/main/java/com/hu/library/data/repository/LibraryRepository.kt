package com.hu.library.data.repository

import com.hu.library.data.model.*

interface LibraryRepository {

    suspend fun getAnnouncements(): List<Announcement>

    suspend fun getBooks(): List<Book>
    suspend fun getNewArrivals(): List<Book>
    suspend fun searchBooks(query: String): List<Book>

    suspend fun getStaff(): List<Staff>

    suspend fun getProjects(): List<Project>
    suspend fun getProjectById(id: Int): Project

    suspend fun submitRequest(request: RequestForm): ResponseStatus

    suspend fun loginUser(user: User): User
}
