/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeefxcalculator;

/**
 *
 * @author Zeeshan
 */
public class Calculate {
    
    public static double calculate(double num1, double num2, char operator){
        //calculation against every operator pressed/passed
        switch(operator){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case 'X':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;
        }
    }
    
    public static double calculate(double num1, char operator){
        //validating the operator and calculating the percentage
        if(operator == '%')
            return num1 / 100;
        return 0;
    }
    
}
