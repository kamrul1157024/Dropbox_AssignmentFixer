import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main{

    public static void main(String[] args)
    {
        JFrame jFrame=new JFrame();



     /*   JTextField t1;
        t1=new JTextField("");
        t1.setBounds(50,100, 200,30);
*/
     StringBuffer text=new StringBuffer();

     JButton file=new JButton("Choose Assingments");
     file.setBounds(50,100,200,30);
     file.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
             JFileChooser chooser = new JFileChooser();
             chooser.setCurrentDirectory(new java.io.File("."));
             chooser.setDialogTitle("choosertitle");
             chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
             chooser.setAcceptAllFileFilterUsed(false);

             if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                 text.append(chooser.getCurrentDirectory());
             } else {
                 file.setText("Bal Amar !-_-");
             }

         }
     });





        JButton b=new JButton("ok");
        b.setBounds(100,150,95,30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                System.out.println(text.toString());
                new FileRenamer(text.toString());
            }
        });



        jFrame.add(b);
        jFrame.add(file);
        //jFrame.add(t1);
        jFrame.setSize(300,300);
        jFrame.setLayout(null);
        jFrame.setVisible(true);

    }
}
