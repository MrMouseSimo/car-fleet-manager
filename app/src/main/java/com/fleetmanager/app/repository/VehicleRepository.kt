package com.fleetmanager.app.repository

import androidx.lifecycle.LiveData
import com.fleetmanager.app.data.Vehicle
import com.fleetmanager.app.data.VehicleDao
import com.fleetmanager.app.data.VehicleStatus

class VehicleRepository(private val vehicleDao: VehicleDao) {

    val allVehicles: LiveData<List<Vehicle>> = vehicleDao.getAllVehicles()
    val totalCount: LiveData<Int> = vehicleDao.getTotalCount()
    val availableCount: LiveData<Int> = vehicleDao.getAvailableCount()
    val maintenanceCount: LiveData<Int> = vehicleDao.getMaintenanceCount()
    val inUseCount: LiveData<Int> = vehicleDao.getInUseCount()

    fun getVehiclesByStatus(status: VehicleStatus): LiveData<List<Vehicle>> {
        return vehicleDao.getVehiclesByStatus(status)
    }

    suspend fun getVehicleById(id: Long): Vehicle? {
        return vehicleDao.getVehicleById(id)
    }

    suspend fun insert(vehicle: Vehicle): Long {
        return vehicleDao.insertVehicle(vehicle)
    }

    suspend fun update(vehicle: Vehicle) {
        vehicleDao.updateVehicle(vehicle)
    }

    suspend fun delete(vehicle: Vehicle) {
        vehicleDao.deleteVehicle(vehicle)
    }
}
