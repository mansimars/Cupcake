package com.example.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.R
import com.example.cupcake.ui.utils.CupcakeNavigationType
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Main(
    navigationType: CupcakeNavigationType,
    onNextButtonClicked:(Int) -> Unit,
    modifier: Modifier = Modifier
)
{
    CupcakeTheme() {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.cupcake2),
                    contentDescription = "logo",
                    modifier = Modifier.padding(30.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Order Cupcakes", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(15.dp))

                if (navigationType == CupcakeNavigationType.PHONE) {
                    makeButton(
                        onClick = { onNextButtonClicked(1) },
                        text = stringResource(R.string.numCake1) + " Cupcake"
                    )
                    makeButton(
                        onClick = { onNextButtonClicked(6) },
                        text = stringResource(R.string.numCake6) + " Cupcakes"
                    )
                    makeButton(
                        onClick = { onNextButtonClicked(12) },
                        text = stringResource(R.string.numCake12) + " Cupcakes"
                    )
                }
            }

            if(navigationType==CupcakeNavigationType.SIDEWAYS)
            {
                Column() {
                    makeButton(
                        onClick = { onNextButtonClicked(1) },
                        text = stringResource(R.string.numCake1) + " Cupcake"
                    )
                    makeButton(
                        onClick = { onNextButtonClicked(6) },
                        text = stringResource(R.string.numCake6) + " Cupcakes"
                    )
                    makeButton(
                        onClick = { onNextButtonClicked(12) },
                        text = stringResource(R.string.numCake12) + " Cupcakes"
                    )
                }

            }
        }
    }
}

@Composable
fun makeButton(modifier: Modifier = Modifier,
               onClick: () -> Unit,
               text:String,
               enable: Boolean=true)
{
    Button(onClick = onClick,
        modifier = modifier
            .padding(6.dp)
            .width(200.dp),
        enabled=enable) {
        Text(text = text,
            modifier= Modifier.wrapContentWidth())
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainPreview()
{
//    Main()
}