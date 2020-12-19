package Form;

import Boat.Boat;
import Boat.MotorBoat;
import Logics.CicleMotors;
import Logics.IAdditional;
import Boat.IBoatDelegate;
import Logics.RectangleMotors;
import Logics.TraungleMotors;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;

public class FormBoatConfing {
    public JDialog frame;
    public Boat boat = null;
    private IBoatDelegate eventAddBoat;
    private JSpinner spinnerCountMotos = new JSpinner();
    private Random random = new Random();

    FormBoatConfing(JFrame parentFrame) {
        initialize( parentFrame );
    }

    private void initialize(JFrame parentFrame) {
        frame = new JDialog( frame );
        frame.setBounds( 100, 100, 800, 500 );
        frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        frame.getContentPane().setLayout( null );
        frame.setTitle( "Добавление лодки" );

        JPanel typeBoatPanel = new JPanel( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        typeBoatPanel.setBorder( BorderFactory.createTitledBorder( "Тип лодки" ) );
        typeBoatPanel.setBounds( 10, 10, 170, 85 );
        frame.getContentPane().add( typeBoatPanel );

        setBoatLabels( typeBoatPanel, "Лодка" );
        setBoatLabels( typeBoatPanel, "Катер" );

        JPanel groupPanelOptions = new JPanel( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        groupPanelOptions.setBorder( BorderFactory.createTitledBorder( "Параметры" ) );
        groupPanelOptions.setBounds( 10, 100, 170, 180 );
        frame.getContentPane().add( groupPanelOptions );

        JLabel lblWeight = new JLabel( "Вес лодки" );
        groupPanelOptions.add( lblWeight );

        JLabel lblSpeed = new JLabel( "Скорость" );
        groupPanelOptions.add( lblSpeed );

        JSpinner spinnerSpeed = new JSpinner();
        spinnerSpeed.setModel( new SpinnerNumberModel( 100, 100, 300, 1 ) );
        groupPanelOptions.add( spinnerSpeed );

        JSpinner spinnerWeight = new JSpinner();
        spinnerWeight.setModel( new SpinnerNumberModel( 300, 300, 1000, 1 ) );
        groupPanelOptions.add( spinnerWeight );

        JCheckBox checkBoxMotors = new JCheckBox( "Моторы" );
        groupPanelOptions.add( checkBoxMotors );

        JCheckBox checkBoxCabin = new JCheckBox( "Кабина" );
        groupPanelOptions.add( checkBoxCabin );

        JCheckBox checkBoxLines = new JCheckBox( "Линии" );
        groupPanelOptions.add( checkBoxLines );

        JPanel groupPanelMotors = new JPanel( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        groupPanelMotors.setBorder( BorderFactory.createTitledBorder( "Тип моторов" ) );
        groupPanelMotors.setBounds( 180, 210, 200, 200 );
        frame.getContentPane().add( groupPanelMotors );

        JLabel lblTypeMotor = new JLabel( "Тип моторов" );
        groupPanelMotors.add( lblTypeMotor );

        setMotorLabels( groupPanelMotors, "Круглый" );
        setMotorLabels( groupPanelMotors, "Прямоугольный" );
        setMotorLabels( groupPanelMotors, "Треугольный" );

        JLabel lblCountMotor = new JLabel( "Количество моторов" );
        groupPanelMotors.add( lblCountMotor );

        spinnerCountMotos.setModel( new SpinnerNumberModel( 1, 1, 3, 1 ) );
        groupPanelMotors.add( spinnerCountMotos );

        BoatPanel boatPanel = new BoatPanel();
        boatPanel.setBackground( new Color( 175, 238, 238 ) );
        boatPanel.setBounds( 222, 27, 296, 174 );
        boatPanel.setTransferHandler( new TransferHandler( "text" ) );
        boatPanel.setTransferHandler( new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                String data;
                try {
                    if (t.getTransferData( DataFlavor.stringFlavor ) instanceof IAdditional) {
                        if (boat != null && boat.getClass() == MotorBoat.class) {
                            ((MotorBoat) boat).SetCountMotors( (int) spinnerCountMotos.getValue() );
                            ((MotorBoat) boat).SetMotors( (IAdditional) t.getTransferData( DataFlavor.stringFlavor ) );
                            boatPanel.repaint();
                        }
                        return true;
                    } else {
                        data = (String) t.getTransferData( DataFlavor.stringFlavor );
                    }
                } catch (Exception e) {
                    return false;
                }
                switch (data) {
                    case "Лодка":
                        boat = new Boat( (int) spinnerSpeed.getValue(), (int) spinnerWeight.getValue(), Color.GREEN );
                        boat.SetPosition( 30, 30, 150, 60 );
                        break;
                    case "Катер":
                        boat = new MotorBoat( (int) spinnerSpeed.getValue(), (int) spinnerWeight.getValue(), Color.GREEN, Color.red, checkBoxLines.isSelected(),
                                checkBoxCabin.isSelected(), checkBoxMotors.isSelected(), 1, random.nextInt( 3 ) + 1 );
                        boat.SetPosition( 30, 30, 320, 60 );
                        break;
                    default:
                        return false;
                }
                boatPanel.setBoat( boat );
                boatPanel.repaint();
                return true;
            }

            public boolean canImport(TransferHandler.TransferSupport info) {
                try {
                    return info.isDataFlavorSupported( DataFlavor.stringFlavor ) || info.getTransferable().getTransferData( DataFlavor.stringFlavor ) instanceof IAdditional;
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        } );
        frame.getContentPane().add( boatPanel );

        JPanel groupPanelColors = new JPanel( new GridLayout( 5, 2, 0, 2 ) );
        groupPanelColors.setBorder( BorderFactory.createTitledBorder( "Цвета" ) );
        groupPanelColors.setBounds( boatPanel.getX() + boatPanel.getWidth() + 10, 10, 170, 150 );
        frame.getContentPane().add( groupPanelColors );

        JLabel mainColorLabel = new JLabel( "Основной" );
        mainColorLabel.setBorder( BorderFactory.createBevelBorder( 0 ) );
        mainColorLabel.setBounds( boatPanel.getX() + boatPanel.getWidth() + 10, 10, 170, 150 );
        mainColorLabel.setTransferHandler( new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                Color data;
                try {
                    data = (Color) t.getTransferData( DataFlavor.stringFlavor );
                } catch (Exception e) {
                    return false;
                }
                if (boat != null) {
                    boat.MainColor = data;
                    boatPanel.repaint();
                }
                return true;
            }

            public boolean canImport(TransferSupport info) {
                try {
                    return info.getTransferable().getTransferData( DataFlavor.stringFlavor ).getClass() == Color.class;
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
            }
        } );
        groupPanelColors.add( mainColorLabel );

        JLabel dopColorLabel = new JLabel( "Дополнительный" );
        dopColorLabel.setBorder( BorderFactory.createBevelBorder( 0 ) );
        dopColorLabel.setBounds( boatPanel.getX() + boatPanel.getWidth() + 10, 10, 170, 150 );
        dopColorLabel.setTransferHandler( new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                Color data;
                try {
                    data = (Color) t.getTransferData( DataFlavor.stringFlavor );
                } catch (Exception e) {
                    return false;
                }
                if (boat != null) {
                    if (boat.getClass() == MotorBoat.class) {
                        ((MotorBoat) boat).SetDopColor( data );
                        boatPanel.repaint();
                    }
                }
                return true;
            }

            public boolean canImport(TransferSupport info) {
                try {
                    return info.getTransferable().getTransferData( DataFlavor.stringFlavor ).getClass() == Color.class;
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
            }
        } );
        groupPanelColors.add( dopColorLabel );

        setColors( Color.BLUE, groupPanelColors );
        setColors( Color.GREEN, groupPanelColors );
        setColors( Color.RED, groupPanelColors );
        setColors( Color.YELLOW, groupPanelColors );
        setColors( Color.PINK, groupPanelColors );
        setColors( Color.ORANGE, groupPanelColors );
        setColors( Color.CYAN, groupPanelColors );
        setColors( Color.MAGENTA, groupPanelColors );

        JButton addButton = new JButton( "Добавить" );
        addButton.addActionListener( e -> {
            if (boat == null) {
                JOptionPane.showMessageDialog( frame, "Сначала создайте лодку!", "Добавление лодки", JOptionPane.ERROR_MESSAGE );
                return;
            }
            if (eventAddBoat != null) {
                eventAddBoat.BoatDelegate( boat );
            }
            frame.dispatchEvent( new WindowEvent( frame, WindowEvent.WINDOW_CLOSING ) );
        } );
        addButton.setBounds( groupPanelOptions.getX(), groupPanelOptions.getY() + groupPanelOptions.getHeight() + 10, 100, 50 );
        frame.add( addButton );

        JButton cancelButton = new JButton( "Отменить" );
        cancelButton.addActionListener( e -> frame.dispatchEvent( new WindowEvent( frame, WindowEvent.WINDOW_CLOSING ) ) );
        cancelButton.setBounds( groupPanelOptions.getX(), addButton.getY() + addButton.getHeight() + 10, 100, 50 );
        frame.add( cancelButton );
    }

    private void setMotorLabels(JPanel parent, String name) {
        IAdditional typeMotors;
        switch (name) {
            case "Треугольный":
                typeMotors = new TraungleMotors();
                break;
            case "Прямоугольный":
                typeMotors = new RectangleMotors();
                break;
            default:
                typeMotors = new CicleMotors();
        }

        JLabel DNDLabel = new JLabel( name );
        DNDLabel.setBorder( BorderFactory.createBevelBorder( 0 ) );
        DNDLabel.setBounds( 10, 70, 280, 50 );
        DNDLabel.setTransferHandler( new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[0];
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        return typeMotors;
                    }
                };
            }
        } );
        DNDLabel.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseAction( e );
            }
        } );
        parent.add( DNDLabel );
    }

    void setColors(Color color, JPanel groupPanelColors) {
        JPanel panel = new JPanel();
        panel.setBackground( color );
        panel.setTransferHandler( new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[0];
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        return panel.getBackground();
                    }
                };
            }
        } );
        panel.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseAction( e );
            }
        } );
        panel.setBounds( 10, 10, 100, 100 );
        groupPanelColors.add( panel );
    }

    void setBoatLabels(JPanel parent, String name) {
        JLabel DNDLabel = new JLabel( name );
        DNDLabel.setBorder( BorderFactory.createBevelBorder( 0 ) );
        DNDLabel.setBounds( 10, 70, 280, 50 );
        DNDLabel.setTransferHandler( new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new StringSelection( ((JLabel) c).getText() );
            }
        } );
        DNDLabel.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseAction( e );
            }
        } );
        parent.add( DNDLabel );
    }

    public void addEvent(IBoatDelegate ev) {
        if (ev != null)
            eventAddBoat = ev;
    }

    public void mouseAction(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton( e )) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag( c, e, TransferHandler.COPY );
        }
    }
}
