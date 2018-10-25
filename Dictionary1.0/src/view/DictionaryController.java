package view;

import data.Dictionary;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;
import speak.Speak;

import java.util.Optional;

public class DictionaryController {
    @FXML
    TextField tfSearch;
    @FXML
    Text word;
    @FXML
    TextArea textDetail;
    @FXML
    ListView<String> list;

    public void initialize() {
        Dictionary.getDataFromFile("dict.txt");
        list.setItems(Dictionary.getAllWord());
        tfSearchChangedValue();
        listChangedValue();
    }

    private void listChangedValue(){
        try {
            list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null){
                //tfSearch.setText(newValue);
                textDetail.setText("");
                word.setText("");
                if (Dictionary.getDetail(newValue) != null) {
                    textDetail.setText(Dictionary.getDetail(newValue));
                    word.setText(newValue);
                } else {
                    textDetail.setText("");
                    word.setText("");
                }
            }});
        } catch (NullPointerException e){
        }
    }

    private void tfSearchChangedValue() {
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null)
                list.getItems().removeAll();
                list.setItems(Dictionary.getWordSearch(newValue));
                list.getSelectionModel().selectFirst();
            if (Dictionary.getDetail(newValue)==null)
            {
                word.setText("");
                textDetail.setText("");
            }
        });
    }

    public void tfSearchAction() {
        try {
            String str = tfSearch.getText().trim().toLowerCase();
            if (!str.equals("")) {
                if (Dictionary.getDetail(str) == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("The word don't have in Dictionary");
                    alert.showAndWait();
                } else {
                    word.setText(str);
                    textDetail.setText(Dictionary.getDetail(str));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void buttonAddOnClicked(){
        Dialog<Pair<String,String>> dialog = new Dialog();
        dialog.setTitle("Add Word");
        dialog.setHeaderText("Do you want to add new word ?");
        ButtonType okButtonType = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField newWord = new TextField();
        newWord.setPromptText("Ex : Hello");
        TextField newDetail = new TextField();
        newDetail.setPromptText("Ex : Xin chao");

        grid.add(new Label("Word: "),0,0);
        grid.add(newWord,0,1);
        grid.add(new Label("Detail: "),1,0);
        grid.add(newDetail,1,1);

        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);


        newWord.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType){
                if (Dictionary.getDetail(newWord.getText()) != null)
                {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("The word existed in Dictionary");
                    alert1.showAndWait();
                }
                else{
                    Dictionary.addWord(newWord.getText(),newDetail.getText());
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("The word added successful");
                    alert2.showAndWait();
                }

            }
            return null;
        } );
            dialog.showAndWait();
    }

    public void buttonEditOnClicked(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit");
        dialog.setHeaderText("Are you want to edit ?");
        dialog.setContentText("New Mean: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String newMean = result.get();
            String wordInstance = list.getSelectionModel().getSelectedItem();
            if (wordInstance == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You don't choose word");
                alert.showAndWait();
            }
            else {
                Dictionary.removeWord(wordInstance);
                Dictionary.addWord(wordInstance,newMean);
                textDetail.setText(newMean);
            }
        }
    }
    public void buttonRemoveOnClicked(){
        Alert confirmRemove = new Alert(Alert.AlertType.NONE);
        confirmRemove.setTitle("Message");
        confirmRemove.setContentText("Are you want to delete this word  ?");
        ButtonType buttonTypeOk = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmRemove.getButtonTypes().addAll(buttonTypeOk,buttonTypeCancel);
        Optional<ButtonType> resultRemove = confirmRemove.showAndWait();

        if(resultRemove.get()==buttonTypeOk){
            try {
                Dictionary.removeWord(list.getSelectionModel().getSelectedItem());
                list.getItems().removeAll();
                list.setItems(Dictionary.getAllWord());
            }
            catch (Exception e){
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("You don't choose word");
                alert2.showAndWait();
            }
        }
        else{
            confirmRemove.close();
        }

    }
    public void buttonSpeakOnClicked(){
        new Speak(word.getText());
    }
}
