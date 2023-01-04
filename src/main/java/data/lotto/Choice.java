package data.lotto;

import io.output.Message;
import java.util.Scanner;
import java.util.Set;

import static io.output.Printer.printer;


public class Choice extends Lotto {

    public Choice() {
        super ( );
    }

    Set<Integer> numbersEntered;

    public Choice(Set<Integer> numbersEntered) {
        this.numbersEntered = numbersEntered;
    }

    public int getNumbersFromUser(Scanner scanner) {
        printer ( ).print ( Message.ENTER_NUMBER.getMsg ( ) );
        while (!scanner.hasNextInt ( )) {
            scanner.nextLine ( );
            printer ( ).print ( Message.CORRECT_NUMBERS.getMsg ( ) );
        }
        return scanner.nextInt ( );
    }

    public Set<Integer> choiceNums(Scanner scanner) {
        while (numbersEntered.size ( ) < getNumbers ( )) {
            int numberInput = getNumbersFromUser ( scanner );
            if ( !isNumberDuplicate ( numberInput ) ) {
                if ( isNumberOutOfBounds ( numberInput ) ) {
                    printer ( ).print ( Message.RANGE_NUMBERS.getMsg ( ) );
                    continue;
                }
                numbersEntered.add ( numberInput );
            } else {
                printer ( ).print ( Message.RANGE_NUMBERS.getMsg ( ) );
            }
        }
        return numbersEntered;
    }

    public boolean isNumberDuplicate(int number) {
        return numbersEntered.contains ( number );
    }

    public boolean isNumberOutOfBounds(int number) {
        return number < getMinNumbers ( ) || number > getMaxNumbers ( );
    }
}


