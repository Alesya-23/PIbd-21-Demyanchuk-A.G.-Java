package Form;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Boat.Boat;
import Exeptions.HarbourNotFoundExeption;
import Exeptions.HarbourOverflowException;
import Logics.IAdditional;
import Boat.Harbour;
import Boat.HarbourCollection;
import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.Provider;
import java.util.LinkedList;

import static org.apache.log4j.LogManager.getLogger;

public class FormHarbour {
    private static final Logger logger = getLogger( Form.FormHarbour.class );
    private JFrame frame;
    private JTextField textFieldGetPlace;
    private HarbourPanel harbourPanel;
    private Harbour<Boat, IAdditional> harbour;
    private JButton btnTakeBoat;
    private JButton btnTakeLastBoat;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private HarbourCollection harbourCollection;
    private JList<String> listOfHarbour;
    private final DefaultListModel<String> listHarborModel = new DefaultListModel<>();
    private LinkedList<Boat> deleteBoats = new LinkedList<Boat>();

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

        JLabel stationsLabel = new JLabel( "Гавани:" );
        stationsLabel.setBounds( width, 20, 100, 20 );
        frame.add( stationsLabel );

        JTextField addNewStationTextField = new JTextField( 2 );
        addNewStationTextField.setFont( addNewStationTextField.getFont().deriveFont( 20f ) );
        addNewStationTextField.setBounds( width, 40, 200, 20 );
        frame.add( addNewStationTextField );

        JButton addStationButton = new JButton( "Добавить гавань" );
        addStationButton.addActionListener( e -> {
            if (addNewStationTextField.getText().equals( "" )) {
                JOptionPane.showMessageDialog( frame, "Введите название гавани", "Ошибка", JOptionPane.INFORMATION_MESSAGE );
                return;
            }
            logger.info( "Добавили гавань " + addNewStationTextField.getText() );
            harbourCollection.AddParking( addNewStationTextField.getText() );
            ReloadLevels();
        } );
        addStationButton.setBounds( width, 60, 200, 20 );
        frame.add( addStationButton );

        harbourCollection = new HarbourCollection( width, height );

        for (String key : harbourCollection.keys()) {
            listHarborModel.addElement( key );
        }
        listOfHarbour = new JList<>( listHarborModel );
        listOfHarbour.setLayoutOrientation( JList.VERTICAL );
        listOfHarbour.setBounds( width + 20, 90, 130, 80 );
        listOfHarbour.addListSelectionListener( e -> {
            if (listOfHarbour.getSelectedIndex() > -1) {
                harbourPanel.setHarbour( harbourCollection.get( listHarborModel.get( listOfHarbour.getSelectedIndex() ) ) );
                harbourPanel.repaint();
            }
        } );
        frame.add( listOfHarbour );

        JButton deleteStationButton = new JButton( "Удалить гавань" );
        deleteStationButton.addActionListener( e -> {
            if (listOfHarbour.getSelectedIndex() > -1) {
                if (JOptionPane.showConfirmDialog( frame, "Удалить гавань " + listHarborModel.get( listOfHarbour.getSelectedIndex() ) + "?", "Удаление", JOptionPane.OK_CANCEL_OPTION )
                        == JOptionPane.OK_OPTION) {
                    logger.info( "Удалили парковку " + addNewStationTextField.getText() );
                    harbourCollection.DelParking( listHarborModel.get( listOfHarbour.getSelectedIndex() ) );
                    ReloadLevels();
                    harbourPanel.repaint();
                }
            }
        } );
        deleteStationButton.setBounds( width, 180, 200, 20 );
        frame.add( deleteStationButton );

        JButton addBusButton = new JButton( "Добавить лодку" );
        addBusButton.addActionListener( e -> EventQueue.invokeLater( () -> {
            try {
                FormBoatConfing window = new FormBoatConfing( frame );
                window.addEvent( this::AddBoat );
                window.frame.setVisible( true );
                logger.info( "Добавили лодку " );
            } catch (HarbourOverflowException ex) {
                JOptionPane.showMessageDialog( frame, ex.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE );
                logger.warn( ex.getMessage() );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog( frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE );
                logger.fatal( ex.getMessage() );
            }
        } ) );
        addBusButton.setBounds( 533, 220, 200, 20 );
        frame.getContentPane().add( addBusButton );

