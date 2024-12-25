package com.example.cgpacalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpacalculator.ui.theme.CgpaCalculatorTheme
import kotlin.math.log

data class Semester(val grade: String, val credit:Int)

class MainActivity : ComponentActivity() {
    private var semesters : MutableList<Semester> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CgpaCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    CGPA(semesters = semesters)
                }
            }
        }
    }
}

@Composable
fun CGPA(semesters: MutableList<Semester>) {

    var grade1 by remember { mutableStateOf("") }
    var credit1 by remember { mutableStateOf<Int?>(null) }

    var grade2 by remember { mutableStateOf("") }
    var credit2 by remember { mutableStateOf<Int?>(null) }

    var grade3 by remember { mutableStateOf("") }
    var credit3 by remember { mutableStateOf<Int?>(null) }

    var grade4 by remember { mutableStateOf("") }
    var credit4 by remember { mutableStateOf<Int?>(null) }

    var cgpa by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "CGPA Calculator",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        subjectText("Subject 1")
        GradeTextField(grade = grade1){grade1 = it}
        Spacer8dp()
        CreditTextField(credit1){credit1 = it}

        subjectText("Subject 2")
        GradeTextField(grade = grade2){grade2 = it}
        Spacer8dp()
        CreditTextField(credit2) {credit2 = it}

        subjectText("Subject 3")
        GradeTextField(grade = grade3){grade3 = it}
        Spacer8dp()
        CreditTextField(credit3){credit3 = it}

        subjectText("Subject 4")
        GradeTextField(grade = grade4){grade4 = it}
        Spacer8dp()
        CreditTextField(credit4) { credit4 = it}
        Spacer8dp()

        Row() {
            Column() {
                Button(onClick = {
                    val  semester = Semester(grade1, credit1 ?:0)
                    semesters.add(semester)
                    val totalCredit = semesters.sumOf { it.credit }
                    val totalGradePoint = semesters.sumOf { calculateGradePoint(it.grade, it.credit) }

                    if(totalCredit > 0){
                        cgpa = totalGradePoint / totalCredit.toDouble()
                    } else {
                        cgpa = 0.0
                    }

                    grade1 = ""
                    credit1 = null
                    grade2 = ""
                    credit2 = null
                    grade3 = ""
                    credit3 = null
                    grade4 = ""
                    credit4 = null

                }, colors = ButtonDefaults.buttonColors(Color(0xFFBEABE0))) {
                    Text(text = "Calculate CGPA", fontSize = 16.sp, color = Color.Black)
                }

                Spacer8dp()

                Surface(modifier = Modifier.width(160.dp).wrapContentHeight(), color = Color(0xFF263238), shape = RoundedCornerShape(15.dp)) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Your all time\nCGPA : $cgpa",
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFFFFFFFF))
                    )
                }
            }

            Surface(modifier = Modifier.height(95.dp).padding(top = 7.dp, start = 10.dp), color = Color(0xFF263238), shape = RoundedCornerShape(15.dp)) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = 7.dp), textAlign = TextAlign.Center,
                        text = "Previous Semester",
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFFFFFFFF))
                    )
                    if(semesters.isNotEmpty()){
                        for(semester in semesters){
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Grade: ${semester.grade}, Credit: ${semester.credit}", color = Color.White, textAlign = TextAlign.Center)
                        }
                    }

                }
            }
        }


    }
}

fun calculateGradePoint(grade: String, credit: Int):Double {
   return   when(grade.uppercase()){
        "A" -> 4.0
        "B" -> 3.0
        "C" -> 2.0
        "D" -> 1.0
        else -> 0.0
    } * credit
}

@Composable
fun subjectText(subject: String) {

    Text(
        text = subject,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 7.dp),
        style = TextStyle(fontSize = 23.sp, fontFamily = FontFamily.Serif)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextField(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade, onValueChange = { text ->
            onValueChange(text)
        }, modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text(text = "Enter Grade", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFF7E57C2)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextField(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit?.toString() ?: "",
        onValueChange = { text ->
            onValueChange(text.toIntOrNull())
        }, modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text(text = "Enter Credit", color = Color.Black, fontSize = 12.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFF7D8CCED)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black),

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun Spacer8dp() {
    Spacer(modifier = Modifier.padding(top = 8.dp))
}

//
//@Preview(showBackground = true)
//@Composable
//fun CGPApreview() {
//    CgpaCalculatorTheme {
//        CGPA()
//    }
//}


//@Preview(showBackground = true)
//@Composable
//fun GradeTextField(){
//    CgpaCalculatorTheme{
//        GradeTextField()
//    }
//}
