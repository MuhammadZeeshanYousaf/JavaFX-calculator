package zeefxcalculator;

import java.text.DecimalFormat;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * FXML Controller class
 * 
 * @author Zeeshan
 */
public class CalculatorController {
    //Data Members
    @FXML
    public List<Label> history_labelList;
    public Label mainLabel, smallLabel;
    private double num1 = 0.0;
    private String operator = "";
    private boolean start = true;
    private final DecimalFormat DeciFormat = new DecimalFormat();

    //Procedures
    @FXML
    public void processNumbers(ActionEvent e){
        
        String num_BtnPressed = ((Button)e.getSource()).getText();
        
        if(start){
            mainLabel.setText("");
            start = false;
        }
        
        String mainLabelTXT = mainLabel.getText().isEmpty()? "" : mainLabel.getText();
        //Check for the point button pressed and process against
        if(num_BtnPressed.equals(".") && mainLabel.getText().isEmpty())
            mainLabel.setText("0" + num_BtnPressed);
        else if(num_BtnPressed.equals(".")){
            for(int i = 0; i < mainLabelTXT.length(); i++){
                if (mainLabelTXT.charAt(i) == '.') {
                    return;
                }
            }
            //Second Approach
            /*
            String[] mainLabelCharacters = mainLabelTXT.split("");
            for(String mainLabelCharacter : mainLabelCharacters) {
                if (mainLabelCharacters.equals(".")) {
                    return;
                }
            }
            */
            mainLabel.setText(mainLabelTXT + num_BtnPressed);
        }
        else    
            mainLabel.setText(mainLabelTXT + num_BtnPressed);
        
        smallLabel.setText("");
    }
    
    @FXML
    public void processOperators(ActionEvent e) {
        String operator_BtnPressed = ((Button)e.getSource()).getText();
         if(!operator_BtnPressed.equals("=")) {
             //This just to ensure that user does not override the operator that he pressed
//            if(!operator.isEmpty())
//                return;
            
            operator = operator_BtnPressed;     //assigning operator to operator variable that is pressed
            num1 = Double.parseDouble(mainLabel.getText());     //assigning number that is on the cal screen to num1 variable
            smallLabel.setText(operator_BtnPressed);        //Populating the operator screen
            start = true;               //Getting ready for next input (reference function: processNumbers() )
         }
         else{
             //If user didn't press the operator yet or operator variable is empty then return
             if(operator.isEmpty())
                 return;
             double num2 = Double.parseDouble(mainLabel.getText());     //Getting second number pressed
             double result = Calculate.calculate(num1, num2, operator.charAt(0));  //Calculating result
             
             //All decimal formats conversion which will remove .0 from 12.0 type of numbers
             DeciFormat.setDecimalSeparatorAlwaysShown(false);
             String num1_str = DeciFormat.format(num1);
             String num2_str = DeciFormat.format(num2);
             String result_str = DeciFormat.format(result);
             
             //Updating Calculator screen with result
             mainLabel.setText(result_str);
             
            //Filling history labels for equals button pressed
            String historyLabel_string = num1_str + operator + num2_str + " = " + result_str;
            historyLabels(historyLabel_string);     //Calling the function to update history
            operator = "";     //free the operator variable
         }
    }
    
    @FXML
    public void processBackspace(ActionEvent e) {
        String labelTxt = mainLabel.getText();  //Getting calculator screen text
        //Checking if cal screen is not null and its length is greater than 1 and screen text is not zero
        if( labelTxt != null && labelTxt.length() > 1 && !labelTxt.equals("0")) 
        {
            //removing last character from calculator screen text and writing it back
            String subString = labelTxt.substring(0, labelTxt.length()-1);
            mainLabel.setText(subString);
        }
        else{
            mainLabel.setText("0");
            start = true;
        }
    }
    
    @FXML
    public void processPercentage(ActionEvent e)
    {
        num1 = Double.parseDouble(mainLabel.getText());     //getting cal screen number
        smallLabel.setText(((Button)e.getSource()).getText());      //writing the operator symbol in operator screen
        double result = Calculate.calculate(num1, ((Button)e.getSource()).getText().charAt(0));    //calculating result
        
        //All decimal formats conversion which will remove .0 from 12.0 type of numbers
        DeciFormat.setDecimalSeparatorAlwaysShown(false);
        String num1_str = DeciFormat.format(num1);
        String result_str = DeciFormat.format(result);
        
        //Updating Calculator screen with result
        mainLabel.setText(result_str + "");
        start = true;   //getting ready for the next numbers  (reference function: processNumbers() )
        
        //Filling history labels for percentage button pressed
        String historyLabel_string = num1_str + " % = " + result_str;
        historyLabels(historyLabel_string);     //updatng history
    }
    
    @FXML
    public void processResetBtn(ActionEvent e){
        operator = "";          //reseting the operator
        mainLabel.setText("0");     //reseting the calculator screen
        smallLabel.setText("");     //Reseting the operator screen
        start = true;               //Getting ready for next input (reference function: processNumbers() )
    }
    
    //This function is updating history_labels for new calcultions
    //Reference functions: processOperators(), processPercentage()
    public void historyLabels(String recent_history)
    {
        for(int i = 9; i > 0; i--)
        {
            System.out.println("(((((((((((((((((((((((((((---LOOP_CHECK---)))))))))))))))))))))))))))");
            //moving all previous history to the next history labels
            history_labelList.get(i).setText(history_labelList.get(i-1).getText());
        }
        //updating first label with new calculation
        history_labelList.get(0).setText(recent_history);
    }
}
