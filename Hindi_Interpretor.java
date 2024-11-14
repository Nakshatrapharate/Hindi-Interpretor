import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class Hindi_Interpretor{
	
	public static String convertToBinaryUsingBuiltInMethods(String input) {
        byte[] bytes = input.getBytes();
        StringBuilder binaryString = new StringBuilder();

        for (byte b : bytes) {
            String binary = Integer.toBinaryString(b & 0xFF);
            binaryString.append(String.format("%8s", binary).replace(' ', '0'));
        }

        return binaryString.toString();
    }
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        File alp = new File("alp.txt");
        JFrame frame = new JFrame("Binary Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        Hashtable<String, Integer> mot = new Hashtable<String, Integer>();
        mot.put("मुव्", 1);
        mot.put("ऐड", 2);
        mot.put("सब", 3);
        mot.put("मल", 4);
        mot.put("दिव", 5);
        mot.put("जम्प", 6);
        mot.put("इन्ट", 7);
        JTextArea inputText = new JTextArea(10, 30);
        JTextArea outputText = new JTextArea(10, 30);
        outputText.setEditable(false);
        try {
            Font devanagariFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\LAPTOP\\Downloads\\MANGAL\\MANGAL.TTF")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(devanagariFont);
            inputText.setFont(devanagariFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputText.getText();
                StringBuilder output = new StringBuilder();
        try {
        	String bin = "";
        	int s=0;
            Scanner sc = new Scanner(alp);
            while (sc.hasNextLine()) {
                String cmd = sc.nextLine();
                String[] words = cmd.split("\\s");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals("शुरू") || words[i].equals("अंत")) { 
                    	
                    	s=1;          
                    } else if (mot.containsKey(words[i])) {
                   
                    	s=1;
                  
                    } else if (words[i].charAt(words[i].length() - 1) == ',') {
                    	
                        continue;
                    } else if(words[i].charAt(words[i].length() - 1) == '.'){
                    	
                        continue;
                    }
                    else
                    {
                    	s=0;
                    }
                    bin+= convertToBinaryUsingBuiltInMethods(words[i]) + " ";
                    }
                if(s==0)
                {
                	output.append("ERROR");
                	break;
                	
                }
                else
                {
 
                	output.append(bin);
               }
            output.append("\n");
            bin="";
            }
            
        } 
        catch (Exception z) {
            System.out.println(z);
        }
        outputText.setText(output.toString());
    }
        });
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Enter your assembly code:"), BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(inputText), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.add(new JLabel("Output:"), BorderLayout.NORTH);
        outputPanel.add(new JScrollPane(outputText), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(convertButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);
        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
}
}
