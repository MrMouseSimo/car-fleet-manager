package com.fleetmanager.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fleetmanager.app.data.FleetDatabase
import com.fleetmanager.app.data.Vehicle
import com.fleetmanager.app.data.VehicleStatus
import com.fleetmanager.app.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FleetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: VehicleRepository

    val allVehicles: LiveData<List<Vehicle>>
    val totalCount: LiveData<Int>
    val availableCount: LiveData<Int>
    val maintenanceCount: LiveData<Int>
    val inUseCount: LiveData<Int>

    init {
        val vehicleDao = FleetDatabase.getDatabase(application).vehicleDao()
        repository = VehicleRepository(vehicleDao)
        allVehicles = repository.allVehicles
        totalCount = repository.totalCount
        availableCount = repository.availableCount
        maintenanceCount = repository.maintenanceCount
        inUseCount = repository.inUseCount
    }

    fun getVehiclesByStatus(status: VehicleStatus): LiveData<List<Vehicle>> {
        return repository.getVehiclesByStatus(status)
    }

    fun insert(vehicle: Vehicle) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(vehicle)
    }

    fun update(vehicle: Vehicle) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(vehicle)
    }

    fun delete(vehicle: Vehicle) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(vehicle)
    }
}
