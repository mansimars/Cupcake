package com.example.cupcake

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.cupcake.model.OrderViewModel

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.data.DataSource.quantity
import com.example.cupcake.ui.Main
import com.example.cupcake.ui.theme.MainSelectOption
import com.example.cupcake.ui.theme.MainSummary
import com.example.cupcake.ui.utils.CupcakeNavigationType

enum class CupcakeScreen (@StringRes val title: Int){
    Start(R.string.cupcake),
    Flavour(R.string.choose_flavour),
    Summary(R.string.order_summary)

}

@Composable
fun CupcakeAppBar(
    currentScreen: CupcakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier

){
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title))},
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }


    )
}

@Composable
fun CupcakeApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier=Modifier,
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()){

    val navigationType: CupcakeNavigationType
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route?: CupcakeScreen.Start.name
    )

    when(windowSize){
        WindowWidthSizeClass.Compact -> {navigationType=CupcakeNavigationType.PHONE}
        else -> {navigationType = CupcakeNavigationType.SIDEWAYS}
    }

    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(navController = navController,
                    startDestination = CupcakeScreen.Start.name,
                    modifier.padding(innerPadding)){

            composable(route = CupcakeScreen.Start.name){
                Main(
                    navigationType = navigationType,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(CupcakeScreen.Flavour.name)
                    }
                )
            }

            composable(route = CupcakeScreen.Flavour.name){
                MainSelectOption(uiState.price,
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(CupcakeScreen.Start.name, inclusive = false)
                    },
                    onNextClick = {
                        viewModel.setFlavour(it)
                        navController.navigate(CupcakeScreen.Summary.name)
                    })
            }

            composable(route = CupcakeScreen.Summary.name){
                MainSummary(uiState,
                    onCancelClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(CupcakeScreen.Start.name, inclusive = false)

                    },
                    onSendClick = {

                    })
            }
        }
    }
}