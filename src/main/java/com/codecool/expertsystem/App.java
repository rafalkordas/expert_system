package com.codecool.expertsystem;

import com.codecool.expertsystem.view.Display;
import com.codecool.expertsystem.parsers.FactParser;
import com.codecool.expertsystem.parsers.RuleParser;
import com.codecool.expertsystem.error.CarNotInXmlException;

public class App {

    public static void main( String[] args ) {
        String answer;
        ESProvider esProvider = new ESProvider(new FactParser(), new RuleParser());
        
        do {

            esProvider.resetAnswers();
            Display.clearScreen();
            Display.printTitleMenu();
            esProvider.collectAnswers();

            try {
                Display.printMessage("We found car/s for you:\n" + esProvider.evaluate());
            } catch (CarNotInXmlException e) {
                Display.printMessage("There is no car matching your requirements.");
            }
            finally{
                answer = Display.getStringInput("do you want to answer questions again? (y/n)");
            }

        } while (answer.equals("y"));

    }
}
