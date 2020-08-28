package me.giverplay.games.games.pong;

import me.giverplay.games.GameBase;
import me.giverplay.games.Main;

import java.awt.image.BufferedImage;

public class Pong extends GameBase
{
  public Pong(String name, BufferedImage icon)
  {
    super(name, icon, 240, 160, 3);
  }

  @Override
  public void tick()
  {

  }

  @Override
  public void render()
  {

  }

  @Override
  public void start()
  {
    getFrame().setVisible(true);
  }

  @Override
  public void destroy()
  {
    Main.getMenu().showFrame();
    this.getFrame().dispose();
  }
}
