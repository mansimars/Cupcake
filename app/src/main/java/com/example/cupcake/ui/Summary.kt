package com.example.cupcake.ui.theme

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.makeButton


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainSummaryPreview()
{
//    MainSummary()
}

@Composable
fun MainSummary(orderUiState: OrderUiState,
                onCancelClick: () -> Unit,
                onSendClick: () -> Unit,
                modifier: Modifier=Modifier)
{
    CupcakeTheme() {


        Column(modifier = Modifier.padding(start = 10.dp,top=10.dp)) {

            Spacer(modifier = Modifier.height(70.dp))

            Text(text = "QUANTITY")

            Text(text = "${orderUiState.quantity} Cupcake",
                fontWeight = FontWeight.Bold)

            Divider(modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, end = 10.dp),
                color = Color.DarkGray,
                thickness = 1.dp)

            Text(text = "FLAVOUR")

            Text(text = orderUiState.flavour,
                fontWeight = FontWeight.Bold)

            Divider(modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, end = 10.dp),
                color = Color.DarkGray,
                thickness = 1.dp)

            Text(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), text = "Subtotal $${orderUiState.price}",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold)

            makeButton(onClick = onSendClick, text = "Send Order to Another App",
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth())

            makeButton(onClick = onCancelClick , text = "Cancel",
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth())

        }
    }
}