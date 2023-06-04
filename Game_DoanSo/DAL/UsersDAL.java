/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import DTO.Users;
import multiplechoiceserver.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class UsersDAL {
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public Vector<Users> getUsers() {
		Vector<Users> listUsers = new Vector<Users>();
		try {
			String sql = "SELECT * FROM user";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Users Users = new Users();
				Users.setEmail(rs.getString("Email"));
                                Users.setPassword(rs.getString("Password"));
                                Users.setName(rs.getString("Name"));
                                Users.setGender(rs.getString("Gender"));
                                Users.setDoB(rs.getString("Birth"));
                                //Users.setStatus(0);
                                Users.setTotalMatch(rs.getInt("Total_match"));
				listUsers.add(Users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return listUsers;
	}
    public Vector<Users> getRanking(){
        Vector<Users> listUsers = new Vector<Users>();
		try {
			String sql = "SELECT COUNT(Winner) AS WINS, user.* FROM user,game WHERE game.Winner = user.Email\n" +
                                        "GROUP BY Email\n" +
                                        "ORDER BY WINS DESC";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Users Users = new Users();
				Users.setEmail(rs.getString("Email"));
                                Users.setPassword(rs.getString("Password"));
                                Users.setName(rs.getString("Name"));
                                //Users.setGender(rs.getString("Gender"));
                                //Users.setDoB(rs.getDate("DoB"));
                                //Users.setStatus(0);
                                Users.setTotalMatch(rs.getInt("Total_match"));
				listUsers.add(Users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return listUsers;
    } 
    public int getPointByMail(String email){
        int point = 0;
        try {
			String sql = "SELECT COUNT(Winner) AS WINS FROM game WHERE Winner = ?";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
                        pstm.setString(1, email);
			rs = pstm.executeQuery();
			while (rs.next()) {
                                point = rs.getInt("WINS");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return point;
    }
    public Vector<Users> getPercentRanking(){
        Vector<Users> listUsers = new Vector<Users>();
		try {
			String sql = "SELECT COUNT(Winner)/Total_match*100 AS Percent, user.Name, user.Email FROM game,user WHERE game.Winner=user.Email\n" +
                                        "GROUP BY Email\n" +
                                        "ORDER BY Percent DESC";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Users Users = new Users();
                                Users.setEmail(rs.getString("Email"));
                                Users.setName(rs.getString("Name"));
				listUsers.add(Users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return listUsers;
    }
    public int getWinPercentByMail(String email){
        int percent = 0;
        try {
			String sql = "SELECT COUNT(Winner)/Total_match*100 AS Percent FROM game,user WHERE game.Winner = user.Email AND user.Email = ?";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
                        pstm.setString(1, email);
			rs = pstm.executeQuery();
			while (rs.next()) {
                                percent = rs.getInt("Percent");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return percent;
    }
    public Vector<Users> searchRank(String key){
        Vector<Users> listUsers = new Vector<Users>();
		try {
			String sql = "SELECT tmp.* from (SELECT row_number() over(ORDER BY WINS DESC) AS Rank,COUNT(Winner) AS WINS, user.* FROM user,game WHERE game.Winner = user.Email\n" +
                                        "GROUP BY user.Email\n" +
                                        "ORDER BY WINS DESC) AS tmp WHERE concat(tmp.Name) LIKE '%" + key + "%'";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Users Users = new Users();
				Users.setEmail(rs.getString("Email"));
                                Users.setPassword(rs.getString("Password"));
                                Users.setName(rs.getString("Name"));
                                //Users.setGender(rs.getString("Gender"));
                                //Users.setDoB(rs.getDate("DoB"));
                                //Users.setStatus(0);
                                Users.setTotalMatch(rs.getInt("Total_match"));
				listUsers.add(Users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return listUsers;
    }
    public int getRankByMail(String email){
        int rank = 0;
        try {
			String sql = "SELECT tmp.Rank from (SELECT row_number() over(ORDER BY WINS DESC) AS Rank,COUNT(Winner) AS WINS, user.* FROM user,game WHERE game.Winner = user.Email\n" +
                                        "GROUP BY user.Email\n" +
                                        "ORDER BY WINS DESC) AS tmp WHERE tmp.Email = ?";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
                        pstm.setString(1, email);
			rs = pstm.executeQuery();
			while (rs.next()) {
                                rank = rs.getInt("Rank");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return rank;
    }
}
