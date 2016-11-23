package de.tudresden.inf.lat.born.gui.testmaker;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.tudresden.inf.lat.born.gui.common.BornIcon;
import de.tudresden.inf.lat.born.gui.common.FormatTool;
import de.tudresden.inf.lat.born.gui.common.Message;
import de.tudresden.inf.lat.born.owlapi.annotator.AnnotationCreator;
import de.tudresden.inf.lat.born.owlapi.annotator.AnnotatorConfiguration;
import de.tudresden.inf.lat.born.owlapi.processor.ProcessorConfigurationImpl;
import de.tudresden.inf.lat.born.problog.connector.BayesianNetworkCreatorConfiguration;

/**
 * This is the panel for the test maker. This class contains a model for the
 * annotator ({@link AnnotatorConfiguration}), but not for the Bayesian network
 * creator ({@link BayesianNetworkCreatorConfiguration}).
 * 
 * @author Julian Mendez
 */
public class TestMakerPanel extends JPanel implements TestMakerView {

	private static final long serialVersionUID = -7460256750941145085L;

	private FormatTool formatTool = new FormatTool();

	private JButton buttonSelectInputOntologyFile = new JButton();
	private JButton buttonSelectInputBayesianNetworkFile = new JButton();
	private JButton buttonSaveOntologyFile = new JButton();
	private JButton buttonSaveBayesianNetwork = new JButton();

	private JTextField textInputOntologyFile = new JTextField();
	private JTextField textThreshold = new JTextField();
	private JTextField textInputBayesianNetworkFile = new JTextField();
	private JTextField textListOfParents = new JTextField();

	private final JLabel lblInputOntology = new JLabel(Message.LBL_INPUT_ONTOLOGY);
	private final JLabel lblThreshold = new JLabel(Message.LBL_THRESHOLD);
	private final JLabel lblInputBayesianNetwork = new JLabel(Message.LBL_INPUT_BAYESIAN_NETWORK);
	private final JLabel lblListOfParents = new JLabel(Message.LBL_LIST_OF_PARENTS);

	private final AnnotatorConfiguration model;

	/**
	 * Constructs a new test maker view.
	 * 
	 * @param model
	 *            model
	 */
	public TestMakerPanel(AnnotatorConfiguration model) {
		this.model = Objects.requireNonNull(model);
		createPanel();
	}

