package Form;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;

import Boat.Boat;
import Boat.MotorBoat;
import Boat.Harbour;
import Logics.IAdditional;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class FormHarbour {
    private JFrame frame;
    private JTextField textFieldGetPlace;
    private HarbourPanel harbourPanel;
    private Harbour<Boat, IAdditional> harbour;
    private JButton btnTakeBoat;
    private JButton btnParkingBoat;

    public static void main(String[] args) {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    FormHarbour window = new FormHarbour();
                    window.frame.setVisible( true );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    public FormHarbour() {
        initialize();
    }

    private void initialize() {
        int width = 533;
        int height = 390;
        frame = new JFrame();
        frame.setBounds( 100, 100, 778, 546 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( null );

        harbour = new Harbour<Boat, IAdditional>( width, height );

        harbourPanel = new HarbourPanel( harbour );
        harbourPanel.setBounds( 0, 0, width, height );
        harbourPanel.setBackground( new Color( 135, 216, 250 ) );
        frame.getContentPane().add( harbourPanel );

        btnParkingBoat = new JButton( "Припарковать лодку" );
        btnParkingBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color mainColor = JColorChooser.showDialog( frame, "Выберите цвет лодки", Color.BLUE );
                if (mainColor != null) {
                    var boat = new Boat( 100, 1000, mainColor );
                    if (harbour.add( boat )) {
                        harbourPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog( frame, "Гавань переполнена", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                    }
                }
                harbourPanel.repaint();
            }
        } );
        btnParkingBoat.setBounds( 548, 16, 193, 51 );
        frame.getContentPane().add( btnParkingBoat );

        JButton btnTakeMotorBoat = new JButton( "Припарковать катер" );
        btnTakeMotorBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color mainColor = JColorChooser.showDialog( frame, "Выберите цвет лодки", Color.BLUE );
                Color dopColor = JColorChooser.showDialog( frame, "Выберите цвет лодки", Color.BLUE );
                if (mainColor != null) {
                    Random random = new Random();
                    int typeMotors = random.nextInt( 3 ) + 1;
                    int countMotor = random.nextInt( 3 ) + 1;
                    var motorBoat = new MotorBoat( 100, 1000, mainColor, dopColor, true, true, true, typeMotors, countMotor );
                    if (harbour.add( motorBoat )) {
                        harbourPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog( frame, "Гавань переполнена", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                    }
                }
            }
        } );
        btnTakeMotorBoat.setBounds( 548, 83, 193, 29 );
        frame.getContentPane().add( btnTakeMotorBoat );

        textFieldGetPlace = new JTextField();
        textFieldGetPlace.setBounds( 657, 299, 84, 26 );
        frame.getContentPane().add( textFieldGetPlace );
        textFieldGetPlace.setColumns( 10 );

        btnTakeBoat = new JButton( "Забрать" );
        btnTakeBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (textFieldGetPlace.getText() != "") {
                    var boat = harbour.remove( Integer.parseInt( textFieldGetPlace.getText() ) );
                    if (boat != null) {
                        EventQueue.invokeLater( () -> {
                            FormBoat formBoat;
                            try {
                                formBoat = new FormBoat( frame );
                                formBoat.frame.setVisible( true );
                                frame.setVisible( false );
                            } catch (Exception exp) {
                                exp.printStackTrace();
                                return;
                            }
                            formBoat.setBoat( boat );
                        } );
                    } else
                        JOptionPane.showMessageDialog( frame, "Парковочное место пусто", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                    harbourPanel.repaint();
                }
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
 