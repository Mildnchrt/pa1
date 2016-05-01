package converter;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * GUI component
 * @author mildnchrt
 *
 */
public class ConverterUI extends JFrame{
	private JTextField input1, input2;
	private JComboBox unit1, unit2;
	private JLabel equals;
	private JPanel contents;
	private JRadioButton right, left;
	private JButton convertButton, clear;
	private UnitConverter unitconverter;
	
	public ConverterUI ( UnitConverter uc ) {
		this.unitconverter = uc;
		this.setTitle("Length Converter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	public static void main (String[] args){
		UnitConverter uc = new UnitConverter();
		ConverterUI view = new ConverterUI(uc);
		view.setVisible(true);
	}
	
	/**
	 * initial components in the window
	 */
	private void initComponents() {
		GridLayout layout1 = new GridLayout(2, 1);
		Container c = new Container();
		Container c2 = new Container();
		FlowLayout layout = new FlowLayout();
		c.setLayout(layout);
		c2.setLayout(layout);
		this.setLayout(layout1);
		
		setBounds(100,100,690,90);
		
		right = new JRadioButton("Right -> Left");
		left = new JRadioButton("Left -> Right");
		left.setSelected(true);
		c2.add(left);
		c2.add(right);
		
		input1 = new JTextField();
		input1.setPreferredSize(new Dimension (100, 30));
		c.add(input1);
		
		unit1 = new JComboBox<Unit>();
		Unit[] lengths = unitconverter.getUnits();
		for ( Unit u : lengths ) unit1.addItem( u );
		c.add(unit1);
		
		equals = new JLabel( "=" );
		c.add(equals);
		
		input2 = new JTextField();
		input2.setPreferredSize(new Dimension (100, 30));
		c.add(input2);
		
		unit2 = new JComboBox<Unit>();
		for ( Unit u : lengths ) unit2.addItem( u );
		c.add(unit2);
		
		convertButton = new JButton(" Convert! ");
		clear = new JButton(" Clear ");
		c.add(convertButton);
		c.add(clear);
		
		this.add(c);
		this.add(c2);

		convertButton.addActionListener(new ConvertButtonListener());
		clear.addActionListener(new ClearButtonListener());
		
		left.addActionListener(new LeftButtonListener());
		right.addActionListener(new RightButtonListener());
		
	    input1.addActionListener(new ConvertButtonListener());
	    input2.addActionListener(new ConvertButtonListener());
	}
	/**
	 * ActionListener of JRadioButton Left
	 * auto detect and call method convert() to convert value from fromUnit to toUnit
	 * @author mildnchrt
	 *
	 */
	class LeftButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			convert();
			right.setSelected(false);
			input1.setEnabled(true);
			input2.setEnabled(false);
		}
	}
	
	/**
	 * ActionListener of JradioButton right
	 * auto detect and call method convert() to convert value from toUnit to fromUnit
	 * @author mildnchrt
	 *
	 */
	class RightButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			convert();
			left.setSelected(false);
			input2.setEnabled(true);
			input1.setEnabled(false);
		}
	}
	/**
	 * ActionListener of JButton clear
	 * clear input value in JTextField and set JComboBox to initial unit
	 * @author mildnchrt
	 *
	 */
	class ClearButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			unit1.setSelectedIndex(0);
			unit2.setSelectedIndex(0);
			input1.setText(null);
			input2.setText(null);
		}
	}
	
	/**
	 * ActionListener of JButtun convertButton
	 * call method convert() to covert input value
	 * @author mildnchrt
	 *
	 */
	class ConvertButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			convert();
		}
	}
	
	/**
	 * covert input value from initial unit to selected unit
	 */
	public void convert() {
		String s;
		if (right.isSelected()) {
			s = input2.getText().trim();
		}
		else {
			s = input1.getText().trim();
		}
		if ( s.length() > 0) {
			try {
				double value = Double.valueOf( s );
				Unit unitSelect1 = (Unit) unit1.getSelectedItem();
				Unit unitSelect2 = (Unit) unit2.getSelectedItem();
				if (right.isSelected()) {
					input1.setText(Double.toString(unitconverter.converter(value, unitSelect2, unitSelect1)));
				}
				else if (!right.isSelected()){
					input2.setText(Double.toString(unitconverter.converter(value, unitSelect1, unitSelect2)));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
