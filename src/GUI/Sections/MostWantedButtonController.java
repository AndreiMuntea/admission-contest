package GUI.Sections;

import domain.DTO.TopSectionsByNumberDTO;
import domain.Option;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.OptionsServices;
import utils.MyException;
import utils.obs.AbstractObserver;
import utils.obs.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 12/3/2016.
 */
public class MostWantedButtonController extends AbstractObserver<Option> {
    @FXML
    TableView<TopSectionsByNumberDTO> table;
    @FXML
    TableColumn<TopSectionsByNumberDTO, String> sectionNameColumn;
    @FXML
    TableColumn<TopSectionsByNumberDTO, Integer> sectionNumberColumn;


    private ObservableList<TopSectionsByNumberDTO> model;
    private OptionsServices service;


    public MostWantedButtonController(){}

    public void setService(OptionsServices service)
    {
        this.service = service;
        service.addObserver(this);
        sectionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
        sectionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfCandidates"));

        model = FXCollections.observableArrayList();
        model.addAll(loadList());
        table.setItems(model);
    }


    @Override
    public void update(Observable<Option> observable) {
        model.clear();
        model.addAll(FXCollections.observableArrayList(loadList()));
        table.setItems(model);
    }

    @Override
    public void update(Observable<Option> observable, Object o) {

    }

    public List<TopSectionsByNumberDTO> loadList(){
        List<TopSectionsByNumberDTO> list = new ArrayList<>();
        try{
            list.addAll(service.getTop5MostWantedSections());
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, e.getError());
            a.showAndWait();
            list.clear();
        }
        return list;
    }
}
