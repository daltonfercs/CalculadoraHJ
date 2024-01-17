package com.example.calculadorahj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACION = "*"
    val DIVISION = "/"
    val PORCENTAJE = "%"

    var operacionActual =   ""

    var primerNumero:Double = Double.NaN
    var segundoNumero:Double = Double.NaN

    lateinit var txtTemp:TextView
    lateinit var txtResult:TextView
    lateinit var formatoDecimal:DecimalFormat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.##########")
        txtTemp = findViewById(R.id.txtTemp)
        txtResult   =   findViewById(R.id.txtResult)
    }

    fun cambiarOperador(b:View){

        if (txtTemp.text.isNotEmpty() || primerNumero.toString() != "NaN"){

        calcular()
        val boton:Button    =   b as Button
        operacionActual =   boton.text.toString().trim()

        txtResult.text = formatoDecimal.format(primerNumero)    +   operacionActual
        txtTemp.text    =   ""
        }
        }

    fun calcular(){
        try {
            if (primerNumero.toString() != "NaN"){
                if (txtTemp.text.toString().isEmpty()){
                    txtTemp.text    =   txtResult.text.toString()
                }

                segundoNumero   =   txtTemp.text.toString().toDouble()
                txtTemp.text    =   ""

                when(operacionActual){
                    "+" -> primerNumero =   (primerNumero   +   segundoNumero)
                    "-" -> primerNumero =   (primerNumero   -   segundoNumero)
                    "*" -> primerNumero =   (primerNumero   *   segundoNumero)
                    "/" -> primerNumero =   (primerNumero   /   segundoNumero)
                    "%" -> primerNumero =   (primerNumero   %   segundoNumero)

                }
            }else{
                primerNumero   =   txtTemp.text.toString().toDouble()

            }
        } catch (e:Exception){

        }

    }

    fun seleccionarNumero(b:View){
        val boton:Button    = b as Button
        if (txtTemp.text.toString() ==  "0")
            txtTemp.text=""
        txtTemp.text = txtTemp.text.toString() + boton.text.toString()
    }

    fun igual(b:View){

        calcular()
        txtResult.text  =   formatoDecimal.format(primerNumero)
        //primerNumero    =   Double.NaN
        operacionActual =   ""
    }
    fun cambio(b: View){
        val boton:Button    = b as Button
        if (txtResult.text.toString() != "0" || txtTemp.text.toString() != "0") {
            if (boton.text.toString().trim() == "+/-") {
                if (txtResult.text.equals("NaN")){
                    txtTemp.text    = txtResult.text.toString()

                    primerNumero *= (-1)

                    txtResult.text  =   formatoDecimal.format(primerNumero)

                } else{

                    primerNumero *= (-1)

                    txtResult.text  =   formatoDecimal.format(primerNumero)
                    txtTemp.text = "0"
                }

            }
        }
    }

    fun borrar(b:View){
        val boton:Button    =   b as Button
        if (boton.text.toString().trim()=="AC"){
            primerNumero    =   Double.NaN
            segundoNumero    =   Double.NaN
            txtTemp.text    =   "0"
            txtResult.text   =   "0"
        }
    }

}