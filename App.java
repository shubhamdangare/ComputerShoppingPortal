import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

class App{
    
    public static native int calcBill(int[] params);
    static { 
        System.loadLibrary("calc"); 
    }

    public static void main(String args[]){
        
        MarvellousLogin mf = new MarvellousLogin();
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}

class AppFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 600;

    private Hashtable<String,Integer> processors;
    private Hashtable<String,Integer> ram;
    private Hashtable<String,Integer> graphicsCards;
    JLabel resultLabel2;

    public AppFrame(){

        setTitle("Marvellous Computer Shopping Portal");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new GridLayout(5,2));

        processors = new Hashtable<String,Integer>();
        processors.put("i3",10000);
        processors.put("i5",15000);
        processors.put("i7",20000);

        JPanel panel1 = new JPanel();
        JLabel processorLabel = new JLabel("select processor:");
        panel1.add(processorLabel);

        JPanel panel2 = new JPanel();
        Set<String> processorKeys = processors.keySet(); //Returns a Set view of the keys contained in this map.
        JComboBox processorComboBox = new JComboBox(processorKeys.toArray());  //Returns an array containing all of the elements in this set.
        panel2.add(processorComboBox);


        ram = new Hashtable<String,Integer>();
        ram.put("2 GB",3000);
        ram.put("4 GB",5000);
        ram.put("8 GB",8000);

        JPanel panel3 = new JPanel();
        JLabel ramLabel = new JLabel("select Ram:");
        panel3.add(ramLabel);

        JPanel panel4 = new JPanel();
        Set<String> ramKeys = ram.keySet();
        JComboBox ramComboBox = new JComboBox(ramKeys.toArray());
        panel4.add(ramComboBox);
    
        
        graphicsCards = new Hashtable<String,Integer>();
        graphicsCards.put("2 GB",2000);
        graphicsCards.put("4 GB",5000);
        graphicsCards.put("8 GB",8000);

        JPanel panel5 = new JPanel();
        JLabel graphicsCardsLabel = new JLabel("select graphicsCards:");
        panel5.add(graphicsCardsLabel);

        JPanel panel6 = new JPanel();
        Set<String> graphicsCardsKeys = graphicsCards.keySet();
        JComboBox graphicsCardsComboBox = new JComboBox(graphicsCardsKeys.toArray());
        panel6.add(graphicsCardsComboBox);
    
        JPanel panel7 = new JPanel();
        JButton calculateButton = new JButton("Calculate Bill");
        calculateButton.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent event){

                    int processorPrice = processors.get((String)processorComboBox.getSelectedItem());
                    int ramPrice = ram.get((String)ramComboBox.getSelectedItem());
                    int graphicsCardsPrice = graphicsCards.get((String)graphicsCardsComboBox.getSelectedItem());
                    int params[] = new int[3];
                    params[0] = processorPrice;
                    params[1] = ramPrice;
                    params[2] = graphicsCardsPrice;

                    int result = App.calcBill(params);
                    resultLabel2.setText("" + result);
                }
        });

            panel7.add(calculateButton);
            JPanel panel8 = new JPanel();

            JPanel panel9 =new JPanel();
            JLabel resultLabel = new JLabel("your estimated total is :");
            panel9.add(resultLabel);

            JPanel panel10 = new JPanel();
            resultLabel2 = new JLabel("");
            panel10.add(resultLabel2);

            add(panel1);
            add(panel2);
            add(panel3);
            add(panel4);
            add(panel5);
            add(panel6);
            add(panel7);
            add(panel8);
            add(panel10);
            add(panel9);
        
    }
    
}

class MarvellousLogin extends JFrame implements ActionListener{

        JLabel l1,l2;
        JTextField tf1;
        JPasswordField tf2;
        JButton b;
        public static int icnt = 3;

        public MarvellousLogin(){

            super("Marvellous E Shopping Portal");
            setContentPane(new JLabel(new ImageIcon("/////////////////////////////")));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            l1 = new JLabel("username");
            l1.setBounds(50,70,150,20);

            tf1 = new JTextField();
            tf1.setBounds(50,100,200,20);
            tf1.setToolTipText("enter your Name");

            l2 = new JLabel("password");
            l2.setBounds(50,130,150,20);

            tf2 = new JPasswordField();
            tf2.setBounds(50,160,200,20);
            tf2.setToolTipText("enter your Password");

            b = new JButton("Login");
            b.setBounds(90,200,80,30);
            b.addActionListener(this);

            add(l1);
            add(tf1);
            add(l2);
            add(tf2);
            add(b);
            setSize(300,300);
            setLayout(null);
            setVisible(true);

        }

        public void actionPerformed(ActionEvent e){
            checkAuthentication();
        }
        public void checkAuthentication(){

            String username = tf1.getText();
            String password = tf2.getText();
            if((username.equals("admin")) && (password.equals("admin"))){
                
                AppFrame af = new AppFrame();
                af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                af.setVisible(true);
                dispose();
            }

            else{
                    --icnt;
                    if(icnt == 0){
                        JOptionPane.showMessageDialog(this,"Number of attempts finished");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"MarvellousAuthentication Failed");
                        tf1.setText("");
                        tf2.setText("");
                    }
            }
        }

}












