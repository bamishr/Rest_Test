package io.indrian16.getrestapi.network

import io.indrian16.getrestapi.model.Post
import io.indrian16.getrestapi.model.Todo
import io.indrian16.getrestapi.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/todos")
    fun getTodos(): Observable<List<Todo>>

    @GET("/users")
    fun getUsers(): Observable<List<User>>

}