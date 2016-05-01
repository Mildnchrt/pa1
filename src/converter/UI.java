package converter;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * GUI of unitConverter
 * @author Nutcharueta Sihirunwong 5810545866
 *
 */
public class UI extends JFrame{

	private JFrame frame;
	private JMenu menu, exit;
	private JTextField input1, input2;
	private JComboBox< Unit > unit1, unit2;
	private JButton convertButton, clearButton;
	private UnitConverter unitconverter;
	private JMenuItem length, area, weight, time;
	private boolean leftToRight, isError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitConverter uc = new UnitConverter();
					UI window = new UI( uc );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param uc is unitConverter object
	 */
	public UI( UnitConverter uc ) {
		this.unitconverter = uc;
		this.setTitle("Length Converter");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds( 0, 0, 700, 22 );
		frame.getContentPane().add( menuBar );
		
		menu = new JMenu( "Unit Type" );
		menuBar.add(menu);
		
		input1 = new JTextField();
		input1.setBounds( 0, 34, 113, 28 );
		frame.getContentPane().add( input1 );
		input1.setColumns(10);
		
		
		unit1 = new JComboBox< Unit >();
		unit1.setBounds( 113, 36, 113, 27 );
		Unit[] unit = unitconverter.getUnits( UnitType.Length );
		for ( Unit u : unit ) unit1.addItem( u );
		frame.getContentPane().add( unit1 );
		unit1.addActionListener( new selectUnitActionListener() );
		
		JLabel equal = new JLabel( "=" );
		equal.setBounds( 238, 40, 61, 16 );
		frame.getContentPane().add( equal );
		
		input2 = new JTextField();
		input2.setBounds( 260, 34, 113, 28 );
		frame.getContentPane().add( input2 );
		input2.setColumns(10);
		
		unit2 = new JComboBox< Unit >();
		unit2.setBounds( 377, 36, 113, 27 );
		for ( Unit u : unit ) unit2.addItem( u );
		frame.getContentPane().add( unit2 );
		unit2.addActionListener( new selectUnitActionListener() );
		
		convertButton = new JButton( "CONVERT" );
		convertButton.setBounds( 491, 35, 107, 29 );
		frame.getContentPane().add( convertButton );
		
		clearButton = new JButton( "CLEAR") ;
		clearButton.setBounds( 593, 35, 107, 29 );
		frame.getContentPane().add( clearButton );
		
		convertButton.addActionListener( new ConvertButtonListener() );
		clearButton.addActionListener(new ClearButtonListener() );
		
		input1.addActionListener( new ConvertButtonListener() );
		input1.addMouseListener( new MouseInputListener() );
		input1.addKeyListener( new KeyInputListener() );
		
	    input2.addActionListener( new ConvertButtonListener() );
	    input2.addMouseListener( new MouseInputListener() );
		input2.addKeyListener( new KeyInputListener() );
	    
	    length = new JMenuItem("Length");
	    menu.add(length);
	    
	    area = new JMenuItem("Area");
	    menu.add(area);
	    
	    weight = new JMenuItem("Weight");
	    menu.add(weight);
	    
	    time = new JMenuItem("Time");
	    menu.add(time);
	   
	    exit = new JMenu( "Exit" );
	    menu.add(exit);
	    
	    exit.add( new ExitAction() );
	    length.addActionListener( new setUnitLength() );
	    area.addActionListener( new setUnitArea() );
	    weight.addActionListener( new setUnitWeight() );
	    time.addActionListener( new setUnitTime() );
	}
	
	/**
	 * ActionListener of JButton clear
	 * clear input value in JTextField and set JComboBox to initial unit
	 * @author Nutcharueta Sihirunwong 5810545866
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
	 * @author Nutcharueta Sihirunwong 5810545866
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
		if ( leftToRight ) { s = input1.getText().trim(); }
		else { s = input2.getText().trim(); }
		
		if ( s.length() > 0) {
			try {
				double value = Double.valueOf( s );
				Unit unitSelect1 = (Unit) unit1.getSelectedItem();
				Unit unitSelect2 = (Unit) unit2.getSelectedItem();
				if ( leftToRight ) {
					input2.setText(  Double.toString( unitconverter.converter( value, unitSelect1, unitSelect2 ) )  );
				}
				else {
					input1.setText( Double.toString( unitconverter.converter( value, unitSelect2, unitSelect1 ) ) );
				}
			}
			catch ( NumberFormatException e ) {
				if ( leftToRight ) {
					input1.setForeground( Color.RED );
					input1.selectAll();
				}
				else {
					input2.setForeground( Color.RED );
					input2.selectAll();
				}
				isError = true;
			}
		}
	}
	
	/**
	 * An AbstractAction class to exit the UI/program
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	public class ExitAction extends AbstractAction {
		public ExitAction() {
			super( "Are you sure to exit" );
		}
		public void actionPerformed( ActionEvent evt ) {
			System.exit( 0 );
		}
	}
	
	/**
	 * ActionListener of JMenuItem length
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class setUnitLength implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			unit1.removeAllItems();
			unit2.removeAllItems();
			Unit[] unit = unitconverter.getUnits( UnitType.Length );
			for ( Unit u : unit ) unit1.addItem( u );
			for ( Unit u : unit ) unit2.addItem( u );
		}
	}
	
	/**
	 * ActionListener of JMenuItem area
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class setUnitArea implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			unit1.removeAllItems();
			unit2.removeAllItems();
			Unit[] unit = unitconverter.getUnits( UnitType.Area );
			for ( Unit u : unit ) unit1.addItem( u );
			for ( Unit u : unit ) unit2.addItem( u );
		}
	}
	
	/**
	 * ActionListener of JMenuItem weight
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class setUnitWeight implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			unit1.removeAllItems();
			unit2.removeAllItems();
			Unit[] unit = unitconverter.getUnits( UnitType.Weight );
			for ( Unit u : unit ) unit1.addItem( u );
			for ( Unit u : unit ) unit2.addItem( u );
		}
	}
	
	/**
	 * ActionListener of JMenuItem time
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class setUnitTime implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			unit1.removeAllItems();
			unit2.removeAllItems();
			Unit[] unit = unitconverter.getUnits( UnitType.Time );
			for ( Unit u : unit ) unit1.addItem( u );
			for ( Unit u : unit ) unit2.addItem( u );
		}
	}
	
	/**
	 * MouseListener of JTextField for change text color
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class MouseInputListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if ( isError ) {
				( (JTextField) e.getSource()).setForeground( Color.BLACK );
				( (JTextField) e.getSource()).setText("");
				isError = false;
			}
			if (e.getSource().equals( input1 )) {
				input1.selectAll();
			}
			else {
				input2.selectAll();
			}
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}
	
	/**
	 * KeyListener of JTextField for change text color to black color
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class KeyInputListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			
			( (JTextField) e.getSource() ).setForeground( Color.BLACK );
			isError = false;
			
			if ( e.getSource().equals( input1 ) ) {
				leftToRight = true; 
			}
			else {
				leftToRight = false;
			}
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {
			convert();
		}
	}
	/**
	 * auto detect when select unit
	 * @author Nutcharueta Sihirunwong 5810545866
	 *
	 */
	class selectUnitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			convert();
		}
	}
	
	/**
	 * method clear all value when change unit
	 */
	public void clear() {
		unit1.setSelectedIndex(0);
		unit2.setSelectedIndex(0);
		input1.setText(null);
		input2.setText(null);
	}
}
