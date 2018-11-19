package com.example.steven.stk.data.model

data class FirebaseAuthModel(var kind: String,
                             var idToken: String,
                             var refreshToken: String,
                             var expiresIn: String)

/*
{
    "kind": "identitytoolkit#VerifyCustomTokenResponse",
    "idToken": "[ID_TOKEN]",
    "refreshToken": "[REFRESH_TOKEN]",
    "expiresIn": "3600"
}*/
