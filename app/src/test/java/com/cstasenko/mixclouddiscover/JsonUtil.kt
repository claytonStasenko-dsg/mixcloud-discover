package com.cstasenko.mixclouddiscover

import com.google.gson.Gson
import java.io.InputStream

class JsonUtil {
    companion object {
        inline fun <reified T : Any> getLocalJsonFile(fileName: String): T {
            val input: InputStream =
                ClassLoader.getSystemResourceAsStream(fileName)
            val inputAsString = input.bufferedReader().use { it.readText() }
            return Gson().fromJson(inputAsString, T::class.java)
        }
    }
}