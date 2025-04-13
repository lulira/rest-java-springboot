package br.com.lulira.exception;

import java.util.Date;


// classe responsável por formatar a excessão de forma mais amigável
public record ExceptionResponse(Date timestamp, String message, String details) {


}
