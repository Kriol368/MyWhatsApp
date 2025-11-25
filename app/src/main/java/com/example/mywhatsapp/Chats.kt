package com.example.mywhatsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Contact(
    val name: String,
    val group: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsTab(scrollBehavior: TopAppBarScrollBehavior) {
    val defaultImageRes = android.R.drawable.ic_menu_gallery

    val contacts = listOf(
        Contact("Jefe de departamento", "Departamento Informática", R.drawable.image1),
        Contact("Tutora DAM", "Departamento Informática", R.drawable.image2),
        Contact("Tutora DAW", "Departamento Informática", R.drawable.image3),
        Contact("Tutora ASIX", "Departamento Informática", R.drawable.image4),
        Contact("Presidenta", "Comunidad Propietarios", R.drawable.image5),
        Contact("Secretaria", "Comunidad Propietarios", R.drawable.image6),
        Contact("Administrador", "Comunidad Propietarios", R.drawable.image7),
        Contact("Entrenadora", "Gimnasio", R.drawable.image8),
        Contact("Nutricionista", "Gimnasio", R.drawable.image9),
        Contact("Fisioterapeuta", "Gimnasio", R.drawable.image10)
    )

    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        val groupedContacts = contacts.groupBy { it.group }

        groupedContacts.forEach { (group, groupContacts) ->
            stickyHeader {
                GroupHeader(group = group)
            }

            items(groupContacts) { contact ->
                ContactItem(contact = contact)
            }
        }
    }
}

@Composable
fun GroupHeader(group: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFCCCCCC))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = group,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp
        )
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = "Foto de ${contact.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = contact.name,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp
        )
    }
}