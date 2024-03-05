package com.example.navigationdrawerharjoitus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationdrawerharjoitus.model.Screen
import com.example.navigationdrawerharjoitus.ui.theme.NavigationDrawerHarjoitusTheme
import com.example.navigationdrawerharjoitus.view.ScreenA
import com.example.navigationdrawerharjoitus.view.ScreenB
import com.example.navigationdrawerharjoitus.view.ScreenC
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerHarjoitusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Lista enum-luokan objekteista, joihin on määritelty otsikot ja navigointireitit
                    val screens = listOf(
                        Screen.SCREEN_A,
                        Screen.SCREEN_B,
                        Screen.SCREEN_C
                    )

                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    fun drawerToggle() {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }

                    val navController = rememberNavController()
                    //Muuttuja navigationin backstackin tilaa varten
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    //Muuttuja, johon tallennetaan backstackin tilan mukainen sijainti.
                    val currentDestination = navBackStackEntry?.destination
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        //Drawerin sisältö
                        drawerContent = {
                            ModalDrawerSheet {
                                //forEachilla lisätään enum-objektilistaan pohjautuen navigation draweriin itemeitä ja annetaan nille ominaisuuksia
                                screens.forEach { screen ->
                                    NavigationDrawerItem(
                                        //Asetetaan labeliksi enum-objektiin määritelty label.
                                        label = { Text("${screen.label}")},
                                        //Verrataan nykyisen sijainnin reittiä kierroksella käsiteltävän enum-objektin reittiin.
                                        //Tämän perusteella määritellään, onko navigation item valittuna vai ei.
                                        selected = screen.route == currentDestination?.route,
                                        onClick = { scope.launch {
                                            navController.navigate("${screen.route}")
                                            drawerToggle()
                                        }
                                        }
                                    )
                                }

                            }
                        }
                    ) {
                            NavHost(navController, startDestination = "screenA") {
                                composable(route = "screenA") {
                                    ScreenA(onMenuClick = {drawerToggle()})
                                }
                                composable(route = "screenB") {
                                    ScreenB(onMenuClick = {drawerToggle()})
                                }
                                composable(route = "screenC") {
                                    ScreenC(onMenuClick = {drawerToggle()})
                                }

                            }

                    }

                }
            }
        }
    }
}

