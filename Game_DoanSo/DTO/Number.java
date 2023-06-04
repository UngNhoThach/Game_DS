/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.awt.Color;
import java.awt.Rectangle;


public class Number {

    private Rectangle hitbox;
    private int id, x, y, width, height;
    private int row, col;
    private boolean hit, fill;
    private Color color;

    public Number() {
    }

    public Number(int id, int row, int col, int tileX, int tileY, int width, int height, Color color, int tileSize) {
        this.id = id;
        this.x = col * tileSize + tileX;
        this.y = row * tileSize + tileY;
        this.width = width;
        this.height = height;
        this.row = row;
        this.col = col;
        this.hit = false;
        this.fill = false;
        this.color = color;
        this.hitbox = new Rectangle(x, y, width, height);

    }

    public boolean Collision(Rectangle r, Rectangle s) {
        return r.intersects(s);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
