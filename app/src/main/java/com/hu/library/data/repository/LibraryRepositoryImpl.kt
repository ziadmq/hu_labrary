package com.hu.library.data.repository

import com.hu.library.data.model.*
import com.hu.library.data.remote.RetrofitClient

class LibraryRepositoryImpl : LibraryRepository {

    private val api = RetrofitClient.api

    override suspend fun getAnnouncements(): List<Announcement> {
        return api.getAnnouncements()
    }

    override suspend fun getBooks(): List<Book> {
        return api.getBooks()
    }

    override suspend fun getNewArrivals(): List<Book> {
        return api.getNewArrivals()
    }

    override suspend fun searchBooks(query: String): List<Book> {
        return api.searchBooks(query)
    }

    override suspend fun getStaff(): List<Staff> {
        return api.getStaff()
    }

    override suspend fun getProjects(): List<Project> {
        return api.getProjects()
    }

    override suspend fun getProjectById(id: Int): Project {
        return api.getProjectById(id)
    }

    override suspend fun submitRequest(request: RequestForm): ResponseStatus {
        return api.submitRequest(request)
    }

    override suspend fun loginUser(user: User): User {
        return api.loginUser(user)
    }
}
