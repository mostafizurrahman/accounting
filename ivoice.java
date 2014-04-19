/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting_company;

/**
*
* @author Mony
*/
import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.io.FileOutputStream;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
 
 
import com.google.zxing.WriterException;
 


/**
*
* @author Msaveonjur
*/
public class invoice extends JFrame
{
    private JButton btn_save,btn_refresh,btn_addItem;
    private JTextField[] txt_address,txt_client_address,txt_item_list;
    private JPasswordField jpassword;
    private JComboBox combo;
    private JLabel[] lblcountry,lbl_item_list,lbl_invoice_com,lbl_address,lbl_client_address,jPasswordField;
    private JPanel TitlePanel,panel_address, panel_item_list,panel_control,panel_client_address;
    private String [] address;
    private File fileToSave;
    private JLabel header,copy_right,item_name_description,item_price,item_quantity,item_total_price,item_remove;
    private int number_of_items = 0,row_count,column_count;
    private int width = 100, height = 25, X_position = 5,Y_position = 15;
    private java.awt.Font Item_TableHeader;
    private java.awt.Font Table_content_font;
           
    private ArrayList<JLabel[]> array_list_items = new ArrayList<JLabel[]>();
    private ArrayList<JCheckBox> array_list_checkbox = new ArrayList<JCheckBox>();
    final JPanel panel_item_container = new JPanel();
    private JPanel panel_scroll = new JPanel();
    /****MENU****/
    private JTextArea textNote;
    
    private JScrollPane textPane;
    private GridBagConstraints constraints = new GridBagConstraints();
    JMenuBar menubar;
    JMenu Option,Help,Accounting;
    JMenuItem opt_invoice,opt_search,opt_mailing,opt_exit,hlp_about_us,hlp_about_operation,hlp_web_url,acc_import_excel,acc_expense,acc_due;
    
    Connection conn = null;
    
