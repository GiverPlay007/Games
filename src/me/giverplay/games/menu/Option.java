package me.giverplay.games.menu;

import me.giverplay.games.GameBase;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Option
{
  public static final int WIDTH = 260;
  public static final int HEIGHT = 50;

  private Class<? extends GameBase> game;

  private GameMenu menu;
  private BufferedImage icon;
  private String name;

  private int x, y;

  public Option(int x, int y, BufferedImage icon, String name, GameMenu menu, Class<? extends GameBase> game)
  {
    this.x = x;
    this.y = y;
    this.name = name;
    this.icon = icon;
    this.menu = menu;
    this.game = game;
  }

  public Class<? extends GameBase> getGameClass()
  {
    return this.game;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public BufferedImage getIcon()
  {
    return icon;
  }

  public String getName()
  {
    return name;
  }

  public void launch()
  {

  }

  private int getLoc(Graphics g)
  {
    return ((x + 60) + (WIDTH - g.getFontMetrics().stringWidth(name))) / 2;
  }

  public void draw(Graphics g)
  {
    g.setColor(new Color(0, 0, 0, 100));
    g.fillRect(x, y, WIDTH, HEIGHT);

    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 24));
    g.drawString(name, getLoc(g), y + 32);

    if(menu.getSelected() == this)
    {
      g.setColor(new Color(0, 0, 0, 20));
      g.fillRect(x, y, WIDTH, HEIGHT);
    }

    g.setColor(Color.BLACK);
    g.drawRect(x, y, WIDTH, HEIGHT);

    g.drawImage(icon, x + 8, y + 4, 42, 42, null);
  }
}
