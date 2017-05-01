package moudule;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class SwingWinMenus extends JFrame {
  public static int WIDTH = 300;

  public static int HEIGHT = 300;

  public static String TITLE = "SwingWin";

  Container frameContainer;

  JTextField textField = new JTextField(50);

  JMenuBar menuBar = new JMenuBar();

  JMenu fileMenu = new JMenu("File");

  JMenuItem fileNew = new JMenuItem("New");

  JMenuItem fileOpen = new JMenuItem("Open");

  JMenuItem fileSave = new JMenuItem("Save");

  JMenuItem fileExit = new JMenuItem("Exit");

  JMenu editMenu = new JMenu("Edit");

  JMenuItem editCut = new JMenuItem("Cut");

  JMenuItem editCopy = new JMenuItem("Copy");

  JMenuItem editPaste = new JMenuItem("Paste");

  JMenu specialMenu = new JMenu("Special");

  JCheckBoxMenuItem specialCheck1 = new JCheckBoxMenuItem("Check 1");

  JCheckBoxMenuItem specialCheck2 = new JCheckBoxMenuItem("Check 2", true);

  JSeparator separator = new JSeparator();

  JRadioButtonMenuItem specialRadio1 = new JRadioButtonMenuItem("Radio 1");

  JRadioButtonMenuItem specialRadio2 = new JRadioButtonMenuItem("Radio 2");

  ButtonGroup buttonGroup = new ButtonGroup();

  public SwingWinMenus() {
    super("");
    fileMenu.add(fileNew);
    fileMenu.add(fileOpen);
    fileMenu.add(fileSave);
    fileMenu.add(fileExit);
    editMenu.add(editCut);
    editMenu.add(editCopy);
    editMenu.add(editPaste);
    specialMenu.add(specialCheck1);
    specialMenu.add(specialCheck2);
    specialMenu.add(separator);
    buttonGroup.add(specialRadio1);
    buttonGroup.add(specialRadio2);
    specialMenu.add(specialRadio1);
    specialMenu.add(specialRadio2);
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(specialMenu);
    setJMenuBar(menuBar);

    frameContainer = getContentPane();
    frameContainer.setLayout(null);
    textField.setBounds(100, 100, 100, 20);
    frameContainer.add(textField);

    setupEventHandlers();
    setSize(450, 450);
    setVisible(true);
  }

  void setupEventHandlers() {
    addWindowListener(new WindowHandler());
    fileNew.addActionListener(new MenuItemHandler());
    fileOpen.addActionListener(new MenuItemHandler());
    fileSave.addActionListener(new MenuItemHandler());
    fileExit.addActionListener(new MenuItemHandler());
    editCut.addActionListener(new MenuItemHandler());
    editCopy.addActionListener(new MenuItemHandler());
    editPaste.addActionListener(new MenuItemHandler());
    specialCheck1.addItemListener(new ItemHandler());
    specialCheck2.addItemListener(new ItemHandler());
    specialRadio1.addItemListener(new ItemHandler());
    specialRadio2.addItemListener(new ItemHandler());
  }

  public static void main(String[] args) {
    SwingWinMenus app = new SwingWinMenus();
  }

  public class WindowHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }
  }

  public class MenuItemHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      if (cmd.equals("Exit"))
        System.exit(0);
      else
        textField.setText(cmd);
    }
  }

  public class ItemHandler implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      AbstractButton button = (AbstractButton) e.getItem();
      String label = button.getText();
      if (button.isSelected())
        label += " true";
      else
        label += " false";
      textField.setText(label);
    }
  }
}
