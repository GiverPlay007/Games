package me.giverplay.pong;

import java.awt.*;

public class Bolinha extends Entity
{
  private double dx;
  private double dy;

  public Bolinha(int x, int y, Game game)
  {
    super(x, y, 10, 10, Color.YELLOW, game);
  }

  @Override
  public void tick()
  {

  }

  @Override
  public void render(Graphics g)
  {
    g.setColor(this.getColor());
    g.fillOval(getX(), getY(), getWidth(), getHeight());
  }
}
