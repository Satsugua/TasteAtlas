package com.example.tasteatlas.feature_login.data.tokenResponse

data class TokenInfo(
    val access_token: String,
    val asclient_id: Any,
    val expires: Any,
    val expires_in: Int,
    val issued: Any,
    val refresh_token: Any,
    val token_type: String,
    val userName: String
)