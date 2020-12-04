package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.JLabel;

import Boat.Boat;
import Boat.MotorBoat;
import Enums.Direction;
import Logics.Updated;

public class FormBoat implements Updated {
    public JFrame frame;
    public JFrame parentFrame;
    BoatPanel boatPanel;
    Boat boat;
    final Random random = new Random();
    JButton btnLeft;
    JButton btnRigth;
    JButton btnUp;
    JButton btnDown;
    JButton btnBack;
    JLabel lblCountMotors;
    JLabel lblTypeMotors;

    public void run() {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    FormBoat window = new FormBoat( parentFrame );
                    window.frame.setVisible( true );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    public FormBoat(JFrame frame) {
        initialize();
        this.parentFrame = frame;
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground( new Color( 240, 248, 255 ) );
        frame.setBounds( 100, 100, 831, 549 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( null );

        boat = getBoat();

        boatPanel = new BoatPanel( boat );
        boatPanel.setBackground( new Color( 135, 206, 250 ) );
        boatPanel.setBounds( 0, 0, 603, 493 );
        frame.getContentPane().add( boatPanel );

        btnUp = new JButton( "" );
        btnUp.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        btnUp.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat = getBoat();
                boat.MoveTransport( Direction.Up );
                boatPanel.repaint();
            }
        } );
        btnUp.setIcon( new ImageIcon( ".\\assets\\up.jpg" ) );
        btnUp.setBounds( 690, 352, 35, 35 );
        frame.getContentPane().add( btnUp );

        btnDown = new JButton( "" );
        btnDown.setBackground( UIManager
                .getColor( "PasswordField.selectionBackground" ) );
        btnDown.setIcon( new ImageIcon(
                ".\\assets\\down.jpg" ) );
        btnDown.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat = getBoat();
                boat.MoveTransport( Direction.Down );
                boatPanel.repaint();
            }
        } );
        btnDown.setBounds( 690, 416, 35, 35 );
        frame.getContentPane().add( btnDown );

        btnRigth = new JButton( "" );
        btnRigth.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        btnRigth.setIcon( new ImageIcon(
                ".\\assets\\rigth.jpg" ) );
        btnRigth.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat = getBoat();
                boat.MoveTransport( Direction.Right );
                boatPanel.repaint();
            }
        } );
        btnRigth.setBounds( 722, 383, 35, 35 );
        frame.getContentPane().add( btnRigth );

        btnLeft = new JButton( "" );
        btnLeft.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        btnLeft.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat = getBoat();
                boat.MoveTransport( Direction.Left );
                boatPanel.repaint();
            }
        } );
        btnLeft.setIcon( new ImageIcon(
                ".\\assets\\left.jpg" ) );
        btnLeft.setBounds( 656, 383, 35, 35 );
        frame.getContentPane().add( btnLeft );

        btnBack = new JButton( "На парковку!" );
        btnBack.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                parentFrame.setVisible( true );
                frame.setVisible( false );
            }
        } );
        btnBack.setBackground( new Color( 173, 216, 230 ) );
        btnBack.setBounds( 638, 79, 156, 35 );
        frame.getContentPane().add( btnBack );

        lblCountMotors = new JLabel( "Кол-во моторов: " );
        lblCountMotors.setBackground( new Color( 173, 216, 230 ) );
        lblCountMotors.setBounds( 618, 16, 176, 29 );
        frame.getContentPane().add( lblCountMotors );

        lblTypeMotors = new JLabel( "Тип моторов:" );
        lblTypeMotors.setBounds( 618, 45, 191, 20 );
        frame.getContentPane().add( lblTypeMotors );
    }

    public void lblTypeMotorsIn(int typeMotors) {
        if (typeMotors == 1) {
            lblTypeMotors.setText( "Тип моторов: " + "эллипсический" );
        }
        if (typeMotors == 2) {
            lblTypeMotors.setText( "Тип моторов: " + "прямоугольный" );
        }
        if (typeMotors == 3) {
            lblTypeMotors.setText( "Тип моторов: " + "смешанный" );
        }
    }

    @Override
    public void update() {
        if (boat != null)
            boatPanel.repaint();
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat Doat) {
        this.boat = Doat;
        boat.SetPosition( random.nextInt( 200 ), random.nextInt( 200 ),
                boatPanel.getWidth(), boatPanel.getHeight() );
        boatPanel.setBoat( boat );
        if (boat.getClass() == MotorBoat.class) {
            lblTypeMotorsIn( ((MotorBoat) boat).getAddClass() );
            lblCountMotors.setText( "Количество моторов: " + ((MotorBoat) boat).getCountMotors() );
        }
        boatPanel.repaint();
    }
}
