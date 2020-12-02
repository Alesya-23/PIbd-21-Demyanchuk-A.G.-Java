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
    JButton btnLeft;
    JButton btnRigth;
    JButton btnUp;
    JButton btnDown;
    JButton btnCreate;
    private boolean canDraw;
    Direction direction;
    JLabel lblCountMotors;
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

        btnUp = new JButton( "" );
        btnUp.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        btnUp.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat.MoveTransport( Direction.Up );
                boatPanel.repaint();
            }
        } );
        btnUp.setIcon( new ImageIcon("C:\\Users\\aleca\\worcspace\\JavaFormBoat\\assets\\up.jpg") );
        btnUp.setBounds( 690, 352, 35, 35 );
        frame.getContentPane().add( btnUp );

        btnDown = new JButton( "" );
        btnDown.setBackground( UIManager
                .getColor( "PasswordField.selectionBackground" ) );
        btnDown.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormBoat\\assets\\down.jpg" ) );
        btnDown.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boat.MoveTransport( Direction.Down );
                boatPanel.repaint();
            }
        } );
        btnDown.setBounds( 690, 416, 35, 35 );
        frame.getContentPane().add( btnDown );

        btnRigth = new JButton( "" );
        btnRigth.setBackground( UIManager.getColor( "MenuItem.selectionBackground" ) );
        btnRigth.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormBoat\\assets\\rigth.jpg" ) );
        btnRigth.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
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
                boat.MoveTransport( Direction.Left );
                boatPanel.repaint();
            }
        } );
        btnLeft.setIcon( new ImageIcon(
                "C:\\Users\\aleca\\worcspace\\JavaFormBoat\\assets\\left.jpg" ) );
        btnLeft.setBounds( 656, 383, 35, 35 );
        frame.getContentPane().add( btnLeft );

        countMotor = random.nextInt( 3 ) + 1;
        boat = new Boat( random.nextInt( 300 ), random.nextInt( 1000 ), Color.green,
                Color.red, true, true, true, countMotor );
        btnCreate = new JButton( "Создать" );
        btnCreate.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                canDraw = true;
                boatPanel.drawCan( canDraw );
                boat.SetPosition( random.nextInt( 200 ), random.nextInt( 200 ),
                        boatPanel.getWidth(), boatPanel.getHeight() );
                lblCountMotors.setText( "Кол-во моторов: " + countMotor );
                boatPanel.repaint();
            }
        } );
        btnCreate.setBackground( new Color( 173, 216, 230 ) );
        btnCreate.setBounds( 638, 71, 115, 29 );
        frame.getContentPane().add( btnCreate );

        boatPanel = new BoatPanel( boat, this, canDraw );
        boatPanel.setBackground( new Color( 135, 206, 250 ) );
        boatPanel.setBounds( 0, 0, 603, 493 );
        frame.getContentPane().add( boatPanel );

        lblCountMotors = new JLabel( "Кол-во моторов: " );
        lblCountMotors.setBackground( new Color( 173, 216, 230 ) );
        lblCountMotors.setBounds( 638, 16, 156, 39 );
        frame.getContentPane().add( lblCountMotors );
    }

    @Override
    public void update() {
        boatPanel.repaint();
    }
}
