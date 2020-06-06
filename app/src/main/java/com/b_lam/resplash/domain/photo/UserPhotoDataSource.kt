package com.b_lam.resplash.domain.photo

import com.b_lam.resplash.data.photo.model.Photo
import com.b_lam.resplash.data.user.UserService
import com.b_lam.resplash.domain.BaseDataSource
import kotlinx.coroutines.CoroutineScope

class UserPhotoDataSource(
    private val userService: UserService,
    private val username: String,
    private val order: String?,
    private val stats: Boolean?,
    private val resolution: String?,
    private val quantity: Int?,
    private val orientation: String?,
    scope: CoroutineScope
) : BaseDataSource<Photo>(scope) {

    override suspend fun getPage(page: Int, perPage: Int): List<Photo> {
        return userService.getUserPhotos(
            username = username,
            page = page,
            per_page = perPage,
            order_by = order,
            stats = stats,
            resolution = resolution,
            quantity = quantity,
            orientation = orientation
        )
    }
}