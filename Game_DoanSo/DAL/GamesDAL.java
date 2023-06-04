/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import DTO.Games;
import multiplechoiceserver.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GamesDAL {
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    public Vector<Games> getGames() {
		Vector<Games> listGames = new Vector<Games>();
		try {
			String sql = "SELECT * FROM game";
			conn = ConnectionDB.Connect();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Games Games = new Games();
				Games.setCode(rs.getString("Code"));
                                Games.setTimeStart(rs.getDate("Time_start"));
                                Games.setWinner(rs.getInt("Winner"));
				listGames.add(Games);
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
		return listGames;
	}
}
