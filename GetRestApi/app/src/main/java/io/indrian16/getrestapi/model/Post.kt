package io.indrian16.getrestapi.model

data class Post(val userId: Int,
                val id: Int,
                val title: String,
                val body: String)