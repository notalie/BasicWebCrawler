package Sorter;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class WebsiteSorterGUI extends javafx.application.Application {
    String page = "&page=1";


    public void start(Stage stage) {
        Button runner = new Button("Sort");
        runner.setMinSize(100,20);

        TextField websiteInput = new TextField("https://www.supercheapauto.com.au/shop-by-category/4wd-and-outdoors/roof-racks-and-bike-carriers/roof-racks?segment=1&page=1");
        TextField fileNameInput = new TextField("test.txt");

        GridPane.setHalignment(runner, HPos.CENTER);

        GridPane mainPane = new GridPane();
        //mainPane.setGridLinesVisible(true);
        mainPane.add(runner,0,0);
        mainPane.add(websiteInput,0,1);
        mainPane.add(fileNameInput,0,2);



        runner.setOnAction(event -> {
            for(int i=1;i<5;i++) {
                String websiteURL = websiteInput.getText().substring(0,websiteInput.getText().length()-8);
                websiteURL+=i;
                websiteURL+=page;
                Reader reader = new Reader();
                reader.setFileName(fileNameInput.getText());
                try {
                    reader.webReader(websiteURL);
                } catch (WebsiteSortException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("Error In Sorting!");
                    errorAlert.show();
                }
            }
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setContentText("Sorted!");
            errorAlert.show();
        });

        Scene scene = new Scene(mainPane, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Website Thing Fuck You");
        stage.show();
    }


}
