package it.spaghetticode.bgm.editor.windows;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import it.spaghetticode.bgm.core.Project;
import it.spaghetticode.bgm.editor.MainKt;
import it.spaghetticode.bgm.editor.UiUtilsKt;
import it.spaghetticode.bgm.editor.windows.properties_view.Properties;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Editor extends JFrame {
    Editor self;
    private JPanel panel1;
    private JMenuItem fileMenu;
    private JMenu projectMenu;

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
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JMenuBar menuBar1 = new JMenuBar();
        menuBar1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(menuBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        fileMenu = new JMenu();
        fileMenu.setSelected(false);
        fileMenu.setText("File");
        fileMenu.setVerticalAlignment(0);
        fileMenu.setVerticalTextPosition(0);
        menuBar1.add(fileMenu, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menuBar1.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(50, -1), null, null, 0, false));
        projectMenu = new JMenu();
        projectMenu.setText("Project");
        menuBar1.add(projectMenu, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /** @noinspection ALL */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    @Getter
    private Project project;

    public Editor(Project p) throws HeadlessException {
        self = this;
        setProject(p);

        setTitle("Boardgame Maker - " + project.getName());
        setContentPane(panel1);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        setupMenuBar();
    }

    /**
     * Here is possible to add some menues if necessary and its functionallity
     */
    private void setupMenuBar() {
        //FILE
        {//Close
            JMenuItem jmi = new JMenuItem("Close");
            jmi.addActionListener(actionEvent -> {
                File currentOpened = new File(MainKt.getSETTINGS_PATH() + MainKt.getOPENED_PATH());
                currentOpened.delete();
                UiUtilsKt.switchView(self, new Launcher());
            });
            fileMenu.add(jmi);
        }

        //PROJECT
        {//Properties
            JMenuItem jmi = new JMenuItem("Properties...");
            jmi.addActionListener(actionEvent -> {
                Properties properties = new Properties(project);
                properties.open();
            });
            projectMenu.add(jmi);
        }
    }

    public void setProject(Project project) {
        if (project.getLocation() == null || !project.getLocation().exists()) {
            JOptionPane.showMessageDialog(this, "Unable to find location path for project.", "Error", ERROR);
            return;
        }
        this.project = project;
    }
}
