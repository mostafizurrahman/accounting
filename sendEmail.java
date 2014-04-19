/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting_company;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Mony
 */
public class sendEmail extends JFrame
{
    private JPanel panel_mail,panel_control, TitlePanel;
    private JButton btn_send;
    private JTextArea txt_message;
    private JTextField txt_to_address,txt_from_address,txt_subject;
    private JPasswordField txt_password; 
    private JLabel header,from,to,password,subject;
    
    public sendEmail()
    {
        super("Sending Email");
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        /*********************** HEADING PANEL *******************************/
        
        header = new JLabel(" S.ALI AUTOMOBILE ",JLabel.CENTER);
        header.setBackground(Color.GRAY);
        header.setForeground(Color.LIGHT_GRAY);
        header.setSize(500, 35);
        header.setLocation(0, 0);
        header.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,32));
        //Font f = new Font("Arial",Font.BOLD,14);
        TitlePanel = new JPanel();
        TitlePanel.setLayout(null);
        TitlePanel.setBackground(Color.BLUE);
        TitlePanel.add(header);
        TitlePanel.setSize(500, 35);
        TitlePanel.setLocation(0, 0);
        this.add(TitlePanel);
        
        
        /*********************** BODY PANEL *******************************/
        
        
        panel_mail = new JPanel();
        panel_mail.setLayout(null); 
        panel_mail.setSize(500,355);
        panel_mail.setLocation(0, 35);
        
        from = new JLabel("From : ",JLabel.RIGHT);
        //header.setBackground(Color.GRAY);
        from.setForeground(Color.red);
        from.setSize(100, 25);
        from.setLocation(0, 5);
        from.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,14));
        
        txt_from_address = new JTextField(30);
        txt_from_address.setSize(375, 25);
        txt_from_address.setLocation(100, 5);
        txt_from_address.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,12));
        
        
        subject = new JLabel("Subject : ",JLabel.RIGHT);
        //header.setBackground(Color.GRAY);
        subject.setForeground(Color.red);
        subject.setSize(100, 25);
        subject.setLocation(0, 35);
        subject.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,14));
        
        txt_subject = new JTextField(30);
        txt_subject.setSize(375, 25);
        txt_subject.setLocation(100, 35);
        txt_subject.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,12));
        
        
        to = new JLabel("To : ",JLabel.RIGHT);
        //header.setBackground(Color.GRAY);
        to.setForeground(Color.red);
        to.setSize(100, 25);
        to.setLocation(0, 65);
        to.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,14));
        
        txt_to_address = new JTextField(30);
        txt_to_address.setSize(375, 25);
        txt_to_address.setLocation(100, 65);
        txt_to_address.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,12));
        
        
        
        
        
              
        /********message**********/
        
        JPanel middlePanel = new JPanel ();
        middlePanel.setLayout(null);
        middlePanel.setLocation(5, 100);
        middlePanel.setSize(485,255);
        middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Email Body" ) );

        txt_message = new JTextArea ( 12, 50 );
        txt_message.setEditable ( true ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( txt_message );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll.setLocation(7, 15);
        scroll.setSize(470,230);
        
        //scroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        middlePanel.add ( scroll );
        
        
        /*******************control setting *******************/
        
        panel_control= new JPanel();
        panel_control.setLayout(null);
        panel_control.setSize(500,40);
        panel_control.setLocation(0,390);
        
        password = new JLabel("Password : ",JLabel.RIGHT);
        //header.setBackground(Color.GRAY);
        password.setForeground(Color.red);
        password.setSize(110, 25);
        password.setLocation(0, 0);
        password.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,14));
        
        
        txt_password = new JPasswordField();
        txt_password.setLocation(110, 0);
        txt_password.setSize(250,25);
        
        btn_send = new JButton("Send Email");
        btn_send.setSize(110,25);
        btn_send.setLocation(378,0);
        btn_send.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,14));
        btn_send.setForeground(Color.red);
        
        
        /*****************sending mail operation********************/
        
        btn_send.addActionListener
        (
            new ActionListener() 
            {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    sending_email();
                }
            }
        
        );
        
        
        panel_control.add(btn_send);
        panel_control.add(password);
        panel_control.add(txt_password);
        
        panel_mail.add(txt_from_address);
        panel_mail.add(from);
        panel_mail.add(txt_subject);
        panel_mail.add(subject);
        panel_mail.add(txt_to_address);
        panel_mail.add(to);
        panel_mail.add(middlePanel);
      
        
        
        this.add(panel_control);
        this .add(panel_mail);
        
        this.setVisible(true);
    }
    
    
    
    public void sending_email()
    {
        final String username = txt_from_address.getText();
        final String password = txt_password.getText();

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance
        (
            props,
            new javax.mail.Authenticator() 
            {
            @Override
                protected PasswordAuthentication getPasswordAuthentication() 
                {
                    return new PasswordAuthentication(username, password);
                }
            }
         );
        
        
        try 
        {
        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(txt_from_address.getText()));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(txt_to_address.getText()));
            message.setSubject(txt_subject.getText());
            message.setText(txt_message.getText());

            Transport.send(message);
            JOptionPane.showMessageDialog(null,"Delivery status okay" ,"Message Sent",JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("Done");

        } 
        catch (MessagingException e) 
        {
            JOptionPane.showMessageDialog(null,"Error","Can not send message" ,JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
}
