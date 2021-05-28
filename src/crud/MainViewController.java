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
    }    
    
    @FXML
    private void handleButtonAction(Event event){
        System.out.println("Button clicked");
    }
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            return conn;
        }catch(Exception ex){
            System.out.println("ERROR : " + ex.getMessage());
            return null;
        }
    }
    
    private ObservableList<Songs> getBooksList(){
        ObservableList<Songs> songList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Songs";
        Statement st;
        ResultSet res;
        try {
            st = conn.createStatement();
            res = st.executeQuery(query);
            Songs songs;
            while(res.next()){
                songs = new Songs(res.getInt("id"), res.getString("title"), res.getString("artist"), res.getString("genre"), res.getInt("year"));
                songList.add(songs);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return songList;
    }
            
    private void showBooks(){
        ObservableList<Songs> list = getBooksList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Songs, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Songs, String>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<Songs, String>("artist"));
        colGenre.setCellValueFactory(new PropertyValueFactory<Songs, String>("genre"));
        colYear.setCellValueFactory(new PropertyValueFactory<Songs, Integer>("year"));
    }
}
