package View;

import Class_name.Vcd;
import Inernet.Select_mysql_vcd_album;
import Music.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 上官刀刀 on 2017/10/8.用来显示VCD的详细信息的对话框
 */
public class MusicDetailsDialog extends JDialog{
    private JPanel jpanel_message=new JPanel();
    private JLabel jLabel_title=new JLabel("list of Tracks");
    private JPanel jPanel_list=new JPanel();
    private JPanel jPanel_ok=new JPanel();
    private JButton ok=new JButton("ok");
    private JButton jButton_try=new JButton("试听");
    private JButton jButton_stop=new JButton("停止");
    private JLabel name=new JLabel("name");
    private JLabel star=new JLabel("star");
    private JLabel type=new JLabel("type");
    private JLabel time=new JLabel("time");
    private JLabel price=new JLabel("price");
    private JLabel image_label=new JLabel();;
    private JPanel jPanel_img=new JPanel();
    private  Music music;
    private String music_url;
    //构造函数
    MusicDetailsDialog()
    {

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setTitle("光盘详细信息");
        setSize(new Dimension(400,600));
        setBackground(Color.gray);
        setLocation(500,200);
        ImageIcon image=new ImageIcon("F:\\图片\\33.jpg");
        image.setImage(image.getImage().getScaledInstance(200, 250,Image.SCALE_DEFAULT ));
        image_label.setIcon(image);
        image_label.setSize(100,150);
        jpanel_message.setBackground(Color.lightGray);
        jpanel_message.setPreferredSize(new Dimension(150, 250));
        name.setPreferredSize(new Dimension(150,30));
        name.setFont(new Font("华文楷体",0,18));
        jpanel_message.add(name);
        star.setPreferredSize(new Dimension(150,25));
        star.setFont(new Font("华文楷体",0,18));
        jpanel_message.add(star);
        type.setPreferredSize(new Dimension(150,25));
        type.setFont(new Font("华文楷体",0,18));
        jpanel_message.add(type);
        time.setPreferredSize(new Dimension(150,25));
        time.setFont(new Font("华文楷体",0,18));
        jpanel_message.add(time);
        price.setPreferredSize(new Dimension(150,60));
        price.setFont(new Font("华文楷体",0,18));
        jpanel_message.add(price);
        jLabel_title.setPreferredSize(new Dimension(350, 50));
        jPanel_list.setBackground(Color.white);
        jPanel_list.setPreferredSize(new Dimension(370,180));
        jPanel_ok.setPreferredSize(new Dimension(370,50));
        jPanel_ok.add(ok);
        jPanel_ok.add(jButton_try);
        jPanel_ok.add(jButton_stop);
        add(jpanel_message);
        jPanel_img.add(image_label);
        add(jPanel_img);
        add(jLabel_title);
        add(jPanel_list);
        add(jPanel_ok);
        setlistener();
    }
    public void setview(String name,String star,String type,String time,String url,String price,String music_url,String album)
    {
        this.music_url=music_url;
        this.name.setText("歌名:"+name);
        this.star.setText("歌手:"+star);
        this.type.setText("类别:"+type);
        this.time.setText("长度:"+time+"分");
        this.price.setText("价格:¥"+price+"");
        this.name.updateUI();
        ImageIcon image=new ImageIcon(url);
        image.setImage(image.getImage().getScaledInstance(200, 250,Image.SCALE_DEFAULT ));
        image_label.setIcon(image);
        image_label.updateUI();
        jPanel_img.updateUI();
        jPanel_img.repaint();
        jPanel_img.validate();
        jpanel_message.updateUI();
        jpanel_message.repaint();
        jpanel_message.validate();
        Select_mysql_vcd_album select_mysql_vcd_album = new Select_mysql_vcd_album();
        java.util.List<Vcd> list = null;
        list = select_mysql_vcd_album.select_result(album);
        for (int i = 0; i < list.size(); i++) {
            JButton jButton = new JButton(list.get(i).getName()+"   "+list.get(i).getTime()+" minutes");
            jButton.setPreferredSize(new Dimension(370, 20));
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            jButton.setHorizontalAlignment(JButton.LEFT);
            jPanel_list.add(jButton);
            jPanel_list.updateUI();
            jPanel_list.repaint();
        }

    }
    public void setlistener()
    {
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jButton_try.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music=new Music(music_url);
                music.start();
            }
        });
        jButton_stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.stop();
            }
        });

    }
}
