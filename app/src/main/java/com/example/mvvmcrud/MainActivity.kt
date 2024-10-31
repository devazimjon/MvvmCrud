package com.example.mvvmcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.image_preview.ImagePreview
import com.example.mvvmcrud.data.api.PostApi
import com.example.mvvmcrud.data.model.PostResponse
import com.example.mvvmcrud.ui.theme.MvvmCrudTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val client = PostApi.createPostClient()


        setContent {
            MvvmCrudTheme {
                val posts = remember {
                    mutableStateOf<List<PostResponse>>(emptyList())
                }
                LaunchedEffect(Unit) {
                    posts.value = client.getPosts()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        ImagePreview(
                            rememberVectorPainter(Icons.Default.AccountBox)
                        )
                        LazyColumn {
                            items(posts.value.size) {
                                PostItem(
                                    posts.value[it]
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: PostResponse) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = post.title)
        Spacer(Modifier.height(8.dp))
        Text(text = post.body)
    }
}