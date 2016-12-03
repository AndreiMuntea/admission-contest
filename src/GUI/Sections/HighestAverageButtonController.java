package GUI.Sections;

import domain.DTO.TopSectionsByAverageDTO;
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
public class HighestAverageButtonController extends AbstractObserver<Option> {
    @FXML
    TableView<TopSectionsByAverageDTO> table;
    @FXML
    TableColumn<TopSectionsByAverageDTO, String> sectionNameColumn;
    @FXML
    TableColumn<TopSectionsByAverageDTO, Double> sectionAverageColumn;


    private ObservableList<TopSectionsByAverageDTO> model;
    private OptionsServices service;


    public HighestAverageButtonController(){}

    public void setService(OptionsServices service)
    {
        this.service = service;
        service.addObserver(this);
        sectionNameColumn.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
        sectionAverageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));

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

    public List<TopSectionsByAverageDTO> loadList(){
        List<TopSectionsByAverageDTO> list = new ArrayList<>();
        try{
            list.addAll(service.getTop10SectionsByAverage());
        } catch (MyException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, e.getError());
            a.showAndWait();
            list.clear();
        }
        return list;
    }
}
