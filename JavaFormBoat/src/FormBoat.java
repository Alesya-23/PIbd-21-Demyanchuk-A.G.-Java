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

public class FormBoat implements Updated {
    private JFrame frame;
    BoatPanel boatPanel;
    Boat boat;
    final Random random = new Random();
    JButton Left;
    JButton Rigth;
    JButton Up;
    JButton Down;
    JButton Create;
    private boolean canDraw;
    Direction direction;
    JLabel CountMotors;
    private int countMotor;

    public static void main(String[] args) {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    FormBoat window = new FormBoat();
                    window.frame.setVisible( true );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    public FormBoat() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground( new Color( 240, 248, 255 ) );
        frame.setBounds( 100, 100, 831, 549 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().setLayout( null );

        Up = new JButton( "" );
        Up.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        Up.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat.MoveTransport( Direction.Up );
                boatPanel.repaint();
            }
        } );
        Up.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormsBoat\\up.jpg" ) );
        Up.setBounds( 690, 352, 35, 35 );
        frame.getContentPane().add( Up );

        Down = new JButton( "" );
        Down.setBackground( UIManager
                .getColor( "PasswordField.selectionBackground" ) );
        Down.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormsBoat\\down.jpg" ) );
        Down.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat.MoveTransport( Direction.Down );
                boatPanel.repaint();
            }
        } );
        Down.setBounds( 690, 416, 35, 35 );
        frame.getContentPane().add( Down );

        Rigth = new JButton( "" );
        Rigth.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        Rigth.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormsBoat\\rigth.jpg" ) );
        Rigth.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat.MoveTransport( Direction.Right );
                boatPanel.repaint();
            }
        } );
        Rigth.setBounds( 722, 383, 35, 35 );
        frame.getContentPane().add( Rigth );

        Left = new JButton( "" );
        Left.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        Left.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat.MoveTransport( Direction.Left );
                boatPanel.repaint();
            }
        } );
        Left.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormsBoat\\left.jpg" ) );
        Left.setBounds( 656, 383, 35, 35 );
        frame.getContentPane().add( Left );

        countMotor = random.nextInt( 3 ) + 1;
        boat = new Boat( random.nextInt( 300 ), random.nextInt( 1000 ), Color.green,
                Color.red, true, true, true, countMotor );
        Create = new JButton( "Создать" );
        Create.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canDraw = true;
                boatPanel.drawCan( canDraw );
                boat.SetPosition( random.nextInt( 200 ), random.nextInt( 200 ),
                        boatPanel.getWidth(), boatPanel.getHeight() );
                CountMotors.setText( "Кол-во моторов: " + countMotor );
                boatPanel.repaint();
            }
        } );
        Create.setBackground( new Color( 173, 216, 230 ) );
        Create.setBounds( 638, 71, 115, 29 );
        frame.getContentPane().add( Create );

        boatPanel = new BoatPanel( boat, this, canDraw );
        boatPanel.setBackground( new Color( 135, 206, 250 ) );
        boatPanel.setBounds( 0, 0, 603, 493 );
        frame.getContentPane().add( boatPanel );

        CountMotors = new JLabel( "Кол-во моторов: " );
        CountMotors.setBackground( new Color( 173, 216, 230 ) );
        CountMotors.setBounds( 638, 16, 156, 39 );
        frame.getContentPane().add( CountMotors );
    }

    @Override
    public void update() {
        boatPanel.repaint();
    }
}