	@Override
	public void addButtonSelectInputOntologyFileListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonSelectInputOntologyFile.addActionListener(listener);
		buttonSelectInputOntologyFile.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonSelectInputBayesianNetworkFileListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonSelectInputBayesianNetworkFile.addActionListener(listener);
		buttonSelectInputBayesianNetworkFile.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonSelectOutputOntologyFileListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonSaveOntologyFile.addActionListener(listener);
		buttonSaveOntologyFile.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonSaveBayesianNetworkListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonSaveBayesianNetwork.addActionListener(listener);
		buttonSaveBayesianNetwork.setActionCommand(actionCommand);
	}

	@Override
	public void addTextFieldInputOntologyFileListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		textInputOntologyFile.addActionListener(listener);
		textInputOntologyFile.setActionCommand(actionCommand);
	}

	@Override
	public void addTextFieldListOfParentsListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		textListOfParents.addActionListener(listener);
		textListOfParents.setActionCommand(actionCommand);
	}

	@Override
	public void addTextFieldThresholdListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		textThreshold.addActionListener(listener);
		textThreshold.setActionCommand(actionCommand);
	}

	@Override
	public void addTextFieldMaxNumberOfVarListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		textInputBayesianNetworkFile.addActionListener(listener);
		textInputBayesianNetworkFile.setActionCommand(actionCommand);
	}

	/**
	 * Creates the panel.
	 */
	void createPanel() {
		setLayout(null);

		setBackground(BornIcon.BACKGROUND_COLOR);

		buttonSelectInputOntologyFile.setIcon(BornIcon.OPEN_FILE);
		buttonSelectInputOntologyFile.setBounds(215, 101, 50, 26);
		buttonSelectInputOntologyFile.setToolTipText(Message.TOOLTIP_BUTTON_OPEN_INPUT_ONTOLOGY_FILE);
		buttonSelectInputOntologyFile.setOpaque(false);
		buttonSelectInputOntologyFile.setContentAreaFilled(false);
		buttonSelectInputOntologyFile.setBorderPainted(false);
		add(buttonSelectInputOntologyFile);

		buttonSelectInputBayesianNetworkFile.setIcon(BornIcon.OPEN_FILE);
		buttonSelectInputBayesianNetworkFile.setBounds(215, 188, 50, 26);
		buttonSelectInputBayesianNetworkFile.setToolTipText(Message.TOOLTIP_BUTTON_OPEN_INPUT_ONTOLOGY_FILE);
		buttonSelectInputBayesianNetworkFile.setOpaque(false);
		buttonSelectInputBayesianNetworkFile.setContentAreaFilled(false);
		buttonSelectInputBayesianNetworkFile.setBorderPainted(false);
		add(buttonSelectInputBayesianNetworkFile);

		buttonSaveOntologyFile.setIcon(BornIcon.SAVE_FILE);
		buttonSaveOntologyFile.setBounds(215, 348, 50, 26);
		buttonSaveOntologyFile.setToolTipText(Message.TOOLTIP_BUTTON_OPEN_INPUT_ONTOLOGY_FILE);
		buttonSaveOntologyFile.setOpaque(false);
		buttonSaveOntologyFile.setContentAreaFilled(false);
		buttonSaveOntologyFile.setBorderPainted(false);
		add(buttonSaveOntologyFile);

		textInputOntologyFile.setBackground(BornIcon.TEXT_BACKGROUND_COLOR);
		textInputOntologyFile.setBounds(277, 101, 688, 28);
		textInputOntologyFile.setToolTipText(Message.TOOLTIP_TEXT_FIELD_INPUT_ONTOLOGY_FILE);
		textInputOntologyFile.setAlignmentX(LEFT_ALIGNMENT);
		textInputOntologyFile.setEditable(false);
		add(textInputOntologyFile);

		textInputBayesianNetworkFile.setBackground(BornIcon.TEXT_BACKGROUND_COLOR);
		textInputBayesianNetworkFile.setBounds(277, 186, 688, 28);
		textInputBayesianNetworkFile.setToolTipText(Message.TOOLTIP_TEXT_FIELD_MAX_NUMBER_OF_VAR);
		textInputBayesianNetworkFile.setAlignmentX(LEFT_ALIGNMENT);
		textInputBayesianNetworkFile.setEditable(false);
		add(textInputBayesianNetworkFile);

		textThreshold.setBackground(BornIcon.TEXT_BACKGROUND_COLOR);
		textThreshold.setBounds(277, 281, 259, 28);
		textThreshold.setToolTipText(Message.TOOLTIP_TEXT_FIELD_THRESHOLD);
		textThreshold.setAlignmentX(LEFT_ALIGNMENT);
		add(textThreshold);

		lblInputOntology.setBounds(287, 139, 249, 15);
		lblInputOntology.setForeground(BornIcon.FOREGROUND_COLOR);
		add(lblInputOntology);

		lblThreshold.setBounds(287, 321, 259, 15);
		lblThreshold.setForeground(BornIcon.FOREGROUND_COLOR);
		add(lblThreshold);

		lblInputBayesianNetwork.setBounds(287, 226, 249, 15);
		lblInputBayesianNetwork.setForeground(BornIcon.FOREGROUND_COLOR);
		add(lblInputBayesianNetwork);

		lblListOfParents.setBounds(278, 516, 328, 15);
		lblListOfParents.setForeground(BornIcon.FOREGROUND_COLOR);
		add(lblListOfParents);

		buttonSaveBayesianNetwork.setIcon(BornIcon.SAVE_FILE);
		buttonSaveBayesianNetwork.setBounds(215, 550, 50, 26);
		buttonSaveBayesianNetwork.setToolTipText(Message.TOOLTIP_BUTTON_OPEN_INPUT_ONTOLOGY_FILE);
		buttonSaveBayesianNetwork.setOpaque(false);
		buttonSaveBayesianNetwork.setContentAreaFilled(false);
		buttonSaveBayesianNetwork.setBorderPainted(false);
		add(buttonSaveBayesianNetwork);

		textListOfParents.setBackground(BornIcon.TEXT_BACKGROUND_COLOR);
		textListOfParents.setBounds(277, 476, 688, 28);
		textListOfParents.setToolTipText(Message.TOOLTIP_TEXT_FIELD_THRESHOLD);
		textListOfParents.setAlignmentX(LEFT_ALIGNMENT);
		add(textListOfParents);

	}

	@Override
	public AnnotatorConfiguration getModel() {
		return model;
	}

	@Override
	public void setButtonLoadEnabled(boolean status) {
		buttonSelectInputOntologyFile.setEnabled(status);
	}

	@Override
	public void setButtonComputeInferenceEnabled(boolean status) {
		buttonSaveBayesianNetwork.setEnabled(status);
	}

	@Override
	public void updateInputOntologyFile() {
		String inputOntologyFile = getInputOntologyFile();
		if (Objects.nonNull(inputOntologyFile) && !inputOntologyFile.trim().isEmpty()) {
			try {
				getModel().setInputOntology(new FileInputStream(inputOntologyFile));
			} catch (IOException e) {
				setInputOntologyFile(Message.WRONG_FILE_NAME_ERROR);
			}
		}
	}

	@Override
	public void updateThreshold() {
		try {
			double th = Double.parseDouble(textThreshold.getText().trim());
			getModel().setThreshold(th);
		} catch (NumberFormatException e) {
		}
	}

	@Override
	public void updateInputBayesianNetworkFile() {
		String inputBayesianNetworkFile = getInputBayesianNetworkFile();
		if (Objects.nonNull(inputBayesianNetworkFile) && !inputBayesianNetworkFile.trim().isEmpty()) {
			try {
				getModel().setInputBayesianNetworkVariables(AnnotationCreator
						.extractVariables(ProcessorConfigurationImpl.read(new FileReader(inputBayesianNetworkFile))));
			} catch (IOException e) {
				setInputOntologyFile(Message.WRONG_FILE_NAME_ERROR);
			}
		}
	}

	@Override
	public String getInputOntologyFile() {
		return textInputOntologyFile.getText().trim();
	}

	@Override
	public void setInputOntologyFile(String fileName) {
		Objects.requireNonNull(fileName);
		textInputOntologyFile.setText(formatTool.formatText(fileName));
	}

	@Override
	public String getThreshold() {
		return textThreshold.getText().trim();
	}

	@Override
	public void setThreshold(String threshold) {
		Objects.requireNonNull(threshold);
		textThreshold.setText(formatTool.formatText(threshold));
	}

	@Override
	public String getInputBayesianNetworkFile() {
		return textInputBayesianNetworkFile.getText().trim();
	}

	@Override
	public void setInputBayesianNetworkFile(String inputBayesianNetworkFile) {
		Objects.requireNonNull(inputBayesianNetworkFile);
		textInputBayesianNetworkFile.setText(formatTool.formatText(inputBayesianNetworkFile));
	}

	@Override
	public String getListOfParents() {
		return textListOfParents.getText().trim();
	}

	@Override
	public void setListOfParents(String listOfParents) {
		Objects.requireNonNull(listOfParents);
		textListOfParents.setText(formatTool.formatText(listOfParents));
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(BornIcon.BACKGROUND, 0, 0, null);
	}

}
