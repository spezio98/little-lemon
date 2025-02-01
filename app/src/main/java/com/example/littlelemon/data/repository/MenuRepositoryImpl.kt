package com.example.littlelemon.data.repository

import com.example.littlelemon.data.local.MenuItemDao
import com.example.littlelemon.data.remote.service.MenuApi
import com.example.littlelemon.domain.model.MenuList
import com.example.littlelemon.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuApi: MenuApi,
    private val menuItemDao: MenuItemDao
) : MenuRepository{

    override suspend fun getMenu(): MenuList {
        return withContext(Dispatchers.IO){
            val localItems = menuItemDao.getMenuItems().first()

            if (localItems.isEmpty()) {
                try {
                    val menuListDto = menuApi.getMenu()
                    val menuListEntity = menuListDto.menu.map { it.toEntityModel() }
                    menuItemDao.insertMenuItems(menuListEntity)
                    return@withContext MenuList(menuListEntity.map { it.toDomainModel() })
                } catch (e: Exception){
                    return@withContext MenuList(emptyList())
                }
            }
            return@withContext MenuList(localItems.map { it.toDomainModel() })
        }

    }
}