import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {

    JButton stageButton;
    JButton commitButton;
    JTextField textField1;
    JTextField textField2;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        stageButton = new JButton("Stage");
        stageButton.addActionListener(this);

        commitButton = new JButton("Commit");
        commitButton.addActionListener(this);

        textField1 = new JTextField("repositories/day_planner");
        textField1.setPreferredSize(new Dimension(250, 40));
        textField2 = new JTextField("added something");
        textField2.setPreferredSize(new Dimension(250, 40));

        this.add(stageButton);
        this.add(textField1);
        this.add(commitButton);
        this.add(textField2);
        this.pack();
        this.setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        File dir = new File("/Users/jacoblovins/Desktop/" + textField1.getText());
        if (e.getSource() == stageButton) {
            // String command = "cd /Users/jacoblovins/Desktop/" + textField1.getText() + "
            // && git add -A";
            System.out.println(dir);

            try {
                Process stage = Runtime.getRuntime().exec("git add -A", null, dir);

                int exitVal1 = stage.waitFor();
                if (exitVal1 == 0) {
                    System.out.println("Successfully Staged");
                    // System.exit(0);
                } else {
                    System.out.println("something went wrong in staging!");
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        } else if (e.getSource() == commitButton) {
            try {
                Process commit = Runtime.getRuntime().exec("git commit -m \"added something\"", null, dir);

                int exitVal = commit.waitFor();
                if (exitVal == 0) {
                    System.out.println("Successfully committed");
                    // System.exit(0);
                } else {
                    System.out.println(exitVal);
                    System.out.println("something went wrong in committing!");
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }
}
