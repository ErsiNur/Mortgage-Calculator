import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;


public class Main extends Application{

    Stage window;



    public static void main(String[] args){
        launch(args);
    }

    @Override
    public  void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Mortgage Calc - JavaFx");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,5));
        grid.setVgap(10);
        grid.setHgap(10);

        //House Price Label / Input
        Label housePriceLabel = new Label("House Price:");
        GridPane.setConstraints(housePriceLabel, 0,0);

        TextField housePriceInput = new TextField();
        housePriceInput.setPromptText("ex. $100000");
        GridPane.setConstraints(housePriceInput, 1,0);

        Label housePriceErrorLabel = new Label();
        GridPane.setConstraints(housePriceErrorLabel,2,0);
        //END OF HOUSE PRICE

        //DownPayment Price Label / Input
        Label downPaymentPriceLabel = new Label("Down Payment Price:");
        GridPane.setConstraints(downPaymentPriceLabel, 0,1);

        TextField downPaymentPriceInput = new TextField();
        downPaymentPriceInput.setPromptText("ex. $20000");
        GridPane.setConstraints(downPaymentPriceInput,1,1 );

        Label downPaymentPriceErrorLabel = new Label();
        GridPane.setConstraints(downPaymentPriceErrorLabel,2,1);
        //END OF DP PRICE

        //Rate % Label / Input
        Label rateLabel = new Label("Rate %:");
        GridPane.setConstraints(rateLabel,0,2);

        TextField rateInput = new TextField();
        rateInput.setPromptText("ex. 5%");
        GridPane.setConstraints(rateInput,1,2);

        Label rateErrorLabel = new Label();
        GridPane.setConstraints(rateErrorLabel,2,2);
        //END OF RATE %

        //Custom Term Length Slider Label / Input
        Label termLengthLabel = new Label("Custom Term Length:");
        GridPane.setConstraints(termLengthLabel,0,3);

        Slider termLength = new Slider(0,100,15);
        termLength.setShowTickLabels(true);
        termLength.setShowTickMarks(true);
        termLength.setMajorTickUnit(5.0);
        GridPane.setConstraints(termLength, 1, 3);

        Label termLengthSliderLabel = new Label("15 Years");
        GridPane.setConstraints(termLengthSliderLabel,2,3);
        //END OF SLIDER

        //Calculate Button
        Button calculate = new Button("Calculate Mortgage");
        GridPane.setConstraints(calculate,1,4);
        //END OF BUTTON

        //10 Year Term Monthly Payment
        Label tenYearTermMPLabel = new Label("10 Year Term Monthly Payment: ");
        GridPane.setConstraints(tenYearTermMPLabel,0,6);
        Label tenYearTermMPValue = new Label();
        GridPane.setConstraints(tenYearTermMPValue, 1,6);

        //20 Year Term Monthly Payment
        Label twentyYearTermMPLabel = new Label("20 Year Term Monthly Payment: ");
        GridPane.setConstraints(twentyYearTermMPLabel,0,7);

        Label twentyYearTermMPValue = new Label();
        GridPane.setConstraints(twentyYearTermMPValue,1,7);

        //30 Year Term Monthly Payment
        Label thirtyYearTermMPLabel = new Label("30 Year Term Monthly Payment:");
        GridPane.setConstraints(thirtyYearTermMPLabel,0,8);

        Label thirtyYearTermMPValue = new Label();
        GridPane.setConstraints(thirtyYearTermMPValue,1,8);

        //Custom Year Term Monthly Payment
        Label customYearTermMPLabel = new Label();
        GridPane.setConstraints(customYearTermMPLabel,0,9);

        Label customYearTermMPValue = new Label();
        GridPane.setConstraints(customYearTermMPValue,1,9);

        grid.getChildren().addAll(
                housePriceLabel,
                housePriceInput,
                housePriceErrorLabel,

                downPaymentPriceLabel,
                downPaymentPriceInput,
                downPaymentPriceErrorLabel,

                calculate,

                termLengthLabel,
                termLength,
                termLengthSliderLabel,

                rateLabel,
                rateInput,
                rateErrorLabel,

                tenYearTermMPLabel,
                tenYearTermMPValue,

                twentyYearTermMPLabel,
                twentyYearTermMPValue,

                thirtyYearTermMPLabel,
                thirtyYearTermMPValue,

                customYearTermMPLabel,
                customYearTermMPValue
        );

        //SLIDE VALUE DISPLAY CONTROL
        termLength.valueProperty().addListener((ChangeListener) (arg0, arg1, arg2) -> {
            termLengthSliderLabel.textProperty().setValue(String.valueOf((int) termLength.getValue() + " Years"));
        });

        //ON CLICK CALCULATE CONTROL
        calculate.setOnAction(event -> {

            double downPaymentPrice = CheckError.isDouble(downPaymentPriceInput,downPaymentPriceErrorLabel);
            double housePrice = CheckError.isDouble(housePriceInput,housePriceErrorLabel) - downPaymentPrice;
            double rate = CheckError.isDouble(rateInput,rateErrorLabel);
            int customTerm = (int) termLength.getValue();

            double tenYearMPValue = MortgageComputer.computePayment(housePrice,rate,10);
            double twentyYearMPValue = MortgageComputer.computePayment(housePrice,rate,20);
            double thirtyYearMPValue = MortgageComputer.computePayment(housePrice,rate,30);
            double customYearMPValue = MortgageComputer.computePayment(housePrice,rate,customTerm);


            tenYearTermMPValue.setText(String.format(" $%.2f", tenYearMPValue));
            twentyYearTermMPValue.setText(String.format(" $%.2f", twentyYearMPValue));
            thirtyYearTermMPValue.setText(String.format(" $%.2f", thirtyYearMPValue));

            customYearTermMPLabel.setText(String.format("%d Year Term Monthly Payment:", customTerm));
            customYearTermMPValue.setText(String.format(" $%.2f", customYearMPValue));



        });


        Scene scene = new Scene(grid, 710,350);
        window.setScene(scene);


        window.show();
    }


}
