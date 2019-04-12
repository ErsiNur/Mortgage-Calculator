import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckError {
    public CheckError() {
    }

    public static int isInt(TextField inputField, Label errorField) {
        int value = 0;

        try {
            value = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException var4) {
            errorField.setText("THIS FIELD MUST BE INTEGER ie 1 or 10 etc..");
        }

        return value;
    }

    public static double isDouble(TextField inputField, Label errorField) {
        double value = 0.0D;

        try {
            value = Double.parseDouble(inputField.getText());
        } catch (NumberFormatException var5) {
            errorField.setText("THIS FIELD MUST BE DOUBLE ie 1.0 or 10.3 etc..");
        }

        return value;
    }
}
