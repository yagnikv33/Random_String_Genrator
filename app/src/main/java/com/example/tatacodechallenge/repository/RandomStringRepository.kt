package com.example.tatacodechallenge.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.example.tatacodechallenge.data.RandomText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class RandomStringRepository(private val context: Context) {
    private val contentUri = Uri.parse("content://com.iav.contestdataprovider/text")

    fun getRandomString(length: Int): RandomText? {
        return try {
            val bundle = Bundle().apply {
                putInt(ContentResolver.QUERY_ARG_LIMIT, 2)
            }

            val uriWithParams = contentUri.buildUpon()
                .appendQueryParameter("length", length.toString())
                .build()

            val cursor = context.contentResolver.query(
                uriWithParams,
                null,
                bundle,
                null
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    val dataIndex = it.getColumnIndex("data")
                    val jsonStr = it.getString(dataIndex)
                    val json = JSONObject(jsonStr).getJSONObject("randomText")

                    return RandomText(
                        value = json.getString("value"),
                        length = json.getInt("length"),
                        created = json.getString("created")
                    )
                }
            }
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
