package edu.ucb.pablostify.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import edu.ucb.pablostify.shared.login.data.LoginRepositoryImpl
import edu.ucb.pablostify.shared.login.domain.usecase.LoginUseCase
import edu.ucb.pablostify.shared.login.presentation.LoginScreen
import edu.ucb.pablostify.shared.login.presentation.state.LoginViewModel
import edu.ucb.pablostify.shared.moviedetail.data.MovieDetailRepositoryImpl
import edu.ucb.pablostify.shared.moviedetail.domain.usecase.GetMovieDetailUseCase
import edu.ucb.pablostify.shared.moviedetail.domain.usecase.SubmitReviewUseCase
import edu.ucb.pablostify.shared.moviedetail.presentation.MovieDetailScreen
import edu.ucb.pablostify.shared.moviedetail.presentation.state.MovieDetailViewModel
import edu.ucb.pablostify.shared.movielist.data.MovieRepositoryImpl
import edu.ucb.pablostify.shared.movielist.domain.usecase.GetMovieListUseCase
import edu.ucb.pablostify.shared.movielist.presentation.MovieListScreen
import edu.ucb.pablostify.shared.movielist.presentation.state.MovieListViewModel
import edu.ucb.pablostify.shared.navigation.Route
import edu.ucb.pablostify.shared.profile.data.ProfileRepositoryImpl
import edu.ucb.pablostify.shared.profile.domain.usecase.GetProfileUseCase
import edu.ucb.pablostify.shared.profile.domain.usecase.LogoutUseCase
import edu.ucb.pablostify.shared.profile.presentation.ProfileScreen
import edu.ucb.pablostify.shared.profile.presentation.state.ProfileViewModel
import edu.ucb.pablostify.shared.register.data.RegisterRepositoryImpl
import edu.ucb.pablostify.shared.register.domain.usecase.RegisterUseCase
import edu.ucb.pablostify.shared.register.presentation.RegisterScreen
import edu.ucb.pablostify.shared.register.presentation.state.RegisterViewModel

// NOTA: aquí se instancia todo "a mano" (sin DI) para que el proyecto compile y navegue
// de una vez. Cuando agregues Koin (u otro), reemplaza estos remember { ... } por
// koinInject<...>() y borra las líneas que arman los repository/usecase manualmente.
@Composable
fun App() {
    var currentRoute by remember { mutableStateOf<Route>(Route.Login) }

    MaterialTheme {
        when (val route = currentRoute) {
            Route.Login -> {
                val viewModel = remember {
                    LoginViewModel(LoginUseCase(LoginRepositoryImpl()))
                }
                LoginScreen(
                    viewModel = viewModel,
                    onNavigateToHome = { currentRoute = Route.MovieList },
                    onNavigateToRegister = { currentRoute = Route.Register },
                    onNavigateToForgotPassword = { /* TODO: pantalla de recuperar contraseña */ }
                )
            }

            Route.Register -> {
                val viewModel = remember {
                    RegisterViewModel(RegisterUseCase(RegisterRepositoryImpl()))
                }
                RegisterScreen(
                    viewModel = viewModel,
                    onNavigateToHome = { currentRoute = Route.MovieList },
                    onNavigateToLogin = { currentRoute = Route.Login }
                )
            }

            Route.MovieList -> {
                val viewModel = remember {
                    MovieListViewModel(GetMovieListUseCase(MovieRepositoryImpl()))
                }
                MovieListScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { movieId -> currentRoute = Route.MovieDetail(movieId) },
                    onNavigateToAddMovie = { /* TODO: pantalla de agregar película */ }
                )
            }

            is Route.MovieDetail -> {
                val viewModel = remember {
                    val repository = MovieDetailRepositoryImpl()
                    MovieDetailViewModel(
                        GetMovieDetailUseCase(repository),
                        SubmitReviewUseCase(repository)
                    )
                }
                MovieDetailScreen(
                    movieId = route.movieId,
                    viewModel = viewModel,
                    onNavigateBack = { currentRoute = Route.MovieList }
                )
            }

            Route.Profile -> {
                val viewModel = remember {
                    val repository = ProfileRepositoryImpl()
                    ProfileViewModel(GetProfileUseCase(repository), LogoutUseCase(repository))
                }
                ProfileScreen(
                    viewModel = viewModel,
                    onNavigateToLogin = { currentRoute = Route.Login },
                    onNavigateToAccountSettings = { /* TODO */ },
                    onNavigateToSecurity = { /* TODO */ }
                )
            }
        }
    }
}
