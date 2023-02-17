package com.example.cupcake.ui.theme

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.data.DataSource.flavors
import com.example.cupcake.ui.makeButton

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainSelectOptionPreview()
{
//    MainSelectOption()
}

@Composable
fun MainSelectOption(subtotal: Int,
                    onCancelClick: ()-> Unit,
                    onNextClick: (String) -> Unit)
{

    var selectedValue by rememberSaveable { mutableStateOf("") }
    CupcakeTheme() {
        Column {

            for (i in 0..flavors.size-1){
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedValue == flavors.get(i),
                        onClick = {selectedValue= flavors.get(i)})
                    Text(text = flavors.get(i),
                        )
                }
            }

            Divider(modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
                    color = Color.DarkGray,
                    thickness = 1.dp)

            Text(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), text = "Subtotal $$subtotal" ,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold)

            Row() {
                makeButton(onClick = onCancelClick, text = "Cancel")

                makeButton(onClick = {onNextClick(selectedValue)}, text = "Next", enable = selectedValue.isNotEmpty())
            }









        }
    }
}
