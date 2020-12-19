package Form;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;

import Boat.Boat;
import Logics.IAdditional;
import Boat.Harbour;
import Boat.HarbourCollection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class FormHarbour {
    private JFrame frame;
    private JTextField textFieldGetPlace;
    private HarbourPanel harbourPanel;
    private Harbour<Boat, IAdditional> harbour;
    private JButton btnTakeBoat;
    private JButton btnTakeLastBoat;
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
            harbourCollection.AddHarbour( addNewStationTextField.getText() );
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
                    harbourCollection.DelHarbour( listHarborModel.get( listOfHarbour.getSelectedIndex() ) );
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
            } catch (Exception ex) {
                ex.printStackTrace();
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
                    int numPlace = 0;
                    if (!textFieldGetPlace.getText().equals( "" )) {
                        try {
                            numPlace = Integer.parseInt( textFieldGetPlace.getText() );
                        } catch (Exception ex) {
                            return;
                        }
                        if ((harbourCollection.get( listHarborModel.get( listOfHarbour.getSelectedIndex() ) ).get( numPlace )) != null) {
                            var boat = harbourCollection.get( listHarborModel.get( listOfHarbour.getSelectedIndex() ) ).remove( numPlace );
                            if (boat != null) {
                                deleteBoats.add( boat );
                            }
                        } else
                            JOptionPane.showMessageDialog( frame, "Нет лодки", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
                        harbourPanel.repaint();
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
                            }
                    );
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
            if (harbourCollection.get( listHarborModel.get( listOfHarbour.getSelectedIndex() ) ).add( boat )) {
                harbourPanel.repaint();
            } else {
                JOptionPane.showMessageDialog( frame, "Не удалось поставить лодку", "Сообщение", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }
}
