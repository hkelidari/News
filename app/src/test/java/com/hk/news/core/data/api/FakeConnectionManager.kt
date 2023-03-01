package com.hk.news.core.data.api

class FakeConnectionManager(
    private val isConnected: Boolean = true
) : ConnectionManager {
    override fun isConnected(): Boolean {
        return isConnected
    }
}