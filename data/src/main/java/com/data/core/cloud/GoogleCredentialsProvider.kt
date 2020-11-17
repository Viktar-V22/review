package com.data.core.cloud

import android.content.res.Resources
import com.data.R
import com.google.api.gax.core.CredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.OAuth2Credentials
import javax.inject.Inject

class GoogleCredentialsProvider @Inject constructor(
    private val resources: Resources
) : CredentialsProvider {

    private val oAuth2: OAuth2Credentials by lazy {
        resources.openRawResource(R.raw.credential).use { input ->
            GoogleCredentials.fromStream(input)
        }
    }

    override fun getCredentials() = oAuth2
}