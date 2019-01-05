package View;

import Class_name.Vcd;
import Inernet.Select_mysql_vcd_name;
import Inernet.Select_mysql_vcd_type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;

/**
 * Created by 上官刀刀 on 2017/9/29.主界面类
 */
public class MainFrame extends JFrame {
    public static ResultSet rs;
    //VCD的主面板
    private MusicPanel musicPanel=new MusicPanel();
    //初始化一个菜单栏
    private JMenuBar menuBar_superVcd = new JMenuBar();
    //定义标题栏中的三个按钮（文件，选项，帮助）
    private JMenu  file_button=new JMenu ("文件");
    private JMenu  select_button=new JMenu ("选项");
    private JMenu  help_button=new JMenu ("帮助");
    //音乐，选择音乐目录标签的定义
    private JLabel jLabel_music=new JLabel("音乐");
    private JLabel jLabel_select_music=new JLabel("选择音乐目录");
    //选择音乐种类控件
    private JComboBox comboBox_music=new JComboBox();
    //构建一个jpanel用来设置音乐类型的排版
    private JPanel jPanel_music = new JPanel();
    private JPanel jPanel_buttons=new JPanel();
    private JButton jButton_removeall=new JButton("清空");
    private JButton jButton_exit=new JButton("退出");
    private Vector<JButton> jButtonList=new Vector<JButton>();
    //主界面的构造函数
    public MainFrame()
    {
        jPanel_buttons.setPreferredSize(new Dimension(1300,50));
        jPanel_buttons.add(jButton_removeall);
        jPanel_buttons.add(jButton_exit);
        //加入文件，选项帮助按钮到菜单栏
        menuBar_superVcd.add(file_button);
        menuBar_superVcd.add(select_button);
        menuBar_superVcd.add(help_button);
        //将菜单加入窗口中
        setJMenuBar(menuBar_superVcd);
        //选择类型的定义
        comboBox_music.addItem("-----");
        comboBox_music.addItem("大陆");
        comboBox_music.addItem("新加坡");
        comboBox_music.addItem("欧美");
        comboBox_music.addItem("港台");
        //jPanel_music的布局设置
        jPanel_music.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        jPanel_music.add(jLabel_select_music);
        jPanel_music.add(comboBox_music);
        add(jPanel_music);
        add(musicPanel);
        add(jPanel_buttons);
        //设置成流线型的布局
        setLayout(new FlowLayout(FlowLayout.LEFT));
        //设置点击关闭按钮可用
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置标题
        setTitle("SuperVCD_store");
        //设置长款
        setSize(1400,800);
        //设置初始位置
        setLocation(200,100);
        //设置窗口可见
        setVisible(true);
        lister();
    }
    private void   lister(){
        jButton_removeall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove_all_button();
            }
        });
        comboBox_music.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(ItemEvent.SELECTED ==e.getStateChange() ) {
                    remove_all_button();
                    Select_mysql_vcd_type select_mysql_vcd_type = new Select_mysql_vcd_type();
                    List<Vcd> list = null;
                    list = select_mysql_vcd_type.select_result(comboBox_music.getSelectedItem().toString());
                    for (int i = 0; i < list.size(); i++) {
                        JButton jButton = new JButton(list.get(i).getName());
                        jButton.setPreferredSize(new Dimension(1400, 30));
                        jButton.setContentAreaFilled(false);
                        jButton.setBorderPainted(false);
                        jButton.setHorizontalAlignment(JButton.LEFT);
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Select_mysql_vcd_name select_mysql_vcd_name=new Select_mysql_vcd_name();
                                List<Vcd> list = select_mysql_vcd_name.select_result(jButton.getText().toString());
                                MusicDetailsDialog musicDetailsDialog=new MusicDetailsDialog();
                                musicDetailsDialog.setview(list.get(0).getName(),list.get(0).getStar(),list.get(0).getType(),list.get(0).getTime(),list.get(0).getUrl(),list.get(0).getPrice(),list.get(0).getMusic_url(),list.get(0).getAlbum());
                                musicDetailsDialog.setVisible(true);
                            }
                        });
                        jButtonList.add(jButton);
                        musicPanel.add(jButton);
                        musicPanel.updateUI();
                        musicPanel.repaint();
                    }
                }
            }
        });
    }
    private  void remove_all_button()
    {
        for(int i=0;i<jButtonList.size();i++)
        {
            musicPanel.remove(jButtonList.get(i));
        }
        for(int i=0;i<jButtonList.size();i++)
        jButtonList.remove(i);
        musicPanel.updateUI();
        musicPanel.repaint();
        musicPanel.validate();
    }


}
