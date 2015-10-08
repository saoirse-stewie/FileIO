package Assignment1;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by g00238234 on 17/09/2015.
 */
public  class Assingment_1 extends JPanel {

    JFileChooser chooser = new JFileChooser();
    public static File file;

    JFrame jf = new JFrame();

    public Assingment_1() {
        int width = 1000;
        int height = 1000;

        JButton b1 = new JButton("Load");
        JButton b2 = new JButton("Reverse");
        JButton b3 = new JButton("Reverse pair");
        JButton b4 = new JButton("Count");

        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();


        JFrame jf = new JFrame("Assignment 1");
        jf.setLayout(new GridLayout(1, 4));

        jf.setSize(width, height);
        JTextArea jta = new JTextArea(40, 10);
        jta.setSize(198, 600);
        JTextArea jta2 = new JTextArea(40, 10);
        jta2.setSize(198, 600);
        JTextArea jta3 = new JTextArea(40, 10);
        jta3.setSize(198, 600);
        JTextArea jta4 = new JTextArea(40, 10);
        jta4.setSize(198, 600);

        jta.setLineWrap(true);
        jta2.setLineWrap(true);
        jta3.setLineWrap(true);
        jta4.setLineWrap(true);

        jta.setEditable(true);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p.add(b1);
        p2.add(b2);
        p3.add(b3);
        p4.add(b4);

        p.add(jta);
        p2.add(jta2);
        p3.add(jta3);
        p4.add(jta4);

        jf.add(p);
        jf.add(p2);
        jf.add(p3);
        jf.add(p4);


        jf.setVisible(true);

        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                JFileChooser jft = new JFileChooser();
                int returnval = jft.showOpenDialog(null);
                int bufferSize = 8 * 1024;
                int words = 0;


                if (returnval == JFileChooser.APPROVE_OPTION) {

                    file = jft.getSelectedFile();
                    SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws Exception {
                            String fileContents;
                            fileContents = "";
                            int i;

                            try {
                                FileReader fileReader = new FileReader(file);

                                while ((i = fileReader.read()) != -1) {
                                    char ch = (char) i;
                                    fileContents = fileContents + ch;

                                }
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();

                            }

                            return fileContents;
                        }
                        public void done()
                        {
                            try {
                                String ch=get();
                                jta.append(ch);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }

                    };swingWorker.execute();
                }
            }
        });

        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                try {
                    SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>() {

                        @Override
                        protected String doInBackground() throws Exception {
                            String fileContents;
                            fileContents = " ";
                            String[] parts = new String[0];
                            try {
                                FileReader fr = new FileReader(file);
                                BufferedReader br = new BufferedReader(fr);

                                String line = br.readLine();
                                parts = new String[(int) file.length()];


                                while (line != null) {

                                    parts = (line.split(" "));

                                    for (int x = parts.length - 1; x >= 0; x--) {

                                        fileContents += parts[x] + " ";
                                    }
                                    if (line.compareTo("") == 0) {
                                        fileContents = fileContents + "\r\n\n";
                                    }
                                    line = br.readLine();


                                }

                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            return fileContents;
                        }

                        public void done() {
                            try {
                                String data = get();

                                jta2.append(data);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                        //jta.append(line + " " + count + "\n");
                    };
                    swingWorker.execute();

                } finally {

                }

            }

        });
        b3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                try {
                    SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>() {

                        @Override
                        protected String doInBackground() throws Exception {
                            FileReader fr = new FileReader(file);
                            BufferedReader br = new BufferedReader(fr);
                            String[] parts = new String[(int) file.length()];
                            String fileContents;
                            fileContents = " ";
                            String line = br.readLine();

                            while (line != null) {
                                parts = line.split(" ");
                                String[] reversedPart = new String[parts.length];

                                for (int x = parts.length - 1; x >= 0; x--) {

                                    reversedPart[x] = parts[x];
                                }
                                for (int x = 0; x < parts.length; x++) {

                                    if (x < reversedPart.length - 1 && x % 2 == 0 || x % 2 == 1) {

                                        fileContents += reversedPart[x + 1] + " ";
                                        fileContents += reversedPart[x] + " ";
                                        x++;
                                    }
                                }
                                if (line.compareTo("") == 0) {
                                    fileContents = fileContents + "\r\n\n";
                                }
                                line = br.readLine();
                            }

                            return fileContents;
                        }

                        public void done() {
                            try {
                                String data = get();

                                jta3.append(data);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    swingWorker.execute();

                } finally {

                }

            }
        });

        b4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {


                String test;
                String delim = " \\n\\r\\t ";


                int bufferSize = 8 * 1024;
                SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>() {

                    @Override
                    protected String doInBackground() throws Exception {

                        FileReader fr = new FileReader(file);
                        String[] parts = new String[(int) file.length()];
                        String fileContents;
                        fileContents = "";
                        String temp = "";
                        int i;

                        while ((i = fr.read()) != -1) {
                            char ch = (char) i;

                            fileContents = fileContents + ch;
                        }

                        parts = (fileContents.split(" "));
                        int b = 0;
                        int d = 0;
                        int counter[] = new int[parts.length];
                        String f ="";
                        parts[b] = parts[0];
                       // counter[d] = counter[0];

                        for (int j = 0; j < parts.length; j++) {
                            counter[j] = 1;

                            for (int k = 0; k < parts.length; k++) {
                                if (j != k) {
                                    if (parts[j].compareTo(parts[k]) == 0) {
                                        counter[j]++;

                                    }
                                }
                            }

                        }

                        for (int j = 0; j < parts.length; j++) {

                            if (parts[b].compareTo(parts[j]) != 0) {

                                b++;
                                parts[b] = parts[j];
                                System.out.println(parts[b]);


                            }
                        }
                        for (int l = 0; l <= b; l++) {
                            f += parts[l] + " " ;
                            f += counter[l] + " ";
                            f+= "\r \n";

                        }




                            return f;
                    }

                    public void done() {
                        try {
                            String data = get();

                            jta4.append(data);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                };
                swingWorker.execute();
            }
        });
    }

}