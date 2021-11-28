package com.example.loginscreenjetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginscreenjetpack.ui.theme.DefaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultTheme {
                SignInScreen()
            }
        }
    }
}

@Composable
fun SignInScreen(){
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val isFormValid by derivedStateOf {
        email.isNotBlank() && password.length >= 7
    }

    val context = LocalContext.current
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
            Image(
                painterResource(id = R.drawable.ic_visa ),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Card(
                Modifier
                    .weight(2f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Welcome Back!", fontWeight = FontWeight.Bold, fontSize=32.sp)
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = email,
                            onValueChange = {email = it},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                            label = { Text(text = "Email")},
                            singleLine = true,
                            trailingIcon = {
                                if(email.isNotBlank()){
                                    IconButton(onClick = { email = ""}) {
                                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = {password = it},
                            label = { Text(text = "Password")},
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                            visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = {isPasswordVisible = !isPasswordVisible}) {
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle",
                                    )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                Toast.makeText(context, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "Log In")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            TextButton(onClick = {}) {
                                Text(text = "Sign Up")
                            }
                            TextButton(onClick = {}) {
                                Text(text = "Forget Password?", color = Color.Gray)
                            }
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DefaultTheme {
        SignInScreen()
    }
}