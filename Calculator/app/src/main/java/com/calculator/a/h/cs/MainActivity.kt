package com.calculator.a.h.cs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calculator.a.h.cs.ui.theme.CalculatorTheme
import org.mariuszgromada.math.mxparser.Expression
import java.math.RoundingMode
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var numbers = remember {
                mutableStateOf(0)
            }

            var result = remember {
                mutableStateOf("")
            }
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Calculator()
            }
        }
    }
}


@Composable
fun Calculator() {


    var numbers = remember {
        mutableStateOf("")
    }

    var allNumbersString = remember {
        mutableStateOf("")
    }

    var result = remember {
        mutableStateOf("")
    }
    var equalOperator = remember {
        mutableStateOf("")
    }
    var num1 = remember {
        mutableStateOf("")
    }
    var num2 = remember {
        mutableStateOf("")
    }
    var res = remember {
        mutableStateOf("")
    }
    var operator1 = remember {
        mutableStateOf("")
    }
    var operator2 = remember {
        mutableStateOf("")
    }
    var AC = remember {
        mutableStateOf("")
    }
    var CE = remember {
        mutableStateOf("")
    }

    //appending in string
    if (numbers.value != "") {

        allNumbersString.value += numbers.value

        numbers.value = ""
    }

    //removing last item form string
    if (AC.value == "AC") {
        allNumbersString.value = allNumbersString.value.dropLast(1)
        AC.value = ""
    }

    //clearing the string
    if (CE.value == "CE") {
        allNumbersString.value = ""
        result.value = ""
        CE.value = ""
    }


    //operations
    if (equalOperator.value == "=") {
        equalOperator.value = ""

        result.value = solution(allNumbersString.value)

        allNumbersString.value = ""
    }


    //main column
    Column(
        modifier = Modifier
            .background(color = Color(0xff101010))
            .fillMaxSize()
    ) {
        //card with given data and result
        Column(
            modifier = Modifier
                .background(color = Color(0xff181818))
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 20.dp)
        ) {
            Text(
                text = "Calculator",
                color = Color(0xffffffff),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(30.dp),
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = result.value.toString(),
                    color = Color(0xffA8A8A8),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.End,
                    lineHeight = 30.sp,
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp, end = 10.dp)

                )
                Text(
                    text = allNumbersString.value,
                    color = Color(0xffffffff),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.End,
                )
            }
        }


        Surface(
            shadowElevation = 100.dp, modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        ) {

        }

        //data column with numbers and Arithmetic Operators
        Column(
            modifier = Modifier
                .background(Color(0xff181818))
                .weight(1f)
                .padding(top = 20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                NumberCard(text = "AC", { newNumber ->
                    AC.value = newNumber
                })
                NumberCard(text = "CE", { newNumber ->
                    CE.value = newNumber
                })
                NumberCard(text = "%", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "/", { newNumber ->
                    numbers.value = newNumber
                })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                NumberCard(text = "7", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "8", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "9", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "x", { newNumber ->
                    numbers.value = newNumber
                })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                NumberCard(text = "4", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "5", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "6", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "-", { newNumber ->
                    numbers.value = newNumber
                })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                NumberCard(text = "1", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "2", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "3", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "+", { newNumber ->
                    numbers.value = newNumber
                })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                NumberCard(text = "0", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "00", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = ".", { newNumber ->
                    numbers.value = newNumber
                })
                NumberCard(text = "=") { newNumber ->
                    equalOperator.value = newNumber
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberCard(text: String, onClick: (String) -> Unit) {
    var context = LocalContext.current
    Card(colors = CardDefaults.cardColors(
        containerColor = Color(0xff101010)
    ), elevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
    ), onClick = {
        onClick(text)
    }) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color(0xff101010))
                .height(40.dp)
                .width(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = Color(0xffffffff),
                fontSize = 25.sp,
            )
        }
    }
}


fun solution(expersion: String):String {
    var answer = ""
    try {
        answer = Expression(expersion.replace("x", "*").replace("%","%")).calculate().toString()
    }catch (e:Exception){
        answer = e.message.toString()
    }
    return answer
}