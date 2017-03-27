package view;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by ilario
 * on 27/03/17.
 */
public class AddressBookSelector {
    private static JFrame frame = new JFrame("AddressBookSelector");
    private JTextField inputPathTextField;
    private JButton openBrowserButton;
    private JTextField outputNameTextField;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panel;
    private JButton saveBrowseButton;
    private JLabel inputFileErrorLabel;
    private JLabel outputFileErrorLabel;
    private ResourceBundle resourceBundle;

    public AddressBookSelector() {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(600, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        resourceBundle = ResourceBundle.getBundle("string");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JTextField getInputPathTextField() {
        return inputPathTextField;
    }

    public JButton getOpenBrowserButton() {
        return openBrowserButton;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getSaveBrowseButton() {
        return saveBrowseButton;
    }

    public JTextField getOutputNameTextField() {
        return outputNameTextField;
    }

    public void setNonExistentFileError(boolean state) {
        if (state) {
            inputFileErrorLabel.setText(resourceBundle.getString("input_file_non_existent"));
            inputFileErrorLabel.setVisible(true);
        } else {
            inputFileErrorLabel.setVisible(false);
        }
    }

    public void setNonExistentFolderError(boolean state) {
        if (state) {
            outputFileErrorLabel.setText(resourceBundle.getString("output_folder_non_existent"));
            outputFileErrorLabel.setVisible(true);
        } else {
            outputFileErrorLabel.setVisible(false);
        }
    }

    public void successMsg() {
        JOptionPane.showMessageDialog(frame, resourceBundle.getString("success_dialog_msg"));
    }
}