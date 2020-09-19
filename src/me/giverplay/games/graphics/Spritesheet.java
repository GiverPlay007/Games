package me.giverplay.games.graphics;

import me.giverplay.games.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class Spritesheet
{
  private static BufferedImage image;

  private Spritesheet(){}

  public static BufferedImage getSprite(int x, int y, int w, int h)
  {
    try
    {
      return image.getSubimage(x, y, w, h);
    }
    catch(Throwable e)
    {
      System.out.println("Erro ao obter sprite");
      System.out.println("Mensagem: " + e.getMessage());
      System.out.println("Throwable: " + e.getCause());
      return null;
    }
  }

  static
  {
    try
    {
      image = ImageIO.read(Main.class.getResource("/assets/images/Spritesheet.png"));
    }
    catch(IOException e)
    {
      System.out.println("Erro ao inicializar sprite");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
