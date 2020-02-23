package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class MainServer extends javax.swing.JFrame {

    public MainServer() {
        initComponents();
        try
        {
            s=new ServerSocket(1565);
            slist=new ArrayList<>();
            nlist=new ArrayList<>();
            h=new HandleClient();
            h.start();
 
        }catch(Exception e){}
    }

    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        namelist = new javax.swing.JTable();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        txtcmd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cid = new javax.swing.JTextField();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtmsg = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        mid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CHAT Server");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        namelist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        namelist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clients"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        namelist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                namelistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(namelist);
        if (namelist.getColumnModel().getColumnCount() > 0) {
            namelist.getColumnModel().getColumn(0).setResizable(false);
        }

        jInternalFrame1.setTitle("Sent Command To Client");
        jInternalFrame1.setVisible(true);

        txtcmd.setBackground(new java.awt.Color(255, 255, 204));

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Send To ");

        cid.setEditable(false);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(81, 81, 81)
                        .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(txtcmd))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(txtcmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jInternalFrame2.setTitle("Message Client");
        jInternalFrame2.setVisible(true);

        txtmsg.setBackground(new java.awt.Color(255, 255, 204));
        txtmsg.setColumns(20);
        txtmsg.setRows(5);
        jScrollPane2.setViewportView(txtmsg);

        jLabel2.setText("Send To ");

        mid.setEditable(false);

        jButton2.setText("Send");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("All");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jRadioButton1))
                .addGap(22, 22, 22))
        );

        display.setEditable(false);
        display.setBackground(new java.awt.Color(255, 255, 204));
        display.setColumns(20);
        display.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        display.setRows(5);
        jScrollPane3.setViewportView(display);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jInternalFrame1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        pack();
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        try {
            
            JOptionPane.showMessageDialog(null,"Server is Closing");
              Socket client_close;
              PrintWriter end;
              for(int x=0;x<slist.size();x++)
              {
                try{
                    client_close=slist.get(x);
                    end=new PrintWriter(client_close.getOutputStream(),true);
                    end.println("f73d5eab4fa29ffd6014aac366cc48de");
                }catch(Exception e){}
        }
           
            s.close();
            System.exit(0);
            
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void namelistMouseClicked(java.awt.event.MouseEvent evt) {
      String data=String.valueOf(((DefaultTableModel)namelist.getModel()).getValueAt(namelist.getSelectedRow(), 0));
      mid.setText(data.substring(data.indexOf("[")+1, data.indexOf("]")));
        cid.setText(data.substring(data.indexOf("[")+1, data.indexOf("]")));
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if(!txtcmd.getText().equals("") && !cid.getText().equals(""))
        {
            Socket cl=slist.get(Integer.parseInt(cid.getText()));
            try
            {
                out=new PrintWriter(cl.getOutputStream(),true);
                out.println("cmd:"+txtcmd.getText());
            }catch(Exception e){}
        }
        txtcmd.setText("");
        cid.setText("");
        txtcmd.requestFocus();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
         if(!txtmsg.getText().equals("") )
        {
            if(a){               
                String contents=txtmsg.getText();
                Socket sock;
                PrintWriter out;
                for(int x=0;x<slist.size();x++)
               {
                try{
                sock=slist.get(x);
                out=new PrintWriter(sock.getOutputStream(),true);
                out.println("[ADMIN] : "+contents);
                }catch(Exception e){}
               }
            }
            else
                if(!mid.getText().equals("")){
           {
            Socket cl=slist.get(Integer.parseInt(mid.getText()));
            try
            {
                out=new PrintWriter(cl.getOutputStream(),true);
                String contents=txtmsg.getText();
                contents.replace("\n", "~");
                contents=contents+"\n";
                out.println("msg:"+txtmsg.getText());
            }catch(Exception e){}
        }
         txtmsg.setText("");
        mid.setText("");
        txtmsg.requestFocus();
            }
            
    }
         txtmsg.setText("");
        mid.setText("");
        txtmsg.requestFocus();
    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if(jRadioButton1.isSelected()){
            a=true;
        }
        else{
            a=false;
        }
        
    }

    
  public void loadList()
  {
      while(namelist.getRowCount()>0)
      {
          ((DefaultTableModel)namelist.getModel()).removeRow(0);
      }
      int row=0;
      for(int x=0;x<nlist.size();x++)
      {
          if(!nlist.get(x).equals(""))
          {
              ((DefaultTableModel)namelist.getModel()).addRow(new Object[]{});
              ((DefaultTableModel)namelist.getModel()).setValueAt(nlist.get(x), row, 0);
               row++;
          }
      }
  }
  private ServerSocket s;
  private Socket c;
  private String name;
  private ArrayList<Socket> slist;
  private ArrayList<String> nlist;
  private BufferedReader brc;
  private PrintWriter out;
  private HandleClient h;
  private boolean a; 
  
  
   
    private javax.swing.JTextField cid;
    private javax.swing.JTextArea display;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mid;
    private javax.swing.JTable namelist;
    private javax.swing.JTextField txtcmd;
    private javax.swing.JTextArea txtmsg;
   
class HandleClient extends Thread
{
    private int tid;
    public void SendToAllClients(String message)
    {
        Socket so;
        PrintWriter out;
        for(int x=0;x<slist.size();x++)
        {
            try{
                so=slist.get(x);
                out=new PrintWriter(so.getOutputStream(),true);
                out.println(message);
            }catch(Exception e){}
        }
    }
    class ProcessClient extends Thread
    {
        private int id;
        private String name;
        private Socket c;
        private BufferedReader brc;
        public ProcessClient(int id,String name,Socket c)
        {
            this.id=id;
            this.name=name;
            this.c=c;
        }
        public void run()
        {
            String msg;
            try{
            brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
           loadList();
                SendToAllClients(name+" joined Chat");
            while((msg=brc.readLine())!=null)
            {
              display.append("["+name+"] : "+msg+"\n");
                SendToAllClients("["+name+"] : "+msg);
            }
                SendToAllClients(name+ " Left Chat");
            nlist.set(id, "");
            loadList();
           }catch(Exception e){}
        }
    }
    public void run()
    {
        while(true)
        {
            try {
                
                c=s.accept();
                brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
                name=brc.readLine();
                slist.add(c);
                nlist.add(name+"["+tid+"]");
                ProcessClient t=new ProcessClient(tid, name, c);
                t.start();
                tid++;
                
            } catch (IOException ex) {
                Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
}
