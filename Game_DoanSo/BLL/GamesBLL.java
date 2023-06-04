/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.GamesDAL;
import DTO.Games;
import java.util.Vector;


public class GamesBLL {
     private GamesDAL gamesDAL = new GamesDAL();
    
    public Vector<Games> getGames() {
        Vector<Games> listGames = gamesDAL.getGames();
        return listGames;
    }
}
