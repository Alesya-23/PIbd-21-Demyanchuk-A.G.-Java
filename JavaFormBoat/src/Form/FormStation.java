package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormStation {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormStation window = new FormStation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormStation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 907, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTypeBoat = new JLabel("тип лодки");
		lblTypeBoat.setBounds(47, 31, 152, 29);
		frame.getContentPane().add(lblTypeBoat);
		
		JLabel lblBoat = new JLabel("лодка");
		lblBoat.setBackground(Color.LIGHT_GRAY);
		lblBoat.setBounds(52, 74, 87, 27);
		frame.getContentPane().add(lblBoat);
		
		JLabel lblMotorBoat = new JLabel("катер");
		lblMotorBoat.setBackground(Color.LIGHT_GRAY);
		lblMotorBoat.setForeground(new Color(0, 0, 0));
		lblMotorBoat.setBounds(56, 109, 81, 28);
		frame.getContentPane().add(lblMotorBoat);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(222, 27, 296, 174);
		frame.getContentPane().add(panel);
		
		JLabel lblColor = new JLabel("Цвета");
		lblColor.setBounds(629, 25, 69, 20);
		frame.getContentPane().add(lblColor);
		
		JLabel lblMainColor = new JLabel("Основной");
		lblMainColor.setBounds(597, 61, 101, 20);
		frame.getContentPane().add(lblMainColor);
		
		JLabel lblDopColor = new JLabel("Дополнительный");
		lblDopColor.setBounds(722, 61, 134, 20);
		frame.getContentPane().add(lblDopColor);
		
		JPanel panelRed = new JPanel();
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(577, 109, 39, 28);
		frame.getContentPane().add(panelRed);
		
		JPanel panelBlue = new JPanel();
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(577, 167, 39, 28);
		frame.getContentPane().add(panelBlue);
		
		JPanel panelGreen = new JPanel();
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(651, 109, 39, 28);
		frame.getContentPane().add(panelGreen);
		
		JPanel panelYellow = new JPanel();
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(651, 167, 39, 28);
		frame.getContentPane().add(panelYellow);
		
		JPanel panelMagenta = new JPanel();
		panelMagenta.setBackground(Color.MAGENTA);
		panelMagenta.setBounds(722, 109, 39, 28);
		frame.getContentPane().add(panelMagenta);
		
		JPanel panelOrange = new JPanel();
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(795, 109, 39, 28);
		frame.getContentPane().add(panelOrange);
		
		JPanel panelCyan = new JPanel();
		panelCyan.setBackground(Color.CYAN);
		panelCyan.setBounds(722, 167, 39, 28);
		frame.getContentPane().add(panelCyan);
		
		JPanel panelPink = new JPanel();
		panelPink.setBackground(Color.PINK);
		panelPink.setBounds(795, 167, 39, 28);
		frame.getContentPane().add(panelPink);
		
		JButton btnAdd = new JButton("Добавить");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(741, 247, 115, 29);
		frame.getContentPane().add(btnAdd);
		
		JButton btnCansel = new JButton("Отмена");
		btnCansel.setBounds(741, 300, 115, 29);
		frame.getContentPane().add(btnCansel);
		
		JCheckBox checkBoxCabin = new JCheckBox("Кабина");
		checkBoxCabin.setBackground(Color.LIGHT_GRAY);
		checkBoxCabin.setBounds(222, 226, 139, 29);
		frame.getContentPane().add(checkBoxCabin);
		
		JCheckBox checkBoxLines = new JCheckBox("Линии");
		checkBoxLines.setBackground(Color.LIGHT_GRAY);
		checkBoxLines.setBounds(222, 263, 139, 29);
		frame.getContentPane().add(checkBoxLines);
		
		JCheckBox checkBoxMotors = new JCheckBox("Моторы");
		checkBoxMotors.setBackground(Color.LIGHT_GRAY);
		checkBoxMotors.setBounds(222, 300, 139, 29);
		frame.getContentPane().add(checkBoxMotors);
		
		JSpinner spinnerSpeed = new JSpinner();
		spinnerSpeed.setModel(new SpinnerNumberModel(100, 100, 300, 1));
		spinnerSpeed.setBounds(108, 248, 69, 27);
		frame.getContentPane().add(spinnerSpeed);
		
		JSpinner spinnerWeight = new JSpinner();
		spinnerWeight.setModel(new SpinnerNumberModel(300, 300, 1000, 1));
		spinnerWeight.setBounds(108, 305, 69, 27);
		frame.getContentPane().add(spinnerWeight);
		
		JLabel lblParametrs = new JLabel("Параметры");
		lblParametrs.setBackground(Color.LIGHT_GRAY);
		lblParametrs.setBounds(33, 215, 69, 20);
		frame.getContentPane().add(lblParametrs);
		
		JLabel lblWeight = new JLabel("Вес лодки");
		lblWeight.setBackground(new Color(240, 240, 240));
		lblWeight.setBounds(15, 309, 87, 25);
		frame.getContentPane().add(lblWeight);
		
		JLabel lblSpeed = new JLabel("Скорость");
		lblSpeed.setBackground(Color.LIGHT_GRAY);
		lblSpeed.setBounds(15, 252, 69, 20);
		frame.getContentPane().add(lblSpeed);
		
		JLabel lblTypeMotors = new JLabel("Тип моторов");
		lblTypeMotors.setBounds(431, 217, 152, 18);
		frame.getContentPane().add(lblTypeMotors);
		
		JLabel lblbCircleMotor = new JLabel("Круглый");
		lblbCircleMotor.setBounds(409, 270, 69, 20);
		frame.getContentPane().add(lblbCircleMotor);
		
		JLabel lblRectMotor = new JLabel("квадратный");
		lblRectMotor.setBounds(494, 267, 96, 23);
		frame.getContentPane().add(lblRectMotor);
		
		JLabel lblMixedMotor = new JLabel("смешанный");
		lblMixedMotor.setBounds(601, 267, 90, 20);
		frame.getContentPane().add(lblMixedMotor);
		
		JLabel lblCountMotor = new JLabel("Количество моторов");
		lblCountMotor.setBounds(396, 309, 167, 29);
		frame.getContentPane().add(lblCountMotor);
		
		JSpinner spinnerCountMotors = new JSpinner();
		spinnerCountMotors.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spinnerCountMotors.setBounds(614, 312, 32, 26);
		frame.getContentPane().add(spinnerCountMotors);
	}
}
