package com.mercadolibre.stock.utils;

import com.mercadolibre.stock.exception.CustomException;

import java.util.Objects;

public class CustomLogger {
    public static String formatError(Exception e){
        String extraMsg = "";
        if(e instanceof CustomException){
            CustomException ce = (CustomException) e;
            if(Objects.nonNull(ce.getCause()) && !ce.getCause().getMessage().trim().isEmpty())
                extraMsg = "(" + ce.getCause().getMessage() + ") ";
        }

        return e.getClass().getName() + " [" + e.getStackTrace()[0].getMethodName() + "] [line " + e.getStackTrace()[0].getLineNumber() + "] >>> " + extraMsg + e.getMessage() + " <<<";
    }
}
