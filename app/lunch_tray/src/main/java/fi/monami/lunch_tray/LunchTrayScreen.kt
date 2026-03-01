/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.monami.lunch_tray


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fi.monami.lunch_tray.datasource.DataSource
import fi.monami.lunch_tray.ui.AccompanimentMenuScreen
import fi.monami.lunch_tray.ui.CheckoutScreen
import fi.monami.lunch_tray.ui.EntreeMenuScreen
import fi.monami.lunch_tray.ui.SideDishMenuScreen
import fi.monami.lunch_tray.ui.StartOrderScreen


enum class LunchTrayScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    EntreeMenu(title = R.string.choose_entree),
    SideDishMenu(title = R.string.choose_side_dish),
    AccompanimentMenu(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

// TODO: AppBar
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateBack: Boolean,
    navigateUp: ()-> Unit = {},
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {Text(stringResource(currentScreen.title))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen,
                navController.previousBackStackEntry != null,
                {navController.navigateUp()}
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController,
            LunchTrayScreen.Start.name,
            Modifier.padding(innerPadding)
        ) {
            composable(LunchTrayScreen.Start.name){
                StartOrderScreen(
                    {
                        navController.navigate(LunchTrayScreen.EntreeMenu.name)
                    }
                )
            }

            composable(LunchTrayScreen.EntreeMenu.name){
                EntreeMenuScreen(
                    DataSource.entreeMenuItems,
                    {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    {
                        navController.navigate(LunchTrayScreen.SideDishMenu.name)
                    },
                    {viewModel.updateEntree(it)}
                )
            }

            composable(LunchTrayScreen.SideDishMenu.name){
                SideDishMenuScreen(
                    DataSource.sideDishMenuItems,
                    {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    {
                        navController.navigate(LunchTrayScreen.AccompanimentMenu.name)
                    },
                    {viewModel.updateSideDish(it)}
                )
            }

            composable(LunchTrayScreen.AccompanimentMenu.name){
                AccompanimentMenuScreen(
                    DataSource.accompanimentMenuItems,
                    {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    {
                        navController.navigate(LunchTrayScreen.Checkout.name)
                    },
                    {viewModel.updateAccompaniment(it)}
                )
            }

            composable(LunchTrayScreen.Checkout.name){
                CheckoutScreen(
                    uiState,
                    {
                        navController.navigate(LunchTrayScreen.Start.name)
                    },
                    {
                        viewModel.resetOrder()
                        navController.navigate(LunchTrayScreen.Start.name)
                    }
                )
            }
        }
    }
}
