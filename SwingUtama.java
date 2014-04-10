package toturialJDBC;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.DefaultRowSorter;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.util.regex.PatternSyntaxException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;

public class SwingUtama extends JFrame {

	private static final long serialVersionUID = 458648069351365650L;

	private JButton btnTambahkanTask;
	private JButton btnDisplay;
	private JButton btnSave;
	private JButton btnBack;
	private JTextField textNama;
	private JTextField textDeadline;
	private JTextField textDeskripsi;
	private JPanel contentPane;
	private JPanel panelHome;
	private JPanel panelAdd;
	private JPanel panelDisplay;
	private JPanel panelUpdate;

	private static Database db = new Database();
	private static TableTask tt = new TableTask();
	private JTable table;
	private JButton btnBackToMenu;
	private JTextField textNamaUpdate;
	private JTextField textDeadlineUpdate;
	private JTextField textDeskripsiUpdate;
	private JLabel lblInsertData;
	private JTextField textSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingUtama frame = new SwingUtama();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public SwingUtama() {
		setBackground(Color.BLACK);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.POPUP);
		setTitle("Versi 0.1");
		addComponentToPane();
	}

	public void addComponentToPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 349);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		panelUpdate = new JPanel();
		panelUpdate.setBounds(0, 0, 524, 300);
		// panel.add(panelUpdate);

		textNamaUpdate = new JTextField();
		textNamaUpdate.setBounds(148, 70, 196, 20);
		textNamaUpdate.setColumns(10);

		JLabel lblNamaTaskUpdate = new JLabel("Nama Task");
		lblNamaTaskUpdate.setBounds(38, 73, 71, 14);

		JLabel lblDeadlineUpdate = new JLabel("Deadline");
		lblDeadlineUpdate.setBounds(38, 111, 71, 14);

		textDeadlineUpdate = new JTextField();
		textDeadlineUpdate.setBounds(148, 108, 110, 20);
		textDeadlineUpdate.setColumns(10);

		textDeskripsiUpdate = new JTextField();
		textDeskripsiUpdate.setBounds(148, 148, 174, 41);
		textDeskripsiUpdate.setColumns(10);

		JLabel lblDeskripsi_1 = new JLabel("Deskripsi");
		lblDeskripsi_1.setBounds(38, 149, 71, 14);

		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.setBackground(SystemColor.controlHighlight);
		btnUpdate_2.setBounds(148, 221, 82, 23);
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnCancel = new JButton("cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setBounds(272, 221, 82, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panelDisplay);// Adding to content pane,
													// not
													// to Frame
				repaint();
				printAll(getGraphics());

			}
		});

		JLabel lblUpdateTask = new JLabel("Update Task");
		lblUpdateTask.setBounds(160, 22, 82, 14);
		panelUpdate.setLayout(null);
		panelUpdate.add(btnUpdate_2);
		panelUpdate.add(btnCancel);
		panelUpdate.add(lblNamaTaskUpdate);
		panelUpdate.add(lblDeadlineUpdate);
		panelUpdate.add(lblDeskripsi_1);
		panelUpdate.add(textDeskripsiUpdate);
		panelUpdate.add(textNamaUpdate);
		panelUpdate.add(textDeadlineUpdate);
		panelUpdate.add(lblUpdateTask);

		panelAdd = new JPanel();
		panelAdd.setBounds(0, 0, 434, 250);
		// panel.add(panelAdd);

		textNama = new JTextField();
		textNama.setBounds(170, 60, 228, 20);
		textNama.setColumns(10);

		textDeadline = new JTextField();
		textDeadline.setBounds(170, 98, 130, 20);
		textDeadline.setColumns(10);

		textDeskripsi = new JTextField();
		textDeskripsi.setBounds(170, 136, 228, 54);
		textDeskripsi.setColumns(10);

		JLabel lblNamaTask = new JLabel("Nama Task");
		lblNamaTask.setBounds(10, 63, 92, 14);

		JLabel lblDeadline = new JLabel("Deadline");
		lblDeadline.setBounds(10, 101, 92, 14);

		JLabel lblDeskripsi = new JLabel("Deskripsi");
		lblDeskripsi.setBounds(10, 139, 92, 14);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textNama.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "masukkan nama task");
				} else {
					db.insertData(textNama.getText(), textDeadline.getText(),
							textDeskripsi.getText());
					textNama.setText("");
					textDeadline.setText("");
					textDeskripsi.setText("");
					JOptionPane.showMessageDialog(null,
							"tugas berhasil ditambahkan");
					tt.reset();
				}
			}
		});
		btnSave.setBounds(168, 216, 107, 23);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panelHome);// Adding to content pane, not
												// to Frame
				repaint();
				printAll(getGraphics());
			}
		});
		btnBack.setBounds(295, 216, 103, 23);

		panelAdd.setLayout(null);
		panelAdd.add(textNama);
		panelAdd.add(textDeadline);
		panelAdd.add(textDeskripsi);
		panelAdd.add(lblNamaTask);
		panelAdd.add(lblDeadline);
		panelAdd.add(lblDeskripsi);
		panelAdd.add(btnSave);
		panelAdd.add(btnBack);

		lblInsertData = new JLabel("Insert Data");
		lblInsertData.setBounds(150, 11, 92, 14);
		panelAdd.add(lblInsertData);

		panelDisplay = new JPanel();
		panelDisplay.setBounds(0, 0, 524, 300);
		panelDisplay.setBorder(new EmptyBorder(5, 5, 5, 5));
		// panel.add(panelDisplay);

		table = new JTable(tt);
		final RowSorter<TableTask> sorter = new TableRowSorter<TableTask>(tt);
		table.setRowSorter(sorter);

		table.setBounds(0, 0, 420, 250);
		panelDisplay.add(table, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(table);

		btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setBackground(SystemColor.controlHighlight);
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panelHome);// Adding to content pane, not
												// to Frame
				repaint();
				printAll(getGraphics());
			}
		});

		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.setBackground(SystemColor.controlHighlight);
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selRow = table.getSelectedRow();
				Object idToDelete = tt.getIdToDelete(table
						.convertRowIndexToModel(selRow));

				if (selRow != -1) {
					tt.removeRow(selRow);
					db.deleteData(idToDelete.toString());
				}
			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(SystemColor.controlHighlight);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(panelUpdate);// Adding to content pane, not
				// to Frame
				repaint();
				printAll(getGraphics());
			}
		});

		textSearch = new JTextField();
		textSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String text = textSearch.getText();
				if (text.length() == 0) {
					((DefaultRowSorter<TableTask, Integer>) sorter)
							.setRowFilter(null);

				}

				else {

					JOptionPane.showMessageDialog(textSearch, "not found");
					try {
						((DefaultRowSorter<TableTask, Integer>) sorter)
								.setRowFilter(RowFilter.regexFilter(text));
					} catch (PatternSyntaxException e) {

						System.err.println("Bad regex pattern");
					}

				}
			}
		});
		GroupLayout gl_panelDisplay = new GroupLayout(panelDisplay);
		gl_panelDisplay
				.setHorizontalGroup(gl_panelDisplay
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panelDisplay
										.createSequentialGroup()
										.addGroup(
												gl_panelDisplay
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panelDisplay
																		.createSequentialGroup()
																		.addContainerGap(
																				283,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnUpdate)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnDelete_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnBackToMenu))
														.addGroup(
																gl_panelDisplay
																		.createSequentialGroup()
																		.addComponent(
																				textSearch,
																				GroupLayout.PREFERRED_SIZE,
																				127,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnSearch)))
										.addContainerGap())
						.addComponent(scrollPane, Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE));
		gl_panelDisplay
				.setVerticalGroup(gl_panelDisplay
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelDisplay
										.createSequentialGroup()
										.addGroup(
												gl_panelDisplay
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																textSearch,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnSearch))
										.addGap(7)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												196, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panelDisplay
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnBackToMenu)
														.addComponent(
																btnDelete_1)
														.addComponent(btnUpdate))
										.addGap(484)));
		panelDisplay.setLayout(gl_panelDisplay);

		panelHome = new JPanel();
		panelHome.setBackground(UIManager.getColor("Button.background"));
		panelHome.setBounds(-12, 0, 547, 315);
		panel.add(panelHome);

		JLabel lblTitle = new JLabel("Student Activity Manager");
		lblTitle.setBackground(UIManager.getColor("Button.focus"));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));

		btnTambahkanTask = new JButton("Insert");
		btnTambahkanTask.setBackground(SystemColor.activeCaption);
		btnTambahkanTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panelAdd);// Adding to content pane, not to
												// Frame
				repaint();
				printAll(getGraphics());
			}
		});

		btnDisplay = new JButton("Display");
		btnDisplay.setBackground(SystemColor.activeCaption);
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(panelDisplay);// Adding to content pane,
													// not to Frame
				repaint();
				printAll(getGraphics());
			}
		});
		GroupLayout gl_panelHome = new GroupLayout(panelHome);
		gl_panelHome
				.setHorizontalGroup(gl_panelHome
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panelHome
										.createSequentialGroup()
										.addGap(48)
										.addComponent(lblTitle,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addGap(40))
						.addGroup(
								gl_panelHome
										.createSequentialGroup()
										.addContainerGap(452, Short.MAX_VALUE)
										.addGroup(
												gl_panelHome
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																btnDisplay)
														.addComponent(
																btnTambahkanTask,
																GroupLayout.PREFERRED_SIZE,
																67,
																GroupLayout.PREFERRED_SIZE))
										.addGap(28)));
		gl_panelHome.setVerticalGroup(gl_panelHome.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panelHome
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 54,
								GroupLayout.PREFERRED_SIZE).addGap(55)
						.addComponent(btnTambahkanTask)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnDisplay).addGap(138)));
		panelHome.setLayout(gl_panelHome);
	}
}
