package com.fleetmanager.app.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromVehicleStatus(status: VehicleStatus): String {
        return status.name
    }

    @TypeConverter
    fun toVehicleStatus(status: String): VehicleStatus {
        return VehicleStatus.valueOf(status)
    }
}
