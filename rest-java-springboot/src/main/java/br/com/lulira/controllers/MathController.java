package br.com.lulira.controllers;


import br.com.lulira.math.SimpleMath;
import br.com.lulira.request.converters.NumberConvert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.lulira.request.converters.NumberConvert.convertToDouble;
import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    // operação soma
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
           @PathVariable("numberOne") String numberOne,
           @PathVariable("numberTwo")String numberTwo

    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric value");
        return math.sum(NumberConvert.convertToDouble (numberOne), NumberConvert.convertToDouble(numberTwo));
    }

    // operação subtração
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")String numberTwo

    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric value");
        return math.subtraction(NumberConvert.convertToDouble (numberOne) , NumberConvert.convertToDouble(numberTwo));
    }


    // operação multiplicação
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")String numberTwo

    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric value");
        return math.multiplication(NumberConvert.convertToDouble (numberOne), NumberConvert.convertToDouble(numberTwo));
    }


    // operação divisão
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")String numberTwo

    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric value");
        return math.division(NumberConvert.convertToDouble (numberOne), NumberConvert.convertToDouble(numberTwo));
    }


    // operação média
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")String numberTwo

    ) throws Exception {

        if (!NumberConvert.isNumeric(numberOne) || !NumberConvert.isNumeric(numberTwo)) throw new UnsupportedOperationException("Please set a numeric value");
        return math.mean(NumberConvert.convertToDouble(numberOne) , NumberConvert.convertToDouble(numberTwo) / 2);
    }


    // operação raiz quadrada
    @RequestMapping("/squareroot/{number}")
    public Double squareRoot(
            @PathVariable("number") String number

    ) throws Exception {
        if (!NumberConvert.isNumeric(number)) throw new UnsupportedOperationException("Please set a numeric value");

        return  Math.sqrt(NumberConvert.convertToDouble(number));
    }



}
