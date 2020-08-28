package me.giverplay.games.game;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public abstract class GameBase extends Canvas
{
  private int w, h, scale;

  private JFrame frame;
  private BufferedImage icon;
  private String name;

  public GameBase(String name, BufferedImage icon, int w, int h, int scale)
  {
    this.name = name;
    this.icon = icon;
    this.w = w;
    this.h = h;
    this.scale = scale;

    setupFrame();
  }

  private void setupFrame()
  {
    frame = new JFrame(name);
    frame.add(this);
    frame.setIconImage(icon);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(w * scale, h * scale));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  @Override
  public String getName()
  {
    return name;
  }

  public BufferedImage getIcon()
  {
    return icon;
  }

  public JFrame getFrame()
  {
    return frame;
  }

  public int getGameWidth()
  {
    return this.w;
  }

  public int getGameHeight()
  {
    return this.h;
  }

  public int getScale()
  {
    return scale;
  }

  public int getScaledWidth()
  {
    return w * scale;
  }

  public int getScaledHeight()
  {
    return h * scale;
  }

  public abstract void tick();

  public abstract void render();

  public abstract void start();

  public abstract void destroy();
}
