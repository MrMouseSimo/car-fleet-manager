package com.fleetmanager.app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicles ORDER BY brand, model")
    fun getAllVehicles(): LiveData<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE status = :status")
    fun getVehiclesByStatus(status: VehicleStatus): LiveData<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE id = :id")
    suspend fun getVehicleById(id: Long): Vehicle?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: Vehicle): Long

    @Update
    suspend fun updateVehicle(vehicle: Vehicle)

    @Delete
    suspend fun deleteVehicle(vehicle: Vehicle)

    @Query("SELECT COUNT(*) FROM vehicles")
    fun getTotalCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM vehicles WHERE status = 'AVAILABLE'")
    fun getAvailableCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM vehicles WHERE status = 'MAINTENANCE'")
    fun getMaintenanceCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM vehicles WHERE status = 'IN_USE'")
    fun getInUseCount(): LiveData<Int>
}
