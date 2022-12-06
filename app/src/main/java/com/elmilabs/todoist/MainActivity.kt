package com.elmilabs.todoist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmilabs.todoist.ui.theme.TodoistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TodoistTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val list by viewModel.allNotes.collectAsState()

                    NoteScreen(
                        noteList = list
                    )
                }
            }
        }
    }
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(noteList: List<NoteUI>, modifier: Modifier = Modifier) {
    Scaffold(
        modifier, floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        NoteList(noteData = noteList, modifier = Modifier.padding(it))

    }
}

@Preview
@Composable
fun NoteScreenPreview() {
    val noteData = listOf(
        NoteUI("", "test content andoird".repeat(5), 0),
        NoteUI("", "test adsfkm dsf., dfsaml andoird".repeat(50), 0),
        NoteUI("", "tedsfma mfds l.fd doird".repeat(20), 0),
        NoteUI("", "test content ldjknsffa ".repeat(1000), 0),
        NoteUI(
            "", "test codfsakmflkds .dsf kdsmبيسنةم بةثصينخبةثصهبثص نةمبشيستىاعه ةنسىهعبا rd", 0
        )
    )
    NoteScreen(noteList = noteData)

}

@Composable
fun NoteList(noteData: List<NoteUI>, modifier: Modifier = Modifier) {
    LazyColumn(modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(noteData) {
            NoteCard(it)
        }
    }
}

data class NoteUI(
    val id: String, val content: String, val priority: Int = 0, val background: String? = null
)

@Composable
fun NoteCard(noteUI: NoteUI, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val maxLines = if (expanded) 7 else 3
    Card(
        modifier = modifier
            .animateContentSize(
                spring(
                    Spring.DampingRatioMediumBouncy, Spring.StiffnessMedium
                )
            )
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            }, shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            text = noteUI.content,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(16.dp),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    TodoistTheme {
        NoteCard(NoteUI("", "test content andoird", 0))
    }
}


@Preview(showBackground = true)
@Composable
fun NoteListPreview() {
    val noteData = listOf(
        NoteUI("", "test content andoird".repeat(5), 0),
        NoteUI("", "test adsfkm dsf., dfsaml andoird".repeat(50), 0),
        NoteUI("", "tedsfma mfds l.fd doird".repeat(20), 0),
        NoteUI("", "test content ldjknsffa ".repeat(1000), 0),
        NoteUI(
            "", "test codfsakmflkds .dsf kdsmبيسنةم بةثصينخبةثصهبثص نةمبشيستىاعه ةنسىهعبا rd", 0
        )
    )
    TodoistTheme {
        NoteList(
            noteData
        )
    }
}