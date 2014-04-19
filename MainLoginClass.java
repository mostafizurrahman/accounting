
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting_company;

/**
 *
 * @author Mony
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import com.mysql.jdbc.dr // 
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Monjur
 */
public class MainLoginClass extends JFrame
{
    private JButton login;
    private JTextField userName;
    private JPasswordField password;
    private JLabel copy_right,label_userName,label_password,header,footer;
    AnimatePanel_label cars_panel = new AnimatePanel_label();
    JPanel TitlePanel = new JPanel();
      
    
    
    public MainLoginClass() throws IOException
    {
        super("User Login");
        this.setSize(518,535);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //login and registration button creation
        
        login = new JButton ("Log in");
        login.setSize(80,25);
        login .setLocation(330, 475); 
        this.add(login);
        
        label_password = new JLabel("Password : ");
        label_password.setAlignmentX(RIGHT_ALIGNMENT);
        label_password.setSize(70,25);
        label_password.setLocation(210,445);
        //label_password.setBackground(Color.PINK);
        this.add(label_password);
        //*********COpyt RIGHTS*/////////////
        
        copy_right = new JLabel("<html>Copy Right © 2014, All RIghts Preserved</html>");
        copy_right.setHorizontalAlignment(JLabel.CENTER);
        copy_right.setFont(new java.awt.Font("Arial",java.awt.Font.ITALIC+java.awt.Font.BOLD,10));
        copy_right.setSize(120,80);
        copy_right.setLocation(20,320);
        this.add(copy_right);
       
        
        label_userName = new JLabel("User Name : ");
        label_userName.setAlignmentX(RIGHT_ALIGNMENT);
        label_userName.setSize(70,25);
        label_userName.setLocation(210,415);
        this.add(label_userName);
        
        
        userName = new JTextField();
        userName.setSize(150,25);
        //fixed the length of input
        userName.setLocation(290, 415);
        this.add(userName);
        
        
        password = new JPasswordField();
        password.setSize(150,25);
        //fixed the length of input
        password.setLocation(290, 445);
        this.add(password);
        
        
        header = new JLabel(" S. ALI AUTOMOBILE ",JLabel.CENTER);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.LIGHT_GRAY);
        header.setSize(520,100);
        header.setLocation(0, 0);
        header.setFont(new Font("Arial",Font.BOLD,48));
        //Font f = new Font("Arial",Font.BOLD,14);
    
        TitlePanel.setBackground(Color.BLACK);
        TitlePanel.add(header);
        TitlePanel.setSize(520,90);
        TitlePanel.setLocation(0,0);
        this.add(TitlePanel);
        
        
        
        footer = new JLabel("<HTML>House#: 39, Road#: 14,<br>Sector#: 12, Uttara,<br>Dhaka 1230, Bangladesh</HTML>",JLabel.CENTER);
        footer.setBorder(BorderFactory.createLineBorder(Color.lightGray,3));
        footer.setForeground(Color.RED);
        footer.setSize(165,100);
        footer.setLocation(5, 400);
        footer.setFont(new Font("Arial",Font.BOLD,14));
        
