package me.giverplay.games.games.pong;

import me.giverplay.games.game.GameBase;
import me.giverplay.games.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pong extends GameBase
{
  private BufferedImage screen;
  private Graphics graphics;
  private Graphics screenGraphics;

  public Pong(String name, BufferedImage icon)
  {
    super(name, icon, 240, 160, 3);

    setup();
  }

  private void setup()
  {
    screen = new BufferedImage(getScaledWidth(), getScaledHeight(), BufferedImage.TYPE_INT_RGB);
    screenGraphics = screen.getGraphics();

    createBufferStrategy(3);
    graphics = getBufferStrategy().getDrawGraphics();
  }

  @Override
  public void tick()
  {
    if(!getFrame().isVisible())
    {
      destroy();
      return;
    }
  }

  @Override
  public void render()
  {
    screenGraphics.setColor(Color.BLACK);
    screenGraphics.fillRect(0, 0, getScaledWidth(), getScaledHeight());

    graphics.drawImage(screen, 0, 0, getScaledWidth(), getScaledHeight(), null);
    getBufferStrategy().show();
  }

  @Override
  public void start()
  {
    getFrame().setVisible(true);
  }

  @Override
  public void destroy()
  {
    Main.getMenu().stopGame();
  }
}
