
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by pmcgi on 5/6/2017.
 */

public class SongList extends JFrame {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cubes";     //Connection string – where's the database?
    static final String USER = "pmcgie";
    static final String PASSWORD = System.getenv("SQL_PW");


    //GUI Components
    private JList songList;
    private JRadioButton addSongRadioButton;
    private JRadioButton deleteSongRadioButton;
    private JButton submitButton;
    private JTextField AddArtist;
    private JTextField AddSong;
    private JTextField DeleteArtist;
    private JTextField DeleteSong;
    private JRadioButton goBackToMainRadioButton;
    private JPanel MainPanel;
    private JTextField MoodComments;

    public SongList(JTextField artistSearch, JTextField songSearch) throws SQLException {
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //User has option to Search Playlist by Artist/Song or by the mood they are in with "Personal Music" Digital Assistant
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (addSongRadioButton.isSelected()) {
                    try {
                        Class.forName(JDBC_DRIVER);
                    } catch (ClassNotFoundException cnfe) {
                        System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
                        cnfe.printStackTrace();
                        System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
                    }

                    try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                         Statement statement = conn.createStatement()) {

                        //Create a table in a database. Stores ID and time taken
                        String createTableSQL = "CREATE TABLE IF NOT EXISTS songlist (Artist varchar(50),Song VARCHAR(50),MoodComments VARCHAR (100))";
                        statement.execute(createTableSQL);

                        String InsertStatement = "INSERT INTO songlist VALUES ( ? , ? ,?)";
                        PreparedStatement psInsert = conn.prepareStatement(InsertStatement);

                        String Artist = AddArtist.getText();
                        String Song = AddSong.getText();
                        String Mood = MoodComments.getText();

                        psInsert.setString(1, Artist);
                        psInsert.setString(2, Song);
                        psInsert.setString(3,Mood);
                        psInsert.execute();

                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        System.exit(-1);
                    }
                }
            }
        });
    }
}
