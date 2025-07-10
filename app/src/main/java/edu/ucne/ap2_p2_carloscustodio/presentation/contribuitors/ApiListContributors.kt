package edu.ucne.ap2_p2_carloscustodio.presentation.contribuitors

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import edu.ucne.ap2_p2_carloscustodio.presentation.ApiEjemplo.ContributorsViewModel
import edu.ucne.ap2_p2_carloscustodio.presentation.remote.dto.ContributorDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiListContribuitors(
    owner: String,
    repoName: String,
    onBack: () -> Unit,
    viewModel: ContributorsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(owner, repoName) {
        viewModel.getContributors(owner, repoName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Contribuidores de $repoName",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        containerColor = Color.Gray
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 18.dp)
            ) {
                when {
                    state.isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    state.errorMessage != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = state.errorMessage ?: "",
                                color = Color.Red,
                                fontSize = 18.sp
                            )
                        }
                    }
                    state.contributors.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay contribuidores.",
                                fontSize = 16.sp
                            )
                        }
                    }
                    else -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.contributors) { contributor ->
                                ContributorRow(
                                    contributor = contributor,
                                    onImageClick = { imageUrl ->
                                        selectedImageUrl = imageUrl
                                    }
                                )
                            }
                        }
                    }
                }
            }

            if (selectedImageUrl != null) {
                Dialog(
                    onDismissRequest = { selectedImageUrl = null }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.85f))
                            .clickable(onClick = { selectedImageUrl = null }),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable(enabled = false) {}
                        ) {
                            AsyncImage(
                                model = selectedImageUrl,
                                contentDescription = "Avatar grande",
                                modifier = Modifier
                                    .size(320.dp)
                                    .border(3.dp, Color.White)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContributorRow(
    contributor: ContributorDto,
    onImageClick: (String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = contributor.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .border(2.dp, Color.Black)
                    .clickable { onImageClick(contributor.avatarUrl ?: "") }
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contributor.login,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = "Contribuciones: ${contributor.contributions}",
                    fontSize = 15.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}