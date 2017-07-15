package cn.omsfuk.blog;

import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * Created by omsfuk on 2017/7/14.
 */
public class EncodeTest {



    public static void main(String[] args) throws SQLException {

            byte[] bytes0 = "# 基本".getBytes();
            for (int i = 0; i < bytes0.length; i++) {
                System.out.println(bytes0[i] + " " + Integer.toHexString(bytes0[i]));
            }


//        SqlSession session = MyBatisUtil.getSession();
//        System.out.println(session.getMapper(test.class).get());
//        session.select("select * from note where id = 12", new ResultHandler() {
//            @Override
//            public void handleResult(ResultContext resultContext) {
//
//            }
//        });

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noteit?useUnicode=true&characterEncoding=UTF-8&useSSL=true&allowMultiQueries=true&user=root&password=root");
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM note where id = 12");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("content"));
            byte[] bytes = new byte[0];
            try {
                bytes = rs.getString("content").getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < bytes.length; i++) {
                System.out.print(Integer.toHexString(bytes[i]));
            }
        }
    }
}
