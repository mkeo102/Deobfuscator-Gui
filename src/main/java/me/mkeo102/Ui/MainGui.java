package me.mkeo102.Ui;

import org.objectweb.asm.ClassReader;
import uwu.narumi.deobfuscator.Deobfuscator;
import uwu.narumi.deobfuscator.transformer.Transformer;
import uwu.narumi.deobfuscator.transformer.composed.*;
import uwu.narumi.deobfuscator.transformer.composed.qprotect.latest.qProtectTransformer;
import uwu.narumi.deobfuscator.transformer.composed.qprotect.v1_7_2.qProtectV1_7_2_Transformer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class MainGui extends JFrame {


    //Please note that I hard coded all of this. I didn't use the fucking intellij gui designer bc I don't like it. Please be considerate


    public final static String[] Transformers = {"QProtect_Latest", "QProtect V1_7_2", "Binsecure", "Bozoriusz", "Caesium", "CheatBreaker", "Clean", "Monsey", "Paramorphism", "Radon", "Scuti", "Superblaubeere", "Skidfuscator"};
    public static boolean completed = false;
    public static String Status = "Idle...";
    final JCheckBox BinsecureUseStackAnalyzer, ScutiIsStrongStringType, SuperblaubeereIsPacked;
    private final JPanel panel1;
    private final JButton button1, inputButton, outputButton;
    private final JFileChooser input, output;
    private final JTextArea status;
    private final JComboBox comboBox1;
    File inputFile, outputFile;

    public MainGui() {

        this.setTitle("Deobfuscator by Narumii | Gui by Mkeo102");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.panel1 = new JPanel();
        this.setResizable(false);
        this.setBounds(0, 0, 500, 100);
        panel1.setBounds(0, 0, 500, 30);


        input = new JFileChooser();

        inputButton = new JButton("Input");
        inputButton.addActionListener(

                e -> {
                    int response = input.showOpenDialog(null);

                    if (response == JFileChooser.APPROVE_OPTION) {
                        inputFile = new File(input.getSelectedFile().getAbsolutePath());
                    }
                }

        );


        output = new JFileChooser();


        status = new JTextArea();
        status.setEditable(false);
        status.setFocusable(false);
        status.setText(Status);

        outputButton = new JButton("Output");
        outputButton.addActionListener(

                e -> {
                    int response = output.showOpenDialog(null);

                    if (response == JFileChooser.APPROVE_OPTION) {
                        outputFile = new File(output.getSelectedFile().getAbsolutePath());
                    }
                }

        );

        this.button1 = new JButton("Start");
        button1.addActionListener(
                e -> {

                    try {
                        startDeobfuscator();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
        );


        this.comboBox1 = new JComboBox();

        for (String s : Transformers) {

            comboBox1.addItem(s);

        }

        BinsecureUseStackAnalyzer = new JCheckBox("BinsecureUseStackAnalyzer");

        ScutiIsStrongStringType = new JCheckBox("ScutiIsStrongStringType");

        SuperblaubeereIsPacked = new JCheckBox("SuperblaubeereIsPacked");


        add(inputButton, outputButton, button1, comboBox1, BinsecureUseStackAnalyzer, ScutiIsStrongStringType, SuperblaubeereIsPacked, status);

        this.panel1.setVisible(true);
        this.add(panel1);

        this.setVisible(true);


        //BinsecureUseStackAnalyzer visibility check
        while (true) {
            status.setText(Status);
            BinsecureUseStackAnalyzer.setVisible(comboBox1.getItemAt(comboBox1.getSelectedIndex()) == "Binsecure");
            ScutiIsStrongStringType.setVisible(comboBox1.getItemAt(comboBox1.getSelectedIndex()) == "Scuti");
            SuperblaubeereIsPacked.setVisible(comboBox1.getItemAt(comboBox1.getSelectedIndex()) == "Superblaubeere");
        }

    }

    public void add(Component... o) {

        for (Component c : o) {

            this.panel1.add(c);

        }

    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    //"QProtect_Latest", "QProtect V1_7_2", "Binsecure", "Bozoriusz", "Caesium", "CheatBreaker", "Clean", "Monsey", "Paramorphism", "Radon", "Scuti", "Superblaubeere"


    public void startDeobfuscator() throws FileNotFoundException {

        completed = false;
        Status = "Working...";
        this.status.setText(Status);

        Deobfuscator.builder()
                .input(Path.of(inputFile.getAbsolutePath()))
                .output(Path.of(outputFile.getAbsolutePath()))
                .transformers(

                        getTransformer()

                ).normalize()
                .classReaderFlags(ClassReader.SKIP_FRAMES)
                .classWriterFlags(0)
                .consoleDebug()
                .build()
                .start();

    }

    private Transformer getTransformer() {

        switch ((String) comboBox1.getItemAt(comboBox1.getSelectedIndex())) {
            case "QProtect_Latest": {
                return new qProtectTransformer();
            }
            case "QProtect V1_7_2": {
                return new qProtectV1_7_2_Transformer();
            }
            case "Caesium": {
                return new CaesiumTransformer();
            }
            case "Binsecure": {
                return new BinsecureTransformer(BinsecureUseStackAnalyzer.isSelected());
            }
            case "Bozoriusz": {
                return new BozoriuszTransformer();
            }
            case "CheatBreaker": {
                return new CheatBreakerTransformer();
            }
            case "Clean": {
                return new CleanTransformer();
            }
            case "Monsey": {
                return new MonseyTransformer();
            }
            case "Paramorphism": {
                return new ParamorphismTransformer();
            }
            case "Radon": {
                return new RadonTransformer();
            }
            case "Scuti": {
                return new ScutiTransformer(ScutiIsStrongStringType.isSelected());

            }
            case "Superblaubeere": {
                return new SuperblaubeereTransformer(SuperblaubeereIsPacked.isSelected());
            }
            case "Skidfuscator": {
                return new SkidfuscatorTransformer();
            }

        }

        return null;

    }


}
