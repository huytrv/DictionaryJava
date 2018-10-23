package Dictionary;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.sql.*;

public class DictionaryApplication extends DictionaryManagement {
	private JFrame frame;
	private JButton jButton1, jButton2, jButton3, jButton4, jButton5;
    private JTextArea jTextArea;
    private JLabel jLabel2, jLabel3;
    private JList<String> jList;
    private JPanel jPanel1, jPanel2, jPanel3, jPanel4;
    private JScrollPane jScrollPane1, jScrollPane2;
    protected JTextField jTextField1, jTextField2;
    private ArrayList<String> strings = new ArrayList<String>();
    private String text;
    private String word;
    
    public DictionaryApplication() {
    	DictionaryManagement dic = new DictionaryManagement();
    	dic.insertFromFile();
        runApplication();
    }
                      
    private void runApplication() {
    	frame = new JFrame();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jPanel2 = new JPanel();
        jButton1 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jPanel3 = new JPanel();
        jTextField1 = new JTextField();
        jButton2 = new JButton();
        jScrollPane1 = new JScrollPane();
        jList = new JList<>();
        jPanel4 = new JPanel();
        jScrollPane2 = new JScrollPane();
        jTextArea = new JTextArea();
        jButton5 = new JButton();
        jTextField2 = new JTextField();
        jLabel3 = new JLabel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon(getClass().getResource("/Dictionary/images/icon.png"));
        frame.setIconImage(img.getImage());
        frame.setTitle("Dictionary PRO");

        jPanel1.setBackground(new Color(58, 58, 255));

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/Dictionary/images/dic.png")));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
            .addGap(0, 216, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new Color(100, 100, 255));
        JTextField word_target = new JTextField();
        JTextField word_explain = new JTextField();
        Object[] inpWord = {
            "Word target:", word_target,
            "Word explain:", word_explain
        };
        jButton1.setIcon(new ImageIcon(getClass().getResource("/Dictionary/images/add.png")));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//.showConfirmDialog(null, addWord, "Add new word", JOptionPane.OK_CANCEL_OPTION);
            	int option = JOptionPane.showConfirmDialog(null, inpWord, "Add new word", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
            	if (option == JOptionPane.OK_OPTION) {
            		int buf = 0;
            		String target = word_target.getText().toLowerCase();
            		String explain = word_explain.getText().toLowerCase();
            		for (int i=0; i<Word.size(); i++) {
        	    		if (Word.get(i).getWord_target() == target) buf ++;
        	    	} 
        	    	if (buf == 0) {
            			Dictionary d = new Dictionary(target, explain);
            	    	Word.add(d);	
            	    	DictionaryManagement dic = new DictionaryManagement(); 	
            	    	dic.sortDictionary();
            	    	dic.dictionaryExportToFile();
        	    	}
            	} 
            	word_target.setText(null);
            	word_explain.setText(null);
            }
        });

        jButton3.setIcon(new ImageIcon(getClass().getResource("/Dictionary/images/edit.png")));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	int option = JOptionPane.showConfirmDialog(null, inpWord, "Edit the word", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
                	if (option == JOptionPane.OK_OPTION) {
                		String target = word_target.getText().toLowerCase();
                		String explain = word_explain.getText().toLowerCase();
                		for (int i=0; i<Word.size(); i++) {
                    		if (target.equals(Word.get(i).getWord_target())) {
                    			Dictionary d = new Dictionary(target, explain);
                    			Word.set(i, d);
                    		}
                    		else if (target.equals(Word.get(i).getWord_explain())) {
                    			Dictionary d = new Dictionary(explain, target);
                    			Word.set(i, d);
                    		}	
                    	}	
                		DictionaryManagement dic = new DictionaryManagement(); 	
            	    	dic.sortDictionary();
            	    	dic.dictionaryExportToFile();
                	}
                	word_target.setText(null);
                	word_explain.setText(null);
            	}
        });
        Object[] reWord = {"Word target:", word_target};
        jButton4.setIcon(new ImageIcon(getClass().getResource("/Dictionary/images/remove.png")));
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                	int option = JOptionPane.showConfirmDialog(null, reWord, "Remove the word", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                	if (option == JOptionPane.OK_OPTION) {
                		String target = word_target.getText().toLowerCase();	
                		for (int i=0; i<Word.size(); i++) {
                    		if (target.equals(Word.get(i).getWord_target())) {
                    			Word.remove(i);
                    		}
                    	}
                		DictionaryManagement dic = new DictionaryManagement(); 	
            	    	dic.sortDictionary();
            	    	dic.dictionaryExportToFile();
                	}
                	word_target.setText(null);
                	word_explain.setText(null);
            	}
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(24, 24, 24)
            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton3)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton4)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(jButton1)
            .addComponent(jButton3)
            .addComponent(jButton4)))
        );

        jButton2.setText("Translate");
            
        jTextField1.setFont(new Font("Times New Roman", 0, 15)); 
 		jTextField1.addKeyListener(new KeyAdapter() {
 			public void keyReleased(KeyEvent e) {
			  int dem = 0;  
		      jList.setFont(new Font("Times New Roman", 0, 14));
		     
		        strings.clear();
				jList.setModel(new AbstractListModel<String>() {
					private static final long serialVersionUID = 1L;
					public int getSize() { return strings.size(); }
		            public String getElementAt(int i) { return strings.get(i); }
		        });
		      JTextField textField = (JTextField) e.getSource();
		      text = textField.getText();	
		      text = text.trim();
		      text = text.toLowerCase();
		      
		      jTextField2.setText(text);		      
		      for (int i=0; i<Word.size(); i++) {
		    	  if (text.equals(Word.get(i).getWord_target())) {
		    		  jTextField2.setText(Word.get(i).getWord_explain());	    		 
		    	  }      
		    	  for (int j=0; j<text.length(); j++) {
		    		  if (Word.get(i).getWord_target().length() >= text.length()) {
						if(text.charAt(j) == Word.get(i).getWord_target().charAt(j)) dem++;
						if (dem == text.length()) {
							strings.add(Word.get(i).getWord_target());
							jList.setModel(new AbstractListModel<String>() {
								private static final long serialVersionUID = 1L;
								public int getSize() { return strings.size(); }
					            public String getElementAt(int i) { return strings.get(i); }
					        });
		    	  		}	 
		    		  }
		    	  }
		    	  dem = 0;
		      }
		     jButton2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	try {
			        	Connection connection = MySQLConnUtils.getMySQLConnection();
			        	 
			            Statement statement = connection.createStatement();
			       
			            String sql = "Select word, detail from tbl_edict";
			       
			            ResultSet rs = statement.executeQuery(sql);
			       
			            while (rs.next()) {
			                word = rs.getString("word");
			                String detail = rs.getString("detail");
			                
			                if (word.equals(text)) {
				                jTextArea.setText(detail);
			                }
			            }
			            connection.close();
			        }
			        catch (ClassNotFoundException ev) {
			        	System.out.print("Class not found.");
			        }
			        catch (SQLException ev) {
			        	System.out.print("Can't access data.");
			        }
	            }
	         });
    
           }
        });
        jTextField2.setFont(new Font("Times New Roman", 0, 15)); 
        
 		jList.addListSelectionListener(new ListSelectionListener() {
	    	  public void valueChanged(ListSelectionEvent e) {
	 				if (e.getValueIsAdjusting()) {
	 					@SuppressWarnings("rawtypes")
						JList choice = (JList) e.getSource();
	 					String sel = choice.getSelectedValue().toString();
	 					jTextField1.setText(sel);
	 					text = jTextField1.getText();
	 					 for (int i=0; i<Word.size(); i++) {
	 				    	  if (sel.equals(Word.get(i).getWord_target())) {
	 				    		  jTextField2.setText(Word.get(i).getWord_explain());	    		 
	 				    	  }    
	 					 }
	 				}
	 			}
	 		});
        jScrollPane1.setViewportView(jList);
        
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
            	try {
            		String sound = "C:\\Users\\hstar\\eclipse-workspace\\Dictionary\\src\\Dictionary\\sounds\\" + text +".mp3" ;
					Player player = new Player(new FileInputStream(sound));
					while(!player.isComplete()) {
						player.play();								
					}		
				} catch (FileNotFoundException | JavaLayerException e2) {					
					JOptionPane.showMessageDialog(null, "Can't pronounce this word");
				}          	
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jTextField1)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(jTextField1)
            .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1)
            .addContainerGap())
        );

        jTextArea.setFont(new Font("Tahoma", 0, 12));
        jTextArea.setEditable(false);
        jScrollPane2.setViewportView(jTextArea);

        jButton5.setIcon(new ImageIcon(getClass().getResource("/Dictionary/images/mic.png")));    

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/Dictionary/images/tran.png")));

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(15, 15, 15)
            .addComponent(jLabel3)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextField2, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton5)
            .addGap(5, 5, 5))
            .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
            .addComponent(jTextField2)
            .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
            .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        frame.pack();
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DictionaryApplication().setVisible(true);
            }
        });
        
    }

	protected void setVisible(boolean b) {
		frame.setVisible(true);
		
	}
}
