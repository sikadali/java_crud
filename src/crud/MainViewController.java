package crud;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sika Dali
 */
public class MainViewController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfArtist;
    @FXML
    private TextField tfGenre;
    @FXML
    private TextField tfYear;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Songs> tbSongs;
    @FXML
    private TableColumn<Songs, Integer> colId;
    @FXML
    private TableColumn<Songs, String> colTitle;
    @FXML
    private TableColumn<Songs, String> colArtist;
    @FXML
    private TableColumn<Songs, String> colGenre;
    @FXML
    private TableColumn<Songs, Integer> colYear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showSongs();
    }  
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if (event.getSource() == btnDelete){
            deleteRecord();
        }
    }
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/song_library_crud", "root", "");
            return conn;
        }catch(Exception ex){
            System.out.println("ERROR : " + ex.getMessage());
            return null;
        }
    }
    
    private ObservableList<Songs> getBooksList(){
        ObservableList<Songs> songList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM songs";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Songs songs;
            while(rs.next()){
                songs = new Songs(rs.getInt("id"), rs.getString("title"), rs.getString("artist"), rs.getString("genre"), rs.getInt("year"));
                songList.add(songs);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return songList;
    }
            
    private void showSongs(){
        ObservableList<Songs> list = getBooksList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Songs, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Songs, String>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<Songs, String>("artist"));
        colGenre.setCellValueFactory(new PropertyValueFactory<Songs, String>("genre"));
        colYear.setCellValueFactory(new PropertyValueFactory<Songs, Integer>("year"));
        
        tbSongs.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO songs VALUES (" + tfId.getText() + ",'" + tfTitle.getText() + "','" + tfArtist.getText() + "','"
                + tfGenre.getText() + "'," + tfYear.getText() + ")";
        executeQuery(query);
        showSongs();
    }
    
    private void updateRecord(){
        String query = "UPDATE `songs` SET`title`='" + tfTitle.getText() + "',`artist`='" + tfArtist.getText() 
            + "',`genre`='" + tfGenre.getText() + "',`year`='" + tfYear.getText() + "' WHERE `id`='" + tfId.getText() + "'";
        executeQuery(query);
        showSongs();
    }

    private void deleteRecord(){
        String query = "DELETE FROM `songs` WHERE `id`='" + tfId.getText() + "'";
        executeQuery(query);
        showSongs();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
