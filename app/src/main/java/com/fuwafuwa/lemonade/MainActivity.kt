package com.fuwafuwa.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fuwafuwa.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Lemonade()
            }
        }
    }
}

@Composable
fun Lemonade() {
    var step by remember {
        mutableStateOf(1)
    }
    var squeeze by remember {
        mutableStateOf(0)
    }

        when (step) {
            1 -> LemonadeComponent(stringResource(id = R.string.lemon_tree),
                painterResource(id = R.drawable.lemon_tree),
                stringResource(id = R.string.lemon_tree_des),
                imageClick = {
                    step = 2
                    squeeze = (2..4).random()
                })
            2 -> LemonadeComponent(stringResource(id = R.string.lemon_squeeze),
                painterResource(id = R.drawable.lemon_squeeze),
                stringResource(id = R.string.lemon_squeeze_des),
                imageClick = {
                    squeeze--
                    if (squeeze == 0) {
                        step = 3
                    }
                })
            3 -> LemonadeComponent(stringResource(id = R.string.lemon_drink),
                painterResource(id = R.drawable.lemon_drink),
                stringResource(id = R.string.lemon_drink_des),
                imageClick = {

                    step = 4
                })
            4 -> LemonadeComponent(stringResource(id = R.string.lemon_restart),
                painterResource(id = R.drawable.lemon_restart),
                stringResource(id = R.string.lemon_restart_des),
                imageClick = {
                    step = 1
                })
        }
}

@Composable
fun LemonadeComponent(stringId:String, painterId:Painter, contentDes:String, imageClick:()->Unit,modifier: Modifier =Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier.fillMaxSize()){
        Text(text = stringId, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterId,
            contentDescription = contentDes,
            modifier = Modifier
                .clickable(onClick = imageClick )
                .border(width = 3.dp, color = Color(105, 205, 216)))
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Lemonade()
    }
}