    @SuppressWarnings("empty-statement")
    public invoice()
    {
        super("Invoice Creator");
        try
        {

            this.setSize(610,560);
            this.setLayout(null);
            this.setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            
            /***************OPENT CONNECTION************/
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/master_db", "root", "kharap");
            Class.forName("com.mysql.jdbc.Driver");
            /***************Menu***************/

            menubar = new JMenuBar();
            java.awt.Font f = new java.awt.Font("Arial",Font.BOLD,14);



            Option = new JMenu("Options");
            Option.setFont(f);
            Option.setForeground(Color.darkGray);
            Option.setMnemonic(KeyEvent.VK_O);
            Option.addSeparator();
            Option.setLayout(null);
            menubar.add(Option);


        /**********option menu items ****************/
            try
            {
                UIManager.put("MenuItem.margin", new Insets(2, -17, 2, -12));
            }
            catch(Exception exe)
            {

            }
            ImageIcon exit_icon = new ImageIcon(getClass().getResource("exit.PNG"));

            opt_exit = new JMenuItem("",exit_icon);
            opt_exit.setMnemonic(KeyEvent.VK_E);
            opt_exit.setToolTipText("Exit application");
            opt_exit.setLayout(null);
            opt_exit.setSize(66,23);
            opt_exit.setLocation(0, 106);
            opt_exit.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    JOptionPane.showMessageDialog(null,"Good Bye!!! See You Later. :D","Exit Message", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(1);
                }
            });
            exit_icon = new ImageIcon(getClass().getResource("invoice.png"));
            opt_invoice = new JMenuItem("",exit_icon);
            opt_invoice.setToolTipText("Create Invoice");
            opt_invoice.setLayout(null);
            opt_invoice.setSize(66,23);
            opt_invoice.setLocation(0,80);
            opt_invoice.setMnemonic(KeyEvent.VK_I);
            opt_invoice.addActionListener(new ActionListener() 
            {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                   panel_item_container.setVisible(true);
                    panel_scroll.setVisible(true );
                    repaint();
                    JOptionPane.showMessageDialog(null,"This Menu Item is for Invoice generator. :D","Exit Message", JOptionPane.INFORMATION_MESSAGE);
                    /*** invoice generator will be here************/
                }
            });
            exit_icon = new ImageIcon(getClass().getResource("search.jpg"));
            opt_search = new JMenuItem("",exit_icon);
            opt_search.setToolTipText("Search For Information");
            opt_search.setLayout(null);
            opt_search.setSize(66,25);
            opt_search.setLocation(0, 52);
            opt_search.addActionListener(new ActionListener() 
            {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    JOptionPane.showMessageDialog(null,"This Menu Item is for search. :D","Exit Message", JOptionPane.INFORMATION_MESSAGE);
                    /*** invoice generator will be here************/
                }
            });
            exit_icon = new ImageIcon(getClass().getResource("email.jpg"));
            opt_mailing = new JMenuItem("",exit_icon);
            opt_mailing.setToolTipText("Mail Clients");
            opt_mailing.setLayout(null);
            opt_mailing.setSize(66,25);
            opt_mailing.setLocation(0, 25);
            opt_mailing.addActionListener(new ActionListener() 
            {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    JOptionPane.showMessageDialog(null,"This Menu Item is for mailing client. :D","Exit Message", JOptionPane.INFORMATION_MESSAGE);
                    /*** invoice generator will be here************/
                }
            });
            Option.add(opt_invoice);
            Option.add(opt_mailing);
            Option.add(opt_search);
            Option.add(opt_exit);
            Help = new JMenu("Help");
            Help.setFont(f);
            Help.setForeground(Color.darkGray);
            menubar.add(Help);

            Accounting = new JMenu("Accounting");
            Accounting.setFont(f);
            Accounting.setForeground(Color.darkGray);

            menubar.add(Accounting);
            this.setJMenuBar(menubar);

            this.setVisible(true);
            /*********************************/
            /***************END Menu***************/


            /* Item panel as Scrllable panel where layout manager is BridBangLayoutManger add controls like labels
            * buttons and other stuff dynamically
            */

            //header and title

            header = new JLabel(" S.ALI AUTOMOBILE ",JLabel.CENTER);
            header.setBackground(Color.GRAY);
            header.setForeground(Color.LIGHT_GRAY);
            header.setSize(476,35);
            header.setLocation(0, 0);
            header.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,36));
            //Font f = new Font("Arial",Font.BOLD,14);
            TitlePanel = new JPanel();
            TitlePanel.setLayout(null);
            TitlePanel.setBackground(Color.BLUE);
            TitlePanel.add(header);
            TitlePanel.setSize(476,35);
            TitlePanel.setLocation(105,0);
            this.add(TitlePanel);

            /******copy rights label*****/
            copy_right = new JLabel("<html>Copy Right© 2014<br>All RIghts Preserved</html>");
            copy_right.setHorizontalAlignment(JLabel.CENTER);
            copy_right.setFont(new java.awt.Font("Arial",Font.NORMAL,9));
            copy_right.setSize(90,80);
            copy_right.setLocation(5,123);
            this.add(copy_right);
            //buttons
            btn_save = new JButton("Generate Invoice");
            btn_save.setSize(width+15,height);
            btn_save.setLocation(475, 478);

            btn_refresh = new JButton("Refresh");
            btn_refresh.setSize(width+15,height);
            btn_refresh.setLocation(355, 478);
            this.add(btn_refresh);

            btn_addItem = new JButton("Add Item");

            btn_addItem.setSize(width+15,height);
            btn_addItem.setLocation(100, 197);
            
            /****************ADD ITEMS TO THE ITEM CONTAINER PANEL*********************/
            row_count = 1;
            
            
            
            
            try
            {
                btn_addItem.addActionListener(new ActionListener() 
                {

                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                      
                        load_data_to_panel();
                    }
                    
                });
            }
            catch(Exception exc)
            {
                
            }
            
            /*************************************/

            //3 panels
                //client addresss information
            panel_client_address = new JPanel();
            panel_client_address.setLayout(null);
            panel_client_address.setSize(250,200);
            panel_client_address.setBackground(Color.PINK);
            panel_client_address.setLocation(355,45);

            //Border border = panel_client_address.getBorder();
            //Border margin = new EmptyBorder(5,5,5,5) ;

            panel_client_address.setBorder(BorderFactory.createTitledBorder("Client address"));


            //panel_client_address.setBorder(,);



                // Company address infortmation
            panel_address = new JPanel();
            panel_address.setLayout(null);
            panel_address.setSize(250,200);
            panel_address.setLocation(100,45);
            panel_address.setBackground(Color.PINK);
            panel_address.setBorder(BorderFactory.createTitledBorder("Company Address"));
            
            
            
            
            /****** create item collection in the panel ************/
            
            
            //panel_item_container = new JPanel();
            panel_item_container.setBorder(BorderFactory.createTitledBorder("Item list for invoice"));
            GridBagLayout grid_bag = new GridBagLayout(); 
            panel_item_container.setLayout(grid_bag);
            panel_item_container.setAutoscrolls(true);
            panel_item_container.setSize(345,250);
            panel_item_container.setLocation(0,0);
            panel_item_container.setBackground(Color.WHITE);
                  
            
            /********************************* SETTING SCROLLING TO THE PANEL_ITEM_CONTAINER*************************************/
           
                
                panel_scroll.setLayout(null);//new BorderLayout());
              

                panel_scroll.setSize(panel_item_container.getWidth(), panel_item_container.getHeight());
                panel_scroll.setLocation(5,250);
                JScrollPane scroll = new JScrollPane(panel_item_container);
                scroll.setSize(new Dimension( panel_item_container.getWidth(), panel_item_container.getHeight()));
                panel_scroll.add(scroll);//,BorderLayout.WEST);
            
             /********************************* SETTING SCROLLING TO THE PANEL_ITEM_CONTAINER************************************/              
            
            
            Item_TableHeader = new java.awt.Font("Arial",java.awt.Font.BOLD+java.awt.Font.ITALIC,14);
         
            Table_content_font = new java.awt.Font("Arial",java.awt.Font.BOLD,10);
            
            
            
            item_name_description = new JLabel("Item Name/Descriptions");           
            item_name_description.setFont(Item_TableHeader);
            item_name_description.setBackground(new Color(100,0,0));
            item_name_description.setBorder(BorderFactory.createLineBorder(Color.RED));
            item_price = new JLabel("Price");
            item_price.setFont(Item_TableHeader);
            item_price.setBackground(Color.RED);
            item_price.setBorder(BorderFactory.createLineBorder(Color.RED));
            item_quantity = new JLabel("Quantity");
            item_quantity.setFont(Item_TableHeader);
            item_quantity.setBackground(new Color(100,0,0));
            item_quantity.setBorder(BorderFactory.createLineBorder(Color.RED));
            item_total_price = new JLabel("Total Price");
            item_total_price.setFont(Item_TableHeader);
            item_total_price.setBackground(new Color(100,0,0));
            item_total_price.setBorder(BorderFactory.createLineBorder(Color.RED));
            item_remove = new JLabel("Remove");
            item_remove.setFont(Item_TableHeader);
            item_remove.setBackground(new Color(100,0,0));
            item_remove.setBorder(BorderFactory.createLineBorder(Color.RED));
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            constraints.insets.set(5, 5, 5, 5);
           // constraints.gridwidth = 3;
            //constraints.gridheight = 0;
            constraints.gridx = 0;
            constraints.gridy = 0;
            

            
            //constraints.weightx = 100;
            //constraints.weighty = 100;
            //constraints.anchor = GridBagConstraints.EAST;
            grid_bag.setConstraints(item_name_description, constraints);
            panel_item_container.add(item_name_description);
            
            
           // constraints.insets.set(3, 3, 3,3);
            ///constraints.gridwidth = 1;
            //constraints.gridheight = 1;
            constraints.gridx = 1;
            constraints.gridy = 0;
            //constraints.weightx = 100;
            //constraints.weighty = 100;
            //constraints.anchor = GridBagConstraints.HORIZONTAL;
            grid_bag.setConstraints(item_price, constraints);
            panel_item_container.add(item_price);
            
            
            //constraints.insets.set(3, 3, 3, 3);
           // constraints.gridwidth = 0;
            //constraints.gridheight = 0;
            constraints.gridx = 2;
            constraints.gridy = 0;
            constraints.weightx = 100;
            //constraints.weighty = 100;
            //constraints.anchor = GridBagConstraints.HORIZONTAL;
            grid_bag.setConstraints(item_quantity, constraints);
            panel_item_container.add(item_quantity, constraints);
            
            //constraints.insets.set(3, 3, 3, 3);
            //constraints.gridwidth = 0;
           // constraints.gridheight = 0;
            constraints.gridx = 3;
            constraints.gridy = 0;
            //constraints.weightx = 100;
            //constraints.weighty = 100;
            //constraints.anchor = GridBagConstraints.EAST;
            panel_item_container.add(item_total_price, constraints);
            
             //constraints.gridwidth = 0;
           // constraints.gridheight = 0;
            constraints.gridx = 4;
            constraints.gridy = 0;
            //constraints.weightx = 100;
            //constraints.weighty = 100;
            //constraints.anchor = GridBagConstraints.EAST;
            panel_item_container.add(item_remove, constraints);
           // panel_item_container.repaint();
              
            
                //Item List for sell
            panel_item_list = new JPanel();
            panel_item_list.setLayout(null);
            panel_item_list.setSize(250,250);
            panel_item_list.setLocation(355, 250);
            panel_item_list.setBackground(Color.PINK);
            panel_item_list.add(btn_addItem);
            panel_item_list.setBorder(BorderFactory.createTitledBorder("Item Details"));
            //panel_item_list = new JPanel();

            panel_control = new JPanel();

            panel_control.setLayout(null);


            //creating company address
            address = new String[] {"House: ","Raod: ","Area: ","Thana: ","Disctrict: ","Country: "};
            lbl_address = new JLabel[address.length];
            //int width = 100, height = 25, X_position = 5,Y_position = 15;
            for(int i = 0; i<lbl_address.length; i++)
            {
                lbl_address[i] = new JLabel(address[i].toString());
                lbl_address[i].setHorizontalAlignment(JLabel.RIGHT);
                lbl_address[i].setSize(width - 5, height);
                lbl_address[i].setLocation(X_position, Y_position);
                Y_position += 30;
                panel_address.add(lbl_address[i]);
            }

            X_position = 100;
            Y_position = 15;
            address = new String[] {"39","14","Sector-12","Uttara","Dhaka","Bangladesh"};
            txt_address = new JTextField[address.length];

            for(int i = 0; i<txt_address.length; i++)
            {
                txt_address[i] = new JTextField(20);
                txt_address[i].setText(address[i]);
                txt_address[i].setSize(width + 30, height);
                txt_address[i].setLocation(X_position, Y_position);
                Y_position += 30;
                panel_address.add(txt_address[i]);
            }

            X_position = 5;
            Y_position = 15;
            //creating client address 
            address = new String[] {"House: ","Raod: ","Area: ","Thana: ","Disctrict: ","Country: "};
            lbl_client_address = new JLabel[address.length];
            //int width = 100, height = 25, X_position = 10,Y_position = 10;
            for(int i = 0; i<lbl_client_address.length; i++)
            {
                lbl_client_address[i] = new JLabel(address[i]);
                lbl_client_address[i].setHorizontalAlignment(JLabel.RIGHT);
                //lbl_client_address[i].setText();
                lbl_client_address[i].setSize(width - 5, height);
                lbl_client_address[i].setLocation(X_position, Y_position);
                Y_position += 30;
                panel_client_address.add(lbl_client_address[i]);
            }
            X_position = 100;
            Y_position = 15;
            txt_client_address = new JTextField[address.length];

            for(int i = 0; i<txt_client_address.length; i++)
            {
                //txt_address[i].setText(address[i]);
                txt_client_address[i] = new JTextField(20);
                txt_client_address[i].setSize(width + 30, height);
                txt_client_address[i].setLocation(X_position, Y_position);
                Y_position += 30;
                panel_client_address.add(txt_client_address[i]);
            }

            X_position = 5;
            Y_position = 15;
            try
            {
                
                
                JLabel jl = new JLabel("Item List: ");
             
                jl.setHorizontalAlignment(JLabel.RIGHT);
                jl.setSize(width - 5, height);
                jl.setLocation(X_position, Y_position);
                Y_position += 30;
                panel_item_list.add(jl);
                
                
                //creating Item list
                address = new String[] {"Item Name: ","Item Id: ","Item Price: ","Quantity: ","Manufacturer: "};
                lbl_item_list = new JLabel[address.length];
                //int width = 100, height = 25, X_position = 10,Y_position = 10;
                for(int i = 0; i<address.length; i++)
                {

                    lbl_item_list[i] = new JLabel(address[i].toString());
                    lbl_item_list[i].setHorizontalAlignment(JLabel.RIGHT);
                    lbl_item_list[i].setSize(width - 5, height);
                    lbl_item_list[i].setLocation(X_position, Y_position);
                    Y_position += 30;
                    panel_item_list.add(lbl_item_list[i]);
                }
                X_position = 100;
                Y_position = 45;
                
                combo = new JComboBox();
                combo.setSize(130,25);
                combo.setLocation(X_position, Y_position-30);
                
                
                
                
                
                try
                {
                    String sql="SELECT item_name, item_id FROM product_list;";
                   
                    PreparedStatement stmt = null;

                    stmt = (PreparedStatement) conn.prepareStatement(sql);
                    //stmt = (PreparedStatement) conn.createStatement();
                    
                    ResultSet item_resultSet= stmt.executeQuery();
                    while(item_resultSet.next())
                    {
                        combo.addItem(item_resultSet.getString(1)+" "+item_resultSet.getString(2));
                    }
                }
                catch(Exception ex)
                {
                    
                }

                
                txt_item_list = new JTextField[address.length ];             
                for(int i = 0; i<address.length ; i++)
                {
                    //txt_address[i].setText(address[i]);
                    txt_item_list[i] = new JTextField();
                    txt_item_list[i].setSize(width + 30,height);
                    txt_item_list[i].setLocation(X_position, Y_position);
                    Y_position += 30;
                    panel_item_list.add(txt_item_list[i]);
                }

                X_position = 10;
                Y_position = 10;
                
                
                combo.addActionListener(new ActionListener() 
                {

                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try
                        {
                            String sql="SELECT * FROM product_list WHERE item_id = ?;";
                   
                            PreparedStatement stmt = null;

                            stmt = (PreparedStatement) conn.prepareStatement(sql);
                            String[] splited = combo.getSelectedItem().toString().split(" ");
                            stmt.setString(1, splited[splited.length - 1]);
                            //stmt = (PreparedStatement) conn.createStatement();

                            ResultSet item_resultSet= stmt.executeQuery();
                            item_resultSet.next();
                            for(int i = 0 ; i < txt_item_list.length; i ++)
                            {
                                txt_item_list[i].setText(item_resultSet.getString(i+1));
                            }
                            
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                });
                        
                

            }
            catch(Exception exe)
            {
                JOptionPane.showMessageDialog(null, exe);
            }
            
            
            panel_item_list.add(combo);
            
            
            JLabel label = new JLabel();
            //  set icont to the form
            try
            {
                ImageIcon background = new ImageIcon(getClass().getResource("sali_1.gif"));

                this.setIconImage(background.getImage());
                label.setBounds(0, 0, 100, 100);
                label.setIcon(background);
            }
            catch (Exception ex)
            {

            }
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(120,120);
            panel.add(label);
            panel.setLocation(0, 0);
            btn_save.addActionListener(new ActionListener() 
            {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    try 
                    {
                        generate_invoice();
                        //throw new UnsupportedOperationException("Not supported yet.");
                    } 
                    catch (Exception ex) 
                    {
                        Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            });
            add(panel_address);
            add(btn_save);
            add(panel);
            add(panel_client_address);
            add(panel_item_list);
            add(panel_scroll);	
            
            /*
            document.add(new Paragraph("\n"+"AWT Image"));
            java.awt.Image awtImg = 
            java.awt.Toolkit.getDefaultToolkit()
            .createImage("square.jpg");
            com.lowagie.text.Image image2 = 
            com.lowagie.text.Image.getInstance(awtImg, null);
            document.add(image2);				
            document.newPage();

            // Code 3
            document.add(new Paragraph("Multipages tiff file"));
            RandomAccessFileOrArray ra = 
            new RandomAccessFileOrArray("multipage.tif");
            int pages = TiffImage.getNumberOfPages(ra);
            for(int i = 1; i <= pages; i++){
            document.add(TiffImage.getTiffImage(ra, i));
            }		
            document.newPage();

            // Code 4
            document.add(new Paragraph("Animated Gifs"));
            GifImage img = new GifImage("bee.gif");
            int frame_count = img.getFrameCount();
            for(int i = 1; i <= frame_count; i++){
            document.add(img.getImage(i));
            }*/
                panel_item_container.setVisible(false);
                panel_scroll.setVisible(false );
                this.setVisible(true);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"System Failure to initialize items.","ERROR MESSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
    }/**************** END OF THE CONSTRUCTOR **************************/
    

    public void load_data_to_panel()
    {
        int i = 0;
        JLabel [] tmp_label = new JLabel[txt_item_list.length + 1];

        for (; i < txt_item_list.length ; i++) 
        {
            if(txt_item_list[i].getText().equals(""))
            {
                break;
            }
            else 
            {

                tmp_label[i] = new JLabel();

                tmp_label[i].setText(txt_item_list[i].getText());
            }
        }
        if (i == txt_item_list.length)
        {




            column_count = 0;

            try
            {
                //constraints.anchor = GridBagConstraints.NORTHWEST;
                double d = Double.parseDouble(tmp_label[2].getText());
                double q = Double.parseDouble(tmp_label[3].getText());
                
                DecimalFormat df2 = new DecimalFormat("###.##");
                String s = "" + Double.valueOf(df2.format(d*q));
     

                //constraints.gridwidth = 0;
                // constraints.gridheight = 0;
                constraints.gridx = column_count++;
                constraints.gridy = row_count;
                //constraints.weightx = 100;
                constraints.weighty = 1.0;
                //constraints.anchor = GridBagConstraints.WEST;
                panel_item_container.add(tmp_label[0], constraints);



                //constraints.gridwidth = 0;
                // constraints.gridheight = 0;
                constraints.gridx = column_count++;
                constraints.gridy = row_count;
                //constraints.weightx = 100;
                constraints.weighty = 1.0;
                //constraints.anchor = GridBagConstraints.EAST;
                panel_item_container.add(tmp_label[2], constraints);



                //constraints.gridwidth = 0;
                // constraints.gridheight = 0;
                constraints.gridx = column_count++;
                constraints.gridy = row_count;
                //constraints.weightx = 100;
                constraints.weighty = 1.0;
                //constraints.anchor = GridBagConstraints.CENTER;
                panel_item_container.add(tmp_label[3], constraints);



                //constraints.gridwidth = 0;
                // constraints.gridheight = 0;
                constraints.gridx = column_count++;
                constraints.gridy = row_count;
                //constraints.weightx = 100;
                constraints.weighty = 1.0;
                try
                {
                    tmp_label[5] = new JLabel(s);
                    //constraints.anchor = GridBagConstraints.EAST;                        
                    panel_item_container.add(tmp_label[5], constraints);
                }
                catch(Exception ex )
                {
                    JOptionPane.showMessageDialog(null, "ERROR IN ALLOCATION");
                }


                JCheckBox checkbox = new JCheckBox("CLICK HERE TO DELETE ROW: "+row_count);
                checkbox.setSelected(true);
                checkbox.addItemListener(new checkboxAction());
                //checkbox.setSelected(true);
                array_list_checkbox.add(checkbox);


                constraints.gridx = column_count;
                constraints.gridy = row_count;
                //constraints.weightx = 100;
                constraints.weighty = 1.0;
                //constraints.anchor = GridBagConstraints.EAST;
                panel_item_container.add(checkbox, constraints);


                array_list_items.add(tmp_label);
            }
            catch(Exception exe)
            {
                JOptionPane.showConfirmDialog(null, "Check quantity and price");
            }


            row_count++;
            panel_item_container.revalidate();
            panel_item_container.repaint();

        }
        else
        {
            JOptionPane.showMessageDialog(null," Blank Field Found","Insertion Aborted", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**************** action listener class for remove data from UI **************************/
    private class checkboxAction implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if(e.getStateChange() != ItemEvent.SELECTED)
            {
                for (JCheckBox jc : array_list_checkbox) 
                {
                    
                    if(e.getSource() == jc)
                    {
                        int index = array_list_checkbox.indexOf(jc);
                  
                        
                        for (int i=array_list_checkbox.size()-1; i> -1; i--) 
                        {
                            if (array_list_checkbox.get(i).equals(jc) ) 
                            {
                                array_list_checkbox.remove(i);
                            }
                        }

                        for(JLabel []c :array_list_items)
                        {
                            if(array_list_items.indexOf(c) == index)
                            {
                                for(JLabel l : c)
                                {

                                    panel_item_container.remove(l);

                                }

                                for (int i=array_list_items.size()-1; i> -1; i--) 
                                {
                                    if (array_list_items.get(i).equals(c) ) 
                                    {
                                        array_list_items.remove(i);
                                    }
                                }
                                break;
                            }                 
                        }
                        panel_item_container.remove(jc);
                        break;
                    }
                }
                panel_item_container.revalidate();
                panel_item_container.repaint();
            }

        
        }
    }
    
 /**************** END of action listener class **************************/

    public void generate_invoice() throws DocumentException, BadElementException, MalformedURLException, IOException, WriterException

    {
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");   

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION)
        {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        
            try 
            {
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                
                document.setMarginMirroring(true);
                document.addCreator("sali_automotive");
                document.addCreationDate();
                document.addProducer();
                PdfWriter writer = PdfWriter.getInstance(document, 
                new FileOutputStream("moni.pdf"));

                document.open();
                com.lowagie.text.Image image;
                try 
                {
                    image = com.lowagie.text.Image.getInstance(getClass().getResource("asli_logo.jpeg"));
                    image.setAlignment(Element.ALIGN_CENTER);
                    document.add(image);

                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Code 1

                /**********************************************/
                Font font = FontFactory.getFont("angelina");
                //font.setColor(Color.BLUE);
                //font.setSize(TOP_ALIGNMENT);
                //BaseFont bf = font.getBaseFont(); 
               
               // Font font = new Font(BaseFont.createFont("Arial", BaseFont.IDENTITY_H,  BaseFont.EMBEDDED), 15, Font.BOLD, new Color(200, 10, 10));
                font.setColor(Color.WHITE);
                font.setSize(32);

                Paragraph para = new Paragraph();
                para.setSpacingAfter(10);
                para.setSpacingBefore(10);
                para.setAlignment(Element.ALIGN_CENTER);
                para.setIndentationLeft(0);
                para.setIndentationRight(0);


                para.setAlignment(Element.ALIGN_CENTER);

                //para.setFont(font);
                Chunk c = new Chunk(" S.ALI AUTOMOTIVE ", font);
                
                c.setBackground(Color.BLUE);
                para.add(c);
                
                
                
                String myString = "000888213213SA23";
    
                PdfContentByte cb = writer.getDirectContent();
                Barcode128 code128 = new Barcode128();
                code128.setCode(myString.trim());
              
                code128.setCodeType(Barcode128.CODE128);
                Image code128Image = code128.createImageWithBarcode(cb, null, null);
                
                code128Image.setRotationDegrees(270.0f);
                code128Image.setAbsolutePosition(5,5);
                code128Image.scalePercent(125);
                document.add(code128Image);
                
                
                
                
                
                /*
                
                
                
                
                final int s = 100;
                int r = 1;

                Charset charset = Charset.forName( "UTF-8" );
                CharsetEncoder encoder = charset.newEncoder();
                byte[] b = null;
                try {
                // Convert a string to UTF-8 bytes in a ByteBuffer
                    java.nio.ByteBuffer bbuf = encoder.encode( CharBuffer.wrap("http://www.saliautomotive.com\nProduct:0912039121112") );/*
                "1éöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùò1" +
                "2éöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùò2" +
                "3éöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùò3" +
                "4éöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùò4" +
                "5éöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùò5" +
                "6éöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùòïëéöàäèüùò6" ) );*/
              /*  b = bbuf.array();
                } catch ( CharacterCodingException e ) {
                System.out.println( e.getMessage() );
                }

                String content = new String( b, "UTF-8" );
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>( 2 );
                hints.put( EncodeHintType.CHARACTER_SET, "UTF-8" );
                ByteMatrix qrCode = qrCodeWriter.encode( content, BarcodeFormat.QR_CODE, s, s, hints );
                 PdfContentByte contentByte = writer.getDirectContent();
                contentByte.setColorFill( Color.BLACK );
                byte[][] array = qrCode.getArray();
                  boolean d = false;
                  for ( int x = 0; x < qrCode.getWidth(); x += r ) 
                  {
                     for ( int y = 0; y < qrCode.getHeight(); y += r ) 
                     {
                         
                         int grayValue = array[y][x] & 0xff; 
                       //  contentByte.setRGBColorFill(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                        //   image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                        if ( grayValue != 0) 
                        {
                           contentByte.rectangle( x, s - y, r, r );
                           contentByte.fill();
                           contentByte.stroke();
                        }
                     }
                  }

            
             
                /*
                
                
               
                String qrCodeText = "http://www.saliautomotive.com\nProduct:0912039121112";
                ByteArrayOutputStream out = QRCode.class. from(qrCodeText).to(null).withSize(300, 300).
                Hashtable hintMap = new Hashtable();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                ByteMatrix matrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 250, 250, hintMap);
                // Make the BufferedImage that are to hold the QRCode
                int matrixWidth = matrix.getWidth();
                BufferedImage bimage = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
                int width = matrix.getWidth(); 
                int height = matrix.getHeight(); 

                byte[][] array = matrix.getArray();

                //create buffered image to draw to
//                Graphics2D graphics = (Graphics2D) bimage.getGraphics();
//                graphics.setColor(Color.WHITE);
//                graphics.fillRect(0, 0, matrixWidth, matrixWidth);
//                // Paint and save the image using the ByteMatrix
//                graphics.setColor(Color.BLACK);

                //iterate through the matrix and draw the pixels to the image
          /*      for (int y = 0; y < height; y++)
                { 
                    for (int x = 0; x < width; x++) 
                    { 
                        int grayValue = array[y][x] & 0xff; 
                        bimage.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                    }
                }
                com.lowagie.text.
              com.itextpdf.text.pdf.BarcodeQRCode
                java.awt.Image i  = bimage;
                image =  i; bimage.getGraphics().drawBytes(bimage.getData().getPixels(250, 250, 250, 250, array), 250, 250, 250, 250);
                //image = bimage.getGraphics().drawBytes(bimage, height, width, WIDTH, WIDTH)
//                bimage.createGraphics();
//
//                Graphics2D graphics = (Graphics2D) bimage.getGraphics();
//                graphics.setColor(Color.WHITE);
//                graphics.fillRect(0, 0, matrixWidth, matrixWidth);
//                // Paint and save the image using the ByteMatrix
//                graphics.setColor(Color.BLACK);

//                for (int i = 0; i < matrixWidth; i++) {
//                    for (int j = 0; j < matrixWidth; j++) {
//                        if (byteMatrix.get(i, j)) {
//                            graphics.fillRect(i, j, 1, 1);
//                        }
//                    }
//                }
//                ImageIO.write(image, fileType, qrFile);
                
                
                

//                code128.setCodeType(Barcode128.CODE128_UCC);
//                code128Image = code128.createImageWithBarcode(cb, null, null);
//                code128Image.setAbsolutePosition(10,650);
//                code128Image.scalePercent(125);
//                doc.add(code128Image);
//
//                BarcodeEAN codeEAN = new BarcodeEAN();
//                codeEAN.setCode(myString.trim());
//                codeEAN.setCodeType(BarcodeEAN.EAN13);
//                Image codeEANImage = code128.createImageWithBarcode(cb, null, null);
//                codeEANImage.setAbsolutePosition(10,600);
//                codeEANImage.scalePercent(125);
//                doc.add(codeEANImage);
                
               /*BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);
                Image qrcodeImage = qrcode.getImage();
                qrcodeImage.setAbsolutePosition(10,500);
                qrcodeImage.scalePercent(200);
                doc.add(qrcodeImage);*/
                
                
                
                
                
                
                document.add(para);
                
                
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100f);
                StringBuilder srB = new StringBuilder();
                for (int i = 0; i<lbl_address.length;i++ )
                {
                    try
                    {
                        srB.append(lbl_address[i].getText()).append(txt_address[i].getText()).append("\n");
                    }
                    catch(Exception ex)
                    {

                    }
                }
                
                
                PdfPCell cellOne = new PdfPCell(new Phrase(srB.toString()+"Phone: +880167876752\nEmail: s.aliautomotive@gmail.com\nweb: http://www.saliautomotive.com"));
                srB = new StringBuilder();
                for (int i = 0; i<lbl_client_address.length;i++ )
                {
                    try
                    {
                        srB.append(lbl_client_address[i].getText()).append(txt_client_address[i].getText()).append("\n");
                    }
                    catch(Exception ex)
                    {

                    }
                }
                Font p = FontFactory.getFont( FontFactory.HELVETICA, 10, Font.BOLDITALIC);
                p.setColor(new Color(230,0,0));
                Paragraph cell_header =  new Paragraph(new Phrase("INVOICE ISSUED TO- ",p));
                p = FontFactory.getFont( FontFactory.HELVETICA, 10, Font.BOLDITALIC);
                p.setColor(new Color(0,230,0));
                Paragraph cell_content =  new Paragraph(new Phrase(srB.toString()));
                
                //cell_header.setBackgroundColor();
                //cell_content.setBorder(Rectangle.NO_BORDER);
                //cell_content.setBackgroundColor(new Color(230,255,230));
                //cell_content.setBorder(Rectangle.NO_BORDER);
                PdfPCell cellTwo = new PdfPCell();
                cellTwo.addElement(cell_header);
                cellTwo.addElement(cell_content);
                cellTwo.setBackgroundColor(new  Color(255,245,245));
                cellOne.setBorder(Rectangle.NO_BORDER);
                //cellOne.setBackgroundColor(new Color(250,250,250));

                cellOne.setHorizontalAlignment(Element.ALIGN_LEFT);

                cellTwo.setBorder(Rectangle.BOX);
                //cellTwo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                //cellTwo.setBackgroundColor(new Color(250,250,100));
                table.addCell(cellOne);
                table.addCell(cellTwo);
            /******************************************table width******************************************/
                
                //table.setTotalWidth(20+document.getPageSize().getLeft() + document.getPageSize().getRight());
                //para.add(table);
                table.setSpacingAfter(5f);
                document.add(table);


                table = new PdfPTable(9);
                table.setWidthPercentage(100f);
            
                PdfPCell cell1 = new PdfPCell(new Phrase("Item Name & Identification"));
              
                cell1.setColspan(4);
                PdfPCell cell2 = new PdfPCell(new Phrase("Price"));
                
                cell2.setColspan(2);
                PdfPCell cell3 = new PdfPCell(new Phrase("Quantity"));
               
                cell3.setColspan(1);
                PdfPCell cell4 = new PdfPCell(new Phrase("Sum of Price"));
                cell4.setColspan(2);
                
            
                
              
                cell1.setBackgroundColor(new Color(250,230,230));
        
         
                cell2.setBackgroundColor(new Color(250,230,230));
           
               
                cell3.setBackgroundColor(new Color(250,230,230));
        
 
                cell4.setBackgroundColor(new Color(250,230,230));
                
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                double total_price = 0.0;
                for(JLabel[] lbl_tmp : array_list_items)
                {
                    cell1 = new PdfPCell(new Phrase(lbl_tmp[0].getText() + " ID: " + lbl_tmp[1].getText()) );
                    cell1.setBackgroundColor(null);
                    cell1.setColspan(4);
                    cell2 = new PdfPCell(new Phrase(lbl_tmp[2].getText()));
                    cell2.setBackgroundColor(null);
                    cell2.setColspan(2);
                    cell3 = new PdfPCell(new Phrase(lbl_tmp[3].getText()));
                    cell3.setBackgroundColor(null);
                    cell3.setColspan(1);
                    cell4 = new PdfPCell(new Phrase(lbl_tmp[5].getText()));
                    cell4.setBackgroundColor(null);
                    cell4.setColspan(2);
                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    total_price += Double.parseDouble(lbl_tmp[5].getText());
                }
                cell1 = new PdfPCell(new Phrase("Total"));
                //cell1.setBackgroundColor(new Color(250, 230, 230));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell1.setColspan(7);
         
               
                cell2 = new PdfPCell(new Phrase("" + total_price));
                cell2.setBackgroundColor(new Color(250, 230, 230));
                cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                cell2.setColspan(2);
                table.addCell(cell1);
                table.addCell(cell2);
                table.setSpacingAfter(5f);
                table.setTotalWidth(20+document.getPageSize().getLeft() + document.getPageSize().getRight());
                document.add(table);
               
                
                
                /*************************total price in words *************************************/
                
                NumToWords nw = new NumToWords();
                String total_price_in_words = nw.convert(total_price);
                document.add(new Paragraph("In word: " + total_price_in_words + " BDT only. \n\n"));
                
                /***************************FOOTER CREATION START*******************************/
                
                Font pf = FontFactory.getFont( FontFactory.HELVETICA, 10, Font.BOLDITALIC);
                pf.setColor(Color.RED);
                Paragraph ph = new Paragraph(new Phrase("Copy Right© 2014, All RIghts Preserved", pf));
                PdfPCell cell = new PdfPCell(ph);
                cell.setBorder(Rectangle.TOP);
                cell.setBorderColor( new Color(44, 67, 144));
                cell.setBorderWidth( 2f);

                 
                PdfPTable table1 = new PdfPTable(1); 
                
                table1.addCell(cell);
                table.setFooterRows(1);//SelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table1.completeRow();
                //table1.setHorizontalAlignment ( Element.ALIGN_LEFT);
                //table1.setWidthPercentage( 100f);
                Rectangle page = document.getPageSize();
                table1.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
                table1.writeSelectedRows(0, -1, document.leftMargin(),document.bottomMargin(),writer.getDirectContentUnder());
                
                //document.add(table1);
                
                /*
                 Rectangle page = document.getPageSize();
        PdfPTable foot = new PdfPTable(1);
        PdfPCell footCell = new PdfPCell(new Phrase(
                "OUR DOCUMENT'S LEGEND", FontFactory.getFont(
                        FontFactory.HELVETICA, 8, Font.BOLDITALIC)));
        //footCell.setColspan(2);
        foot.addCell(footCell);
        footCell = new PdfPCell(new Phrase(""));
        footCell.setColspan(2);
        foot.addCell(footCell);
        footCell.Border = Rectangle.BOTTOM_BORDER;
        footCell.BorderColor = new BaseColor(44, 67, 144);
        footCell.BorderWidth = 2f;
        // 1st row

        foot.addCell(footCell);

        foot.setTotalWidth(page.getWidth() - document.leftMargin()
                - document.rightMargin());
        foot.writeSelectedRows(0, -1, document.leftMargin(),
                document.bottomMargin(), writer.getDirectContent());

  

*/
                
                
                
                
                
                
                
                /***************************FOOTER CREATION START*******************************/
                
                
                
                


        /*******************Design of INVOICE *******************************/
                document.close();
                if(fileToSave != null)
                {
                    try 
                    {
                        PdfReader Read_PDF_To_Watermark = new PdfReader("moni.pdf");
                        int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();

                        PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, new FileOutputStream(fileToSave.getAbsolutePath()));
                        int i = 0;
                        Image watermark_image = Image.getInstance(getClass().getResource("water.png"));
                        watermark_image.setAbsolutePosition(75, 75);
                        PdfContentByte add_watermark;            
                        while (i < number_of_pages) 
                        {
                            i++;
                            add_watermark = stamp.getUnderContent(i);
                            add_watermark.addImage(watermark_image);
                        }
                        stamp.close();
                    }
                    catch (Exception i1) 
                    {
                        i1.printStackTrace();
                    }
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public class item_remove_checked_unchecked
    {
        public int index;
        public ArrayList<JCheckBox> list_checkbox;
        
    }
    
    /********************************** converting currency to words **********************************/
    
    
    public class NumToWords 
    {
        String string;
        String st1[] = { "zero", "one", "two", "three", "four", "five", "six", "seven",
                        "eight", "nine", };
        String st2[] = { "hundred", "thousand", "lakh", "crore" };
        String st3[] = { "ten", "eleven", "twelve", "thirteen", "fourteen",
                        "fifteen", "sixteen", "seventeen", "eighteen", "ninteen", };
        String st4[] = { "twenty", "thirty", "fourty", "fifty", "sixty", "seventy",
                        "eighty", "ninty" };

        public String convert(double number_double) 
        {
                int n = 1;
                int number = (int)number_double;
                double d = number_double - number;
                int prec = (int)(d*100);
                
                String precision = " point ";
                int p;
                p = prec/10;
                if(p == 1)
                {
                    precision += "one";
                }
                else if( p == 2)
                {
                    precision += "two";
                }
                else if( p == 3)
                {
                    precision += "three";
                }
                else if( p == 4)
                {
                    precision += "four";
                }
                else if( p == 5)
                {
                    precision += "five";
                }
                else if( p == 6)
                {
                    precision += "six";
                }
                else if( p == 7)
                {
                    precision += "seven";
                }
                else if( p == 8)
                {
                    precision += "eight";
                }
                else if( p == 9)
                {
                    precision += "nine";
                }
                
                p = prec%10;
                if(p%10 != 0)
                {
                    if(p == 1)
                    {
                        precision +=" one";
                    }
                    else if( p == 2)
                    {
                        precision +=" two";
                    }
                    else if( p == 3)
                    {
                        precision +=" three";
                    }
                    else if( p == 4)
                    {
                        precision +=" four";
                    }
                    else if( p == 5)
                    {
                        precision +=" five";
                    }
                    else if( p == 6)
                    {
                        precision +=" six";
                    }
                    else if( p == 7)
                    {
                        precision +=" seven";
                    }
                    else if( p == 8)
                    {
                        precision +=" eight";
                    }
                    else if( p == 9)
                    {
                        precision +=" nine";
                    }
                }
                else
                {
                    precision +=" zero";
                }
                 
                
                int word;
                string = "";
                while (number != 0) {
                        switch (n) {
                        case 1:
                                word = number % 100;
                                pass(word);
                                if (number > 100 && number % 100 != 0) {
                                        show("and ");
                                }
                                number /= 100;
                                break;

                        case 2:
                                word = number % 10;
                                if (word != 0) {
                                        show(" ");
                                        show(st2[0]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 10;
                                break;

                        case 3:
                                word = number % 100;
                                if (word != 0) 
                                {
                                        show(" ");
                                        show(st2[1]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 100;
                                break;

                        case 4:
                                word = number % 100;
                                if (word != 0) 
                                {
                                        show(" ");
                                        show(st2[2]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 100;
                                break;

                        case 5:
                                word = number % 100;
                                if (word != 0) 
                                {
                                        show(" ");
                                        show(st2[3]);
                                        show(" ");
                                        pass(word);
                                }
                                number /= 100;
                                break;

                        }
                        n++;
                }
                return string + precision;
        }

        public void pass(int number) 
        {
                int word, q;
                if (number < 10) 
                {
                        show(st1[number]);
                }
                if (number > 9 && number < 20) 
                {
                        show(st3[number - 10]);
                }
                if (number > 19) 
                {
                        word = number % 10;
                        if (word == 0) 
                        {
                                q = number / 10;
                                show(st4[q - 2]);
                        } 
                        else 
                        {
                                q = number / 10;
                                show(st1[word]);
                                show(" ");
                                show(st4[q - 2]);
                        }
                }
        }

        public void show(String s) 
        {
                String st;
                st = string;
                string = s;
                string += st;
        }


    }
    
}
