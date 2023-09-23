package com.onats.networkandstorage

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform