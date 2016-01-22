package de.tudresden.inf.lat.born.gui.processor;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import de.tudresden.inf.lat.born.gui.BornIcon;
import de.tudresden.inf.lat.born.gui.Message;
import de.tudresden.inf.lat.born.owlapi.example.ExampleConfiguration;
import de.tudresden.inf.lat.born.owlapi.processor.ProcessorConfiguration;
import de.tudresden.inf.lat.born.owlapi.processor.ProcessorConfigurationImpl;

/**
 * This is the panel to compute inference.
 * 
 * @author Julian Mendez
 */
public class ProcessorPanel extends JPanel implements ProcessorView {

	private static final long serialVersionUID = -3489883631448640992L;

	static final String WRONG_FILE_NAME_ERROR_MESSAGE = "WRONG FILE NAME! ";

	private final JButton buttonOntologyFile = new JButton();
	private final JButton buttonBayesianNetworkFile = new JButton();
	private final JButton buttonResetCompletionRules = new JButton();
	private final JButton buttonConsoleInput = new JButton();
	private final JButton buttonConsoleOutput = new JButton();
	private final JButton buttonComputeInference = new JButton();
	private final JButton buttonUpdateExample = new JButton();
	private final JLabel labelProgress = new JLabel("computing ...");
	private final JTextField textOntologyFile = new JTextField();
	private final JTextField textBayesianNetworkFile = new JTextField();
	private final JTextArea textCompletionRules = new JTextArea();
	private final JTextArea textConsoleInput = new JTextArea();
	private final JTextArea textConsoleOutput = new JTextArea();
	private final JScrollPane scrollCompletionRules = new JScrollPane();
	private final JScrollPane scrollConsoleInput = new JScrollPane();
	private final JScrollPane scrollConsoleOutput = new JScrollPane();
	private final JComboBox<String> comboBoxExample = new JComboBox<String>();
	private final ProcessorConfiguration model;

	public ProcessorPanel(ProcessorConfiguration model) {
		Objects.requireNonNull(model);
		this.model = model;
		setLayout(null);
		createPanel();
	}