        textFieldGetPlace = new JTextField();
        textFieldGetPlace.setBounds( 657, 299, 84, 26 );
        frame.getContentPane().add( textFieldGetPlace );
        textFieldGetPlace.setColumns( 10 );

        btnTakeBoat = new JButton( "Забрать" );
        btnTakeBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (listOfHarbour.getSelectedIndex() > -1) {
                    if (!textFieldGetPlace.getText().equals( "" )) {
                        try {
                            Boat boat = harbourCollection.get( listOfHarbour.getSelectedValue() )
                                    .remove( Integer.parseInt( textFieldGetPlace.getText() ) );
                            logger.info( "Забрана лодка " + boat.toString() );
                            deleteBoats.add( boat );
                            harbourPanel.repaint();
                        } catch (HarbourNotFoundExeption ex) {
                            logger.warn( "Лодка c таким индексом не найдена" );
                            JOptionPane.showMessageDialog( frame, "Лодка не найдена" );
                        } catch (Exception ex) {
                            logger.fatal( "Неизвестная ошибка" );
                            JOptionPane.showMessageDialog( frame, "Лодки с таким индексом нет!", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE );
                        }
                    } else {
                        logger.warn( "Индекс не введен" );
                        JOptionPane.showMessageDialog( frame, "Индекс не введен" );
                    }
                }
            }
        } );
        btnTakeBoat.setBounds( 605, 341, 115, 29 );
        frame.getContentPane().add( btnTakeBoat );

        btnTakeLastBoat = new JButton( "Получить последнюю лодку" );
        btnTakeLastBoat.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (deleteBoats.size() <= 0) {
                    JOptionPane.showMessageDialog( frame, "Нет отчаливших лодок", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    var boat = deleteBoats.getLast();
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
                    deleteBoats.remove( boat );
                }
                harbourPanel.repaint();
            }
        } );
        btnTakeLastBoat.setBounds( 605, 381, 115, 29 );
        frame.getContentPane().add( btnTakeLastBoat );

        JLabel lblPlaceBoat = new JLabel( "Место" );
        lblPlaceBoat.setBounds( 574, 302, 69, 20 );
        frame.getContentPane().add( lblPlaceBoat );

        JLabel lblPickUpBoat = new JLabel( "Забрать лодку" );
        lblPickUpBoat.setBounds( 592, 263, 115, 20 );
        frame.getContentPane().add( lblPickUpBoat );

        JMenu file = new JMenu( "Файл" );
        openFile = new JMenuItem( "Открыть", new ImageIcon( "images/open.png" ) );
        file.add( openFile );
        file.addSeparator();

        saveFile = new JMenuItem( "Сохранить" );
        saveFile.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter( "Text File", "txt" );
                fileChooser.setFileFilter( filter );
                int result = fileChooser.showDialog( frame, "Сохраннить парковки" );
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    if (filename.contains( ".txt" )) {
                        try {
                            harbourCollection.SaveData( filename );
                            logger.info( "Сохранено в файл" + filename );
                        } catch (Exception e) {
                            logger.error( e.getMessage() );
                            JOptionPane.showMessageDialog( frame, "Не удалось сохранить файл" );
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            harbourCollection.SaveData( filename + ".txt" );
                            logger.info( "Сохранено в файл" + filename );
                        } catch (Exception e) {
                            logger.fatal( e.getMessage() );
                            JOptionPane.showMessageDialog( frame, "Не удалось сохранить файл" );
                            e.printStackTrace();
                        }
                    }
                } else {
                    logger.warn( "Не удалось сохранить файл" );
                    JOptionPane.showMessageDialog( frame, "Не удалось сохранить файл" );
                }
            }
        } );
        file.add( saveFile );

        openFile.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter( "Text File", "txt" );
                fileChooser.setFileFilter( filter );
                int result = fileChooser.showOpenDialog( frame );
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    try {
                        harbourCollection.LoadData( filename );
                        logger.info( "Загружено из файла " + filename );
                        ReloadLevels();
                        frame.repaint();
                    } catch (HarbourOverflowException ex) {
                        logger.warn( ex.getMessage() );
                        JOptionPane.showMessageDialog( frame, "занятое место" );
                    } catch (Exception ex) {
                        logger.fatal( ex.getMessage() );
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog( frame, "Не удалось загрузить файл" );
                    }
                } else {
                    logger.warn( "Не удалось загрузить файл c парковками" );
                    JOptionPane.showMessageDialog( frame, "Не удалось загрузить файл" );
                }
            }
        } );
        file.add( openFile );

        JMenuItem menuItemSaveSeparateParking = new JMenuItem( "Сохранить текущую парковку" );
        menuItemSaveSeparateParking.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter( "Text File", "txt" );
                fileChooser.setFileFilter( filter );
                int result = fileChooser.showDialog( frame, "Сохраннить парковку" );
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    if (filename.contains( ".txt" )) {
                        try {
                            harbourCollection.saveSeparateParking( filename, listOfHarbour.getSelectedValue() );
                            logger.info( "Парковка " + listOfHarbour.getSelectedValue() + " сохранена в файл файла " + filename );
                        } catch (Exception e) {
                            logger.error( e.getMessage() );
                            JOptionPane.showMessageDialog( frame, "Не удалось сохранить файл" );
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            harbourCollection.saveSeparateParking( filename + ".txt", listOfHarbour.getSelectedValue() );
                            logger.info( "Парковка " + listOfHarbour.getSelectedValue() + " сохранена в файл файла " + filename );
                        } catch (Exception e) {
                            logger.fatal( e.getMessage() );
                            JOptionPane.showMessageDialog( frame, "Не удалось сохранить файл" );
                            e.printStackTrace();
                        }
                    }
                } else {
                    logger.warn( "Не удалось сохранить файл" );
                    JOptionPane.showMessageDialog( frame, "Не удалось сохранить файл" );
                }
            }
        } );
        file.add( menuItemSaveSeparateParking );

        JMenuItem menuItemLoadSeparateParking = new JMenuItem( "Загрузить отдельную парковку" );
        menuItemLoadSeparateParking.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter( "Text File", "txt" );
                fileChooser.setFileFilter( filter );
                int result = fileChooser.showOpenDialog( frame );
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    try {
                        harbourCollection.loadSeparateParking( filename );
                        logger.info( "Парковка загружена из файла " + filename );
                    } catch (Exception e) {
                        logger.error( e.getMessage() );
                        JOptionPane.showMessageDialog( frame, "Не удалось загрузить парковку" );
                        e.printStackTrace();
                    }
                    ReloadLevels();
                    frame.repaint();
                } else {
                    logger.warn( "Загрузка невозможна" );
                    JOptionPane.showMessageDialog( frame, "Не удалось загрузить парковку" );
                }
            }
        } );
        file.add( menuItemLoadSeparateParking );
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds( 0, 0, 300, 200 );
        menuBar.add( file );
        frame.setJMenuBar( menuBar );
    }

    private void ReloadLevels() {
        int index = listOfHarbour.getSelectedIndex();
        listOfHarbour.setSelectedIndex( -1 );
        listHarborModel.clear();
        for (int i = 0; i < harbourCollection.keys().length; i++) {
            listHarborModel.addElement( harbourCollection.keys()[i] );
        }
        if (listHarborModel.size() > 0 && (index == -1 || index >= listHarborModel.size())) {
            listOfHarbour.setSelectedIndex( 0 );
        } else if (listHarborModel.size() > 0 && index > -1 && index < listHarborModel.size()) {
            listOfHarbour.setSelectedIndex( index );
        }
    }

    private void AddBoat(Boat boat) {
        if (boat != null && listOfHarbour.getSelectedIndex() > -1) {
            try {
                if (harbourCollection.get( listHarborModel.get( listOfHarbour.getSelectedIndex() ) ).add( boat )) {
                    harbourPanel.repaint();
                }
            } catch (HarbourOverflowException ex) {
                JOptionPane.showMessageDialog( frame, "Нет мест", "Переполнение", JOptionPane.INFORMATION_MESSAGE );
                logger.warn( ex.getMessage() );
            }
        }
    }
}
