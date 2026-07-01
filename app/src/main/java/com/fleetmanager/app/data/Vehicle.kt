package com.fleetmanager.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val licensePlate: String,
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val status: VehicleStatus = VehicleStatus.AVAILABLE,
    val assignedTo: String = "",
    val mileage: Int = 0,
    val fuelLevel: Int = 100, // percentage
    val lastMaintenanceDate: String = "",
    val notes: String = ""
)

enum class VehicleStatus {
    AVAILABLE,
    IN_USE,
    MAINTENANCE,
    OUT_OF_SERVICE
}
