package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName

data class FirebaseNotificationModel(
     @SerializedName("Respuesta")
    val respuesta: String,
    @SerializedName("Msg")
    val msg: String,
    val message: String
)


data class Msg (
    @SerializedName("multicast_id")
    val multicastID: Double,
    val success: Int,
    val failure: Int,
    @SerializedName("canonical_ids")
    val canonicalIDS: Int,
    val results: List<ResultFirebase>
)


data class ResultFirebase (
    @SerializedName("message_id")
    val messageID: String
)

