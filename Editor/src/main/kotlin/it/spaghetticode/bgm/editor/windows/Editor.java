package it.spaghetticode.bgm.editor.windows;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import it.spaghetticode.bgm.core.Project;
import it.spaghetticode.bgm.core.ProjectException;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Editor extends JFrame {
    private JPanel panel1;
    private JMenuItem fileMenu;
    private JButton button1;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JMenuBar menuBar1 = new JMenuBar();
        menuBar1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(menuBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        fileMenu = new JMenu();
        fileMenu.setSelected(false);
        fileMenu.setText("File");
        fileMenu.setVerticalAlignment(0);
        fileMenu.setVerticalTextPosition(0);
        menuBar1.add(fileMenu, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menuBar1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        button1 = new JButton();
        button1.setText("Button");
        panel1.add(button1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /** @noinspection ALL */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * Here is possible to add some menues if necessary and its functionallity
     */
    private void setupMenuBar() {
    }

    @Getter
    private Project project;

    public Editor(Project p) throws HeadlessException {
        setProject(p);

        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        setupMenuBar();
        // TODO: 27/12/22 do open from latest still open proj 
    }

    public void setProject(Project project) {
        if (project.getLocation() == null || !project.getLocation().exists()) {
            JOptionPane.showMessageDialog(this, "Unable to find location path for project.", "Error", ERROR);
            return;
        }
        this.project = project;
    }
}
