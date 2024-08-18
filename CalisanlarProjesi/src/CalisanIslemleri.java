
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;


public class CalisanIslemleri {
    private Connection con =null;
    private Statement statement =null;
    private PreparedStatement preparedStatement = null;
    
    
    public boolean girisYap(String kullanici_adi, String parola){
        String sorgu = "Select * From adminler where username = ? and password = ?";

        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public CalisanIslemleri(){
        
        String url = "jdbc:mysql://"+ DataBase.host + ":" + DataBase.port + "/" +DataBase.db_ismi+"?useUnicode=true&characterEncoding=utf8";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadi");
        }
        
        try {
            con = DriverManager.getConnection(url, DataBase.kullanici_adi,DataBase.parola);
            System.out.println("Bağlantı başarılı");
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı başarısız");
        }
        
    }
   
}
