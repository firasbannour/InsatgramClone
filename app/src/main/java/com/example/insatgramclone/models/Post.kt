package com.example.insatgramclone.models

import com.google.firebase.firestore.PropertyName

data class Post(
    var description: String = "",
    @get:PropertyName("image_url") @set:PropertyName("image_url") var imageUrl: String = "",
    @get:PropertyName("creation_time_millis") @set:PropertyName("creation_time_millis") var creationTimeMs: Long = 0,
    var user: com.example.insatgramclone.models.User? = null
)
