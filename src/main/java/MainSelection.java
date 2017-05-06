import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by pmcgi on 5/3/2017.
 */
public class MainSelection extends JFrame {

    private JButton submitButton;
    private JRadioButton searchCurrentPlayListRadioButton;
    private JRadioButton tellPersonalMusicYourRadioButton;
    private JTextField ArtistSearch;
    private JTextField SongSearch;
    private JPanel MainPanel;


    protected MainSelection() {
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //User has option to Search Playlist by Artist/Song or by the mood they are in with "Personal Music" Digital Assistant
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchCurrentPlayListRadioButton.isSelected()) {
                    setVisible(false);
                    try {
                        SongList Songs = new SongList(ArtistSearch, SongSearch);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

                if (tellPersonalMusicYourRadioButton.isSelected()) {
                    //connect with API.ai
                    setVisible(false);
                    RunAPI();
                }
            }
        });
    }

    protected void RunAPI() {
    }

}