	@Override
	public void addButtonOntologyFileListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonOntologyFile.addActionListener(listener);
		buttonOntologyFile.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonBayesianNetworkFileListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonBayesianNetworkFile.addActionListener(listener);
		buttonBayesianNetworkFile.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonResetCompletionRulesListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonResetCompletionRules.addActionListener(listener);
		buttonResetCompletionRules.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonConsoleInputListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonConsoleInput.addActionListener(listener);
		buttonConsoleInput.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonConsoleOutputListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonConsoleOutput.addActionListener(listener);
		buttonConsoleOutput.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonComputeInferenceListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonComputeInference.addActionListener(listener);
		buttonComputeInference.setActionCommand(actionCommand);
	}

	@Override
	public void addComboBoxExampleListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		comboBoxExample.addActionListener(listener);
		comboBoxExample.setActionCommand(actionCommand);
	}

	@Override
	public void addButtonUpdateExampleListener(ActionListener listener, String actionCommand) {
		Objects.requireNonNull(listener);
		Objects.requireNonNull(actionCommand);
		buttonUpdateExample.addActionListener(listener);
		buttonUpdateExample.setActionCommand(actionCommand);
	}

	void createPanel() {

		// ontology

		JLabel lblOntology = new JLabel("ontology");
		lblOntology.setBounds(282, 83, 70, 15);
		add(lblOntology);

		buttonOntologyFile.setIcon(BornIcon.OPEN_FILE);
		buttonOntologyFile.setBounds(213, 43, 54, 28);
		buttonOntologyFile.setToolTipText(Message.tooltipOpenInputOntologyFile);
		add(buttonOntologyFile);

		textOntologyFile.setBounds(282, 43, 688, 28);
		textOntologyFile.setToolTipText(Message.tooltipTextFieldInputOntologyFile);
		textOntologyFile.setAlignmentX(LEFT_ALIGNMENT);
		textOntologyFile.setEditable(false);
		add(textOntologyFile);

		// Bayesian network

		JLabel lblBayesianNetwork = new JLabel("Bayesian network");
		lblBayesianNetwork.setBounds(282, 175, 128, 15);
		add(lblBayesianNetwork);

		buttonBayesianNetworkFile.setIcon(BornIcon.OPEN_FILE);
		buttonBayesianNetworkFile.setBounds(213, 135, 54, 28);
		buttonBayesianNetworkFile.setToolTipText(Message.tooltipOpenInputOntologyFile);
		add(buttonBayesianNetworkFile);

		textBayesianNetworkFile.setBounds(282, 135, 688, 28);
		textBayesianNetworkFile.setToolTipText(Message.tooltipTextFieldBayesianNetworkFile);
		textBayesianNetworkFile.setAlignmentX(LEFT_ALIGNMENT);
		textBayesianNetworkFile.setEditable(false);
		add(textBayesianNetworkFile);

		// rules

		JLabel lblRules = new JLabel("rules");
		lblRules.setBounds(282, 296, 70, 15);
		add(lblRules);

		buttonResetCompletionRules.setIcon(BornIcon.REFRESH);
		buttonResetCompletionRules.setBounds(213, 219, 54, 28);
		buttonResetCompletionRules.setToolTipText(Message.tooltipButtonResetRules);
		add(buttonResetCompletionRules);

		textCompletionRules.setToolTipText(Message.tooltipTextFieldListOfParents);
		textCompletionRules.setAlignmentX(LEFT_ALIGNMENT);
		textCompletionRules.setBounds(282, 219, 688, 65);

		scrollCompletionRules.setBounds(282, 219, 688, 65);
		scrollCompletionRules.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollCompletionRules.setViewportView(textCompletionRules);
		add(scrollCompletionRules);

		// query

		JLabel lblInput = new JLabel("input");
		lblInput.setBounds(282, 413, 70, 15);
		add(lblInput);

		buttonConsoleInput.setIcon(BornIcon.OPEN_FILE);
		buttonConsoleInput.setBounds(213, 335, 54, 28);
		buttonConsoleInput.setToolTipText(Message.tooltipOpenInputOntologyFile);
		add(buttonConsoleInput);

		textConsoleInput.setToolTipText(Message.tooltipTextFieldListOfParents);
		textConsoleInput.setAlignmentX(LEFT_ALIGNMENT);

		scrollConsoleInput.setBounds(282, 336, 668, 65);
		scrollConsoleInput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConsoleInput.setViewportView(textConsoleInput);
		add(scrollConsoleInput);

		// result

		JLabel lblOutput = new JLabel("output");
		lblOutput.setBounds(282, 538, 70, 15);
		add(lblOutput);

		buttonConsoleOutput.setIcon(BornIcon.SAVE_FILE);
		buttonConsoleOutput.setBounds(213, 461, 54, 28);
		buttonConsoleOutput.setToolTipText(Message.tooltipOpenInputOntologyFile);
		add(buttonConsoleOutput);

		scrollConsoleOutput.setBounds(282, 461, 688, 65);
		scrollConsoleOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConsoleOutput.setViewportView(textConsoleOutput);
		add(scrollConsoleOutput);

		textConsoleOutput.setToolTipText(Message.tooltipTextFieldOutputFile);
		textConsoleOutput.setAlignmentX(LEFT_ALIGNMENT);

		// execution

		buttonComputeInference.setIcon(BornIcon.RUN);
		buttonComputeInference.setBounds(213, 573, 54, 28);
		buttonComputeInference.setToolTipText(Message.tooltipComputeInference);
		add(buttonComputeInference);

		labelProgress.setBounds(213, 624, 99, 15);
		labelProgress.setVisible(false);
		add(labelProgress);

		// examples

		comboBoxExample.setBounds(702, 573, 268, 28);
		comboBoxExample.setToolTipText(Message.tooltipComboBoxExample);
		add(comboBoxExample);

		buttonUpdateExample.setIcon(BornIcon.REFRESH);
		buttonUpdateExample.setBounds(622, 573, 54, 28);
		buttonUpdateExample.setToolTipText(Message.tooltipButtonUpdateExample);
		add(buttonUpdateExample);

	}

	@Override
	public ProcessorConfiguration getModel() {
		return model;
	}

	@Override
	public String getOntologyFile() {
		return textOntologyFile.getText();
	}

	@Override
	public void setOntologyFile(String fileName) {
		Objects.requireNonNull(fileName);
		textOntologyFile.setText(fileName);
	}

	@Override
	public String getBayesianNetworkFile() {
		return textBayesianNetworkFile.getText();
	}

	@Override
	public void setBayesianNetworkFile(String fileName) {
		Objects.requireNonNull(fileName);
		textBayesianNetworkFile.setText(fileName);
	}

	@Override
	public String getCompletionRules() {
		return textCompletionRules.getText();
	}

	@Override
	public void setCompletionRules(String text) {
		Objects.requireNonNull(text);
		textCompletionRules.setText(text);
	}

	@Override
	public String getConsoleInput() {
		return textConsoleInput.getText();
	}

	@Override
	public void setConsoleInput(String text) {
		Objects.requireNonNull(text);
		textConsoleInput.setText(text);
	}

	@Override
	public void readConsoleInput(String consoleInputFile) {
		Objects.requireNonNull(consoleInputFile);
		if (!Objects.isNull(consoleInputFile) && !consoleInputFile.trim().isEmpty()) {
			try {
				String text = ProcessorConfigurationImpl.read(new FileReader(consoleInputFile));
				this.textConsoleInput.setText(text);
				updateQuery();
			} catch (IOException e) {
				setOntologyFile(WRONG_FILE_NAME_ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void writeConsoleOutput(String consoleOutputFile) {
		Objects.requireNonNull(consoleOutputFile);
		if (!Objects.isNull(consoleOutputFile) && !consoleOutputFile.trim().isEmpty()) {
			try {
				String text = this.textConsoleOutput.getText();
				ProcessorConfigurationImpl.write(new StringReader(text), new FileWriter(consoleOutputFile));
			} catch (IOException e) {
				setOntologyFile(WRONG_FILE_NAME_ERROR_MESSAGE);
			}
		}
	}

	@Override
	public String getConsoleOutput() {
		return textConsoleOutput.getText();
	}

	@Override
	public void setConsoleOutput(String text) {
		Objects.requireNonNull(text);
		textConsoleOutput.setText(text);
	}

	@Override
	public void setButtonLoadEnabled(boolean b) {
		buttonOntologyFile.setEnabled(b);
	}

	@Override
	public void setButtonComputeInferenceEnabled(boolean b) {
		buttonComputeInference.setEnabled(b);
	}

	@Override
	public void setComboBoxExampleEnabled(boolean b) {
		comboBoxExample.setEnabled(b);
	}

	@Override
	public int getComboBoxExampleIndex() {
		return comboBoxExample.getSelectedIndex();
	}

	@Override
	public void updateOntologyFile() {
		String inputOntologyFile = getOntologyFile();
		if (!Objects.isNull(inputOntologyFile) && !inputOntologyFile.trim().isEmpty()) {
			try {
				getModel().setOntology(ProcessorConfigurationImpl.readOntology(new FileInputStream(inputOntologyFile)));
			} catch (IOException e) {
				setOntologyFile(WRONG_FILE_NAME_ERROR_MESSAGE);
			} catch (OWLOntologyCreationException e) {
				setOntologyFile(WRONG_FILE_NAME_ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void updateBayesianNetworkFile() {
		String bayesianNetworkFile = getBayesianNetworkFile();
		if (!Objects.isNull(bayesianNetworkFile) && !bayesianNetworkFile.trim().isEmpty()) {
			try {
				getModel().setBayesianNetwork(ProcessorConfigurationImpl.read(new FileReader(bayesianNetworkFile)));
			} catch (IOException e) {
				setBayesianNetworkFile(WRONG_FILE_NAME_ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void updateQuery() {
		String query = textConsoleInput.getText();
		if (!Objects.isNull(query) && !query.trim().isEmpty()) {
			getModel().setQuery(query);
		}
	}

	@Override
	public void setResult(String result) {
		Objects.requireNonNull(result);
		textConsoleOutput.setText(result);
	}

	@Override
	public void setComputing(boolean status) {
		labelProgress.setVisible(status);
	}

	@Override
	public void setButtonsEnabled(boolean status) {
		buttonOntologyFile.setEnabled(status);
		buttonBayesianNetworkFile.setEnabled(status);
		buttonResetCompletionRules.setEnabled(status);
		buttonConsoleInput.setEnabled(status);
		buttonConsoleOutput.setEnabled(status);
		buttonComputeInference.setEnabled(status);
		buttonUpdateExample.setEnabled(status);
		comboBoxExample.setEnabled(status);
	}

	@Override
	public void addExamples(Collection<ExampleConfiguration> examples) {
		Objects.requireNonNull(examples);
		examples.forEach(configuration -> comboBoxExample.addItem(configuration.getOntologyName()));
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}
