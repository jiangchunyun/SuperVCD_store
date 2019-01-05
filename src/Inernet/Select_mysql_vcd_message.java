package Inernet;

/**
 * Created by 上官刀刀 on 2017/10/10.
 */
import Class_name.Vcd;
import View.MainFrame;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class Select_mysql_vcd_message {
    private String DB_URL = "jdbc:mysql://localhost:3306/super_vcd";
    // 数据库的用户名与密码，需要根据自己的设置
    private String USER = "root";
    private String PASS = "";
    private ResultSet rs;
    public List<Vcd> select_result() {
        Connection conn = null;
        Statement stmt = null;
        List<Vcd> vcdList=new LinkedList<Vcd>();
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM VCD";
            rs = stmt.executeQuery(sql);
            MainFrame.rs=rs;
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                Vcd vcd=new Vcd();
                vcd.setName( rs.getString("name"));
                vcd.setId( rs.getString("id"));
                vcd.setStar( rs.getString("star"));
                vcd.setType( rs.getString("type"));
                vcd.setTime( rs.getString("time"));
                vcd.setPrice( rs.getString("price"));
                vcd.setUrl(rs.getString("url"));
                vcdList.add(vcd);
            }
            // 完成后关闭
           rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return vcdList;
    }

}