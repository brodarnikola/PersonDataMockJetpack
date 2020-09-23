package com.vjezba.persondatamockjetpack.mock

import com.vjezba.persondatamockjetpack.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor{


    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUrl().toString()
            val responseString = when {
                uri.endsWith("login1") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()

            /*return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()*/
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }
}

const val getListOfReposBeingStarredJson = """
[{"name":"AAAA","address":"1111 ", "name":"BBBB","address":"22222 ", "name":"CCCC","address":"3333 "}] 
"""