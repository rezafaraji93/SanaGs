package com.faraji.sanags.core.presentation.components

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.faraji.sanags.core.presentation.util.Screen
import com.faraji.sanags.core.util.Constants
import com.faraji.sanags.feature_get_info.presentation.GetInfoScreen
import com.faraji.sanags.feature_map_screen.presentation.MapScreen
import com.faraji.sanags.feature_set_info.presentation.SetInfoScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    NavHost(
        navController = navController,
        startDestination = Screen.GetInfoScreen.route
    ) {

        composable(Screen.GetInfoScreen.route) {
            GetInfoScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigate,
            )
        }
        composable(
            Screen.SetInfoScreen.route + "?lat={${Constants.LAT}}&lng={${Constants.LNG}}",
            arguments = listOf(
                navArgument(name = Constants.LAT) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(name = Constants.LNG) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            SetInfoScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigate,
                onPopBackStack = navController::popBackStack
            )
        }

    }

}