package me.giverplay.pong;

import java.awt.*;

public abstract class Entity
{
  private int width, height, x, y;

  private Color color;

  protected Game game;
  protected int speed = 5;

  public Entity(int x, int y, int width, int height, Color color, Game game)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.game = game;
  }

  public void render(Graphics g)
  {
    g.setColor(this.color);
    g.fillRect(x, y, width, height);
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return y;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public void moveX(int toMove)
  {
    this.x += toMove;
  }

  public Color getColor()
  {
    return color;
  }

  public void moveY(int toMove)
  {
    this.y += toMove;
  }

  public abstract void tick();
}
