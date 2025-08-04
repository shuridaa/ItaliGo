package com.example.italigo.ui.view

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.italigo.R

// Define ProfileDetails data class
data class ProfileDetails(val name: String, val email: String, val phone: String)

@Composable
fun ProfileScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<Int?>(null) }
    var showPhotoDialog by remember { mutableStateOf(false) }
    var showAvatarDialog by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf<String?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Enter Your Details", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(20.dp))

        // Profile Picture Section
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.LightGray, CircleShape)
                .clickable { showPhotoDialog = true },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImage != null) {
                Image(
                    painter = painterResource(id = selectedImage!!),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize().clip(CircleShape)
                )
            } else {
                Text("Add Photo", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        // Show Notification
        notificationMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = Color.Green, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name Input
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val profileDetails = ProfileDetails(name = name, email = email, phone = "")
                navController.navigate(
                    "edit_profile/${Uri.encode(profileDetails.name)}/${Uri.encode(profileDetails.email)}"
                ) {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Details")
        }
    }

    if (showPhotoDialog) {
        PhotoSelectionDialog(
            onDismiss = { showPhotoDialog = false },
            onOptionSelected = { option ->
                when (option) {
                    PhotoOption.TAKE_PHOTO -> {
                        Toast.makeText(navController.context, "Taking a photo...", Toast.LENGTH_SHORT).show()
                    }
                    PhotoOption.GALLERY -> {
                        Toast.makeText(navController.context, "Opening gallery...", Toast.LENGTH_SHORT).show()
                    }
                    PhotoOption.AVATAR -> {
                        showPhotoDialog = false
                        showAvatarDialog = true
                    }
                }
            }
        )
    }

    if (showAvatarDialog) {
        AvatarSelectionDialog(
            onDismiss = { showAvatarDialog = false },
            onAvatarSelected = { avatarRes ->
                selectedImage = avatarRes
                notificationMessage = "Profile picture selected successfully!"
                showAvatarDialog = false
            }
        )
    }
}

@Composable
fun PhotoSelectionDialog(
    onDismiss: () -> Unit,
    onOptionSelected: (PhotoOption) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose Profile Picture") },
        text = {
            Column {
                Button(onClick = { onOptionSelected(PhotoOption.TAKE_PHOTO) }) {
                    Text("Take a Photo")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected(PhotoOption.GALLERY) }) {
                    Text("Choose from Gallery")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onOptionSelected(PhotoOption.AVATAR) }) {
                    Text("Choose an Avatar")
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun AvatarSelectionDialog(
    onDismiss: () -> Unit,
    onAvatarSelected: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select an Avatar") },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items((1..13).toList()) { index ->
                    val avatarRes = getAvatarResource(index)
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .clickable { onAvatarSelected(avatarRes) },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = avatarRes),
                            contentDescription = "Avatar $index",
                            modifier = Modifier.size(70.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

enum class PhotoOption {
    TAKE_PHOTO, GALLERY, AVATAR
}

fun getAvatarResource(index: Int): Int {
    return when (index) {
        1 -> R.drawable.a13
        2 -> R.drawable.a2
        3 -> R.drawable.a3
        4 -> R.drawable.a4
        5 -> R.drawable.a5
        6 -> R.drawable.a6
        7 -> R.drawable.a7
        8 -> R.drawable.a8
        9 -> R.drawable.a9
        10 -> R.drawable.a10
        11 -> R.drawable.a11
        12 -> R.drawable.a12
        else -> R.drawable.a13
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}
