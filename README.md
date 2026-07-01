# Car Fleet Manager - Android App

A native Android application to manage a car fleet. Built with Kotlin, Room Database, LiveData, ViewModel, and Navigation Component.

## Features

- **Dashboard** - Overview with real-time stats (Total, Available, In Use, Maintenance)
- **Vehicle List** - Full list of all vehicles with status color indicators
- **Add / Edit Vehicles** - Complete vehicle form with all details
- **Delete Vehicles** - Remove vehicles with a single tap
- **Status Tracking** - 4 statuses: Available, In Use, Maintenance, Out of Service
- **Offline Storage** - All data stored locally using Room (SQLite)

## Vehicle Data Tracked

| Field | Description |
|---|---|
| License Plate | Vehicle identifier |
| Brand / Model / Year | Vehicle info |
| Color | Vehicle color |
| Status | Available / In Use / Maintenance / Out of Service |
| Assigned To | Driver name |
| Mileage | Odometer reading (km) |
| Fuel Level | Percentage (0-100%) |
| Notes | Free text notes |

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (SQLite)
- **UI**: XML Layouts + Material Design 3
- **Navigation**: Navigation Component with Bottom Navigation
- **Async**: Kotlin Coroutines + LiveData
- **Build**: Gradle with Version Catalog (libs.versions.toml)

## Project Structure

```
app/src/main/java/com/fleetmanager/app/
├── MainActivity.kt
├── data/
│   ├── Vehicle.kt          # Room Entity + VehicleStatus enum
│   ├── VehicleDao.kt       # DAO interface
│   ├── FleetDatabase.kt    # Room Database singleton
│   └── Converters.kt       # Type converters
├── repository/
│   └── VehicleRepository.kt
├── viewmodel/
│   └── FleetViewModel.kt
└── ui/
    ├── DashboardFragment.kt
    ├── VehicleListFragment.kt
    ├── AddEditVehicleFragment.kt
    └── adapter/
        └── VehicleAdapter.kt
```

## Setup

1. Clone the repository
2. Open in Android Studio (Hedgehog or later)
3. Sync Gradle
4. Run on Android 7.0+ device or emulator (API 24+)

## Requirements

- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 34
- Minimum SDK 24 (Android 7.0)
- Kotlin 1.9.22
