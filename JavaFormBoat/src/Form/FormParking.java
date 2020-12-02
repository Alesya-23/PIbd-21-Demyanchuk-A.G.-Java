package Form;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Boat.Boat;
import Boat.Parking;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormParking {

    private JFrame frame;
    private JTextField textFieldGetPlace;
    private ParkingPanel parkingPanel;
    private Parking<Boat> parking;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    FormParking window = new FormParking();
                    window.frame.setVisible( true );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the application.
     */
    public FormParking() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds( 100, 100, 778, 546 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( null );

        parkingPanel = new ParkingPanel( parking );
        parkingPanel.setBounds( 0, 0, 533, 490 );
        parkingPanel.setBackground( new Color( 135, 216, 250 ) );
        frame.getContentPane().add( parkingPanel );

        parking = new Parking<Boat>( parkingPanel.getWidth(), parkingPanel.getHeight() );

        parkingPanel.setBoat( parking );

        JButton btnParkingBoat = new JButton( "Припарковать лодку" );
        btnParkingBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ColorDialog dialog = new ColorDialog();
                if (dialog.ShowDialog() == DialogResult.OK) {
                    var boat = new Boat( 100, 1000, dialog.Color );

                    if (parking + boat) {
                        parkingPanel.repaint();
                    } else {
                        MessageBox.Show( "Парковка переполнена" );
                    }
                }
            }
        } );
        btnParkingBoat.setBounds( 548, 16, 193, 51 );
        frame.getContentPane().add( btnParkingBoat );

        JButton btnTakeMotorBoat = new JButton( "Припарковать катер" );
        btnTakeMotorBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        } );
        btnTakeMotorBoat.setBounds( 548, 83, 193, 29 );
        frame.getContentPane().add( btnTakeMotorBoat );

        textFieldGetPlace = new JTextField();
        textFieldGetPlace.setBounds( 657, 299, 84, 26 );
        frame.getContentPane().add( textFieldGetPlace );
        textFieldGetPlace.setColumns( 10 );

        JButton btnTakeBoat = new JButton( "Забрать" );
        btnTakeBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        } );
        btnTakeBoat.setBounds( 605, 341, 115, 29 );
        frame.getContentPane().add( btnTakeBoat );

        JLabel lblPlaceBoat = new JLabel( "Место" );
        lblPlaceBoat.setBounds( 574, 302, 69, 20 );
        frame.getContentPane().add( lblPlaceBoat );

        JLabel lblPickUpBoat = new JLabel( "Забрать лодку" );
        lblPickUpBoat.setBounds( 592, 263, 115, 20 );
        frame.getContentPane().add( lblPickUpBoat );


    }
}
 