        this.add(footer);
        
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                insert_into_database(userName.getText(),password.getText());
            }
        });
        
     
        try{
               ImageIcon background = new ImageIcon(getClass().getResource("sali_1.gif"));

                this.setIconImage(background.getImage());
                
        }
        catch (Exception ex)
        {

        }
        this.setResizable(false );
        this.setLocationRelativeTo(null); 
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AnimatePanel img_panel = new AnimatePanel();
       // final BufferedImage bi = ImageIO.read(getClass().getResource("ciclel.png"));
        
        
        cars_panel.setLocation(210, 100);
        this.add(cars_panel);
        this.add(img_panel);        
        this.repaint();
        this.setVisible(true);
    }
    
  
    public class AnimatePanel_label extends JPanel
    {
        private Image img;
       int index = 1;
       double theta;
       URL url ;
        private ActionListener taskPerformer = new ActionListener() 
        {
          
                @Override
                public void actionPerformed(ActionEvent e) {
                    //JOptionPane.showMessageDialog(null,"loged in successfully! :D");
                    theta += 0.2;
                    if(theta == 1.00 |  theta == 6.00 |  theta == 5.00| theta == 4.00| theta == 3.00|theta == 2.00)
                    {
             
                        index++;
                    }
                    if (theta>5)
                    {
                        theta = 0.00;
                      
                    }
                    repaint();
                    
                }
        };

        public AnimatePanel_label() throws IOException 
        {
            
            theta = 0.00;
            this.setSize(300,300);
            this.setVisible(true);
           url = getClass().getResource("cars"+index+".jpg");
            if (url != null) 
            {
                try 
                {
                    img = ImageIO.read(url);
                   
                    new Timer(400, taskPerformer).start();
                } 
                catch (IOException e) 
                {
                    System.err.println(e);
                }
            } 
            else 
            {
                System.err.println("Could not find image");
            }
        }

            @Override
        protected void paintComponent(Graphics gr) 
        {
            super.paintComponent(gr);
            
            url = getClass().getResource("cars"+index+".jpg");
            
            try 
            {
                img = ImageIO.read(url);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(MainLoginClass.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
             
            Graphics2D g = (Graphics2D) gr;
            //Graphics2D name = (Graphics2D) gr;
            //JOptionPane.showMessageDialog(null,"loged in successfully! :D");
            g.drawImage(img, 0, 0, null);
            if(index == 6)
                index = 1;
        }
    }
    

    

    class AnimatePanel extends JPanel
    {
        private Image img;
        final BufferedImage bi;
       double theta;

        private ActionListener taskPerformer = new ActionListener() 
        {
          

                @Override
                public void actionPerformed(ActionEvent e) {
                    theta += 0.1;
                    
                    if (theta>6)
                        theta = 0.00;
                    
                    
                repaint();
                    //throw new UnsupportedOperationException("Not supported yet.");
                }
        };

        public AnimatePanel() throws IOException {
            bi = ImageIO.read(getClass().getResource("name_only.png"));
            theta = 0.00;
            this.setSize(210,210);
            this.setLocation(0, 100);
            this.setVisible(true);
            URL url = getClass().getResource("ciclel.png");
            if (url != null) {
                try {
                    img = ImageIO.read(url);
                   
                    new Timer(200, taskPerformer).start();
                } catch (IOException e) {
                    System.err.println(e);
                }
            } else {
                System.err.println("Could not find image");
            }
        }

            @Override
        protected void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            Graphics2D g = (Graphics2D) gr;
            //Graphics2D name = (Graphics2D) gr;
            g.drawImage(bi, -5,0, this);
            g.rotate(theta, 105, 105.6);
            g.drawImage(img, 0, 0, null);
            
        }
    }
    
    
    
    private void insert_into_database(String username,String password)
    {
      
        String sql="SELECT username FROM user_info  WHERE username = ? AND password = ?;";
        Connection conn = null;
        PreparedStatement stmt = null;
        try 
 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/master_db", "root", "kharap");
            stmt = conn.prepareStatement(sql);
            //stmt = (PreparedStatement) conn.createStatement();
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet login_resultSet= stmt.executeQuery();
            String login_userName = null;
            login_resultSet.next();
            login_userName = login_resultSet.getString(1);
          
            if ("mostafizur.mony".equals(login_userName))
            {
                cars_panel.disable();
                this.remove(cars_panel);
                JLabel image_label = new JLabel();
                image_label.setSize(300,300);
                image_label.setLocation(210,100);
                ImageIcon img = new ImageIcon(getClass().getResource("moni.JPG"));
                image_label.setIcon(img);//new ImageIcon("C:/Users/Mony/Desktop/moni.JPG"));
                this.add(image_label);
                repaint();
                
                JOptionPane.showMessageDialog(null,"loged in successfully! :D");
                this.dispose();
                
                new invoice();
            }
            else if("monjur.kader".equals(login_userName))
            {
                cars_panel.disable();
                this.remove(cars_panel);
                JLabel image_label = new JLabel();
                image_label.setSize(300,300);
                image_label.setLocation(210,100);
                ImageIcon img = new ImageIcon(getClass().getResource("cars7.png"));
                image_label.setIcon(img);//new ImageIcon("C:/Users/Mony/Desktop/moni.JPG"));
                this.add(image_label);
                repaint();
                
                JOptionPane.showMessageDialog(null,"loged in successfully! :D");
                this.dispose();
                
                new invoice();
            }
          conn.close();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Icorrect Login iformation","Log in Fail!",JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
   }
    
    public class actionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            JOptionPane.showMessageDialog(null,password.getText(), "Error Message",JOptionPane.ERROR_MESSAGE);
        }
        
    }
}

