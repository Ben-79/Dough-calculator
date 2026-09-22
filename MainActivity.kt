package com.mehmert.doughcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.Button
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.mehmert.doughcalculator.ui.theme.DoughCalculatorTheme

class DigitOnlyInputTransformation : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        if (!asCharSequence().isDigitsOnly()) {
            revertAllChanges()
        }
    }
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoughCalculatorTheme {
                DoughCalc()
            }
        }
    }
}
@Composable
fun DoughCalc() {
    val bpState1 = rememberTextFieldState()
    val bpState1Text = bpState1.text.toString()
    val bpState1If = if (bpState1Text == "") 0 else bpState1Text.toInt()
    val bpCalc = doughCalc(bpState1If,8)
    val bpState2 = rememberTextFieldState()
    val bpState2Text = bpState2.text.toString()
    val bpState2If = if (bpState2Text == "") 0 else bpState2Text.toInt()
    val bpSubtractor = if (bpCalc - bpState2If < 0) 0 else bpCalc - bpState2If
    val smState1 = rememberTextFieldState()
    val smState1Text = smState1.text.toString()
    val smState1If = if (smState1Text=="") 0 else smState1Text.toInt()
    val smState2 = rememberTextFieldState()
    val smState2Text = smState2.text.toString()
    val smState2If = if (smState2Text == "") 0 else smState2Text.toInt()
    val smSubtractor = if (smState1If - smState2If < 0) 0 else smState1If - smState2If
    val mdState1 = rememberTextFieldState()
    val mdState1Text = mdState1.text.toString()
    val mdState1If = if (mdState1Text == "") 0 else mdState1Text.toInt()
    val mdCalc = doughCalc(mdState1If,8)
    val mdState2 = rememberTextFieldState()
    val mdState2Text = mdState2.text.toString()
    val mdState2If = if (mdState2Text == "") 0 else mdState2Text.toInt()
    val mdSubtractor = if (mdCalc - mdState2If < 0) 0 else mdCalc - mdState2If
    val lgState1 = rememberTextFieldState()
    val lgState1Text = lgState1.text.toString()
    val lgState1If = if (lgState1Text == "") 0 else lgState1Text.toInt()
    val lgCalc = doughCalc(lgState1If,6)
    val lgState2 = rememberTextFieldState()
    val lgState2Text = lgState2.text.toString()
    val lgState2If = if (lgState2Text == "") 0 else lgState2Text.toInt()
    val lgSubtractor = if (lgCalc - lgState2If < 0) 0 else lgCalc - lgState2If
    fun clearAll(){
        bpState1.clearText()
        bpState2.clearText()
        smState1.clearText()
        smState2.clearText()
        mdState1.clearText()
        mdState2.clearText()
        lgState1.clearText()
        lgState2.clearText()
    }
    Column(Modifier.fillMaxSize().background(Color.Black)) {
        Spacer(Modifier.size(274.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text("Patties/\nTrays", color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center, fontSize = 14.sp)
            Text("Trays\nproofed", color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center, fontSize = 14.sp)
            Text("Amount\nto proof", color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center, fontSize = 14.sp)
        }
        // BP
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                state = bpState1,
                label = { Text("Bp") },
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(3)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                state = bpState2,
                label = { Text("") },
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(2)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(bpSubtractor.toString(), color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center)
        }
        //SM
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                state = smState1,
                label = { Text("Sm") },
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation
                    .maxLength(2).then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                state = smState2,
                label = { Text("") },
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(2)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(smSubtractor.toString(), color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center)
        }
        //MD
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
            OutlinedTextField(
                state = mdState1,
                label = { Text("Md") },
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(3)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                state = mdState2,
                label = { Text("") },
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(2)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(mdSubtractor.toString(), color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center)
        }
        //LG
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
            OutlinedTextField(
                state = lgState1,
                label = { Text("Lg") },
                labelPosition = TextFieldLabelPosition.Attached(alwaysMinimize = true),
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(3)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                state = lgState2,
                label = { Text("") },
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier.size(64.dp),
                inputTransformation = InputTransformation.maxLength(2)
                    .then(DigitOnlyInputTransformation()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Text(lgSubtractor.toString(), color = Color.White, modifier = Modifier.width(64.dp), textAlign = TextAlign.Center)
        }
        Spacer(Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Button(
                onClick = { clearAll() },
                content = { Text("Clear All") }
            )
        }
    }
}

@Preview
@Composable
fun DoughCalcPreview(){
    DoughCalc()
}
fun doughCalc(x: Int,y:Int):Int{
    val a = x/y
    var b = if (x>0) 1 else 0
    b = if (x.mod(y)==0) 0 else b
    return a + b
}