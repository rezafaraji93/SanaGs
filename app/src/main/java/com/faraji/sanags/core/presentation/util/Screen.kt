package com.faraji.sanags.core.presentation.util

sealed class Screen(val route: String) {
    object GetInfoScreen : Screen("addressListScreen")
    object SetInfoScreen : Screen("addAddressScreen")
    object MapScreen : Screen("mapScreen")
}
