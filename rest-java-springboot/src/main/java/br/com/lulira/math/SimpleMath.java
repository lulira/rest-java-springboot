package br.com.lulira.math;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


// classe resposável por fazer as operações matemáticas.
public class SimpleMath {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    @RequestMapping("/smultiplication/{numberOne}/{numberTwo}")
    public Double multiplication(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }

    @RequestMapping("/squareroot/{number}")
    public Double squareRoot(Double number) {
        return Math.sqrt(number) ;
    }




}
